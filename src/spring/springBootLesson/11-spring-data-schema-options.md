# Web App 更新期間的維護模式：從唯讀到全鎖的解決方案
在營運 Web App 的時候，雖然我們有 Docker / K8s 可以滾動更新，但難保用戶在更新的過程中，有一半訪問去到了舊版，另一半去了新版。如果可以，Web App 本身自帶維護模式，可以自我判斷什麼時候應該忽略新的訪問，當然最好。但要做到這一點，前期需要很多規劃。狠心一點，可以直接關掉對外的服務，讓用戶無法訪問。

但在另一些情況下，例如升級/搬遷的情況，下線時間比較長，完全關掉服務並不是一個很好的方向，我們至少還可以提供唯讀的選擇。而且這個可以從資料庫出發，讓 Web App 少處理一點邏輯。

如果 Web App 背後的資料庫是 MSSQL 或 MySQL，唯讀這件事應該是簡單的，只要你把 service account 的權限改變就好。但如果你用Oracle，就要想想辦法。

筆者想到的方法，暫時有兩個。第一個就需要大家寫寫 Script ，一口氣把所有 Table 給鎖起來。例如:

```sql
-- to read only
BEGIN
  FOR t IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'ALTER TABLE ' || t.table_name || ' READ ONLY';
    DBMS_OUTPUT.PUT_LINE('Table ' || t.table_name || ' set to READ ONLY.');
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;

-- to read write
BEGIN
  FOR t IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'ALTER TABLE ' || t.table_name || ' READ WRITE';
    DBMS_OUTPUT.PUT_LINE('Table ' || t.table_name || ' set to READ WRITE.');
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
```

第二個，就是生成一個新的唯讀 User schema，給他所有Select的權限。然後更新 Web App 使用那個唯讀 User schema存取資料。

```sql
-- grant select
BEGIN
  FOR t IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'grant select on ' || t.table_name || ' to READ_ONLY_USER';
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;

-- revoke select
BEGIN
  FOR t IN (SELECT table_name FROM user_tables) LOOP
    EXECUTE IMMEDIATE 'revoke select on ' || t.table_name || ' to READ_ONLY_USER';
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
```

兩個方法有什麼差異呢？ 前者就全部鎖起來，沒有任何一個資料庫用戶可以改寫資料。如果你的業務沒有差異性，全部一起封起來就完事。但如果你只想 Web App 轉成唯讀，但其他背景程式還可以執行更新。那你就只能用後者了。但後著也不是百分百的完全無痛，至少你 Web App 要支援登入與操作的 Schema分離。

例如用Spring boot JPA的話，可以在 application.properties 可以讓登入及操作的Schema不一樣。
```
spring.datasource.username=READ_ONLY_USER
spring.jpa.properties.hibernate.default_schema=ORIGINAL_SCHEMA
```

又或者在 java 層面指定。
```java
@Table(schema = "ORIGINAL_SCHEMA")
```

這看上去，是很有彈性的。但其實也是有些局限。如果你本來的JPA有寫特制的 JPQL 或 Raw Query，又或者你在Java層面加了 `@Subselect`，由於這些都是程式原作者所 hard code 的，JPA沒法幫你改寫。改來改去，可能還是前述寫Script的方法，一口氣把所有 Table 給鎖起來實際一些。
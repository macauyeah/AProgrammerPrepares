# Spring Boot 09 - 多情境設置 maven profile 與 application.properties 

前述我們介紹了很多profile是如何運作的[08-spring-boot-properties-profile](./08-spring-boot-properties-profile.md)。這篇，就因應實際的運行環境，討論不同的存儲及配置方向。

我們先重溫一下，maven profile與spring profile的差異。
- Spring boot 是程式框架。Spring boot 是經過 `spring.profiles.active` 去選擇什麼 (spring boot) Profile 生效
- `spring.profiles.active` 它可以在runtime(運行時)動態更改
- maven 是經過 xml 去選擇編譯時的 (maven) profile
- maven 編譯時為 `spring.profiles.active` 填入一個固定值
- Spring boot 必需經過編譯才能使用。亦即編譯時，可以填入一個值，不填即使是用預設值。

## maven profile 的不足
雖然 maven 會在編譯期間填入值，但編譯出來的結果(jar或war檔），其實一切後期都被可以改動。那麼我們該使用 maven profile 來做什麼呢？

一般來講，筆者會期待編譯的結果，預設是可以運行的。所以早期筆者預編譯時，都會包含對應環境的所有設定，這樣用戶就不需要考慮自己如何填入設定值。這樣做特別有效的是，你的設定表是一個相對大的檔案（例如要入100行的設定），又或是你對目前的發佈平台不熟悉，難以做覆蓋值的改動。例如傳統的tomcat，筆者既不是管理員，也不熟悉它的插入機制。

這時 maven 在編譯時，就直接或簡接指定好 spring profile。在運行時，就不用煩惱。我們的 uat prod profile ，不需要放入 Source code Repository 中，只要 CI Server 看得到，可以在打包過程將入就好。然後就靠 CD Server 送到特定的伺服器運行。

但在 container 的世代來看，只使用 maven profile 是不夠的。什至是在古早的賣實體軟件的時代，也是不夠的。你的執行檔，應該有條件進行動態配置的。因為你也無法預知不同的環境的實際參數，你也不想每次更新設定都要全部編譯流程重跑一次。如果你允許動態配置，那麼在前期 CI Server 編譯中亦可以簡化一些流程，它只包含程式碼的編譯就好。在動態設定上，要麼就交給 CD Server，要麼就人手更新。

Spring boot 提供了自己的動態修改機制，比起傳統的純 java 程式要自己讀寫不同的檔案要來得簡單。下面的章節，我們就來介紹一下spring boot 是怎樣提供動態配置的方式。

## spring boot 動態配置
前述 [08-spring-boot-properties-profile](./08-spring-boot-properties-profile.md) 教學中，已有很多動態改變profile的例子，當時就假設所有的profile都放入了 jar 中。而 spring boot 其實更強大，它還可以讀到 jar 以外的 properties 檔。

假設 spring boot 寫程式，已經有一個打包好的 `web.jar` 檔，執行方式將會是 `java -jar web.jar`。  不論 jar 裏面的 `application.properties` 定義了什麼 `spring.profiles.active`，我們都可以在下jar指令時覆寫它。只要當前指令的工作目錄下，有那個新的 application-NEWPROFILE.properties 就好，例如

```
$ tree /tmp/demo
/tmp/demo
|-- application-container.properties
`-- web.jar

$ cd /tmp/demo
$ java -jar web.jar --spring.profiles.active=container
```

只要 jar 檔裏面，沒有 `application-container.properties` 的存在就好，不然筆者也不確定是哪一個properties在作用。

有了這些載入方式，即使經 container 動態 bind mount, configs / secrets 也可以達到動態配置的效果。
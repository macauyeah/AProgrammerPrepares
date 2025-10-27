# Spring boot web api 異常處理
我們在編寫程式時，經常會遇到一些極端的情況，不會經過 function 的方式回傳結果。例如一個 function 原本是提供讀檔功能，但用戶傳入的並不是一個有效的檔案路徑，又或是誰路徑權限不足，無法讀取。這些不正常的結果，並不是原本 function 所協定的回傳值。那麼，我們會拋出異常 Exception ，中斷所有被呼叫中的 function ，讓上層用戶去考慮怎樣處理這個問題。

在 Web API 中，這些 Exception 就更常見。要求用戶傳入的參數，用戶就是有時候少了幾個。覆寫資料的時候，原本的資料已被刪除。但我們現在是經過 Web Api，不能像過去一直向上拋出異常就能通知用戶。我們需要的，是把異常轉成對應的 Http Status Code，讓用戶端可以快速識別異常的類型。

## java 異常對應 Http Response Code
其實在 spring boot web 中，要做轉譯，是很簡單的。在定義 java `Exception`的時候，若有`@ResponseStatus`，spring boot web 就會自動回應對應的 http error code。 

```java
@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException() {
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
```

以後，任何一個地方拋出 `CustomAuthenticationException` （假設上層沒有人攔截）都會把該 Controller 的結果改為 http 403。Spring boot 也很聰明的，把異常中的 `message` 隱藏 ，免得有網安的問題。

若我們定義 Exception 時，沒有`@ResponseStatus`，Controller 就會變成 http 500，例如我在 controller 中拋個常見的 `IOException`，這次的結果就會變成 http 500。
```java
    @GetMapping("/api/ioError")
    public String forceIOException() throws IOException {
        throw new IOException("force io error");
    }
```

如果某些時候，我們想使用 java `Exception` 中的 message 欄位作為報錯信息，讓 http 客戶端，可以通過固定的 message 檔位找到問題訊息，我們可以在`application.properties`中，加入`server.error.include-message=always`。(有些特殊情況，在開發模式時 `mvn spring-boot:run` ，已經可以見到有 Exception message，但在投産後`java -jar`又看不到。主要因為開發模式中， pom 有 optional spring-boot-devtools，會自動加入了`server.error.include-message=always`，但 mvn package 後就沒有，因為 runtime 沒有 spring-boot-devtools 的覆蓋。)

## 額外處理
異常處理除了想控制 http status code 外，有時還需要做一些額外處理，例如發出通知郵件等。若想做額外處理，需要另做一個 `@RestControllerAdvice` 的類，在接到指定的 exception 時，可以轉換不同的 http code ，而且還可以執行額外 java code ，改變 http ResponseBody 。

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleRuntimeException(Exception ex) {
        return Map.of("ret", false, "anyfields", ex.getMessage());
    }
}
```

但要注意，一旦使用`@RestControllerAdvice` 後，就要考慮有沒有改變了某些預設的行為。例如上述的`@ExceptionHandler(value = RuntimeException.class)`，代表所有`RuntimException.class`的子類，都會歸由該 function 所處理。當然，你也可以多加幾個 function 來處理不同的子類。

# Reference
- [spring-boot-web-api-validate](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-validate)
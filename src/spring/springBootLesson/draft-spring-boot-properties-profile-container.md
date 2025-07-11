# Spring Boot 10 - 多情境設置 maven profile 與 application.properties 

前述我們介紹了很多profile是如何運作的[08-spring-boot-properties-profile](./08-spring-boot-properties-profile.md)。這篇，就因應實際的運行環境，討論不同的存儲及配置方向。

我們先重溫一下，maven profile與spring profile的差異。
- Spring boot 是程式框架。Spring boot 是經過 `spring.profiles.active` 去選擇什麼 (spring boot) Profile 生效
- `spring.profiles.active` 它可以在runtime(運行時)動態更改
- maven 是經過 xml 去選擇編譯時的 (maven) profile
- maven 編譯時為 `spring.profiles.active` 填入一個固定值
- Spring boot 必需經過編譯才能使用。亦即編譯時，可以填入一個值，不填即使是用預設值。

## maven profile
雖然 maven 會在編譯填入值，但編譯出來的結果(jar或war檔），其實一切後期都被可以改動。那麼我們該使用 maven profile 來做什麼呢？

一般來講，筆者會期待編譯的結果，預設是可以運行的。所以早期筆者預編譯時，都會包含對應環境的所有設定，這樣用戶就不需要考慮自己如何填入設定值。這樣做特別有效的是，你的設定表是一個相對大的檔案（例如要入100行的設定），又或是你對目前的發佈平台不熟悉，難以做覆蓋值的改動。例如傳統的tomcat，筆者概不是管理員，也不熟悉它的插入機制。

這時 maven 在編譯時，就直接或簡接指定好 spring profile。在運行時，就不用煩惱。我們的 uat prod profile ，不需要放入 Source code Repository 中，只要 CI/CD Server 看到就好。

但在 container 的世代來看，這是不夠的。什至是在古早的賣軟件的時代，也是不夠的。你的執行檔，應該可以動態配置。
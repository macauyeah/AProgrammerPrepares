# Spring Boot Web 調試工具
之前兩節，都一直在講怎樣寫code，也介紹了Test Case的好。若為初次接觸，Spring有很多設定需要摸索，若開始時就設定錯誤，對不少人來講都會有很大打擊。在這裏，筆者就介紹一些vscode和spring的工具，可以讓IDE多幫忙一下，減少走歪路的機會。

## vscode插件
以下兩個插件，都在於提示用戶設定。

- Spring Boot Dashboard (vscjava.vscode-spring-boot-dashboard)
   - 可以那它來運作spring boot app，省去找尋main 位置的麻煩
   - 綜覽整個程式中的所有Bean (Bean是一個很重要的元素，日後會再提及)
   - 若程式為Spring boot web，可以顯示所 http endpoint。
- Spring Boot Tools (vmware.vscode-spring-boot)
   - 檢查設定檔的設定值有沒有寫錯 （application*.properties， application*.yml）
   - 綜覽檔案中的有以@為首的與spring相關的元素（檔案很大時就會有用）
   - 可以在IDE運行spring時，查看@元素的bean資訊 (not works ?, 加了actuator也是沒有看見)
- Spring Initializr（vscjava.vscode-spring-initializr）
   - 經網絡初始化spring 專案的依賴引用設定
- Maven for Java (vscjava.vscode-maven)
   - 若大家在使用Spring Initializr時，選取了maven作管理工具，那麼這插件就可以在後續幫忙更新引用。
   - 若專案的Spring 及㡳層引用有變，vscode也需要它來引用更新。
   - 這是java 開發工具包(vscjava)的其中一員，它的其他插件也可以順帶安裝。

actuator? 
# api docs - open api
spring init with spring boot web

add maven
```xml
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.5.0</version>
   </dependency>
```

add controller

running program, visit http://localhost:8080/swagger-ui.html , all works out of box

## swagger under proxy
set header if you can modify proxy gateway
https://springdoc.org/index.html#how-can-i-deploy-springdoc-openapi-starter-webmvc-ui-behind-a-reverse-proxy

set config if you only controller your code like using code-server
```java
  @Bean
  public OpenAPI springShopOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9000/proxy/8080/");
        return new OpenAPI().servers(List.of(server));
  }
```

Connect under proxy http://localhost:9000/proxy/8080/swagger-ui.html, 
you might get "Failed to load remote configuration."
you need to paste http://localhost:9000/proxy/8080/v3/api-docs to the "explore" search box and press "explore" again the get the correct json.

# inheritance?
inheritance of controller still works, if you want to test less, un inheritance, higher test coverage you will have.
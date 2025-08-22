# Spring Boot 10 - OpenAPI自動生成 API 客戶端的步驟
之前我們在介紹[Spring Boot Web 調試工具](06-spring-web-debug.md) ，就試安裝 openapi 相關的元件。其實 openapi 並不單是為了提供 swagger 測試介面，它主要是提供一個描述的方式，讓我們針對一個特定 openapi 文件，生成對應的 api server 或 api client 接口。也就是，如果 server 方有提供該文件，道理上可以經 openapi 的工具，生成一個可以直接訪問 server 的 client library。本節，可以沿用之前的 [spring boot web api doc](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-doc) ，為它產生一個client library 作為實驗。

在生成 client library 之前，我們還需要一個工具 openapi-generator-cli 。最簡單的取得方式，就是經過 npm ， 在你需要生成 client library 的專案中，安裝你需要的 openapi-generator-cli 版本。

```bash
npm install @openapitools/openapi-generator-cli
```

那怕你不是使用 nodejs 作為開發，也可以經過這個方法安裝。它只提供使用 cmd 指令的捷徑。

# 生成 Java Client Library
我們先把 backend server 起好 `cd somewhere && mvn spring-boot:run`，然後使用 openapi-generator-cli 去生成以 java spring boot 3 為底的 client library 。

```bash
npx openapi-generator-cli generate \
  -i http://localhost:8080/v3/api-docs \
  --api-package io.github.macauyeah.springboot.tutorial.openapiclient.api \
  --model-package io.github.macauyeah.springboot.tutorial.openapiclient.model \
  --invoker-package io.github.macauyeah.springboot.tutorial.openapiclient.invoker \
  --group-id io.github.macauyeah.springboot.tutorial \
  --artifact-id spring-boot-web-api-open-api-client \
  --artifact-version 0.0.1-SNAPSHOT \
  -g java \
  -p useJakartaEe=true \
  -p useSpringBoot3=true \
  --library webclient \
  -o spring-boot-web-api-open-api-client
```

生成的 source code 就像是 [spring-boot-web-api-open-api-client](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-open-api-client) ，具體的使用方式，可以看看測試用例 [ApiControllerApiTest.java](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-open-api-client/src/test/java/io/github/macauyeah/springboot/tutorial/openapiclient/api/ApiControllerApiTest.java)

```java
    private final ApiControllerApi api = new ApiControllerApi();
    @Test
    public void postDateQueryTest() {
        // default call
        ApiDateRequest apiDateRequest = new ApiDateRequest();
        apiDateRequest.setInputDate(OffsetDateTime.now());
        LOG.debug("default web client postDateQuery:{}", api.postDateQuery(apiDateRequest).block());

        // replace webClient in ApiClient if you have special auth config on
        // webClient, you can also change basePath during new obj creation
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat());
        mapper.registerModule(new JavaTimeModule());
        WebClient webClient = WebClient.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper));
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper));
                })
                .build();

        ApiControllerApi api2 = new ApiControllerApi(
                new ApiClient(webClient)
                        .setBasePath("http://localhost:8080/"));
        LOG.debug("create api2 by local web client postDateQuery:{}", api2.postDateQuery(apiDateRequest).block());

        // use webClient directly
        String response = webClient.post().uri("http://localhost:8080/api/record").bodyValue(apiDateRequest).retrieve()
                .bodyToMono(String.class).block();
        LOG.debug("request by local web client postDateQuery:{}", response);
    }
```

上述例子中，如果大家沒有任何特殊要求，其實經過 `api.postDateQuery(apiDateRequest).block()` 就完成了。有需要改 api endpoint 的，只要生成新的 ApiClient 並設定 basePath `new ApiClient().setBasePath("XXXXXX")` 就好。真的要加入更多權限設定，就需要生成新的 ApiClient 並設定 webClient `new ApiClient(webClient)`

這個生成的 Java Client Library 道理上還是要經過 maven 等打包，變成 jar 檔，才能被其他 Java 專案所引用。筆者就建議大家直接把成生的視為獨立的 module (sub module) 存放，其他專案就以 maven dependency 的方式引用。想要混合現有專案，動態生成專案內某些 java package，暫時不太可行。因為它也有大量的 dependency ，交由 openapi-generator-cli 自己管理會比較好，它們升級時，你也可以完整升級。


# Reference
- [openapi-generator-cli https://github.com/OpenAPITools/openapi-generator-cli](https://github.com/OpenAPITools/openapi-generator-cli)
- [spring-boot-web-api-open-api-client](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-open-api-client)

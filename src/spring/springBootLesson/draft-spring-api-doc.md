# openapi
之前我們在介紹[Spring Boot Web 調試工具](06-spring-web-debug.md) ，就試安裝 openapi 相關的元件。其實 openapi 並不單是為了提供 swagger 測試介面，它主要是提供一個描述的方式，讓我們針對一個特定 openapi 文件，生成對應的 api server 或 api client 接口。也就是，如果 server 方有提供該文件，道理上可以經 openapi 的工具，生成一個可以直接訪問 server 的 client library。



open api generator

typescript axios
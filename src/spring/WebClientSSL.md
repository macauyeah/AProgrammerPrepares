# 升級 WebClient SSL (Reactor Netty 1.2.6)：重新配置 SSL 設定
因為SSL provider 更新了的關係，好多 HttpClient / WebClient 設定SSL的部份都要重寫以免出現 deprecated 問題

`reactor.netty.http.client.HttpClient` 在 1.0.x, 中可以這樣自行設定SSL逾時的部份，但當中的`spec.sslContext().defaultConfiguration` 在新版本，例如1.1.x後就會出現 deprecated。

```java
// deprecated version
HttpClient.create()
  .secure(spec -> spec.sslContext(SslContextBuilder.forClient())
    .defaultConfiguration(SslProvider.DefaultConfigurationType.TCP)
    .handshakeTimeout(Duration.ofSeconds(30))
    .closeNotifyFlushTimeout(Duration.ofSeconds(10))
    .closeNotifyReadTimeout(Duration.ofSeconds(10)));
```

觀看各大網站，都未有更新，唯有自行研究官方說明。

筆者撰寫本文的時候，netty 發行版本為 1.2.6， 1.3.0 還里程碑(M6)的階段。所有參考皆來自1.2.6版本，實際上我們要使用新的後綴為ContextSpec類，看Class名應該有分http 1.1， 2， 3的版本，筆者就試用最基本的http 1.1。`Http11SslContextSpec`, (有條件的朋友可以試用`Http2SslContextSpec`, `Http3SslContextSpec`)

```java
import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

//...
        Http11SslContextSpec http11SslContextSpec = Http11SslContextSpec.forClient();

        HttpClient httpClient = HttpClient.create()
                .secure(spec -> spec.sslContext(http11SslContextSpec)
                        .handshakeTimeout(Duration.ofSeconds(30))
                        .closeNotifyFlushTimeout(Duration.ofSeconds(10))
                        .closeNotifyReadTimeout(Duration.ofSeconds(10)));

        WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
//...
```

雖然這個寫法來看netty 1.2.6，但似乎1.1.x 通用。大家有需要可以交互測試一下。

# Reference
- [netty 1.2.6 http-client-timeout 的設定](https://projectreactor.io/docs/netty/1.2.6/reference/http-client.html#http-client-timeout)
- [netty 1.1.30 timeout-configuration 的設定](https://projectreactor.io/docs/netty/1.1.30/reference/index.html#timeout-configuration)
- [netty 1.2.6 java api doc](https://projectreactor.io/docs/netty/1.2.6/api/index.html)
- [netty release version](https://projectreactor.io/docs/netty)
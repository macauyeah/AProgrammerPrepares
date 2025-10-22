# Spring boot email
使用 Spring boot 對接 SMTP gateway 發 email ，相對是容易的。

基本上，它就是會使用自建的 `org.springframework.mail.javamail.*` , 對接 `javax.mail.*` / `jakarta.mail.*`

以前的所有設定值 ，都可以經 `spring.mail.properties.*` 傳入

例如
```
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true
spring.mail.properties.mail.smtp.socketFactory.port=465
```

就等於過去
```
java.util.Properties props = new java.util.Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.ssl.enable", "true");
props.put("mail.smtp.socketFactory.port", "465");
```

一個最簡單可以連去 google smtp 的簡易 code 如下
```
### application.properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<Login User to SMTP server>
spring.mail.password=<Login password to SMTP server>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

```java
// SpringBootEmailApplicationTests.java
@SpringBootTest
class SpringBootEmailApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromAddress;
    private static final Logger LOG = LoggerFactory.getLogger(SpringBootEmailApplicationTests.class);

    @Test
    void contextLoads() {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromAddress);
            mailMessage.setTo("XXXXXXXX");
            mailMessage.setText("this is backend email trigger for spring boot");
            mailMessage.setSubject("spring boot test mail");

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            LOG.error("Error while Sending Mail");
            throw new RuntimeException(e);
        }
    }
}
```
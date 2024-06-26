# Spring Boot 03 - 做好Database的模組化及測試用例

這節，我們將會使用spring-data-jpa，寫一個業務上的資料庫模組，提供資料表的存取，讓你的好同僚可以直接使用。這樣可以在多模組的環境中，減少同一個資料表在不同地方重複又重複地重定義。將來要更新，也可以使用jar檔的方式發佈。

## 下戴模版
我們跟上節一樣，使用Spring Initializr (Maven) 下載模版，但細節筆者就不再講啦。Dependency主要選擇
- H2 Database
- Spring Data JPA

對pom.xml作一些微調，並把spring-boot-start-data-jpa，h2改為只在測試中生效。
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
```

並把Java檔案搬一搬位置

```bash
# old location
src/main/java/io/github/macauyeah/springboot/tutorial/springbootdatatest/SpringBootDataTestApplication.java
src/main/resources/application.properties
# new location
src/test/java/io/github/macauyeah/springboot/tutorial/springbootdatatest/SpringBootDataTestApplication.java
src/test/resources/application.properties
```

以上的操作，主要是因為我們的目標是提供Schema，或者叫資料表規格。其他用於做連線的操作，我們不需要打包在jar內。所以把那些次要的東西都放在test資料夾中。我們這時可以先用```mvn test```指令，確保一切功能還是正常。

## Entity folder
然後我們入正題，在pom.xml中加入hibernate-core，spring-data-jpa，
```xml
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.hibernate.orm</groupId>
			<artifactId>hibernate-core</artifactId>
		</dependency>
```

然後在main資料夾下加入 Entity、Repository，例如前述用過的Apple和AppleRepo，最後資料夾就像是這樣。
```
.
|-- pom.xml
|-- src
|   |-- main
|   |   `-- java
|   |       `-- io
|   |           `-- github
|   |               `-- macauyeah
|   |                   `-- springboot
|   |                       `-- tutorial
|   |                           `-- springbootdatatest
|   |                               |-- Apple.java
|   |                               `-- AppleRepo.java
|   `-- test
|       |-- java
|       |   `-- io
|       |       `-- github
|       |           `-- macauyeah
|       |               `-- springboot
|       |                   `-- tutorial
|       |                       `-- springbootdatatest
|       |                           |-- SpringBootDataTestApplication.java
|       |                           `-- SpringBootDataTestApplicationTests.java
|       `-- resources
|           `-- application.properties
```

然後我們在Test Case中使用AppleRepo

```java
@SpringBootTest
class SpringBootDataTestApplicationTests {
	@Autowired
	AppleRepo appleRepo;

	@Test
	void contextLoads() {
		Apple apple = new Apple();
		apple.setUuid(UUID.randomUUID().toString());
		apple.setWeight(100.0);
		apple.setGravity(1000.0);
		appleRepo.save(apple);
	}
}
```

這個跟前述[02-spring-data-jpa](02-spring-data-jpa.md)最大的差別，就是我們的main中只有Entity相關的Class，我們發佈jar，別人引用我們的class，別人不會解發其他不相干的商業邏輯。假如發佈02的例子，因為Spring有自動初始化Component的原因，很可能會誤觸發02中的BasicApplicationRunner.java


## Source Code
[spring boot data test](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-data-test)
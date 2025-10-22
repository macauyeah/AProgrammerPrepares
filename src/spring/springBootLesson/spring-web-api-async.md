# Spring Web 異步 Api
在設計網頁應用時，總會有某些功能，是特別消耗時間的，例如我們的應用要提供報表，或長時間搜索。如果，我們的 Web Api 的連結，要強制客戶端等待結果，那麼中途斷線需要重做的機會就變得很高，客戶端的體驗一定不太好。

面對這些情況，我們最好就把原本一個 API 功能分為三個 API 去做。

1. 工作生成 API
2. 查詢狀態 API
3. 查詢結果 API

如果大家有信心，可以把2和3混合在一起，對於客戶端，也是一件好事。不過，2,3 因為回傳的結構可能不一樣，分開處理，程式碼會更易讀。

以下，筆者就以一個模擬報表生成的應用，去解釋如何設計可以即時回傳的 API。

[source code: spring-boot-web-api-async](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-async)

## ReportController.java 詳細解析

假設我們有一個 ReportController，它負責處理與報告生成相關的 HTTP 請求，它提供三個核心 API 端點。

1. 啟動報告生成端點

```java
    @PostMapping("/reportJob/create")
    public ResponseEntity<Object> createJob() {
        String uuid = String.format("%d_%s", (new Date()).getTime(), UUID.randomUUID().toString());
        CompletableFuture.runAsync(() -> {
            try {
                orderStatus.put(uuid, PROCESSING);
                Thread.sleep(10000); // 10-second simulated delay
                reportService.genAndSaveReport(uuid);
                orderStatus.put(uuid, COMPLETED);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return ResponseEntity
                .accepted()
                .header(HttpHeaders.LOCATION, "/reportJob/status/" + uuid)
                .body(Map.of("uuid", uuid, 
                    "status api",
                    "/api/reportJob/status/" + uuid, 
                    "download api",
                    "/api/reportJob/download/" + uuid));
    }

```

運作原理：

- 立即生成唯一的 uuid 來標識這次任務
- 在 CompletableFuture.runAsync 運行長時間的操作。
- API 本身即時回傳了 HTTP 202 (Accepted) 狀態，告訴客戶端請求已被接受但尚未完成
- 在回傳的結果中，還有提示可以查詢狀態和查詢結果的API。
這種設計避免了 HTTP Gateway Timeout，因為回應是即時的 。

2. 檢查進度端點

```java
    @GetMapping("/reportJob/status/{uuid}")
    public ResponseEntity<Object> getStatus(@PathVariable("uuid") String uuid) {
        String status = orderStatus.get(uuid);
        if (status == null)
            return ResponseEntity.notFound().build();

        if (COMPLETED.equals(status)) {
            // return ResponseEntity.status(HttpStatus.SEE_OTHER)
            return ResponseEntity.ok()
                    .header(HttpHeaders.LOCATION, "/api/reportJob/download/" + uuid)
                    .body(Map.of("status", COMPLETED));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("status", PROCESSING));
    }
}
```

單純以 map `orderStatus.get(uuid)` 查看狀態結果。這個map 必需是多線程下使用還是安全的 (`ConcurrentHashMap`)。

3. 下載結果端點

```java
@GetMapping("reportJob/download/{uuid}")
    public ResponseEntity<Resource> download(@PathVariable("uuid") String uuid) {
        
        String status = orderStatus.get(uuid);
        if (status == null || !COMPLETED.equals(status)) {
            return ResponseEntity.notFound().build();
        } else {
            // 下載檔案
        }
    }
}
```

如果大家並不計較是否需要重做失敗的請求，這個例子已經可以簡單地達到即時異步回傳的效果。如果大家還需求考慮請求是否有效完成，就需要用到 message queue 或其他 job server ，這就不是同一個網頁應用的操作範圍。

# Reference
- [source code: spring-boot-web-api-async](https://github.com/macauyeah/spring-boot-demo/tree/main/spring-boot-tutorial/spring-boot-web-api-async)
- [Building a Long-Running Async REST API in Spring Boot (with 202 + 303 Status Codes)](https://medium.com/@arun.badhai/building-a-long-running-async-rest-api-in-spring-boot-with-202-303-status-codes-5b3b46d5ccc6)
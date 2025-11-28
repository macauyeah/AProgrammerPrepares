# 你開始寫 Spring Boot 測試案例了嗎？

雖然筆者過往做 spring boot framework 教學中，都有滲入一些測試用例。筆者也曾經困惑了很長一段時間，所以就獨立開一個主題，聊一下筆者在實務上對spring boot test 的理解。

## 測試案例究竟測試什麼？
測試用例 (test case) 是確保你的程式碼正確性與穩定性的重要步驟，但在 framework 下，並不是所有功能都很容易寫成測試。所以在討論 framework 測試之前，釐清測試的本質。

我們正常編寫的程式，基本流程可以概括為：
```
function input → business logic → function output
```
這意味著我們輸入某些資料（input），然後經過業務邏輯（business logic）的處理，最後產生結果輸出（output）。

我們的測試目標，其實就是確保業務邏輯正確。而我們的手段就是經檢查概定的輸入資料，核對輸出結果。

那麼只要我們可以生成輸入資料，就一定可以檢查輸出結果了吧？其實不是的，因為實務上的輸入和輸出沒有這麼簡單。筆者常接觸到的輸入輸出如下

輸入
1. function 輸入參數
2. 系統狀態資料，例如：資料庫狀態、外部API結果。

輸出
1. function 輸出參數
2. 寫入系統（影響到）的資料，例如：資料庫狀態、使用外部API時的輸入參數。

總之就是考慮了狀態機 (state machine) 的問題，每個狀態+外部輸入都是一個測試用例，然後核對狀態機去了下一個什麼狀態。

言下之意，我們就是暴力地生成輸入參數和模擬狀態資料，道理上就是可以進行測試。

## Spring boot web framework 中，我們又會測試什麼？
𠩤本我們工作流程如下
```
function input → business logic → function output
```

在Spring boot web就變成如下
```
controller request → business logic → controller response
```

在 Spring Boot test 中，我們可以用模擬的 MVC (`MockMvc`) 測試來驗證 controller 的行為。不過，其實進入 controller 前經過很多系統轉換，而這些道理上跟Framework的技術大相關，與業務邏輯小相關。所以為免折磨自己，可以將業務邏輯單獨封裝成服務（service）。之後直接測試服務 ，易寫也易讀。

```
controller request → service input → business logic → service output → controller response
```

道理上 controller 能做的業務邏輯，服務 (service) 都可以無腦重現。這樣還可以重用服務，減少測試的數量。

### 如何實現輸入？
1. **直接 new Object**。大部份的情況下，因為業務是自己編寫的，應該都可以直接 new 出來。
2. **經 json 檔讀入**。如果輸入的參數量太多，逐個經 java new 是很耗時的，我們可以經 json 反序列化變成 Object。但這亦只限於自己可以操作/改寫的類。
3. **Mockito 模擬那些無法簡易經 new 或 json 反序列化的 Object**。例如：spring security authentication object 我們在使用時，其實只看到 interface。我們難似自己實現一個可以反序列化的類，那麼我們可以使用 Mockito 來模擬這些資料。一些外部API的結果，我們也可以用使 Mockito 來模擬。

## 什麼情況下不進行測試？

有些情況下，我們可能選擇不對某些功能進行測試，原因可能包括對功能的了解不足或是單純的懶惰。以下是一些例子：

1. **僅進行配置的Function**：如果你的 Function 只是在 Framework 中填寫配置，而且你並不太了解它的運作原理，可能就不需要進行測試了。例如，Spring boot web 中，需要大家配置一個`SecurityFilterChain` Object，它要求大家將 `HttpSecurity` 轉換為 `SecurityFilterChain` 。因為輸入的 `HttpSecurity` 是系統固定的參數，我們亦沒有檢查它的狀態。這種情況下，它的輸入及輸出，其實我們都沒有真正理解。我們硬測試的話，測試功能可能只流於表面。若我們真的要做測試，也是經過`MockMvc`進行端到端測試（end-to-end testing），測試它在事後的影響範圍。

2. **單純的框架功能**：例如資料庫的儲存庫介面（repository interface），雖然是在框架下生成的，對於自己手動調整的部份功能，筆者通常亦不會進行單獨測試，通常都會搭配業務邏輯一起進行。它可以使用 Mockito 進行模擬測試，或用測試環境的真實資料庫進行測試。

## 面對的挑戰
總括來講，筆者盡可能地把測試用例限定在業務邏輯中，就可以大大地降低寫測試的技術難度。但筆者還是有些問題並未完美解決。

1. 測試用例的數量可能很多，因此共用與維護變得相當困難。逐個用例獨立編寫輸入也是很累的。
2. 對於 Mockito 的使用，筆者還是可免則免。因為要逐個功能模擬，編寫量就指數提高，這亦難似配合外部變化。一般來說，能優先使用測試環境或者 Docker 來模擬環境的，就盡量用。
3. 離線開發、離線測試。系統依懶的外部功能越多，想做單機開發的難度就越高。即使前述有 Docker 測試，對於持續整合（CI）來講也是有一定難度。那麼這時，Mockito 就是一個可取的選擇。但這又回到編寫量及難以偵測外部變化問題。


希望這篇文章能幫助你更好地理解測試案例的編寫方向，並在Spring boot web開發中加入你自己的測試！
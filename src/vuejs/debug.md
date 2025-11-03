# 為何 VueJS debugging （除錯）如些麻煩？

在寫 blog 的時候，筆者曾分享了 VSCode frontend debugging 的好功能，也確實了coding anywhere 並不是一個普通的 notepad + language server 就可以解決的事。我們還要考慮如何 debugging （除錯）的問題。

雖然筆者知道 vscode 可以解決問題，但為何最原始的 nodejs debugger 不能解決問題。如果 node debugger 不能解決問題，那麼 vscode 又做了什麼，令它可以解決問題？經過一輪的實驗，筆者懷疑，強大的並不是 vscode 本身，而瀏覽器才真正的做到 debugger 的功能。vscode 只是以更方便的方式，重現那些結果。

## 為何 backend 的 debugger 不發揮作用？

筆者舉例，現時有一個 vue 3 專案，使用官方建議的方式生成

```bash
$ npm create vue@latest
```

這個專案，在開發模式下，會以 vite 架起一個端口為 5173 的伺服器，讓開發人員可以經過瀏覽器看到 vue 內容。筆者一直都認為，只要在 vite 的指令中插入 inspect 參數，一切就可以成功，就像 nodejs 一樣，只要在開始時加入參數就可以。結果當然是不行的。

經過對比  VueDevTools 的參考功能，筆者發現了一個本質性的問題。vite 其實是一個伺服器級的程式，也許它只是負責把所以 vue + js 動態轉成 html+ js，就像 webpack 一樣。我們想要設的中斷點，都不在它的程式上，所以 debug 參數也沒有用。實質，我們要加的中斷點，其實要施加在客戶端上，也就是瀏覽器上。那因此，VueDevTools 也不包括那些功能。它只是好好地記錄了每個 vue component 或 js 是如何被改寫的過程（就像被 webpack改寫的過程）。

## Vue 官方又是用什麼來除錯的？

既然我們知道了問題所在，就要看看傳統的 javascript 又是如何除錯的。實際上，因為瀏覽器的配合，設立中斷點的功能，原來早就實現了。

[developer.mozilla.org debugger](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/debugger)

只要我們在任何 javascript 地方，插入 “debugger;” 這個神奇的字，瀏覽器就會在 inspect 模式下，自動產生中斷點。之後，你可以控制瀏覽器進行 watch / step into / step over 功能。絕對比console.log更有意義。

在發現了這個方法之後，回去找 vue3 的官方文件，驚訝地發現，它就是提議用這種方式進行除錯。

[reactivity-debugging](https://vuejs.org/guide/extras/reactivity-in-depth.html#reactivity-debugging)

## 未解之謎

雖然我們找到了設定中斷點的方式，但對於 vscode 是如何做到客戶端、伺服器端通用這件事，筆者還是沒有了解到。就以現在的資訊來看，很大機會就是 vscode 操控了瀏覽器的除錯模式，把所有資訊都回傳了 vscode 本身。這也是解譯了為何 vscode 在起動debugger時，必需要求由 vscode 自己叫起瀏覽器。而codeserver這類雲IDE無法叫起本地瀏覽器，就是造成它無法運用除錯功能的原因。

有興趣為 codeserver 一起搵解決方案的朋友，可以到筆者的 https://github.com/macauyeah/AProgrammerPrepares ，以文字教學的方提交你的解決方案。

祝願大家可以早日實現coding 自由。
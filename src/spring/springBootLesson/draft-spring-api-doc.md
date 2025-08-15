# draft 生成 javascript Client Library
typescript axios

```bash
npx openapi-generator-cli generate \
    -i http://localhost:8080/v3/api-docs \
    -g typescript-axios --additional-properties=withSeparateModelsAndApi=true,modelPackage=model,apiPackage=api \
    -o src/openapi/
```

有些基本假設
- 現有 javascript project 已經有使用 typscript 的模式，也有安裝了 axios 。
- project 已經有適合的 proxy 設定，連去你開發中的後端（不然會有CORS問題）。
- openapi 為你生成連上後端的 api 的格式（mode即是在 api 中的輸入輸出參數）。
- 需要你提供axios，以達到必要的錯誤處理。
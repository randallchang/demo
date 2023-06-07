# Table of Contents

- [Table of Contents](#table-of-contents)
- [jkos-app-svc 開發注意事項](#jkos-app-svc-開發注意事項)
  - [前置步驟](#前置步驟)
  - [開發規範](#開發規範)
- [Gitlab Runner](#gitlab-runner)
  - [Usage](#usage)
    - [Ignore Trigger Gitlab Runner](#ignore-trigger-gitlab-runner)
    - [Declare Gitlab Runner Variables](#declare-gitlab-runner-variables)

# jkos-app-svc 開發注意事項

## 前置步驟

1. 安裝 Alibaba Java Coding Guidelines(XenoAmess TPM)(必須，style check 部份依賴此 plugin)
2. 安裝 save actions (推薦，可於 save 時進行 format、清除多餘 import 等額外動作)
3. 安裝 sonarlint (推薦，可進行靜態程式碼分析(bad smell))
4. 安裝 spotbugs (推薦，可進行靜態程式碼分析(bug))
5. 啟用共用格式配置(.editorconfig)
1. editor > code Style
2. 勾選 enable EditorConfig support
6. 匯入 code check xml (掃出來的問題請修正)，步驟如下
1. setting > editor > inspections
2. 點擊 profile 右邊齒輪
3. import profile
4. 選擇 info/rd4-java-code-check.
5. 下方 Ali-check 中取消註釋相關檢查, 取消 POJO 需有 toString 檢查。

## 開發規範

1. 開發規範為阿里巴巴規範， 請看info/阿里巴巴Java開發手冊黃山版， 除命名規則有以下修改之外，其他盡量符合，若有疑問請找 Ted 詢問。
2. 不強迫寫 unit test, 但不可破壞舊有的 unit test。
3. 依賴注入使用constructor方式(不可為null)或者setter方式(可為null)， 使用 setter 方式時請將 setter 置於 class 最下方。
4. method return 請勿回傳 null, 單一物件的話使用 Optional, Collection 的話使用 empty collection, 避免 NullPointerException
5. 資料封裝物件命名規範
1. DTO(Data Transfer Object): 系統對"外"數據傳輸對象。(Request、Response、ResultObject 以外使用)
2. Query: 數據查詢對象，各層接收上層的查詢請求。注意超過 2 個參數的查詢封裝，禁止使用 Map 來傳輸。
6. mapper 命名規範
1. 只查一個使用 find 開頭命名，並以 Optional 回傳，避免 NullPointerException。
2. 回傳多個使用 list 開頭命名。
3. 更新使用 update 開頭命名。
4. 刪除使用 delete 開頭命名。
5. 新增使用 insert 開頭命名。
6. 不可隱藏邏輯, 如預設 where 條件在命名中看不出來, 需顯式命名。
7. 時間格式統一使用 ISO-8601(DateTimeFormatter.ISO_LOCAL_DATE_TIME, yyyy-MM-DDTHH:mm:ss)

# Gitlab Runner

## Usage

### Ignore Trigger Gitlab Runner

``` shell
git push -o ci.skip
```

### Declare Gitlab Runner Variables

``` shell
git push -o ci.variable="_REVISION=HEAD" -o ci.variable="_ENVIRONMENT=sit"
```

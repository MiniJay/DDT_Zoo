# DDT_Zoo

# API
使用台北open data串接API取得資料(共兩支)

台北市立動物園-館區簡介  
https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2

臺北市立動物園_植物資料  
https://data.taipei/#/dataset/detail?id=48c4d6a7-4b09-4d1f-9739-ee837d302bd1

備註: 植物資料的API資料結構異常，資料結構應為一個index對應一項植物且其資料會亂數承接在不同的欄位上，此project找出邏輯性解析資料得以重建  
"F_Function＆Application" 欄位定義"&"符號也不符合API Spec訂定

# Programing
Code: Kotlin  
Framework: MVP  
UI: 透過androidx的Navigation轉導Fragment，搭配RecyclerView呈現資料

# 3rd lib
Glide  
Retrofit2 + rxjava2 + okhttp3-log

# C2Pinyin

[![](https://jitpack.io/v/1552980358/C2Pinyin.svg)](https://jitpack.io/#1552980358/C2Pinyin)

- A Kotlin fork of [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- Full Java & Kotlin supported

- Kotlin features supported

## Add C2Pinyin to project

- Java/Kotlin Gradle project
  
   1. Add following into `repositories` in `<PROJECT_DIR>/build.gradle` file
      - `maven { url 'https://jitpack.io' }`
       
   2. Add following into `dependencies` in `<PROJECT_DIR>/build.gradle` file
      - `implementation 'com.github.1552980358:C2Pinyin:986340e9a3'`

- Android Project 
    
   1. Add following into `repositories` in `<PROJECT_DIR>/build.gradle` file
      - `maven { url 'https://jitpack.io' }`

  2. Add following into `dependencies` in `<PROJECT_DIR>/app/build.gradle` file
      - `implementation 'com.github.1552980358:C2Pinyin:986340e9a3'`
    
## Application
- With Java
```java
// 导入 Pinyin 类
// Import class Pinyin
import projekt.cloud.piece.c2pinyin.Pinyin;

// 检查 orgChar 的值是否汉字
// Check whether orgChar value is Chinese
char orgChar;
orgChar = 'C';
Pinyin.isChinese(orgChar);      // false
orgChar = '中';
Pinyin.isChinese(orgChar);      // true

// 获取 '中' 的拼音
// Get Pinyin of '中'
Pinyin.getPinyin(orgChar)       // "ZHONG"

// 获取 "中文" 的拼音
// Get Pinyin of "中文"
String orgStr = "中文";
Pinyin.getPinyin(orgStr);       // "ZHONGWEN"

// ' ' 作为分隔
// ' ' as a divider character
Pinyin.getPinyin(orgStr, ' ');  // "ZHONG WEN"

// " " 作为分隔
// " " as a divider string
Pinyin.getPinyin(orgStr, " ");  // "ZHONG WEN"

```

- With Kotlin
```kotlin
// 导入 isChinese 方法
// Import method isChinese
import projekt.cloud.piece.c2pinyin.isChinese
// 导入 pinyin 方法
// Import method pinyin
import projekt.cloud.piece.c2pinyin.pinyin

// 检查 orgChar 的值是否汉字
// Check whether orgChar value is Chinese
var orgChar = 'C'
orgChar.isChinese           // false

orgChar = '中'
orgChar.isChinese           // true

// 获取 '中' 的拼音
// Get Pinyin of '中'
orgChar.pinyin              // "ZHONG"

val orgStr = "中文"
orgStr.pinyin               // "ZHONGWEN"

// ' ' 作为分隔
// ' ' as a divider character
orgStr.pinyin()             // "ZHONG WEN"
// 与 [orgStr.pinyin()] 方法相同
// Same as [orgStr.pinyin()]
orgStr.pinyin(' ')          // "ZHONG WEN"

// " " 作为分隔
// " " as a divider string
orgStr.pinyin(" ")          // "ZHONG WEN"

```

## Dictionary

- Two classes required to create customized dictionary: `projekt.cloud.piece.c2pinyin.PinyinDictionary` and `projekt.cloud.piece.c2pinyin.PinyinMap`

    - `PinyinMap` applied for storing Chinese blocks and corresponding Pinyin
        - Constructors
            - `name: String`
                - `name` for storing Chinese block
            - `name: String, vararg array: String`;
                - `name` for storing Chinese block
                - array for putting pinyin strings for corresponding `name` block
            - `name: String, list: List<String>`;
                - `name` for storing Chinese block
                - list for putting pinyin strings list for corresponding `name` block

    - `PinyinDictionary` applied for storing list of `PinyinMap`
        - Constructors
            - No parameter
            - `vararg pinyinMaps: PinyinMap`
                - `pinyinMaps` for storing array of `PinyinMap` instance
                - Add a `*` before arrayOf<PinyinMap>() instance
        - Method `load()`
            - Load external dictionary
            - Method `load`
                - `fun load(file: File)`
                    - Import from external file
                - `fun load(list: List<String>)`
                    - `list` list of String
                - With format with following examples
                    - `长沙=CHANG-SHA`
                    - `银行=YIN-HANG`
                    - One map per line
    
- Apply dictionary
    - With Java
    ```java
    // 导入 Pinyin 类
    // Import class Pinyin
    import projekt.cloud.piece.c2pinyin.Pinyin;
    // 导入 PinyinDictionary 类
    // Import class PinyinDictionary
    import projekt.cloud.piece.c2pinyin.PinyinDictionary;
    // 导入 PinyinMap 类
    // Import class PinyinMap
    import projekt.cloud.piece.c2pinyin.PinyinMap;
    
    PinyinMap map0 = new PinyinMap("银行");
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("CHANG");
    arrayList.add("DU");
    map0.add("YIN", "HANG");                             // With hard coded
    map0.add(arrayList);                                 // With List
    PinyinMap map1 = PinyinMap("长沙", "CHANG", "SHA");   // With hard coded
    PinyinMap map2 = PinyinMap("长度", arrayList);        // With List
    
    PinyinDictionary dictionary = new PinyinDictionary();
    dictionary.load(new File("<FilePath>"));
    arrayList = new ArrayList<>();
    arrayList.add("银行=YIN-HANG");
    dictionary.load(arrayList);
    
    dictionary.add(map0);                          // Add single PinyinMap
    dictionary.add(map0, map1);                    // Add multiple PinyinMaps
    dictionary.add("银行", "YIN", "HANG");          // Same as above
    arrayList = new ArrayList<>();
    arrayList.add("YIN");
    arrayList.add("HANG");
    // Same as above dictionary.add()
    dictionary.add("银行", arrayList);
    
    Pinyin.getPinyin("银行", dictionary);           // Apply single dictionary
    // Apply single dictionary with divider
    Pinyin.getPinyin("银行", '-', dictionary);
    Pinyin.getPinyin("银行", "-", dictionary);
    PinyinDictionary dictionary1 = new PinyinDictionary();
    dictionary1.add(map2);
    // Apply multiple dictionaries
    Pinyin.getPinyin("银行", dictionary, dictionary1);
    // Apply multiple dictionaries with divider
    Pinyin.getPinyin("银行", '-', dictionary, dictionary1);
    Pinyin.getPinyin("银行", "-", dictionary, dictionary1);
    ```
  - With Kotlin
```kotlin
// 导入 pinyin 方法
// Import method pinyin
import projekt.cloud.piece.c2pinyin.pinyin
// 导入 PinyinDictionary 类
// Import class PinyinDictionary
import projekt.cloud.piece.c2pinyin.PinyinDictionary;
// 导入 PinyinMap 类
// Import class PinyinMap
import projekt.cloud.piece.c2pinyin.PinyinMap;

val map0 = PinyinMap("银行")
var arrayList = ArrayList<String>()
arrayList.add("CHANG")
arrayList.add("DU")
map0.add("YIN", "HANG")                               // With hard coded

map0.add(arrayList)                                   // With List

val map1 = PinyinMap("长沙", "CHANG", "SHA")           // With hard coded

val map2 = PinyinMap("长度", arrayList)                // With List

val dictionary = PinyinDictionary()
dictionary.load(File("<FilePath>"))
arrayList = ArrayList()
arrayList.add("银行=YIN-HANG")
dictionary.load(arrayList)

dictionary.add(map0)                                  // Add single PinyinMap
dictionary.add(map0, map1)                            // Add multiple PinyinMaps
dictionary.add("银行", "YIN", "HANG")                 // Same as above
arrayList = ArrayList()
arrayList.add("YIN")
arrayList.add("HANG")
// Same as above dictionary.add()
dictionary.add("银行", arrayList)                             

"银行".pinyin(dictionaries = arrayOf(dictionary))      // Apply single dictionary
// Apply single dictionary with divider
"银行".pinyin('-', dictionary)
"银行".pinyin("-", dictionary)
val dictionary1 = PinyinDictionary()
dictionary1.add(map2)
// Apply multiple dictionaries
"银行".pinyin(dictionaries = arrayOf(dictionary, dictionary1)) 
// Apply multiple dictionaries with divider
"银行".pinyin('-', dictionary, dictionary1)
"银行".pinyin("-", dictionary, dictionary1)
```


## Credit

- [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- [JetBrains/kotlin](https://github.com/JetBrains/kotlin)

- [JitPack](https://jitpack.io/)

## [Apache License 2.0](LICENSE)
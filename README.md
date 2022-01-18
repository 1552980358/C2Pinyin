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
orgChar = 'C'；
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

## Credit

- [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- [JetBrains/kotlin](https://github.com/JetBrains/kotlin)

- [JitPack](https://jitpack.io/)

## [Apache License 2.0](LICENSE)
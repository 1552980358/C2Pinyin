# C2Pinyin

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=FFF)](https://kotlinlang.org/)

[![](https://jitpack.io/v/1552980358/C2Pinyin.svg)](https://jitpack.io/#1552980358/C2Pinyin)

## Usage
- Add dependency
  ```Kotlin
  repositories {
      maven { url = uri("https://jitpack.io") }
  }

  dependencies {
      implementation("com.github.1552980358:C2Pinyin:3.0.0-rc1")
  }
  ```

- Example
  - Convert
    - Char
      ```Kotlin
        '你'.pinyin // Or
        '好'.pinyin(/** Single time custom LetterCase **/) // Or
        
        // Use custom dictionary
        val c2Pinyin = C2Pinyin(/** Custom dictionary **/)
        c2Pinyin.char('你')
        c2Pinyin.char('好')
      ```
    - String
      ```Kotlin
        "你好".pinyin // Or
        "你好".pinyin(/** Single time custom LetterCase **/) // Or
      
        // Use custom dictionary
        val c2Pinyin = C2Pinyin(/** Custom dictionary **/)
        c2Pinyin.string("你好")
      ```
  - Dictionary
    - Word
      - [Single Word dictionary](src/test/kotlin/DictionaryTest.kt#L11)
      - [Single Word jumping conversion](src/test/kotlin/DictionaryTest.kt#L22)
    - [Phase](src/test/kotlin/DictionaryTest.kt#L33)
  - Letter Case
    - [Camelcase](src/test/kotlin/LetterCaseTest.kt#L11)
    - [Uppercase](src/test/kotlin/LetterCaseTest.kt#L42)
    - [Lowercase](src/test/kotlin/LetterCaseTest.kt#L62)

## License
- [Apache-2.0 License](LICENSE)

## Credit
- [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)
  - [Apache-2.0 License](https://github.com/promeG/TinyPinyin?tab=Apache-2.0-1-ov-file#readme)
- [JetBrains/Kotlin](https://github.com/JetBrains/kotlin)
  - [Apache-2.0 License](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt)
  - [Third Party License List](https://github.com/JetBrains/kotlin/tree/master/license)
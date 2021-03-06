# Projekt CloudPiece - C2Pinyin

- A Kotlin fork of [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- Full Java & Kotlin supported

- Kotlin features supported

## Building 
[![](https://jitpack.io/v/1552980358/C2Pinyin.svg)](https://jitpack.io/#1552980358/C2Pinyin)
[![BuildProject](https://github.com/1552980358/C2Pinyin/actions/workflows/BuildProject.yml/badge.svg)](https://github.com/1552980358/C2Pinyin/actions)

## Version Explanation
- `<VERSION>.<FEATURE>.<PATCH>`
- `VERSION` 
  - Updated (+1) when there is any large-scaled architectural update of the project
- `FEATURE`
  - Updated (+1) when there is any feature added to the project
  - Cleared when `VERSION` is updated
- `PATCH`
  - Updated (+1) when there is any bug fixing or optimization of features
  - Cleared when `FEATURE` or `VERSION` is updated

## Add C2Pinyin to project
- VERSION_CODE please refer to the latest version from [Releases](https://github.com/1552980358/C2Pinyin/releases) page or [JitPack](https://jitpack.io/#1552980358/C2Pinyin) release page.

- Kotlin/Java Gradle project

  - Groovy Gradle
    1) Add following into `repositories` block of `build.gradle`
       - `maven { url 'https://jitpack.io' }`
    2) Add following into `dependencies` block of `build.gradle`
       - `implementation 'com.github.1552980358:C2Pinyin:<VERSION_CODE>'`

  - Kotlin DSL
    1) Add following into `repositories` block of `build.gradle.kts`
       - `maven { url = uri("https://jitpack.io") }`
    2) Add following into `dependencies` block of `build.gradle.kts`
       - `implementation("com.github.1552980358:C2Pinyin:<VERSION_CODE>")`

- Android Gradle project

  - Groovy Gradle
    1) Add following into `repositories` block of `<ProjectRoot>/settings.gradle`
       - `maven { url 'https://jitpack.io' }`
    2) Add following into `dependencies` block of `<ProjectRoot>/app/build.gradle`
       - `implementation 'com.github.1552980358:C2Pinyin:<VERSION_CODE>'`

  - Kotlin DSL
    1) Add following into `repositories` block of `<ProjectRoot>/settings.gradle.kts`
       - `maven { url = uri("https://jitpack.io") }`
    2) Add following into `dependencies` block of `<ProjectRoot>/app/build.gradle.kts`
       - `implementation("com.github.1552980358:C2Pinyin:<VERSION_CODE>")`
    
## Usage  
- Kotlin
  ```kotlin
  import projekt.cloud.piece.c2.pinyin.C2Pinyin.convertToPinyin
  import projekt.cloud.piece.c2.pinyin.C2Pinyin.dictionary
  import projekt.cloud.piece.c2.pinyin.C2Pinyin.importDictionary
  import projekt.cloud.piece.c2.pinyin.C2Pinyin.pinyin
  import projekt.cloud.piece.c2.pinyin.dictionary.DictionaryItem 
  
  // Get a char's pinyin
  val zhong = '???'
  val pinyinZhong = zhong.pinyin
  
  val zhongWen = "??????"
  // Get a string's pinyins
  zhongWen.pinyin                   // ZHONGWEN
  // With a separator
  zhongWen.convertToPinyin("-")     // ZHONG-WEN
  
  // Add dictionary item with Dictionary instance getter, with plusAssign operator and Pair<String, List<String>>
  dictionary += "??????" to listOf("YIN", "HANG")
  
  // Two constructors
  // With <Text>, <Pinyin of Text[0]>, <Pinyin of Text[1]> ...
  var dictionaryItemZhuanJi = DictionaryItem("??????", "ZHUAN", "JI")
  // With <Text>, <List<String>>
  val dictionaryItemYinHang = DictionaryItem("??????", listOf("HANG", "ZHANG"))
  // Using Array<String>
  dictionaryItemZhuanJi = DictionaryItem("??????", *arrayOf("ZHUAN", "JI"))
  
  // Operation with Dictionary instance method block
  dictionary { dict ->  // default is [it], customize name please defint here, for example [dict]
      // Add dictionary item
      // with plusAssign operator and DictionaryItem instance
      dict += dictionaryItemYinHang
      // With .add() provided by ArrayList
      dict.add(dictionaryItemZhuanJi)         
      dict.add("??????", "JUE", "SE")             // With <Text>, <Pinyin of Text[0]>, <Pinyin of Text[1]> ...
          .add("??????", listOf("DAI", "FU"))   // With <Text>, <List<String>>
      
      // Remove dictionary item
      dict -= dictionaryItemYinHang
      dict -= arrayOf(dictionaryItemYinHang)
      dict -= "??????"
      dict -= listOf("??????")   // Array is also supported
  }
  
  // Import dictionary with formatted raw text
  val dictionaryStr = "??????=YIN'HANG\n??????=HANG'ZHANG\n??????=ZHUAN'JI\n??????=JUE'SE\n??????=DAI'FU"
  // Import into dictionary, with text
  dictionaryStr.importDictionary()
  // With File
  File("dict.txt").importDictionary()
  // With InputStream
  File("dict.txt").inputStream().use { it.importDictionary() }     // Any child InputStream instance is supported
  ```
  
- Java
  ```Java
  import projekt.cloud.piece.c2.pinyin.C2Pinyin;
  import static projekt.cloud.piece.c2.pinyin.C2Pinyin.convertToPinyin;
  import static projekt.cloud.piece.c2.pinyin.C2Pinyin.dictionary;
  import static projekt.cloud.piece.c2.pinyin.C2Pinyin.getDictionary;
  import static projekt.cloud.piece.c2.pinyin.C2Pinyin.getPinyin;
  import static projekt.cloud.piece.c2.pinyin.C2Pinyin.importDictionary;
  import projekt.cloud.piece.c2.pinyin.dictionary.DictionaryItem;
  ...
  
  // Get a char's pinyin
  var zhong = '???';
  / Identifier method call: C2Pinyin.getPinyin
  getPinyin(zhong);
  
  // Get a string's pinyins
  var zhongWen = "??????";
  // Identifier method call: C2Pinyin.getPinyin
  getPinyin(zhongWen);
  // With a separator
  // Identifier method call: C2Pinyin.convertToPinyin
  convertToPinyin(zhongWen, "-");
  
  var yinHangList = new ArrayList<String>();
  yinHangList.add("YIN");
  yinHangList.add("HANG");

  // Add dictionary item with Dictionary instance getter
  // With method add, <String>, <ArrayList>
  // Identifier method call: C2Pinyin.getDictionary()
  getDictionary().add("??????", yinHangList);

  // Operation with Dictionary instance method block
  // Identifier method call: C2Pinyin.dictionary
  dictionary((dictionary) -> {
      var jueSePinyin = new String[2];
      jueSePinyin[0] = "JUE";
      jueSePinyin[1] = "SE";

      var daiFuList = new ArrayList<String>();
      daiFuList.add("DAI");
      daiFuList.add("FU");

      dictionary.add("??????", "HANG", "ZHANG")   // <Text>, <Pinyin[0]>, <Pinyin[1]> ...
              .add("??????", jueSePinyin)         // <Text>, <Pinyin Array>
              .add(new DictionaryItem("??????", daiFuList));  // <DictionaryItem>

      // Remove dictionary item
      var removeList = new ArrayList<String>();
      removeList.add("??????");
      dictionary.remove("??????")
              .remove("??????", "??????")
              .remove(removeList);        // Array is also supported
  }
  
  // Import dictionary with formatted raw text
  var dictionaryStr = "??????=YIN'HANG\n??????=HANG'ZHANG\n??????=ZHUAN'JI\n??????=JUE'SE\n??????=DAI'FU";

  // Identifier method call: C2Pinyin.importDictionary
  // Using String
  importDictionary(dictionaryStr);
  // Using File
  importDictionary(new File("PATH"));
  // Using Stream
  try {
      importDictionary(new FileInputStream("PATH"));
  } catch (FileNotFoundException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
  }
  ```

## External Dictionary
- Format
  - `<CHINESE_TEXT>=<PINYIN_OF_FIRST_TEXT>'<PINYIN_OF_SECOND_TEXT>`
  - One line per one dictionary item
  - Space is not allowed at any position
  - Not case-sensitive
  - Example
    ```
    ??????-YIN'HANG
    ??????-Hang'Zhang
    ??????-jUe'Se
    ??????-daI'fU
    ```

## Pinyin Cases
- Provide Uppercase, Lowercase and camelcase
- Uppercase is the default cases
- Custom case can be set with 3 methods
  - `C2Pinyin.setUppercase()`
    - Lowercase, Default case, all letters are in uppercase
    - Example: `??????` gives `ZHONGWEN`
  - `C2Pinyin.setLowercase()`
    - Lowercase, all letters are in lowercase
    - Example: `??????` gives `zhongwen`
  - `C2Pinyin.setCamelcase()`
    - Camelcase, first letter of each pinyin is in capital letter, and remaining is in lowercase
    - Example: `??????` gives `ZhongWen`

## Open-Source License
[Apache License 2.0](LICENSE)

## Credit

- [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- [JetBrains/kotlin](https://github.com/JetBrains/kotlin)

- [JitPack](https://jitpack.io/)

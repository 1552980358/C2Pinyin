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

## Open-Source License
[Apache License 2.0](LICENSE)

## Credit

- [promeG/TinyPinyin](https://github.com/promeG/TinyPinyin)

- [JetBrains/kotlin](https://github.com/JetBrains/kotlin)

- [JitPack](https://jitpack.io/)

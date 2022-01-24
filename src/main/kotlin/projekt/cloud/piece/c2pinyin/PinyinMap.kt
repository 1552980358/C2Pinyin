package projekt.cloud.piece.c2pinyin

class PinyinMap(@Suppress("MemberVisibilityCanBePrivate") val name: String) {
    
    private val pinyinList = arrayListOf<String>()
    
    @Suppress("unused")
    constructor(name: String, vararg array: String): this(name) {
        add(*array)
    }
    
    @Suppress("unused")
    constructor(name: String, list: List<String>): this(name) {
        add(list)
    }
    
    @Suppress("unused")
    fun add(vararg array: String) = pinyinList.addAll(array)
    
    @Suppress("unused")
    fun add(list: List<String>) = pinyinList.addAll(list)
    
    fun update(vararg array: String): Boolean {
        pinyinList.clear()
        return add(*array)
    }
    
    fun update(list: List<String>): Boolean {
        pinyinList.clear()
        return add(list)
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    val pinyin get() = when {
        pinyinList.isEmpty() -> EMPTY_STR
        else -> StringBuilder().also { stringBuilder -> pinyinList.forEach { stringBuilder.append(it) } }.toString()
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun getPinyin(divider: String): String {
        if (pinyinList.isEmpty()) {
            return EMPTY_STR
        }
        return StringBuilder(pinyinList.first()).also {
            for (i in 1 until pinyinList.size) {
                it.append(divider).append(pinyinList[i])
            }
        }.toString()
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun getPinyin(divider: Char = ' ') = getPinyin(divider.toString())
    
    @Suppress("unused")
    fun checkContains(string: String) = string.contains(name)
    
    @Suppress("unused")
    fun replace(string: String, divider: String) = string.replace(name, getPinyin(divider))
    
    @Suppress("unused")
    fun replace(string: String, divider: Char) = string.replace(name, getPinyin(divider))
    
    @Suppress("unused")
    fun replace(string: String) = string.replace(name, pinyin)
    
    @Suppress("unused")
    fun replaceIfContains(string: String) = when {
        checkContains(string) -> replace(string)
        else -> string
    }
    
    @Suppress("unused")
    fun replaceIfContains(string: String, divider: Char) = when {
        checkContains(string) -> replace(string, divider)
        else -> string
    }
    
    @Suppress("unused")
    fun replaceIfContains(string: String, divider: String) = when {
        checkContains(string) -> replace(string, divider)
        else -> string
    }
    
}
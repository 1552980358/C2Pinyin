package projekt.cloud.piece.c2pinyin

import java.io.File

class PinyinDictionary() {
    
    private companion object {
        const val EQUAL = '='
        const val DIVIDER = '-'
    }

    private val pinyinMaps = ArrayList<PinyinMap>()
    
    constructor(vararg pinyinMaps: PinyinMap): this() {
        add(*pinyinMaps)
    }
    
    @Suppress("unused")
    fun load(file: File) = load(file.readLines())
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun load(list: List<String>) = list.forEach { line ->
        decodeLine(line)?.let { pinyinMap -> add(pinyinMap) }
    }
    
    private fun decodeLine(string: String): PinyinMap? {
        val indexEqual = string.indexOf(EQUAL)
        if (indexEqual == -1) {
            return null
        }
        return PinyinMap(string.substring(0, indexEqual), string.substring(indexEqual + 1).split(DIVIDER))
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun add(vararg pinyinMaps: PinyinMap) = this.pinyinMaps.addAll(pinyinMaps)
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun add(pinyinMap: PinyinMap) {
        when (val index = pinyinMaps.indexOfFirst { it.name == pinyinMap.name }) {
            -1 -> pinyinMaps.add(pinyinMap)
            else -> pinyinMaps[index] = pinyinMap
        }
    }
    
    @Suppress("unused")
    fun add(name: String, vararg pinyin: String) = add(PinyinMap(name, *pinyin))
    
    @Suppress("unused")
    fun add(name: String, list: List<String>) = add(PinyinMap(name, list))
    
    @Suppress("unused")
    fun remove(index: Int) = pinyinMaps.removeAt(index)
    
    @Suppress("unused")
    fun remove(pinyinMap: PinyinMap) = pinyinMaps.remove(pinyinMap)
    
    fun contains(string: String) = pinyinMaps.indexOfFirst { it.name == string } != -1
    
    @Suppress("unused")
    fun checkContains(string: String): Boolean {
        pinyinMaps.forEach {
            if (it.checkContains(string)) {
                return true
            }
        }
        return false
    }
    
    @Suppress("unused")
    fun replaceAll(string: String): String {
        var tmp = string
        pinyinMaps.forEach {
            tmp = it.replaceIfContains(tmp)
        }
        return tmp
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun replaceAll(string: String, divider: String): String {
        var tmp = string
        pinyinMaps.forEach {
            tmp = it.replaceIfContains(tmp, divider)
        }
        return tmp
    }
    
    @Suppress("unused")
    fun replaceAll(string: String, divider: Char) = replaceAll(string, divider.toString())
    
    operator fun get(string: String): PinyinMap {
        pinyinMaps.forEach {
            if (it.name == string) {
                return it
            }
        }
        throw Exception()
    }
    
    operator fun set(name: String, pinyin: Array<String>) {
        when (val index = pinyinMaps.indexOfFirst { it.name == name }) {
            -1 -> add(name, *pinyin)
            else -> pinyinMaps[index].update(*pinyin)
        }
    }
    
    operator fun set(name: String, pinyin: List<String>) {
        when (val index = pinyinMaps.indexOfFirst { it.name == name }) {
            -1 -> add(name, pinyin)
            else -> pinyinMaps[index].update(pinyin)
        }
    }
    
}
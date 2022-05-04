package projekt.cloud.piece.c2.pinyin.dictionary

/**
 * [DictionaryItem]
 * @param text [String]
 * @param pinyin [List]<[String]>
 **/
data class DictionaryItem(val text: String, var pinyin: List<String>) {
    /**
     * Constructor
     * @param text [String]
     * @param pinyin vararg of [String]
     **/
    constructor(text: String, vararg pinyin: String): this(text, pinyin.toList())
    
    /**
     * Constructor
     * @param pair <[String], [List]<[String]>>
     **/
    constructor(pair: Pair<String, List<String>>): this(pair.first, pair.second)
    
}
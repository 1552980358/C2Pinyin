package projekt.cloud.piece.c2.pinyin.dictionary

/**
 * [Dictionary], child of [ArrayList]<[DictionaryItem]>
 *
 * Method
 *  [set]
 *  [plusAssign]
 *  [minusAssign]
 *  [add]
 *
 **/
class Dictionary internal constructor(): ArrayList<DictionaryItem>() {

    /**
     * [set]
     * @param text [String]
     * @param value [List]<[String]>
     **/
    operator fun set(text: String, value: List<String>) {
        when (val dictionaryItem = find { it.text == text }) {
            null -> this += DictionaryItem(text, value)
            else -> dictionaryItem.pinyin = value
        }
    }

    /**
     * [plusAssign]
     * @param item [Pair]<[String], [List]<[String]>>
     **/
    operator fun plusAssign(item: Pair<String, List<String>>) {
        this += DictionaryItem(item.first, item.second)
    }

    /**
     * [plusAssign]
     * @param dictionaryItem [DictionaryItem]
     **/
    operator fun plusAssign(dictionaryItem: DictionaryItem) {
        add(dictionaryItem)
    }

    /**
     * [plusAssign]
     * @param dictionaryItemList [List]<[DictionaryItem]>
     **/
    operator fun plusAssign(dictionaryItemList: List<DictionaryItem>) {
        addAll(dictionaryItemList)
    }

    /**
     * [plusAssign]
     * @param dictionaryItemList [Array]<[DictionaryItem]>
     **/
    operator fun plusAssign(dictionaryItemList: Array<DictionaryItem>) {
        addAll(dictionaryItemList)
    }

    /**
     * [minusAssign]
     * @param dictionaryItem [DictionaryItem]
     **/
    operator fun minusAssign(dictionaryItem: DictionaryItem) {
        remove(dictionaryItem)
    }
    
    /**
     * [minusAssign]
     * @param dictionaryItems [Array]<[DictionaryItem]>
     **/
    operator fun minusAssign(dictionaryItems: Array<DictionaryItem>) {
        removeAll(dictionaryItems)
    }

    /**
     * [minusAssign]
     * @param text [String]
     **/
    operator fun minusAssign(text: String) {
        removeAll { it.text == text }
    }
    
    /**
     * [minusAssign]
     * @param textList [List]<[String]>
     **/
    operator fun minusAssign(textList: List<String>) {
        textList.forEach { this -= it }
    }
    
    /**
     * [minusAssign]
     * @param texts [Array]<[String]>
     **/
    operator fun minusAssign(texts: Array<String>) {
        texts.forEach { this -= it }
    }

    /**
     * [add]
     * @param text [String]
     * @param pinyin [List]<[String]>
     * @return [DictionaryItem]
     **/
    fun add(text: String, pinyin: List<String>) = apply {
        this += DictionaryItem(text, pinyin)
    }

    /**
     * [add]
     * @param text [String]
     * @param pinyin vararg of [String]
     * @return [DictionaryItem]
     **/
    fun add(text: String, vararg pinyin: String) = apply {
        this += DictionaryItem(text, pinyin.toList())
    }
    
    /**
     * [add]
     * @param dictionaryItems vararg of [DictionaryItem]
     * @return [DictionaryItem]
     **/
    fun add(vararg dictionaryItems: DictionaryItem) = apply {
        this += dictionaryItems
    }
    
    /**
     * [add]
     * @param dictionaryItemList [List]<[DictionaryItem]>
     * @return [DictionaryItem]
     **/
    fun add(dictionaryItemList: List<DictionaryItem>) = apply {
        this += dictionaryItemList
    }
    
    /**
     * [remove]
     * @param text [String]
     * @return [DictionaryItem]
     **/
    fun remove(text: String) = apply {
        this -= text
    }
    
    /**
     * [remove]
     * @param textList [List]<[String]>
     * @return [DictionaryItem]
     **/
    fun remove(textList: List<String>) = apply  {
        this -= textList
    }
    
    /**
     * [remove]
     * @param texts vararg of [String]
     * @return [DictionaryItem]
     **/
    fun remove(vararg texts: String) = apply {
        remove(texts.toList())
    }
    
    

}
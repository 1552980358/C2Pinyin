package me.ks.chan.c2pinyin.translate

class Translator(text: String) {

    private val charStateList: List<CharState>

    init {
        if (text.isEmpty()) {
            throw IllegalArgumentException("Text must not be empty")
        }

        charStateList = text.asRawCharStateMutableList
            .apply(MutableList<CharState>::translate)
        if (!charStateList.none(CharState::isAccessed)) {
            throw IllegalStateException("Unexpected unaccessed translated state")
        }
    }

}

private inline val String.asRawCharStateMutableList: MutableList<CharState>
    get() = mapTo(ArrayList(length), CharState::Raw)

/**
 * Map non-[Accessed] elements into [CharState.Translated] with checking using contract function [CharState.isAccessed]
 **/
private fun MutableList<CharState>.translate() {
    listIterator().let { iterator ->
        iterator.forEach { charState ->
            if (!charState.isAccessed) {
                iterator.set(
                    when (val index = charState.char.pinyinIndex) {
                        null -> { CharState.Unchanged(charState.char) }
                        else -> { CharState.Translated(charState.char, index) }
                    }
                )
            }
        }
    }
}
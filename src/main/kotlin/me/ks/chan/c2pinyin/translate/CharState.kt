package me.ks.chan.c2pinyin.translate

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import me.ks.chan.c2pinyin.resources.pinyin.Pinyin

sealed class CharState {

    abstract val char: Char

    data class Raw(override val char: Char): CharState()

    internal interface Accessed

    @ConsistentCopyVisibility
    data class Translated internal constructor(
        override val char: Char, val index: Int, val pinyin: Pinyin
    ): CharState(), Accessed {

        internal constructor(char: Char, index: Int):
            this(char = char, index = index, pinyin = Pinyin[index])

    }

    data class Unchanged(override val char: Char): CharState(), Accessed

    internal val isAccessed: Boolean
        @OptIn(ExperimentalContracts::class)
        get() {
            contract {
                returns(true) implies (this@CharState is Accessed)
            }
            return (this is Accessed)
        }

}
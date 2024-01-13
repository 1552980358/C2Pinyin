package org.ks.chan.c2pinyin.dictionary

import org.ks.chan.c2pinyin.camelcase
import org.ks.chan.c2pinyin.decode.INDEX_INVALID
import org.ks.chan.c2pinyin.decode.INDEX_JUMP
import org.ks.chan.c2pinyin.decode.PinyinTable
import java.io.File

/**
 * [Dictionary]
 * @param file [File]
 * @return [Dictionary]
 *
 * Create a [Dictionary] instance with [file] with decoding
 **/
fun Dictionary(file: File): Dictionary =
    file.useLines { Dictionary(it.toList()) }

/**
 * [Dictionary]
 * @param lineList [String][List]
 * @return [Dictionary]
 *
 * Create a [Dictionary] instance with [lineList] with decoding
 **/
fun Dictionary(
    lineList: List<String>
): Dictionary {
    return Dictionary().also { dictionary ->
        lineList.forEach {
            decodeLine(dictionary, it)
        }
    }
}

class Dictionary {

    /**
     * [Dictionary.phaseList]
     **/
    private val phaseList = ArrayList<Phase>()

    /**
     * [Dictionary.wordList]
     **/
    private val wordList = ArrayList<Word>()

    /**
     * [Dictionary.allPhases]
     * @param action [kotlin.jvm.functions.Function1]
     *
     * Loop through all [phaseList] and perform [action]
     **/
    fun allPhases(action: (Phase) -> Unit) {
        if (phaseList.isNotEmpty()) {
            phaseList.forEach(action)
        }
    }

    /**
     * [Dictionary.findWord]
     * @param char [Char]
     * @return [Word]
     *
     * Find a [Word] by comparing [char] and [Word.char]
     **/
    fun findWord(char: Char): Word? {
        return when {
            wordList.isNotEmpty() -> {
                wordList.find { it.char == char }
            }
            else -> { null }
        }
    }

    /**
     * [Dictionary.plusAssign]
     * @param word [Word]
     *
     * Add or update [word] to [wordList]
     **/
    operator fun plusAssign(word: Word) {
        when (val index = wordList.indexOfFirst { it.char == word.char }) {
            INDEX_INVALID -> wordList += word
            else -> wordList[index] = word
        }
    }

    /**
     * [Dictionary.plusAssign]
     *
     * Add or update [phase] to [phaseList]
     **/
    operator fun plusAssign(phase: Phase) {
        when (val index = phaseList.indexOfFirst { it.text == phase.text }) {
            INDEX_INVALID -> phaseList += phase
            else -> phaseList[index] = phase
        }
    }

    /**
     * [Dictionary.minusAssign]
     *
     * Remove element from [wordList] or [phaseList] by checking key [string]
     **/
    operator fun minusAssign(string: String) {
        if (string.isNotBlank()) {
            when (string.length) {
                1 -> {
                    string.toCharArray()
                        .first()
                        .let { char ->
                            wordList.find { it.char == char }
                                ?.let(wordList::remove)
                        }
                }
                else -> {
                    phaseList.find { it.text == string }
                        ?.let(phaseList::remove)
                }
            }
        }
    }

}

private const val KEY_VALUE_SEPARATOR = '='
private const val INDEX_NOT_FOUND = -1
private const val PINYIN_SEPARATOR = '-'
private const val PINYIN_JUMP = "!"

/**
 * [decodeLine]
 * @param dictionary [Dictionary]
 * @param line [String]
 * @param keyValueSeparatorIndex [Int]
 *
 * Decode [line] and convert into [Word] or [Phase] for adding to [dictionary]
 **/
private fun decodeLine(
    dictionary: Dictionary,
    line: String,
    keyValueSeparatorIndex: Int = line.indexOf(KEY_VALUE_SEPARATOR),
) {
    if (keyValueSeparatorIndex != INDEX_NOT_FOUND) {
        val tableIndexList = line.substring(keyValueSeparatorIndex + 1)
            .split(PINYIN_SEPARATOR)
            .map {
                when (val pinyin = it.trim()) {
                    PINYIN_JUMP -> INDEX_JUMP
                    else -> PinyinTable.indexOf(pinyin.camelcase)
                }
            }
        val text = line.substring(0, keyValueSeparatorIndex)
            .trim()
        // Ensure all pinyin are valid
        if (tableIndexList.all { it != INDEX_INVALID }) {
            // Ensure all char(s) has/have pairing pinyin
            if (text.length == tableIndexList.size) {
                dictionary.addLine(text = text, tableIndexList = tableIndexList)
            }
        }
    }
}

/**
 * [Dictionary.addLine]
 * @param text [String]
 * @param tableIndexList [Int][List]
 *
 * Add [text] with corresponding [tableIndexList] with conversion to
 * [Word] or [Phase], into [Dictionary]
 **/
private fun Dictionary.addLine(text: String, tableIndexList: List<Int>) {
    when (text.length) {
        1 -> {
            this += Word(text.first(), tableIndexList.first())
        }
        else -> {
            this += Phase(
                text.mapIndexed { index, char ->
                    Word(char = char, tableIndex = tableIndexList[index])
                }
            )
        }
    }
}
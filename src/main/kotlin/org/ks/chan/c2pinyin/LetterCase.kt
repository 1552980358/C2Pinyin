package org.ks.chan.c2pinyin

sealed class LetterCase {

    /**
     * [LetterCase.handleCase]
     * @access Internal
     * @param camelCase [String]
     * @return [String]
     **/
    open fun handleCase(camelCase: String): String {
        return camelCase
    }

    /**
     * [LetterCase.Camel]
     *
     * Camelcase, which is default in [GlobalInstance]
     **/
    data object Camel: LetterCase()

    /**
     * [LetterCase.Upper]
     *
     * Uppercase
     **/
    @Suppress("Unused")
    data object Upper: LetterCase() {
        override fun handleCase(camelCase: String): String {
            return camelCase.uppercase()
        }
    }

    /**
     * [LetterCase.Lower]
     *
     * Lowercase
     **/
    @Suppress("Unused")
    data object Lower: LetterCase() {
        override fun handleCase(camelCase: String): String {
            return camelCase.lowercase()
        }
    }

}

/**
 * [String.camelcase]
 * @access Internal
 * @return [String]
 *
 * Convert [String] into camelcase format
 **/
internal val String.camelcase: String
    get() = when {
        length <= 1 -> uppercase()
        else -> first().uppercase() + substring(1).lowercase()
    }
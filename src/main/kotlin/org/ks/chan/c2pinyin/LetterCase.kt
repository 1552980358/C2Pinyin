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
     * Camel case, which is default in [GlobalInstance]
     **/
    data object Camel: LetterCase()

    @Suppress("Unused")
    data object Upper: LetterCase() {
        override fun handleCase(camelCase: String): String {
            return camelCase.uppercase()
        }
    }

    @Suppress("Unused")
    data object Lower: LetterCase() {
        override fun handleCase(camelCase: String): String {
            return camelCase.lowercase()
        }
    }

}
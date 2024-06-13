package me.ks.chan.c2pinyin

sealed interface LetterCase {

    sealed interface List {
        /**
         * This is for identifying this case support being applied by [C2String.list] method
         **/
    }

    sealed interface String {
        /*TODO*/
    }

    data object Uppercase: LetterCase, List, String {
        /*TODO*/
    }

    data object Lowercase: LetterCase, List, String {
        /*TODO*/
    }

    data object Camelcase: LetterCase, List, String {
        /*TODO*/
    }

    data object SnackCase: LetterCase, String {
        /*TODO*/
    }

}
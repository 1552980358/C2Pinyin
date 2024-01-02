package rough_work

import kotlin.test.Test

class PinyinTable {

    @Test
    fun handlePinyinList() {
        val pinyinTable = pinyinList.map {
            when {
                it.length <= 1 -> { it }
                else -> { it[0] + it.substring(1).lowercase() }
            }
        }
        println(pinyinTable.joinToString(separator = "."))
        println("Ling (zero) index = ${pinyinTable.indexOf("Ling")}")
        ArrayList<Int>(10).let {
            it.fill(-1)
            println(it)
        }
    }

    val pinyinList = listOf(
        "", "A", "AI", "AN", "ANG", "AO", "BA", "BAI",
        "BAN", "BANG", "BAO", "BEI", "BEN", "BENG", "BI", "BIAN", "BIAO", "BIE", "BIN", "BING",
        "BO", "BU", "CA", "CAI", "CAN", "CANG", "CAO", "CE", "CEN", "CENG", "CHA", "CHAI",
        "CHAN", "CHANG", "CHAO", "CHE", "CHEN", "CHENG", "CHI", "CHONG", "CHOU", "CHU", "CHUAI",
        "CHUAN", "CHUANG", "CHUI", "CHUN", "CHUO", "CI", "CONG", "COU", "CU", "CUAN", "CUI",
        "CUN", "CUO", "DA", "DAI", "DAN", "DANG", "DAO", "DE", "DENG", "DI", "DIA", "DIAN",
        "DIAO", "DIE", "DING", "DIU", "DONG", "DOU", "DU", "DUAN", "DUI", "DUN", "DUO", "E",
        "EI", "EN", "ER", "E^", "FA", "FAN", "FANG", "FEI", "FEN", "FENG", "FO", "FOU", "FU",
        "GA", "GAI", "GAN", "GANG", "GAO", "GE", "GEI", "GEN", "GENG", "GONG", "GOU", "GU",
        "GUA", "GUAI", "GUAN", "GUANG", "GUI", "GUN", "GUO", "HA", "HAI", "HAN", "HANG", "HAO",
        "HE", "HEI", "HEN", "HENG", "HONG", "HOU", "HU", "HUA", "HUAI", "HUAN", "HUANG", "HUI",
        "HUN", "HUO", "JI", "JIA", "JIAN", "JIANG", "JIAO", "JIE", "JIN", "JING", "JIONG",
        "JIU", "JU", "JUAN", "JUE", "JUN", "KA", "KAI", "KAN", "KANG", "KAO", "KE", "KEN",
        "KENG", "KONG", "KOU", "KU", "KUA", "KUAI", "KUAN", "KUANG", "KUI", "KUN", "KUO", "LA",
        "LAI", "LAN", "LANG", "LAO", "LE", "LEI", "LENG", "LI", "LIA", "LIAN", "LIANG", "LIAO",
        "LIE", "LIN", "LING", "LIU", "LONG", "LOU", "LU", "LUAN", "LUN", "LUO", "LV", "LVE",
        "M", "MA", "MAI", "MAN", "MANG", "MAO", "ME", "MEI", "MEN", "MENG", "MI", "MIAN",
        "MIAO", "MIE", "MIN", "MING", "MIU", "MO", "MOU", "MU", "NA", "NAI", "NAN", "NANG",
        "NAO", "NE", "NEI", "NEN", "NENG", "NG", "NI", "NIAN", "NIANG", "NIAO", "NIE", "NIN",
        "NING", "NIU", "NONG", "NOU", "NU", "NUAN", "NUO", "NV", "NVE", "O", "OU", "PA", "PAI",
        "PAN", "PANG", "PAO", "PEI", "PEN", "PENG", "PI", "PIAN", "PIAO", "PIE", "PIN", "PING",
        "PO", "POU", "PU", "QI", "QIA", "QIAN", "QIANG", "QIAO", "QIE", "QIN", "QING", "QIONG",
        "QIU", "QU", "QUAN", "QUE", "QUN", "RAN", "RANG", "RAO", "RE", "REN", "RENG", "RI",
        "RONG", "ROU", "RU", "RUAN", "RUI", "RUN", "RUO", "SA", "SAI", "SAN", "SANG", "SAO",
        "SE", "SEN", "SENG", "SHA", "SHAI", "SHAN", "SHANG", "SHAO", "SHE", "SHEI", "SHEN",
        "SHENG", "SHI", "SHOU", "SHU", "SHUA", "SHUAI", "SHUAN", "SHUANG", "SHUI", "SHUN",
        "SHUO", "SI", "SONG", "SOU", "SU", "SUAN", "SUI", "SUN", "SUO", "TA", "TAI", "TAN",
        "TANG", "TAO", "TE", "TENG", "TI", "TIAN", "TIAO", "TIE", "TING", "TONG", "TOU", "TU",
        "TUAN", "TUI", "TUN", "TUO", "WA", "WAI", "WAN", "WANG", "WEI", "WEN", "WENG", "WO",
        "WU", "XI", "XIA", "XIAN", "XIANG", "XIAO", "XIE", "XIN", "XING", "XIONG", "XIU", "XU",
        "XUAN", "XUE", "XUN", "YA", "YAN", "YANG", "YAO", "YE", "YI", "YIAO", "YIN", "YING",
        "YO", "YONG", "YOU", "YU", "YUAN", "YUE", "YUN", "ZA", "ZAI", "ZAN", "ZANG", "ZAO",
        "ZE", "ZEI", "ZEN", "ZENG", "ZHA", "ZHAI", "ZHAN", "ZHANG", "ZHAO", "ZHE", "ZHEI",
        "ZHEN", "ZHENG", "ZHI", "ZHONG", "ZHOU", "ZHU", "ZHUA", "ZHUAI", "ZHUAN", "ZHUANG",
        "ZHUI", "ZHUN", "ZHUO", "ZI", "ZONG", "ZOU", "ZU", "ZUAN", "ZUI", "ZUN", "ZUO"
    )

}
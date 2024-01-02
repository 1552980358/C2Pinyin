package rough_work.encoding

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object ByteArrayConverter {

    @OptIn(ExperimentalEncodingApi::class)
    private fun ByteArray.encode(): String {
        return Base64.encode(this)
    }

    val ByteArray.base64: String
        get() = encode()

}
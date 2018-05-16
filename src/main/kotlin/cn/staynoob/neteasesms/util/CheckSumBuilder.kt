package cn.staynoob.neteasesms.util

import java.security.MessageDigest

object CheckSumBuilder {

    private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    fun getCheckSum(appSecret: String, nonce: String, curTime: Long): String {
        return encode("SHA", appSecret + nonce + curTime)
    }

    private fun encode(algorithm: String, value: String): String {
        try {
            val messageDigest = MessageDigest
                    .getInstance(algorithm)
                    .apply {
                        update(value.toByteArray())
                    }
            return getFormattedText(messageDigest.digest())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun getFormattedText(bytes: ByteArray): String {
        val len = bytes.size
        val sb = StringBuilder(len * 2)
        for (i in 0 until len) {
            sb.append(HEX_DIGITS[bytes[i].toInt() shr 4 and 0x0f])
            sb.append(HEX_DIGITS[bytes[i].toInt() and 0x0f])
        }
        return sb.toString()
    }

}

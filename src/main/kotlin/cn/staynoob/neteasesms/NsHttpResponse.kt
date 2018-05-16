package cn.staynoob.neteasesms

data class NsHttpResponse<out T>(
        val code: Int,
        val msg: String?,
        val obj: T?
) {
    val isSuccessFul: Boolean
        get() = code in 200..299
}

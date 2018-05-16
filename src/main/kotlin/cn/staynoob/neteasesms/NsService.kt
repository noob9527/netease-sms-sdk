package cn.staynoob.neteasesms

interface NsService {
    fun sendCode(
            mobile: String,
            codeLen: Int = 4
    ): NsHttpResponse<String>

    fun sendCode(
            mobile: String,
            templateId: String,
            codeLen: Int = 4
    ): NsHttpResponse<String>

    fun verifyCode(
            mobile: String,
            code: String
    ): NsHttpResponse<Unit>
}
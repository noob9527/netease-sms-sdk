package cn.staynoob.neteasesms

class NsServiceImpl(
        private val nsClient: NsClient,
        private val nsProperties: NsProperties
) : NsService {

    override fun sendCode(mobile: String, codeLen: Int): NsHttpResponse<String> {
        val templateId = nsProperties.defaultCodeTemplateId
                ?: throw NullPointerException("defaultCodeTemplateId should not be null")
        return sendCode(mobile, templateId, codeLen)
    }

    override fun sendCode(
            mobile: String,
            templateId: String,
            codeLen: Int
    ): NsHttpResponse<String> {
        return nsClient.sendCode(mobile, templateId, codeLen).exec()
    }

    override fun verifyCode(
            mobile: String,
            code: String
    ): NsHttpResponse<Unit> {
        return nsClient.verifyCode(mobile, code).exec()
    }
}
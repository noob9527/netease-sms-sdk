package cn.staynoob.neteasesms.integration

import cn.staynoob.neteasesms.NsService
import cn.staynoob.neteasesms.autoconfigure.NsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@Disabled
@ImportAutoConfiguration(NsAutoConfiguration::class)
class NsServiceIt : IntegrationTestBase() {

    @Value("\${ns-test-phone}")
    private lateinit var nsTestPhone: String

    @Autowired
    private lateinit var service: NsService

    @Test
    @DisplayName("sms code test")
    fun smsCodeTest() {
        val codeRes = service.sendCode(nsTestPhone)
        assertThat(codeRes.isSuccessFul).isTrue()
        assertThat(codeRes.obj).isNotNull()
        val code = codeRes.obj!!
        val verifyRes = service.verifyCode(nsTestPhone, code)
        assertThat(verifyRes.isSuccessFul).isTrue()
    }
}
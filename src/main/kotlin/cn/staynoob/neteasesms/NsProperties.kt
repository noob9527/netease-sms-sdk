package cn.staynoob.neteasesms

import cn.staynoob.neteasesms.kotlin.KotlinAllOpen
import cn.staynoob.neteasesms.kotlin.NoArgConstructor
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.annotation.PostConstruct

@Validated
@KotlinAllOpen
@NoArgConstructor
@ConfigurationProperties(prefix = "netease.im.sms")
class NsProperties(
        var appKey: String?,
        var appSecret: String?,
        var defaultCodeTemplateId: String?,
        var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
) {
    var apiUrl = "https://api.netease.im/sms/"

    /**
     * somehow kotlin init logic does not execute
     */
    @Suppress("SENSELESS_COMPARISON")
    @PostConstruct
    private fun init() {
        if (apiUrl == null) apiUrl = "https://api.netease.im/sms/"
        if (logLevel == null) logLevel = HttpLoggingInterceptor.Level.NONE
    }
}
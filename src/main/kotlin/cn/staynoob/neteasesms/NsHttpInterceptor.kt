package cn.staynoob.neteasesms

import cn.staynoob.neteasesms.util.CheckSumBuilder
import cn.staynoob.neteasesms.util.generateRandomString
import okhttp3.Interceptor
import okhttp3.Response
import java.time.Instant

internal class NsHttpInterceptor(
        private val properties: NsProperties
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val appKey = properties.appKey!!
        val nonce = generateRandomString()
        val curTime = Instant.now().epochSecond
        val checkSum = CheckSumBuilder
                .getCheckSum(properties.appSecret!!, nonce, curTime)

        val request = original.newBuilder()
                .addHeader("AppKey", appKey)
                .addHeader("Nonce", nonce)
                .addHeader("CurTime", curTime.toString())
                .addHeader("CheckSum", checkSum)
                .build()

        return chain.proceed(request)
    }
}
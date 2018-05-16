package cn.staynoob.neteasesms

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NsClient {
    @POST("sendcode.action")
    @FormUrlEncoded
    fun sendCode(
            @Field("mobile") mobile: String,
            @Field("templateid") templateId: String,
            @Field("codeLen") codeLen: Int = 4
    ): Call<NsHttpResponse<String>>

    @POST("verifycode.action")
    @FormUrlEncoded
    fun verifyCode(
            @Field("mobile") mobile: String,
            @Field("code") code: String
    ): Call<NsHttpResponse<Unit>>
}
package cn.staynoob.neteasesms

import retrofit2.Call

internal fun <T> Call<out NsHttpResponse<T>>.exec(): NsHttpResponse<T> {
    val res = this.execute()
    if (!res.isSuccessful) {
        val errMsg = res.errorBody()?.string()
        val message = res.message()
        throw NsApiException(errMsg ?: message)
    }
    val body =  res.body()!!
    if (!body.isSuccessFul) {
        throw NsApiException(body.msg ?: "unknown error")
    }
    return body
}

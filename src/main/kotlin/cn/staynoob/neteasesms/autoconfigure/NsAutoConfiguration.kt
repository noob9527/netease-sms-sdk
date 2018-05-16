package cn.staynoob.neteasesms.autoconfigure

import cn.staynoob.neteasesms.*
import cn.staynoob.neteasesms.util.objectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
@AutoConfigureOrder
@EnableConfigurationProperties(NsProperties::class)
class NsAutoConfiguration(
        private val properties: NsProperties
) {

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = properties.logLevel
            }

    companion object {
        private val jacksonConverterFactory =
                JacksonConverterFactory.create(objectMapper)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun nsService(): NsService {
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(NsHttpInterceptor(properties))
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()

        val nsClient = retrofit.create(NsClient::class.java)
        return NsServiceImpl(nsClient, properties)
    }
}
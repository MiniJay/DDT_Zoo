package tw.ddt.ddt_zoo.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import tw.ddt.ddt_zoo.BuildConfig
import java.util.concurrent.TimeUnit

class DttHttpClient {

    companion object {
        private const val connectTimeOut = 30

        fun getOkHttpClient(): OkHttpClient.Builder {
            // OkHttp log
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            return OkHttpClient.Builder()
                .connectTimeout(connectTimeOut.toLong(), TimeUnit.SECONDS)
                .readTimeout(connectTimeOut.toLong(), TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
        }
    }
}
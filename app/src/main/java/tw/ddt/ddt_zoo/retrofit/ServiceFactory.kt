package tw.ddt.ddt_zoo.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter

import java.util.Date

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tw.ddt.ddt_zoo.MyApp
import tw.ddt.ddt_zoo.retrofit.service.ZooService

class ServiceFactory {
    companion object {
        fun getZooService(): ZooService {
            MyApp.zooService?.let {
                return it
            }

            val retrofit = addBaseOperation(
                Retrofit.Builder().baseUrl("https://data.taipei")
            ).build()

            return retrofit.create(ZooService::class.java)
        }

        private fun addBaseOperation(builder: Retrofit.Builder): Retrofit.Builder {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(Date::class.java, DateTypeAdapter())
            val gson = gsonBuilder.create()

            return builder
                .client(DttHttpClient.getOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }
    }
}
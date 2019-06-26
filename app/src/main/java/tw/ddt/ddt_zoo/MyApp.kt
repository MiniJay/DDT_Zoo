package tw.ddt.ddt_zoo

import android.app.Application
import tw.ddt.ddt_zoo.retrofit.ServiceFactory
import tw.ddt.ddt_zoo.retrofit.service.ZooService

class MyApp: Application() {

    companion object {
        var zooService: ZooService? = null
    }

    override fun onCreate() {
        super.onCreate()
        zooService = ServiceFactory.getZooService()
    }
}
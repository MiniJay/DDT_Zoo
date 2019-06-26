package tw.ddt.ddt_zoo.retrofit.service

import io.reactivex.Observable
import retrofit2.http.GET
import tw.ddt.ddt_zoo.model.HomeModel
import tw.ddt.ddt_zoo.model.plant.PlantModel

interface ZooService {
    @GET("/opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getCategory(): Observable<HomeModel>

    @GET("/opendata/datalist/apiAccess?scope=resourceAquire&rid=f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlant(): Observable<PlantModel>
}
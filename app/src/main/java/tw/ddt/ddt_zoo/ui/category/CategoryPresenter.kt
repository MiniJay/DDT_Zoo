package tw.ddt.ddt_zoo.ui.category

import android.os.Bundle
import io.reactivex.observers.DisposableObserver
import tw.ddt.ddt_zoo.model.HomeModel
import tw.ddt.ddt_zoo.model.plant.PlantModel
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults
import tw.ddt.ddt_zoo.retrofit.ZooApiClient

class CategoryPresenter(bundle: Bundle?, private val categoryView: CategoryView) {

    init {
        categoryView.updateTopUI(bundle?.getParcelable("data"))
    }

    fun updateActionbarTitle(bundle: Bundle?) {
        val homeData: HomeModel.Result.Results? = bundle?.getParcelable("data")
        homeData?.E_Name?.let { categoryView.updateActionbarTitle(it) }
    }

    fun queryPlantAPI() {
        ZooApiClient.getPlantResult().subscribe(object : DisposableObserver<PlantModel>() {

            override fun onComplete() {

            }

            override fun onNext(t: PlantModel) {
                var plantRebuildModel = PlantRebuildModel()
                t?.let {
                    plantRebuildModel.result.count = t.result.count

                    val results = t.result.results
                    var rebuildObject: PlantResults? = null
                    for (i in 0 until results.size) {
                        val source = results[i]

                        if (!source.F_Name_En.isNullOrEmpty()) {
                            if (rebuildObject != null) {
                                val insertData = rebuildObject
                                plantRebuildModel.result.results.add(insertData)
                            }
                            rebuildObject = PlantResults()

                            rebuildObject.F_Pic01_URL = source.F_Pic01_URL.toString()
                            rebuildObject.F_Name_Ch = source.F_Name_Ch.toString()
                            rebuildObject.F_Name_En = source.F_Name_En.toString()
                            rebuildObject.F_AlsoKnown = source.F_AlsoKnown.toString()
                            rebuildObject.F_Brief = source.F_Brief.toString()
                            rebuildObject.F_Feature = source.F_Feature.toString()
                            rebuildObject.F_Function = source.F_Function.toString()
                            rebuildObject.F_Update = source.F_Update.toString()
                        } else {
                            source.F_AlsoKnown?.let {
                                if (it.startsWith("http")) {
                                    rebuildObject?.F_Pic01_URL = it
                                }
                            }

                            source.F_Pic04_URL?.let {
                                rebuildObject?.F_Update = it
                            }

                            source.F_Name_Ch.let {
                                rebuildObject?.F_Function = rebuildObject?.F_Function + "\n" + it
                            }
                        }
                    }
                }
                categoryView.updateCell(plantRebuildModel)
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    interface CategoryView {
        fun updateActionbarTitle(value: String)
        fun updateTopUI(data: HomeModel.Result.Results?)
        fun updateCell(data: PlantRebuildModel?)
        fun itemClick(data: PlantResults)
    }
}
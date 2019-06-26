package tw.ddt.ddt_zoo.ui.home

import android.util.Log
import io.reactivex.observers.DisposableObserver
import tw.ddt.ddt_zoo.retrofit.ZooApiClient
import tw.ddt.ddt_zoo.model.HomeModel

class HomePresenter(private val homeView: HomeView) {
    fun questAPI() {
        ZooApiClient.getCategoryResult().subscribe(object: DisposableObserver<HomeModel>(){

            override fun onComplete() {
                Log.d("API_Category", "onComplete")
            }

            override fun onNext(t: HomeModel) {
                Log.d("API_Category", "onNext")
                loadData(t)
            }

            override fun onError(e: Throwable) {
                Log.d("API_Category", "onError")
            }
        })
    }

    fun loadData(data: HomeModel? ) {
        homeView.updateCell(data)
    }

    interface HomeView {
        fun updateCell(data: HomeModel?)
        fun itemClick(data: HomeModel.Result.Results)
    }
}
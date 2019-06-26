package tw.ddt.ddt_zoo.retrofit

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ObservableUtils {
    companion object {
        fun <T> schedulersHandling(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable.retry(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
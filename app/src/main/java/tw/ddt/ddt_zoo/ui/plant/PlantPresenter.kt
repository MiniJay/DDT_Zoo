package tw.ddt.ddt_zoo.ui.plant

import android.os.Bundle
import tw.ddt.ddt_zoo.model.plant.PlantResults

class PlantPresenter(private var bundle: Bundle?, private val plantView: PlantView) {

    init {
        plantView.updateTopUI(bundle?.getParcelable("data"))
    }

    fun updateActionbarTitle(bundle: Bundle?) {
        var plantRebuildModel: PlantResults? = bundle?.getParcelable("data")

        plantRebuildModel?.F_Name_Ch?.let { plantView.updateActionbarTitle(it) }
    }

    interface PlantView {
        fun updateActionbarTitle(value: String)
        fun updateTopUI(data: PlantResults?)
    }
}
package tw.ddt.ddt_zoo.model.plant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantResult(
    var count: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    var results: MutableList<PlantResults> = mutableListOf(),
    val sort: String = ""
) : Parcelable
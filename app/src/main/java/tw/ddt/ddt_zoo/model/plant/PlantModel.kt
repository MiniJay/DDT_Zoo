package tw.ddt.ddt_zoo.model.plant

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantModel(
    val result: PlantResult = PlantResult()
) : Parcelable
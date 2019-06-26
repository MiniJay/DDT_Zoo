package tw.ddt.ddt_zoo.model.plant

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantResults(
    var F_AlsoKnown: String? = "",
    var F_Brief: String? = "",
//            val F_CID: Any = Any(),
    val F_Code: String = "",
    val F_Family: String = "",
    var F_Feature: String? = "",
    @SerializedName("F_Function＆Application")
    var F_Function: String? = "",
    val F_Genus: String = "",
    val F_Geo: String = "",
    val F_Keywords: String = "",
    val F_Location: String = "",
    var F_Name_Ch: String? = "",
    var F_Name_En: String? = "",
    val F_Name_Latin: String = "",
    val F_Pic01_ALT: String? = "",
    var F_Pic01_URL: String? = "",
    val F_Pic02_ALT: String = "",
    val F_Pic02_URL: String = "",
    val F_Pic03_ALT: String = "",
    val F_Pic03_URL: String = "",
    val F_Pic04_ALT: String = "",
    val F_Pic04_URL: String = "",
    val F_Summary: String = "",
    var F_Update: String? = "",
//            val F_Vedio_URL: Any = Any(),
//            val F_Voice01_ALT: Any = Any(),
//            val F_Voice01_URL: Any = Any(),
//            val F_Voice02_ALT: Any = Any(),
//            val F_Voice02_URL: Any = Any(),
//            val F_Voice03_ALT: Any = Any(),
//            val F_Voice03_URL: Any = Any(),
//            val F_pdf01_ALT: String = "",
//            val F_pdf01_URL: Any = Any(),
//            val F_pdf02_ALT: Any = Any(),
//            val F_pdf02_URL: Any = Any(),
    val _id: Int = 0
) : Parcelable
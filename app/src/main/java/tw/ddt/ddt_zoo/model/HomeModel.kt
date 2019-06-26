package tw.ddt.ddt_zoo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HomeModel(val result: Result) : Parcelable {

    @Parcelize
    data class Result(
        val limit : Int = 0,
        val offset : Int = 0,
        val count : Int = 0,
        val sort : String = "",
        val results : List<Results> = mutableListOf()
    ) : Parcelable {

        @Parcelize
        data class Results(
            val E_Pic_URL : String = "",
            val E_Geo : String = "",
            val E_Info : String = "",
            val E_no : Int = 0,
            val E_Category : String = "",
            val E_Name : String = "",
            val E_Memo : String = "",
            val _id : Int = 0,
            val E_URL : String = ""
        ) : Parcelable
    }
}
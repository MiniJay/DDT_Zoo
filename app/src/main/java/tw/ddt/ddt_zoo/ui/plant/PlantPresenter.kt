package tw.ddt.ddt_zoo.ui.plant

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import tw.ddt.ddt_zoo.GlideApp
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.plant.PlantResults

class PlantPresenter(private val context: Context?) {

    fun updateActionbarTitle(bundle: Bundle?) {
        var plantRebuildModel: PlantResults? = bundle?.getParcelable("data")

        context?.let {
            (it as AppCompatActivity).supportActionBar?.title = plantRebuildModel?.F_Name_Ch
        }
    }

    fun setUI(bundle: Bundle?, pic: ImageView, chName: TextView, enName: TextView
              , alsoKnown: TextView, brief: TextView, feature: TextView, application: TextView, time: TextView) {
        var plantRebuildModel: PlantResults? = bundle?.getParcelable("data")

        context?.let {
            GlideApp.with(it)
                .load(plantRebuildModel?.F_Pic01_URL)
                .placeholder(R.drawable.ic_loading)
                .into(pic)
        }
        chName.text = plantRebuildModel?.F_Name_Ch
        enName.text = plantRebuildModel?.F_Name_En
        alsoKnown.text = plantRebuildModel?.F_AlsoKnown
        brief.text = plantRebuildModel?.F_Brief
        feature.text = plantRebuildModel?.F_Feature
        application.text = plantRebuildModel?.F_Function
        time.text = "最後更新時間：${plantRebuildModel?.F_Update}"
    }
}
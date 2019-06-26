package tw.ddt.ddt_zoo.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import tw.ddt.ddt_zoo.GlideApp
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.plant.PlantResults

class PlantFragment : Fragment(), PlantPresenter.PlantView {
    override fun updateTopUI(data: PlantResults?) {
        context?.let {
            GlideApp.with(it)
                .load(data?.F_Pic01_URL)
                .placeholder(R.drawable.ic_loading)
                .into(pic)
        }
        chName.text = data?.F_Name_Ch
        enName.text = data?.F_Name_En
        alsoKnown.text = data?.F_AlsoKnown
        brief.text = data?.F_Brief
        feature.text = data?.F_Feature
        apllication.text = data?.F_Function
        time.text = "最後更新時間：${data?.F_Update}"
    }

    override fun updateActionbarTitle(value: String) {
        (activity as AppCompatActivity).supportActionBar?.title = value
    }

    private lateinit var plantPresenter: PlantPresenter

    private lateinit var pic: ImageView
    private lateinit var chName: TextView
    private lateinit var enName: TextView
    private lateinit var alsoKnown: TextView
    private lateinit var brief: TextView
    private lateinit var feature: TextView
    private lateinit var apllication: TextView
    private lateinit var time: TextView

    private var root:View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (root == null) {

            root = inflater.inflate(R.layout.fragment_plant, container, false)

            root?.let {
                pic = it.findViewById(R.id.pic_image)
                chName = it.findViewById(R.id.textView11)
                enName = it.findViewById(R.id.textView12)
                alsoKnown = it.findViewById(R.id.textView14)
                brief = it.findViewById(R.id.textView16)
                feature = it.findViewById(R.id.textView18)
                apllication = it.findViewById(R.id.textView20)
                time = it.findViewById(R.id.textView21)
            }

            plantPresenter = PlantPresenter(arguments, this)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        plantPresenter.updateActionbarTitle(arguments)
    }
}
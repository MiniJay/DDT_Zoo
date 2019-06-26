package tw.ddt.ddt_zoo.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import tw.ddt.ddt_zoo.R

class PlantFragment : Fragment() {

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
            plantPresenter = PlantPresenter(context)

            root = inflater.inflate(R.layout.fragment_plant, container, false)
            initView(root!!)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        plantPresenter.updateActionbarTitle(arguments)
    }

    private fun initView(root: View) {
        pic =root.findViewById(R.id.pic_image)
        chName = root.findViewById(R.id.textView11)
        enName = root.findViewById(R.id.textView12)
        alsoKnown = root.findViewById(R.id.textView14)
        brief = root.findViewById(R.id.textView16)
        feature = root.findViewById(R.id.textView18)
        apllication = root.findViewById(R.id.textView20)
        time = root.findViewById(R.id.textView21)

        plantPresenter.setUI(arguments, pic, chName, enName, alsoKnown, brief, feature, apllication, time)
    }
}
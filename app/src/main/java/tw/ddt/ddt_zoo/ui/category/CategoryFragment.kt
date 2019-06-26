package tw.ddt.ddt_zoo.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults

class CategoryFragment : Fragment(), CategoryPresenter.CategoryView {
    override fun updateCell(data: PlantRebuildModel?) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun itemClick(data: PlantResults) {
        val bundle = Bundle()
        bundle.putParcelable("data", data)
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.nav_plant, bundle)
    }

    private lateinit var adapter: CategoryAdapter
    private lateinit var presenter: CategoryPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var pic: ImageView
    private lateinit var info: TextView
    private lateinit var memo: TextView
    private lateinit var category: TextView
    private lateinit var web: TextView

    private var root:View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (root == null) {
            presenter = CategoryPresenter(context, this)
            // initView
            root = inflater.inflate(R.layout.fragment_category, container, false)
            initView(root!!)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.updateActionbarTitle(arguments)
    }

    private fun initView(root: View) {
        recyclerView = root.findViewById(R.id.recyclerView1)
        progressBar = root.findViewById(R.id.progressBar)
        pic = root.findViewById(R.id.pic_image)
        info = root.findViewById(R.id.textView5)
        memo = root.findViewById(R.id.textView6)
        category = root.findViewById(R.id.textView7)
        web = root.findViewById(R.id.textView8)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)

        presenter.setTopUI(arguments, pic, info, memo, category, web)

        presenter.queryPlantAPI()
    }

}
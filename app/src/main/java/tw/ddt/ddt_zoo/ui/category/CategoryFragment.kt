package tw.ddt.ddt_zoo.ui.category

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.ddt.ddt_zoo.GlideApp
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.HomeModel
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults

class CategoryFragment : Fragment(), CategoryPresenter.CategoryView {
    override fun updateTopUI(data: HomeModel.Result.Results?) {
        context?.let {
            GlideApp.with(it)
                .load(data?.E_Pic_URL)
                .placeholder(R.drawable.ic_loading)
                .into(pic)
        }
        info.text = data?.E_Info
        if (data?.E_Memo!!.isEmpty()) {
            memo.text = "無休館資訊"
        } else {
            memo.text = data?.E_Memo
        }
        category.text = data?.E_Category

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            web.text = Html.fromHtml("<a href=\"${data?.E_URL}\">在網頁開啟</a>", Html.FROM_HTML_MODE_LEGACY)
        } else {
            web.text = Html.fromHtml("<a href=\"${data?.E_URL}\">在網頁開啟</a>")
        }
        web.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun updateActionbarTitle(value: String) {
        (activity as AppCompatActivity).supportActionBar?.title = value
    }

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

            root = inflater.inflate(R.layout.fragment_category, container, false)

            root?.let {
                recyclerView = it.findViewById(R.id.recyclerView1)
                progressBar = it.findViewById(R.id.progressBar)
                pic = it.findViewById(R.id.pic_image)
                info = it.findViewById(R.id.textView5)
                memo = it.findViewById(R.id.textView6)
                category = it.findViewById(R.id.textView7)
                web = it.findViewById(R.id.textView8)
            }

            initView()
            presenter = CategoryPresenter(arguments, this)
            presenter.queryPlantAPI()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.updateActionbarTitle(arguments)
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }

}
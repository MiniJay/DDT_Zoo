package tw.ddt.ddt_zoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.HomeModel
import androidx.recyclerview.widget.DividerItemDecoration

class HomeFragment : Fragment(), HomePresenter.HomeView {
    override fun updateCell(data: HomeModel?) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun itemClick(data: HomeModel.Result.Results) {
        val bundle = Bundle()
        bundle.putParcelable("data", data)
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.nav_category, bundle)
    }

    private lateinit var homePresenter: HomePresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: HomeAdapter

    private var root:View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_home, container, false)
            root?.let {
                recyclerView = it.findViewById(R.id.recyclerView1)
                progressBar = it.findViewById(R.id.progressBar)
            }

            initView()
            homePresenter = HomePresenter(this)
            homePresenter.questAPI()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        activity?.actionBar?.title = getString(R.string.menu_home)
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(this)
        recyclerView.adapter = adapter
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }
}
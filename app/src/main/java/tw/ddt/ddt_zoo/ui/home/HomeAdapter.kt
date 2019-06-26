package tw.ddt.ddt_zoo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.ddt.ddt_zoo.GlideApp
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.HomeModel

class HomeAdapter(private val listener: HomePresenter.HomeView) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: HomeModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_category, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        data?.let {
            return it.result.count
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let {
            holder.bind(it.result.results[position])
        }
    }

    fun setData(data: HomeModel?) {
        this.data = data
    }

    inner class ViewHolder(itemView: View, listener: HomePresenter.HomeView) : RecyclerView.ViewHolder(itemView) {
        private val picImage: ImageView = itemView.findViewById(R.id.pic_image)
        private val titleText: TextView = itemView.findViewById(R.id.textView2)
        private val infoText: TextView = itemView.findViewById(R.id.textView3)
        private val memoText: TextView = itemView.findViewById(R.id.textView4)

        init {
            itemView.setOnClickListener {
                listener.itemClick(itemView.tag as HomeModel.Result.Results)
            }
        }

        fun bind(data: HomeModel.Result.Results) {
            itemView.tag = data

            GlideApp.with(itemView.context)
                .load(data.E_Pic_URL)
                .placeholder(R.drawable.ic_loading)
                .into(picImage)

            titleText.text = data.E_Name
            infoText.text = data.E_Info
            if (data.E_Memo.isEmpty()) {
                memoText.text = "無休館資訊"
            } else {
                memoText.text = data.E_Memo
            }
        }
    }
}
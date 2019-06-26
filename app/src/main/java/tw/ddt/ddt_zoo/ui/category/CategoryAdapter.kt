package tw.ddt.ddt_zoo.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tw.ddt.ddt_zoo.GlideApp
import tw.ddt.ddt_zoo.R
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults

class CategoryAdapter(private val categoryView: CategoryPresenter.CategoryView) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var plantModel: PlantRebuildModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_plant, parent, false)
        return ViewHolder(view, categoryView)
    }

    override fun getItemCount(): Int {
        plantModel?.let {
            return it.result.count
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        plantModel?.let {
            holder.bind(it.result.results[position])
        }
    }

    fun setData(plantModel: PlantRebuildModel?) {
        this.plantModel = plantModel
    }

    class ViewHolder(itemView: View, categoryView: CategoryPresenter.CategoryView) : RecyclerView.ViewHolder(itemView){

        private val pic: ImageView = itemView.findViewById(R.id.pic_image)
        private val name: TextView = itemView.findViewById(R.id.textView2)
        private val content: TextView = itemView.findViewById(R.id.textView3)

        init {
            itemView.setOnClickListener {
                categoryView.itemClick(itemView.tag as PlantResults)
            }
        }

        fun bind(result: PlantResults) {
            itemView.tag = result

            GlideApp.with(itemView.context)
                .load(result.F_Pic01_URL)
                .placeholder(R.drawable.ic_loading)
                .into(pic)
            name.text = result.F_Name_Ch
            content.text = result.F_AlsoKnown
        }
    }
}
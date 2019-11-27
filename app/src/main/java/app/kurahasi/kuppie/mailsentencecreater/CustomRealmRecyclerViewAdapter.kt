package app.kurahasi.kuppie.mailsentencecreater

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.kurahasi.kuppie.mailsentencecreater.databinding.CellLayoutBinding
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.cell_layout.view.*

class CustomRealmRecyclerViewAdapter(private val context: Context)
    : RecyclerView.Adapter<CustomRealmRecyclerViewAdapter.CustomViewHolder>() {
    private val itemlist = ArrayList<PlanModel>()

    override fun getItemCount(): Int = itemlist.size


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_layout, parent, false)
        return CustomViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, position: Int) {
        viewHolder.binding.textView.text = itemlist[position].time
        viewHolder.binding.textView2.text = itemlist[position].place
        viewHolder.binding.textView3.text = itemlist[position].content

    }

    class CustomViewHolder(val binding: CellLayoutBinding): RecyclerView.ViewHolder(binding.root)

    fun additem(item:PlanModel){
        itemlist.add(item)
    }

    fun clear(){
        itemlist.clear()
    }
}

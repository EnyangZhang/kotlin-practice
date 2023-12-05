package nz.ac.uclive.ezh15.seng440_term1_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var items: List<Item>, private val onItemListener: OnItemListener) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, val onItemListener: OnItemListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var wishItem: TextView
        var price: TextView
        var description: TextView

        init {
            wishItem = itemView.findViewById(R.id.wish_item)
            price = itemView.findViewById(R.id.price)
            description = itemView.findViewById(R.id.description)

            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            onItemListener.onItemClick(adapterPosition)

        }
    }

override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
    val v = LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.card_view, viewGroup, false)
    return ViewHolder(v, onItemListener)
}


override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    viewHolder.wishItem.text = items[position].toString()
    viewHolder.price.text = items[position].price
    viewHolder.description.text = items[position].description

}
    fun setData(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    interface OnItemListener {
        fun onItemClick(position: Int)
    }
}
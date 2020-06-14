package com.example.doodlebluetask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.item_stores_layout.view.*


class StoresAdapter constructor(private val listOfStore: MutableList<Store>) :
    RecyclerView.Adapter<StoresAdapter.StoresViewHolder>() {


    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {

        holder.foodName.text = listOfStore[position].foodName
        holder.foodDescription.text = listOfStore[position].foodDescription
//        holder.count.text = listOfStore[position].count.toString()
    }

    private val TAG = StoresAdapter::class.java.simpleName

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stores_layout, parent, false)
        return StoresViewHolder(view)

    }


    var onItemClick: ((String) -> Unit)? = null

    inner class StoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foodName: TextView = itemView.dish_name
        val foodDescription: TextView = itemView.dish_desc
        val count: ElegantNumberButton = itemView.add_button

        init {


        }


    }

    override fun getItemCount(): Int {

        return listOfStore.size
    }


    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int, viewId: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType, it.id)
        }
        return this
    }


}


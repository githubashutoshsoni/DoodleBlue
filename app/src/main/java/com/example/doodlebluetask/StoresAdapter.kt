package com.example.doodlebluetask

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_stores_layout.view.*


class StoresAdapter constructor(private var listOfStore: MutableList<Store>) :
    RecyclerView.Adapter<StoresAdapter.StoresViewHolder>() {


    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {

        holder.foodName.text = listOfStore[position].foodName
        holder.foodDescription.text = listOfStore[position].foodDescription
        holder.count.number= listOfStore[position].count.toString()
        holder.count.setNumber(listOfStore[position].count.toString(), true)
    }

    private val TAG = StoresAdapter::class.java.simpleName

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stores_layout, parent, false)
        return StoresViewHolder(view)

    }


    fun setList(listOfStore: MutableList<Store>) {
        this.listOfStore.clear()
        this.listOfStore = listOfStore
        notifyDataSetChanged()
    }


    var onItemClick: ((Store, String) -> Unit)? = null

    inner class StoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foodName: TextView = itemView.dish_name
        val foodDescription: TextView = itemView.dish_desc
        val count: ElegantNumberButton = itemView.add_button

        init {


            count.setOnElegantClickListener(object : ElegantNumberButton.OnClickListener {
                override fun onClick(view: View?) {

                    onItemClick?.invoke(listOfStore[adapterPosition], count.number)

                }

            })

        }


    }

    override fun getItemCount(): Int {

        return listOfStore.size
    }


}


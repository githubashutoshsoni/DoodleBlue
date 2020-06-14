package com.example.doodlebluetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cart.*
import java.util.ArrayList

class CartActivity : AppCompatActivity(), SoreAdapterJava.updateOrderItem {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val adapter = SoreAdapterJava(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        viewModel.list.observe(this, Observer {

            var filteredList: MutableList<Store> = mutableListOf<Store>()
            for (item in it) {

                if (item.count > 0) {
                    filteredList.add(item)
                }
            }

            adapter.setArrayList(filteredList as ArrayList<Store>)

            adapter.showTwoItems(false)

        })

        show_more.setOnClickListener {

            adapter.showTwoItems(true)
            show_more.visibility = View.INVISIBLE

        }


    }

    override fun updateItemAtPos(store: Store?, count: Int) {
        viewModel.updateOrder(store!!.foodName, count)
    }
}
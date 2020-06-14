package com.example.doodlebluetask

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    lateinit var listOfStores: ArrayList<Store>

    private val viewModel: MainViewModel by viewModels()

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(tool_bar)





        app_bar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (abs(verticalOffset) - appBarLayout!!.totalScrollRange == 0) {
                    //  Collapsed
                    collapasing_toolbar.title = "Doodle blue"
                    collapasing_toolbar.setCollapsedTitleTextColor(
                        resources.getColor(
                            R.color.white_50,
                            null
                        )
                    )

                } else {
                    //Expanded
                    collapasing_toolbar.title = ""

                }
            }

        })

        listOfStores = ArrayList();
        listOfStores.add(Store("Paneer Butter masala", "Amazing dish by amazing people"))
        listOfStores.add(Store("Omelete", "A big amazing round omelete"))
        listOfStores.add(Store("Fried Chicken", "Big big chicken all to devour"))
        listOfStores.add(Store("French Fries", "Crispy water mouth with ketchup"))
        listOfStores.add(Store("Paneer2 Butter masala", "Amazing dish by amazing people"))
        listOfStores.add(Store("Omelete2", "A big amazing round omelete"))
        listOfStores.add(Store("Fried Chicken2", "Big big chicken all to devour"))
        listOfStores.add(Store("French Fries2", "Crispy water mouth with ketchup"))




        viewModel.insertTasks(listOfStores)

//        viewModel.getAllList.observe(this, Observer {
//
//            if (it != null) {
//
//                Log.d(TAG, "onCreate: i have a list ready")
//
//            }

//        })

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = StoresAdapter(listOfStore = listOfStores)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}
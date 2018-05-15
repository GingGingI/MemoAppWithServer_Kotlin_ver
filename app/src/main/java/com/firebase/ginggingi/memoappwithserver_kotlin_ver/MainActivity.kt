package com.firebase.ginggingi.memoappwithserver_kotlin_ver

/** [[[[[[[[[[[[[[[[[[[[import]]]]]]]]]]]]]]]]]]]]]*/

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.DataModels.MemoDataModel
import org.json.JSONArray
/** [[[[[[[[[[[[[[[[[[[[end]]]]]]]]]]]]]]]]]]]]]*/

class MainActivity : AppCompatActivity() {

    private val Tag : String = "MainActivity"

    var JArr : JSONArray? = null

    var MDModelList : ArrayList<MemoDataModel> = ArrayList()
    var MDModel : MemoDataModel? = null

//    var adapter: 리사이클러뷰 어댑터

    lateinit var rv: RecyclerView
    lateinit var lm: LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun ViewInit() {

    }

    private fun getJsons() {

    }
}

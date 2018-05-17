package com.firebase.ginggingi.memoappwithserver_kotlin_ver

/** [[[[[[[[[[[[[[[[[[[[import]]]]]]]]]]]]]]]]]]]]]*/

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.View
import android.widget.Button
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.Adapter.MRecyclerViewAdaper
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.DataModels.MemoDataModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/** [[[[[[[[[[[[[[[[[[[[end]]]]]]]]]]]]]]]]]]]]]*/

class MainActivity : ActivityBase(), View.OnClickListener {

    private val Tag : String = "MainActivity"

    var JArr : JSONArray? = null

    lateinit var adapter : MRecyclerViewAdaper

    var MDModelList : ArrayList<MemoDataModel> = ArrayList()
    var MDModel : MemoDataModel? = null

//    var adapter: 리사이클러뷰 어댑터

    lateinit var rv: RecyclerView
    lateinit var lm: LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewInit()
        getJsons()
    }

    private fun ViewInit() {
        adapter = MRecyclerViewAdaper()
        lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        findViewById<Button>(R.id.AddMemoBtn).setOnClickListener(this)

        rv = findViewById(R.id.RecyclerView)
        rv.layoutManager = lm
        rv.adapter = adapter
    }

    private fun getJsons() {
        //값이있을경우 제거함
        if (JArr != null) {JArr = JSONArray()}
        if (MDModelList != null) {MDModelList = ArrayList() }
        GJson.InitDatas(URLMaker(0), this)
        GJson.ParseJsonFromUrl()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.AddMemoBtn ->{
                sendToAddView()
            }
        }
    }

    private fun sendToAddView() {
        val intent = Intent(this, MemoAddActivity::class.java)
        startActivity(intent)
    }

    fun CompleteToGetJson(){
        makeJson(GJson.getResult())

        for(i in 0..(JArr!!.length() - 1)) {
            MDModel = MemoDataModel()
            try{
                val idx = JArr!!.getJSONObject(i).getInt("idx")
                val time = JArr!!.getJSONObject(i).getString("Time")
                val title = JArr!!.getJSONObject(i).getString("Title")
                val content = JArr!!.getJSONObject(i).getString("Content")

                MDModel!!.setDatas(idx,time,title,content)
                MDModelList.add(MDModel!!)
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
        adapter.setItems(MDModelList)
        adapter.notifyDataSetChanged()
    }

    fun makeJson(result: String?){
        val JObject = JSONObject(result)
        JArr = JObject.getJSONArray(getString(R.string.JsonName))
    }
}

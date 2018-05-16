package com.firebase.ginggingi.memoappwithserver_kotlin_ver.ConnServer

import android.os.AsyncTask
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.Interfaces.GetJsonModel
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.MainActivity
import org.json.JSONArray

/**
 * Created by GingGingI on 2018-05-16.
 */
class GetJson: GetJsonModel {

    private val TAG:String = "GETJSON"
    private lateinit var mActivity: MainActivity
    protected lateinit var url: String
    protected var JsonArr: JSONArray? = null

    override fun InitDatas(url: String, mActivity: MainActivity) {
        this.mActivity = mActivity
        this.url = url
    }

    override fun ParseJsonFromUrl() {
        val JTask: GetJsonTask = GetJsonTask()
        JTask.execute()
    }

    override fun GiveJsonArr() {
        mActivity.JArr = JsonArr
        mActivity.
    }

    private class GetJsonTask: AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {

        }

    }
}
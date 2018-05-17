package com.firebase.ginggingi.memoappwithserver_kotlin_ver.ConnServer

import android.util.Log
import android.widget.Toast
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.Interfaces.GetJsonModel
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.MainActivity

/**
 * Created by GingGingI on 2018-05-16.
 */
class GetJson: GetJsonModel {

    private val TAG:String = "GETJSON"
    private lateinit var SConn: ServerConnecter
    private lateinit var mActivity: MainActivity
    protected lateinit var url: String
    var TimeWaited: Double = 0.0

    override fun InitDatas(url: String, mActivity: MainActivity) {
        this.mActivity = mActivity
        this.url = url
    }

    override fun ChangeURL(url: String) {
        this.url = url
    }

    override fun ParseJsonFromUrl() {
        SConn = ServerConnecter(url)
        onParsing()
    }

    override fun ParseJsonFromUrl(datas: String) {
        SConn = ServerConnecter(url, datas)
        onParsing()
    }

    fun onParsing(){
        GiveJsonArr()
    }

    fun getResult(): String? {
        return SConn.returnDatas()
    }

    override fun GiveJsonArr() {
//        다받아와질때까지 while을돌려서 시간을잼
        while (!SConn.isComplete) {
            Thread.sleep(100)
            TimeWaited += 0.1
            Log.i(TAG, String.format("%.1f", TimeWaited) + "...${SConn.isComplete} || ${SConn.isGetError}")
        }
        if (SConn.isComplete) {
            if (SConn.isGetError){
                Toast.makeText(mActivity, "Error founded try again", Toast.LENGTH_LONG)
            }else {
                mActivity.CompleteToGetJson()
            }
        }
//        다받아와지면 complete초기화
        SConn.isComplete = false
    }

}
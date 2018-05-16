package com.firebase.ginggingi.memoappwithserver_kotlin_ver.ConnServer

import android.content.Context
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by GingGingI on 2018-05-16.
 */
class ServerConnecter {
    private var context: Context
    private var ServerTask: ServerConnTask
    var url: String
    var datas: String? = null

    init {
        ServerTask = ServerConnTask()
    }

    constructor(context: Context, url: String) {
        this.context = context
        this.url = url
    }

    constructor(context: Context, url: String, datas: String){
        this.context = context
        this.url = url
        this.datas = datas
    }

    private class ServerConnTask: AsyncTask<Void, Void, String>() {

        lateinit var url: String
        var datas: String? = null

        var br: BufferedReader? = null
        var urlConn: HttpURLConnection? = null
        var Is: InputStream? = null

        fun setDatas(url: String, datas: String?) {
            this.url = url
            this.datas = datas
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: Void?): String {
            var result: String?
            if (datas.equals(null)) {
                getJson()
            }else{
                SetData(datas)
            }

            return "200\n"
        }

        fun openConnecter() {
                val HttpURL: URL = URL(url)
                urlConn = HttpURL.openConnection() as HttpURLConnection
                Is = urlConn!!.inputStream
        }

        fun getJson(): String? {
            var result: String? = null

            return result
        }

        fun SetData(datas: String?):String? {
            var result: String? = null

            return result
        }

        fun closeConnecter() {

        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }
}
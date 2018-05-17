package com.firebase.ginggingi.memoappwithserver_kotlin_ver.ConnServer

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by GingGingI on 2018-05-16.
 */
class ServerConnecter {

    private lateinit var ServerTask: ServerConnTask

    private var url: String
    private var result: String? = null
    open var datas: String? = null
    open var isComplete = false
    open var isGetError = false

    constructor(url: String) {
        this.url = url
        getDatas()
    }

    constructor(url: String, datas: String){
        this.url = url
        this.datas = datas
        getDatas()
    }

    fun getDatas() {
        ServerTask = ServerConnTask(this, url, datas)
        ServerTask.execute()
    }

    fun returnDatas(): String? {
        return result
    }

    private class ServerConnTask(SConn: ServerConnecter, url: String, datas: String?) : AsyncTask<Void, Void, String>() {
        var isWorking = false

        var url: String
        var SConn: ServerConnecter
        var datas: String?

        lateinit var br: BufferedReader
        lateinit var urlConn: HttpURLConnection

        init {
            this.SConn = SConn
            this.url = url
            this.datas = datas
        }

        override fun onPreExecute() {
            super.onPreExecute()
            isWorking = true
        }

        override fun doInBackground(vararg params: Void?): String? {
            var result: String? = null
//            서버에서 Json파일을 긁어오고 이를 출력함
            try {
                openConnecter()

                result = getJson()
            }catch (e: MalformedURLException) {
                SConn.isGetError = true
                e.printStackTrace()
            }finally {
                closeConnecter()
            }
            //onpostExecute 실행안댐..?
            SConn.result = result
            SConn.isComplete = true
            isWorking = false
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.i("onPost", "it is called after Thread end")
        }

        fun openConnecter() {
                val HttpURL = URL(url)
                urlConn = HttpURL.openConnection() as HttpURLConnection
        }
        fun closeConnecter() {
            try {
                if (urlConn != null)
                    urlConn.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        fun getJson(): String? {
//            datas존재 시 데이터Post함수 호출
            if (!datas.equals(null)) {
                PostData(datas)
            }
//            스트링버퍼 생성
            val sb = StringBuffer()
            br = BufferedReader(InputStreamReader(urlConn.inputStream, "UTF-8"))

            var lines: String = ""
//            긁어옴
            for (lines in br.readLine()) {
                if (lines == null) { break }
                sb.append(lines)
            }
//            리턴
            return sb.toString()
        }

        fun PostData(datas: String?) {
//            문제발생!!!!
            urlConn.doOutput
//            서버에 값을보내줄준비
            val Os = OutputStreamWriter(urlConn.outputStream)
//            보냄
            Os.write(datas)
//            Outputsream 비우기
            Os.flush()
        }
    }
}
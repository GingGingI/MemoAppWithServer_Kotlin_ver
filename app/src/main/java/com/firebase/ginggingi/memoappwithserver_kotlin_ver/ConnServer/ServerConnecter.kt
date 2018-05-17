package com.firebase.ginggingi.memoappwithserver_kotlin_ver.ConnServer

import android.content.Context
import android.os.AsyncTask
import android.system.Os
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by GingGingI on 2018-05-16.
 */
class ServerConnecter {

    private var ServerTask: ServerConnTask

    private var url: String
    private var datas: String? = null
    private var result: String? = null
    var isComplete = false
    var isGetError = false

    init {
        ServerTask = ServerConnTask()
    }

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
        ServerTask.setDatas(this, url, datas)
        ServerTask.execute()
    }

    fun returnDatas(): String? {
        return result
    }

    private class ServerConnTask: AsyncTask<Void, Void, String>() {

        var isWorking = false

        lateinit var url: String
        lateinit var SConn: ServerConnecter
        var datas: String? = null

        lateinit var br: BufferedReader
        lateinit var urlConn: HttpURLConnection

        fun setDatas(SConn: ServerConnecter, url: String, datas: String?) {
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
            }catch (e: IOException) {
                SConn.isGetError = true
                e.printStackTrace()
            }finally {
                closeConnecter()
                return result
            }
        }

        fun openConnecter() {
                val HttpURL = URL(url)
                urlConn = HttpURL.openConnection() as HttpURLConnection
        }
        fun closeConnecter() {
            try {
                if (br != null)
                    br.close()
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
            br = BufferedReader(InputStreamReader(urlConn.inputStream))

            var lines: String? = null
//            긁어옴
            while ((lines == br!!.readLine()) != null) {
                if (lines != "")
                    sb.append(lines)
            }
//            리턴
            return sb.toString()
        }

        fun PostData(datas: String?) {
            urlConn.doOutput
//            서버에 값을보내줄준비
            val Os = OutputStreamWriter(urlConn.outputStream)
//            보냄
            Os.write(datas)
//            Outputsream 비우기
            Os.flush()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            SConn.result = result
            SConn.isComplete = true
            isWorking = false
        }

    }
}
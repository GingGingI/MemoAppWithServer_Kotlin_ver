package com.firebase.ginggingi.memoappwithserver_kotlin_ver

import java.net.URLEncoder

class DeleteData : ActivityBase() {

    var idx: Int? = null

    fun InitData(idx: Int) {
        this.idx = idx
    }

    fun DelData(){
        var data = URLEncoder.encode("idx", "UTF-8") + "=" + URLEncoder.encode(idx.toString(), "UTF-8")

        GJson.ChangeURL(URLMaker(3))
        GJson.ParseJsonFromUrl(data)
    }

}
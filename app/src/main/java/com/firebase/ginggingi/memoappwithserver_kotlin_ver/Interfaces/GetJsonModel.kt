package com.firebase.ginggingi.memoappwithserver_kotlin_ver.Interfaces

import com.firebase.ginggingi.memoappwithserver_kotlin_ver.MainActivity

/**
 * Created by GingGingI on 2018-05-16.
 */
interface GetJsonModel {
    fun InitDatas(url: String, mActivity: MainActivity)
    fun ChangeURL(url: String)
    fun ParseJsonFromUrl()
    fun ParseJsonFromUrl(datas: String)
    fun GiveJsonArr()
}
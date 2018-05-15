package com.firebase.ginggingi.memoappwithserver_kotlin_ver.DataModels

/**
 * Created by GingGingI on 2018-05-15.
 */
class MemoDataModel {
    private var idx: Int? = null
    private var Time: String? = null
    private var Title: String? = null
    private var Content: String? = null

    fun setDatas(idx: Int, Time: String, Title: String, Content: String) {
        this.idx = idx
        this.Time = Time
        this.Title = Title
        this.Content = Content
    }

    fun getIdx(): Int? { return idx }
    fun getTime(): String? {return Time}
    fun getTitle(): String? {return Title}
    fun getContent(): String? {return Content}

    fun setIdx(temp: Int) {this.idx = temp}
    fun setTime(temp: String) {this.Time = temp}
    fun setTitle(temp: String) {this.Title = temp}
    fun setContent(temp: String) {this.Content = temp}
}
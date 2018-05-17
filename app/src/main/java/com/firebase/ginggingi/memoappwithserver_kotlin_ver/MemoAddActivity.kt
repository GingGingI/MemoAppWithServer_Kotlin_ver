package com.firebase.ginggingi.memoappwithserver_kotlin_ver

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.FloatingActionButton
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import java.net.URLEncoder

class MemoAddActivity : ActivityBase(), View.OnClickListener{

    lateinit var fab: FloatingActionButton

    lateinit var TitleView: EditText
    lateinit var ContentView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmemo)

        ViewInit()
    }

    private fun ViewInit() {
        TitleView = findViewById(R.id.TitleView)
        ContentView = findViewById(R.id.ContentView)

        fab = findViewById(R.id.fab)

        fab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {
                sendData()
            }
        }
    }

    private fun sendData() {
        val title = TitleView.text.toString()
        val content = ContentView.text.toString()
        if (!ChkDataNull(title, content)){
            var data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8")
            data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8")

            GJson.ChangeURL(URLMaker(1))
            GJson.ParseJsonFromUrl(data)
            finish()
        }
    }

    private fun ChkDataNull(title: String, content: String): Boolean{
        if (title == "" || title == null || content == "" || content == null) { return true }
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) { return true }
        return false
    }
}
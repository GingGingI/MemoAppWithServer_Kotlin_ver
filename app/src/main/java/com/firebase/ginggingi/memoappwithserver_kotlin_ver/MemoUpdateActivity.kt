package com.firebase.ginggingi.memoappwithserver_kotlin_ver

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.FloatingActionButton
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.net.URLEncoder

/**
 * Created by GingGingI on 2018-05-15.
 */
class MemoUpdateActivity: ActivityBase(), View.OnClickListener {

    lateinit var fab: FloatingActionButton

    var title: String? = null
    var time: String? = null
    var content: String? = null

    lateinit var titleView: TextView
    lateinit var timeView: TextView
    lateinit var contentView: TextView

    var idx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_updatememo)
        Init()
        ViewInit()
    }

    private fun ViewInit() {
        val intent = getIntent()
        idx = intent.getIntExtra("idx", -1)
        title = intent.getStringExtra("Title")
        time = intent.getStringExtra("Time")
        content = intent.getStringExtra("Content")

        fab.setOnClickListener(this)

        titleView.setText(title)
        timeView.setText(time)
        contentView.setText(content)

        Toast.makeText(this, "$idx 번째 메모입니다.", Toast.LENGTH_SHORT)
    }

    private fun Init() {
        titleView = findViewById(R.id.TitleView)
        timeView = findViewById(R.id.DateView)
        contentView = findViewById(R.id.ContentView)

        fab = findViewById(R.id.fab)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab -> {
                updateData()
            }
        }
    }

    private fun updateData() {
        val title = titleView.text.toString()
        val content = contentView.text.toString()
            if (!ChkDataNull(title, content)){
                var data = (URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8"))
                data += ("&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8"))
                data += ("&" + URLEncoder.encode("idx", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(idx), "UTF-8"))

                GJson.ChangeURL(URLMaker(2))
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
package com.firebase.ginggingi.memoappwithserver_kotlin_ver.Adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.DataModels.MemoDataModel
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.MainActivity
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.MemoUpdateActivity
import com.firebase.ginggingi.memoappwithserver_kotlin_ver.R


/**
 * Created by GingGingI on 2018-05-15.
 */
class MRecyclerViewAdaper : RecyclerView.Adapter<ViewHolder>() {

    private var listViewItemList: ArrayList<MemoDataModel> = ArrayList()
    protected lateinit var mActivity: MainActivity

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        mActivity = parent?.context as MainActivity
        val v : View = LayoutInflater.from(parent?.context as Context).inflate(R.layout.listitem_memo, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Title.setText(listViewItemList.get(position).getTitle())
        holder.Date.setText(listViewItemList.get(position).getTime())
        holder.Content.setText(listViewItemList.get(position).getContent())
        holder.DetailBtn.setOnClickListener(View.OnClickListener {ToUpdateActivity(position)})
        holder.itemView.setOnLongClickListener(View.OnLongClickListener { ToDeleteMemo(position) })
    }

    fun ToUpdateActivity(position: Int) {
        val intent: Intent = Intent(mActivity, MemoUpdateActivity::class.java)
        intent.putExtra("idx", listViewItemList.get(position).getIdx())
        intent.putExtra("Title", listViewItemList.get(position).getTitle())
        intent.putExtra("Time", listViewItemList.get(position).getTime())
        intent.putExtra("Content", listViewItemList.get(position).getContent())
        mActivity.startActivityForResult(intent, 1)
    }

    fun ToDeleteMemo(position: Int): Boolean {
        /** 메모지우기전 물어보는 Dialog */
        val builder: AlertDialog.Builder = AlertDialog.Builder(mActivity)
        builder.setTitle(R.string.DeleteMemo)
        builder.setMessage(R.string.DeleteMemoQuest)
        builder.setPositiveButton(R.string.Accept, DialogInterface.OnClickListener { dialog, which ->
            /* 데이터 삭제 */
        }).setNegativeButton(R.string.Refuse, DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(mActivity, R.string.Refuse, Toast.LENGTH_SHORT).show()
        })
        return true
    }
    override fun getItemCount(): Int {
        return listViewItemList.size
    }

    fun setItems(itemlist: ArrayList<MemoDataModel>) {
        listViewItemList = itemlist
    }
}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val Title: TextView = itemView.findViewById(R.id.Listitem_TitleView)
    val Date: TextView = itemView.findViewById(R.id.Listitem_DateView)
    val Content: TextView = itemView.findViewById(R.id.Listitem_ContentView)
    val DetailBtn: Button = itemView.findViewById(R.id.Listitem_DetailBtn)
}
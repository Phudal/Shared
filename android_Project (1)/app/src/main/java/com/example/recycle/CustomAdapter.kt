package com.example.recycle

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CustomAdapter(arrayList : ArrayList<Column>) : BaseAdapter(), View.OnClickListener {
    public interface ListBtnClickListener{ //버튼클릭 이벤트 처리리
        fun onListBtnClick(position: Int)
    }

    var listBtnClickListener:ListBtnClickListener? = null
    var inflater:LayoutInflater? = null
    var _arrayList: ArrayList<Column>? = null
    var cnt:Int? = null

    init{
        _arrayList = arrayList
        cnt = _arrayList!!.size
    }



    override fun getCount(): Int {
        return cnt!!
    }

    override fun getItem(position: Int): Any {
        return null!!
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var _convertView: View? = null
        if(convertView == null){
            val context: Context = parent!!.context

            if(inflater == null){
                inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
            _convertView = inflater!!.inflate(R.layout.record, parent, false)
        }
        else {
            _convertView = convertView
        }

        val id_edit = _convertView!!.findViewById<TextView>(R.id.id_edit)
        val serial_edit = _convertView!!.findViewById<TextView>(R.id.serial_edit)
        val name_edit = _convertView!!.findViewById<TextView>(R.id.name_edit)
        val button = _convertView!!.findViewById<ImageButton>(R.id.menu)

        id_edit.setText(_arrayList!!.get(position).id)
        serial_edit.setText(_arrayList!!.get(position).serial)
        name_edit.setText(_arrayList!!.get(position).name)

        button.setOnClickListener(this)

        return _convertView
    }

    override fun onClick(v: View?) {
        Log.i("Button - ", "Touch")
        //MainActivity 에서 구현한 onListBtnClick() 호출

        if(this.listBtnClickListener != null)
            this.listBtnClickListener!!.onListBtnClick(v!!.getTag() as Int)
    }
}
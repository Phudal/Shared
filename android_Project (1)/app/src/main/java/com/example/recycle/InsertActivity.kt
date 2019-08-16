package com.example.recycle

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertActivity : AppCompatActivity() {
    var popup_ok: Button? = null
    var popup_cancle: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        popup_ok = findViewById(R.id.popup_insert_ok)
        popup_cancle = findViewById(R.id.popup_insert_cancle)

          //MainActivity에서 가져온 dbHelper object

        popup_ok!!.setOnClickListener { view->
            val name:String = findViewById<EditText>(R.id.popup_name_edit).text.toString().trim()

            if(name.length == 0){
                Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent= Intent()
                intent.putExtra("name", name)
                setResult(Activity.RESULT_OK, intent)
                this.finish()
            }
        }

        popup_cancle!!.setOnClickListener { view->
            this.finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

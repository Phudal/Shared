package com.example.testintent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText m_refEditTextName;

    EditText m_refEditTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_refEditTextName = (EditText)findViewById(R.id.atpd_EditTextName);
        m_refEditTextAge = (EditText)findViewById(R.id.atpd_EditTextAge);
    }
    public void atpd_sendClick(View v){
        String inputName = this.m_refEditTextName.getText().toString().trim();
        String inputAge = this.m_refEditTextAge.getText().toString().trim();

        if (inputName.length()>0 && inputAge.length()>0){
            Intent ref_intent;
            ref_intent = new intent(this,Main2Activity.class);

            ref_intent.putExtra("name",inputName);
            ref_intent.putExtra("age",Integer.parseInt(inputAge));
            startActivity(ref_intent);
        }
    }
}

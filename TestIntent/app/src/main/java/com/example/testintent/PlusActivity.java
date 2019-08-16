package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlusActivity extends AppCompatActivity {
    String name;
    String species;
    //int year;

    EditText editTextName;
    EditText editTextSpecies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

     editTextName = findViewById(R.id.editName);
     editTextSpecies = findViewById(R.id.editSpecies);
     //editText = findViewById(R.id.editYear);

     Button button = findViewById(R.id.backbutton);
     button.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
             name = editTextName.getText().toString();
             //editText의 내용을 string형으로 변환해 name에 저장합니다.
             //showToast("name: ");
             species = editTextSpecies.getText().toString();
             Intent intent = new Intent();
             intent.putExtra("name", name);
             intent.putExtra( "species", species);
             setResult(RESULT_OK, intent);
             //setResult(응답코드, 인텐트)
             finish();
         }
     });
    }
    //public void showToast(String data){
    //    Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    //}
}

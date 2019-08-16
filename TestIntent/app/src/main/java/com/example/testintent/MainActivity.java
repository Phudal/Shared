package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlusActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //requestCode: 액티비티를 띄울 떄 전달했던 요청코드
        //resultCode : 새 액티비티로부터 전달된 응답 코드
        //data: 새 액티비티로부터 전달 받은 인텐트
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            //Toast.makeText(getApplicationContext(), "onActivityResult 메서드 호출됨. 요청 코드: " + requestCode +
            //        "결과코드: "+ resultCode, Toast.LENGTH_SHORT).show();

            if(resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String species = data.getStringExtra("species");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name: " + name, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "응답으로 전달된 species: " + species, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.shinhan.serviceexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent(); // 처음 실행될때 Intent 수신
        processIntent(intent);
    }
    public void onButtonClicked(View view)
    {
        String string = ((EditText)findViewById(R.id.edittext)).getText().toString();
        Intent intent = new Intent(MainActivity.this,MyService.class);
        intent.putExtra("command","show");
        intent.putExtra("name",string);
        startService(intent);
    }
    public void processIntent(Intent intent)
    {
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            Toast.makeText(MainActivity.this,"command : "+command+" name : "+name,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) { // 이미 실행이 중인 상태에서 새로 들어오는 Intent를 받을 경우
        super.onNewIntent(intent);
        processIntent(intent);
    }

}

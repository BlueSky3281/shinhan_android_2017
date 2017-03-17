package com.shinhan.activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate","xxxx");
    }
    public void onButtonCall(View view)
    {
        EditText editText = (EditText)findViewById(R.id.edittext);
        String string = editText.getText().toString();
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("String",string);
        startActivity(intent);
    }
    public void onButtonCall2(View view)
    {
        EditText editText = (EditText)findViewById(R.id.edittext);
        String string = editText.getText().toString();
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("String",string);
        startActivityForResult(intent,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent)
    {
        EditText editText = (EditText)findViewById(R.id.edittext);
        editText.setText("");
        if(requestCode ==0)
        {
            if(resultCode == RESULT_OK)
            {
                String string = intent.getStringExtra("Stringxx");
                editText.setText(string);
            }
        }
    }
}

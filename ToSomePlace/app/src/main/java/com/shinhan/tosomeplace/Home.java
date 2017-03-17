package com.shinhan.tosomeplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void onLogin(View view)
    {
        int menuNum = view.getId();
        if(menuNum == R.id.button1){

        }else if(menuNum == R.id.button2){

        }else if(menuNum == R.id.button3){

        }else if(menuNum == R.id.button4){

        }else{

        }
    }
}

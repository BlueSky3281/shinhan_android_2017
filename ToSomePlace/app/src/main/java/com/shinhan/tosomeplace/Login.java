package com.shinhan.tosomeplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onLogin(View view)
    {
        Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
    }
}

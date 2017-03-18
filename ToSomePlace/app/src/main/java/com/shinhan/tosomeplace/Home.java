package com.shinhan.tosomeplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void onMenuClicked(View view) //메뉴 클릭 이벤트
    {
        int menuNum = view.getId();
        Intent intent = new Intent();
        Button button = (Button)view;
        if(menuNum == R.id.button1){ //사용자 모드
            intent = new Intent(getApplicationContext(),UserMode.class);
        }else if(menuNum == R.id.button2){ //그룹모드
            intent = new Intent(getApplicationContext(),GroupMode.class);
        }else if(menuNum == R.id.button3){ //구글맵모드
            intent = new Intent(getApplicationContext(),MapMode.class);
        }else if(menuNum == R.id.button4){ //사용자관리
            intent = new Intent(getApplicationContext(),UserList.class);
        }else{

        }
        if(intent != null){
            intent.putExtra("title",button.getText().toString());
            intent.putExtra("data","");
            startActivity(intent);
        }
    }
    public void fSubActivityCall(Intent intent,String string)
    {
    }
}

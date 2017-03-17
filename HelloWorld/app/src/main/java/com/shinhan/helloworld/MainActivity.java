package com.shinhan.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // main xml 셋팅, xml에 데이타를 객체화 한다.
        Button button = (Button)findViewById(R.id.button);
        TextView textView = (TextView)findViewById(R.id.textview);

        textView2= (TextView)findViewById(R.id.textview);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"버튼 클릭", Toast.LENGTH_SHORT).show();
                textView2.setText("Button 1 Click");
            }
        });
        textView.setText("안녕 신한!");
    }
    public void  onButton2Clicked(View v)
    {
        Toast.makeText(MainActivity.this,"버튼2 클릭 입니다.", Toast.LENGTH_SHORT).show();
        textView2.setText("Button 2 Click");
    }
    public void  onButton3Clicked(View v) // 웹브라우져 호출
    {
        Toast.makeText(MainActivity.this,"버튼3 클릭 입니다.", Toast.LENGTH_SHORT).show();
        textView2.setText("Button 3 Click");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }
    public void  onButton4Clicked(View v) // 전화걸기
    {
        Toast.makeText(MainActivity.this,"버튼4 클릭 입니다.", Toast.LENGTH_SHORT).show();
        textView2.setText("Button 4 Click");
        Intent callIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-1000-1000"));
        startActivity(callIntent);
    }
}

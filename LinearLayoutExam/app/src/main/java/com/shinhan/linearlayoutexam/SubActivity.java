package com.shinhan.linearlayoutexam;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }
    public void fImageChange(View view)
    {
        Log.i("test",view.getId()+" @@@@@@@@@@@@@@@@ "+R.id.image1);

        ImageView imageView = (ImageView)findViewById(R.id.image1);
        //imageView.setImageResource(R.drawable.images1); // source로 입력
        imageView.setBackgroundResource(R.drawable.images2);

    }
}

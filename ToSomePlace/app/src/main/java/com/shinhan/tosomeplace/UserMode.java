package com.shinhan.tosomeplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.getInstance().debug("------------------------- onCreateCall ------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mode);

        Intent recv = getIntent();
        String string = recv.getStringExtra("title");
        Util.getInstance().debug("title : "+string);
        LinearLayout titleLayer = (LinearLayout)findViewById(R.id.title);
        TextView textView = new TextView(this);
        textView.setText(string);
        titleLayer.addView(textView);
    }
}

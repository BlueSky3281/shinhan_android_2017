package com.shinhan.receiverexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ReceiverMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){ //권한이 없을 경우
            Log.d(TAG,"권한이 없을 경우");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)){ //권한 설명이 필요한 경우
                Toast.makeText(this,"SMS 권한 설명",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"SMS 권한 설명");
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.RECEIVE_SMS},1);
            } else { //SMS 수신 권한 요청
                Log.d(TAG,"SMS 수신 권한 요청");
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.RECEIVE_SMS},1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case 1 : {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"SMS 권한을 사용자가 승인함.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "SMS 권한을 거부함.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

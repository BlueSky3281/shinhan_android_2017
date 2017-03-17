package com.shinhan.serviceexam;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"--------------- onCreate() -----------------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"--------------- onStartCommand() -----------------");
        Log.d(TAG,"flags >> "+flags+" , startID >>"+startId);
        if(intent == null){ // Intent가 NULL일 경우 자동 재시작
            return Service.START_STICKY;
        }else{ //Intent가 정상일 경우
            String command = intent.getStringExtra("command").toString();
            String name = intent.getStringExtra("name").toString();

            Log.d(TAG,"command : "+command);
            Log.d(TAG,"name : "+name);

            if(command.equals("show")){
                Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                showIntent.putExtra("command",command);
                showIntent.putExtra("name", name+" from service");
                startActivity(showIntent);
                sendMessage(showIntent);
            }else if(command == "start"){
                Log.d(TAG,"Open SubActivity");
                Intent subIntent = new Intent(MyService.this,SubActivity.class);
                startActivity(subIntent);

            }else if(command == "stop"){

            }
        }

        return super.onStartCommand(intent, flags, startId);
    }
    public void sendMessage(Intent intent)
    {
        for(int i = 0 ; i < 10 ; i++){
            try{
                startActivity(intent);
                Log.d(TAG,"Main Activity Call");
                Thread.sleep(1000);
            }catch (Exception e){};
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

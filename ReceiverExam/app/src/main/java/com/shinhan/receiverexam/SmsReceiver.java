package com.shinhan.receiverexam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG,"-----------------------onReceive()----------------------------------");
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if(messages != null && messages.length > 0){ // 수신 된 메시지가 있으면
            String sender = messages[0].getOriginatingAddress();
            String contents = messages[0].getMessageBody().toString();
            Log.i(TAG,"sender = "+sender);
            Log.i(TAG,"contents = "+contents);
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] obj = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[obj.length];
        for(int i = 0;i < messages.length ;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])obj[i],format);
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[]) obj[i]);
            }
        }
        return messages;
    }
}

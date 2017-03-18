package com.shinhan.tosomeplace;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by IC-INTPC-087110 on 2017-03-18.
 */

public class Util {
    public static Util instance = new Util();
    private String TAG = "TSP";
    public Util(){

    }
    public static Util getInstance(){
        return instance;
    }
    public void debug(String string){
        Log.d(TAG,string);
    }
    public void showToastMessage(Context context, String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }
}

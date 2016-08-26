package com.example.user.gillian01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CustomizedActivity extends AppCompatActivity {

    public static final String debug_tag ="testAct";
    String avtivityName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(debug_tag, avtivityName+"_create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(debug_tag, avtivityName+ "_start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(debug_tag, avtivityName+ "_resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(debug_tag, avtivityName+ "_pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(debug_tag, avtivityName+ "_stop");
    }

    @Override
    // 旋轉會 DESTORY  產生新的
    // because layout may be diffrent
    protected void onDestroy() {
        super.onDestroy();
        Log.d(debug_tag, avtivityName+ "_destroy");
    }


}

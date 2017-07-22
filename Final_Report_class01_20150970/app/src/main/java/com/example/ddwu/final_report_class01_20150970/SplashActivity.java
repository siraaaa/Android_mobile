package com.example.ddwu.final_report_class01_20150970;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by sira on 2017-06-26.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            Thread.sleep(2000);//2초간 대기하고
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));//메인 액티비티로 넘어간다
        finish();
    }
}
package com.example.ddwu.final_report_class01_20150970;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
    }
    public void onClick(View v){
        finish();
    }
}
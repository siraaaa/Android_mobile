package com.example.ddwu.final_report_class01_20150970;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchShowActivity extends AppCompatActivity {

    ImageView imageView;
    TextView etTitle;
    TextView etDate;
    TextView etStar;
    TextView etContent;
    TextView etReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_show);

        imageView = (ImageView)findViewById(R.id.imgView);
        etTitle = (TextView)findViewById(R.id.etTitle);
        etDate = (TextView)findViewById(R.id.etDate);
        etStar = (TextView)findViewById(R.id.etStar);
        etContent = (TextView)findViewById(R.id.etContent);
        etReview = (TextView)findViewById(R.id.etReview);

        Intent receivedIntent = getIntent();

        String image = receivedIntent.getStringExtra("image");
        String title = receivedIntent.getStringExtra("title");
        String date = receivedIntent.getStringExtra("date");
        String star = receivedIntent.getStringExtra("star");
        String content = receivedIntent.getStringExtra("content");
        String review = receivedIntent.getStringExtra("review");

        imageView.setImageResource(Integer.valueOf(image));
        etTitle.setText(title);
        etDate.setText(date);
        etStar.setText(star);
        etContent.setText(content);
        etReview.setText(review);//인텐트로 받은 항목의 정보 출력
    }

    public void onClick(View v){
        finish();
    }
}

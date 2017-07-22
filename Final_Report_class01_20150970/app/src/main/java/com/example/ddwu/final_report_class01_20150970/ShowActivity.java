package com.example.ddwu.final_report_class01_20150970;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ShowActivity extends AppCompatActivity {
    ImageView imageView;
    EditText etTitle;
    EditText etDate;
    EditText etStar;
    EditText etContent;
    EditText etReview;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        imageView = (ImageView)findViewById(R.id.imgView);
        etTitle = (EditText)findViewById(R.id.etTitle);
        etDate = (EditText)findViewById(R.id.etDate);
        etStar = (EditText)findViewById(R.id.etStar);
        etContent = (EditText)findViewById(R.id.etContent);
        etReview = (EditText)findViewById(R.id.etReview);

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
        switch (v.getId()){
            case R.id.btnUpdate:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", etTitle.getText().toString());
                resultIntent.putExtra("date", etDate.getText().toString());
                resultIntent.putExtra("star", etStar.getText().toString());
                resultIntent.putExtra("content", etContent.getText().toString());
                resultIntent.putExtra("review", etReview.getText().toString());
                setResult(RESULT_OK, resultIntent);
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}

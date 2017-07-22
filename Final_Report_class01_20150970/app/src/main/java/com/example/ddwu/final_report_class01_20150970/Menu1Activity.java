package com.example.ddwu.final_report_class01_20150970;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Menu1Activity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    private EditText etTitle;
    private EditText etDate;
    private EditText etStar;
    private EditText etContent;
    private EditText etReview;
    int mYear, mMonth, mDay;
//    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        myDBHelper = new MyDBHelper(this);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDate = (EditText) findViewById(R.id.etDate);
        etStar = (EditText)findViewById(R.id.etStars);
        etContent = (EditText)findViewById(R.id.etContent);
        etReview = (EditText)findViewById(R.id.etReview);
        Button selectDate = (Button)findViewById(R.id.btnSelectDate);

        GregorianCalendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();
    }


    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnSave:
                SQLiteDatabase db =myDBHelper.getWritableDatabase();
                ContentValues row = new ContentValues();
                row.put(MyDBHelper.COL_TITLE, etTitle.getText().toString());
                row.put(MyDBHelper.COL_DATE, etDate.getText().toString());
                row.put(MyDBHelper.COL_STAR, etStar.getText().toString());
                row.put(MyDBHelper.COL_CONTENT, etContent.getText().toString());
                row.put(MyDBHelper.COL_REVIEW, etReview.getText().toString());
                db.insert(MyDBHelper.TABLE_NAME, null, row);
                myDBHelper.close();
                break;
//            case R.id.btnSelectDate:
//                new DatePickerDialog(Menu1Activity.this, dateSetListener, mYear, mMonth, mDay).show();
//                break;
            case R.id.btnCancel:
                break;
        }
        finish();
    }

    public void onDateClick(View v){
        new DatePickerDialog(Menu1Activity.this, dateSetListener, mYear, mMonth, mDay).show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mYear = year;
                    mMonth = month;
                    mDay = dayOfMonth;
                    UpdateNow();
                }};

    void UpdateNow(){
        etDate.setText(String.format("%d/%d/%d", mYear, mMonth+1, mDay));
    }

}
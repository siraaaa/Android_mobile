package com.example.ddwu.final_report_class01_20150970;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sira on 2017-06-26.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="my_db";
    public static final String TABLE_NAME = "movie_table";

    public static final String COL_ID = "_id";
    public static final String COL_TITLE  = "title";
    public static final String COL_DATE = "date";
    public static final String COL_STAR = "star";
    public static final String COL_CONTENT  = "content";
    public static final String COL_REVIEW = "review";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT, " + COL_DATE + " TEXT, " +
                COL_STAR + " TEXT, "+ COL_CONTENT + " TEXT, " + COL_REVIEW + " TEXT)";
        db.execSQL(createTable);

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'wonderwoman', '2017/5/6', '갤 가돗, 프린스 파인', " +
                "'동주와 종강기념 왕십리CGV에서! 히어로물이었지만 재미있었다', '4');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '가디언즈오브갤럭시', '2017/6/23', '크리스 프랫', " +
                "'동주와 군자CGV, 기대를 많이 하고 봐서 그런지 재미가 없었다. 동주는 옆에서 잤다..', '2.3');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '부산행', '2016/8/1', '공유, 마동석', " +
                "'수민이와 운동 후 중계 CGV에서! 오랜만에 보는 좀비 영화! 심야영화로 보고 집에 돌아오는 길에 좀비가 떠올라서 무서웠다', '4.3');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

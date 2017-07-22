package com.example.ddwu.final_report_class01_20150970;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
//과제명 : 영화 정보 관리 앱
//분반 : 01분반
//학번 : 20150970 김시라
//제출일 : 2017년 6월 26일

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";
    public final static int ACT_SHOW = 100;
    MyData selectedData;

    private ListView listView;
    private MyAdapter adapter;
    private ArrayList<MyData> myDataList;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(this);
        myDataList = new ArrayList<MyData>();
        adapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                int image;
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                selectedData = myDataList.get(pos);
                if(selectedData.getTitle().equals("wonderwoman")){
                    image = R.drawable.wonderwoman;
                    intent.putExtra("image", Integer.valueOf(image).toString());
                }
                else if(selectedData.getTitle().equals("가디언즈오브갤럭시")){
                    image = R.drawable.gallerxy;
                    intent.putExtra("image", Integer.valueOf(image).toString());
                }
                else if(selectedData.getTitle().equals("부산행")){
                    image = R.drawable.busan;
                    intent.putExtra("image", Integer.valueOf(image).toString());
                }
                else{
                    image = R.drawable.movie;
                    intent.putExtra("image", Integer.valueOf(image).toString());
                }
                intent.putExtra("title", selectedData.getTitle());
                intent.putExtra("date", selectedData.getDate());
                intent.putExtra("star", selectedData.getStar());
                intent.putExtra("content", selectedData.getContent());
                intent.putExtra("review", selectedData.getReview());
                startActivityForResult(intent, ACT_SHOW);//선택한 항목 인텐트로 던지기
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("항목 삭제");
                builder.setMessage("항목을 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDBHelper = new MyDBHelper(MainActivity.this);
                        SQLiteDatabase db = myDBHelper.getWritableDatabase();
                        String selected = myDataList.get(pos).getTitle();
                        String whereClause = MyDBHelper.COL_TITLE + "=?";
                        String[] whereArgs = { selected };
                        db.delete(MyDBHelper.TABLE_NAME, whereClause, whereArgs);
                        myDBHelper.close();
                        viewTable();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dlg = builder.create();
                dlg.show();
                return true;//onClick과 onLongClick동시 구현할 때 (true: 이벤트 완료)설정해야 클릭 처리 안됨
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(this, Menu1Activity.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2 = new Intent(this, Menu2Activity.class);
                startActivity(intent2);
                break;
            case R.id.menu3:
                Intent intent3 = new Intent(this, Menu3Activity.class);
                startActivity(intent3);
                break;
            case R.id.menu4:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("종료 알림");
                builder.setMessage("앱을 종료하시겠습니까?");
                builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            switch (resultCode){
                case RESULT_OK:
                    SQLiteDatabase db =myDBHelper.getWritableDatabase();
                    ContentValues row = new ContentValues();
                    row.put(MyDBHelper.COL_TITLE, data.getStringExtra("title"));
                    row.put(MyDBHelper.COL_DATE, data.getStringExtra("date"));
                    row.put(MyDBHelper.COL_STAR, data.getStringExtra("star"));
                    row.put(MyDBHelper.COL_CONTENT, data.getStringExtra("content"));
                    row.put(MyDBHelper.COL_REVIEW, data.getStringExtra("review"));
                    String whereClause = MyDBHelper.COL_TITLE + "=?";
                    String[] whereArgs = {selectedData.getTitle()};//이전에 선택된(수정되기 이전의 값) 항목의 타이틀
                    db.update(MyDBHelper.TABLE_NAME, row, whereClause, whereArgs);
                    myDBHelper.close();
                    viewTable();
                    break;
                case RESULT_CANCELED:
                    setResult(RESULT_CANCELED);
                    break;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewTable();
    }

    private void viewTable() {

        myDataList.clear();//초기화

        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        String[] cols = null;
        String whereClause = null;
        String[] whereArgs = null;
        Cursor cursor = db.query(MyDBHelper.TABLE_NAME, cols, whereClause, whereArgs, null, null, null, null);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            String star = cursor.getString(3);
            String content = cursor.getString(4);
            String review = cursor.getString(5);
            //Drawable drawable = cursor.getBlob(6);

            MyData newData = new MyData(_id, title, date, star, content, review);
            myDataList.add(newData);

        }
        for (MyData data : myDataList) {
            Log.i(TAG, data.toString());
        }

        cursor.close();
        myDBHelper.close();
        adapter.notifyDataSetChanged();
    }
}

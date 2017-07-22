package com.example.ddwu.final_report_class01_20150970;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu3Activity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    EditText searchTitle;
    ListView listView;
    private MyAdapter adapter;
    private ArrayList<MyData> searchList;
    MyData selectedData;

    String[] arWords = new String[]{
            "해리포터", "부산행", "가디언즈오브갤럭시", "원더우먼", "트와일라잇", "변호인", "미녀와 야수", "트랜스포머", "베테랑",
            "harry potter", "wonder woman", "transfomers", "la la land", "zootopia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        //단어 자동완성 기능 추가
        ArrayAdapter<String> adWord = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arWords);
        AutoCompleteTextView autoEdit = (AutoCompleteTextView)findViewById(R.id.etSearchTitle);
        autoEdit.setAdapter(adWord);

        searchTitle = (EditText)findViewById(R.id.etSearchTitle);
        listView = (ListView)findViewById(R.id.searchListView);

        myDBHelper = new MyDBHelper(this);
        searchList = new ArrayList<MyData>();
        adapter = new MyAdapter(this, R.layout.custom_adapter_view, searchList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                int image;
                Intent intent = new Intent(Menu3Activity.this, SearchShowActivity.class);
                selectedData = searchList.get(pos);
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
                startActivity(intent);//선택 항목 인텐트로 던지기
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSearch:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//항목을 검색하고 search버튼을 누르면 키보드가 아래로 내려가는 동작

                searchList.clear();
                SQLiteDatabase db = myDBHelper.getReadableDatabase();
                String[] cols = null;
                String whereClause = MyDBHelper.COL_TITLE + "=?";
                String[] whereArgs = { searchTitle.getText().toString() };

                Cursor cursor = db.query(MyDBHelper.TABLE_NAME, cols, whereClause, whereArgs, null, null, null, null);

                if(cursor.getCount() == 0){
                    searchList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Menu3Activity.this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    while (cursor.moveToNext()) {
                        int _id = cursor.getInt(0);
                        String title = cursor.getString(1);
                        String date = cursor.getString(2);
                        String star = cursor.getString(3);
                        String content = cursor.getString(4);
                        String review = cursor.getString(5);

//            레코드에서 읽어들인 값을 MyItem 객체에 저장
                        MyData newData = new MyData(_id, title, date, star, content, review);
                        searchList.add(newData);
                    }
                    cursor.close();
                    myDBHelper.close();
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.btnSearchCancel:
                finish();
                break;
        }


    }
}

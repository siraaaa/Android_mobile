package com.example.ddwu.final_report_class01_20150970;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sira on 2017-06-26.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<MyData> arrayList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).get_id();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final int pos = position;

        if(view ==null){
            view = layoutInflater.inflate(layout, parent, false);
        }

        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        TextView tvTitle = (TextView) view.findViewById(R.id.textViewTitle);
        TextView tvStar = (TextView)view.findViewById(R.id.textViewStar);
        ImageView imgView = (ImageView)view.findViewById(R.id.imgStar);

        //img.setImageDrawable(arrayList.get(pos).getImage());

        tvTitle.setText(arrayList.get(pos).getTitle());
        tvStar.setText(arrayList.get(pos).getStar());

        if(tvTitle.getText().toString().equals("wonderwoman")){
            img.setImageResource(R.drawable.wonderwoman);
        }
        else if(tvTitle.getText().toString().equals("가디언즈오브갤럭시")){
            img.setImageResource(R.drawable.gallerxy);
        }
        else if(tvTitle.getText().toString().equals("부산행")){
            img.setImageResource(R.drawable.busan);
        }
        else {
            img.setImageResource(R.drawable.noimage);
        }

        double star = Double.valueOf(arrayList.get(pos).getReview());

        if((star >= 0) && (star < 1.5)){
            imgView.setImageResource(R.drawable.star1);
        }
        else if((star >= 1.5) && (star < 2.5)){
            imgView.setImageResource(R.drawable.star2);
        }
        else if((star >= 2.5) && (star < 3.5)){
            imgView.setImageResource(R.drawable.star3);
        }
        else if((star >= 3.5) && (star < 4.5)){
            imgView.setImageResource(R.drawable.star4);
        }
        else
            imgView.setImageResource(R.drawable.star5);

        return view;
    }
}
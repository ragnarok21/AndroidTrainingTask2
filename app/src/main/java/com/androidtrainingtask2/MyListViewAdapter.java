package com.androidtrainingtask2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyListViewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<News> mDataList = new ArrayList<>();

    public MyListViewAdapter(Context cxt){
        mInflater = LayoutInflater.from(cxt);
    }

    public void setData(List<News> dataList){
        this.mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*NewsResponse data = mDataList.get(position);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.listitem,null);
            System.out.println("item created for "+position);
        }

        ((TextView)convertView.findViewById(R.id.textView2)).setText(data.dt_txt);

        return convertView;*/
        return null;

    }
}

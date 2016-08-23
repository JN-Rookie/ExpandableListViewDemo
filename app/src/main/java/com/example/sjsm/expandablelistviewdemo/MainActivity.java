package com.example.sjsm.expandablelistviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mLv_list;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mLv_list = (ExpandableListView) findViewById(R.id.lv_expanded);
        mLv_list.setAdapter(new MyAdapter(mContext));
    }


    private class MyAdapter extends BaseExpandableListAdapter {
        private Context mContext;
        public MyAdapter(Context context) {
            mContext=context;
        }

//        //设置组视图的图片
//        int[] logos = new int[]{R.mipmap.arrow_right, R.mipmap.arrow_right, R.mipmap.arrow_right};
        //设置组视图的显示文字
        private String[] group = new String[]{"魏", "蜀", "吴"};
        //子视图显示文字
        private String[][] child = new String[][]{
                {"夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修"},
                {"马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云"},
                {"吕蒙", "陆逊", "孙权", "周瑜", "孙尚香"}};

        //自己定义一个获得textview的方法
        TextView getTextView() {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            TextView textView = new TextView(MainActivity.this);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(36, 0, 0, 0);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            return textView;
        }

        @Override
        public int getGroupCount() {
            return group.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return child[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return group[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return child[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            LinearLayout layout = new LinearLayout(mContext);
//            layout.setOrientation(LinearLayout.VERTICAL);
//            ImageView pic = new ImageView(mContext);
//            pic.setImageResource(logos[i]);
//            pic.setPadding(50,0,0,0);
//            layout.addView(pic);
            TextView textview=getTextView();
            textview.setText(getGroup(i).toString());
            layout.addView(textview);
            layout.setPadding(100,10,10,10);
            return layout;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            LinearLayout layout=new LinearLayout(mContext);
            TextView textview=new TextView(mContext);
            textview.setText(getChild(i,i1).toString());
            layout.addView(textview);
            return layout;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}

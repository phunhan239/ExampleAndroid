package com.example.dvan.demochangecolor;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvHello;
    private ListView mListView;
    private String[] mSubjects = new String[]{
            "Toán", "Lí", "Hóa", "Sử"
    };
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvHello = (TextView) findViewById(R.id.tvHello);
        mListView = (ListView) findViewById(R.id.lvSubject);

        mAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mSubjects);

        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.getChildAt(position).setBackgroundColor(Color.parseColor("#00ff00"));
            }
        });
    }

    public void changeColor(View view) {

        int oldColor = view.getSolidColor();
        int newColor = 0xffff0000;

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view,
                "backgroundColor",
                oldColor,
                newColor);

        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
    }
}

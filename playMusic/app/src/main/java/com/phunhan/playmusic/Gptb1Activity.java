package com.phunhan.playmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//tao bo lang nghe bang implements View.OnClickListener
public class Gptb1Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtso1, edtso2;
    private TextView tvresult;
    private Button btngiai, btnxoa, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gptb1);

        edtso1 = (EditText) findViewById(R.id.edtSo1);
        edtso2 = (EditText) findViewById(R.id.edtSo2);
        tvresult = (TextView) findViewById(R.id.tvResult);

        btngiai = (Button) findViewById(R.id.btnGiai);
        btnxoa = (Button) findViewById(R.id.btnXoa);
        btnback = (Button) findViewById(R.id.btnBack);

//        nac danh
        btngiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiaiPT(view);
            }
        });
//Tao bo lang nghe
        btnxoa.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    public void GiaiPT(View view) {
        tvresult.setText("da giai");
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnXoa:
                edtso1.setText("");
                edtso2.setText("");
                tvresult.setText("");
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}

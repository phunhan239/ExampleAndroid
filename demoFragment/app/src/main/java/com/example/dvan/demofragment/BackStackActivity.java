package com.example.dvan.demofragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BackStackActivity extends AppCompatActivity {
    private Button mBtnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstack);
        mBtnChange = (Button) findViewById(R.id.btnChange);
        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentContent(new Fragment2());
            }
        });
        initFragment();
    }

    private void initFragment() {
        Fragment1 firstFragment = new Fragment1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_body, firstFragment);
        ft.commit();
    }

//    protected void replaceFragmentContent(Fragment fragment) {
//        if (fragment != null) {
//            FragmentManager fmgr = getSupportFragmentManager();
//            FragmentTransaction ft = fmgr.beginTransaction();
//            ft.replace(R.id.container_body, fragment);
//            ft.commit();
//        }
//    }

    protected void addFragmentContent(Fragment fragment) {
        if (fragment != null) {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.add(R.id.container_body, fragment);
            mFragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            mFragmentTransaction.commit();
        }
    }
}

package com.example.dvan.democircleprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);

        mRatingBar.setStepSize(2f);
        mRatingBar.setRating(2.5f);
    }

}
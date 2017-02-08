package dvan.ListView.Aptech.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;


public class CustomGridViewActivity extends AppCompatActivity {
    private GridView mGridView;
    private String[] arr;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gridview);

        mGridView = (GridView) findViewById(R.id.mLvCustomGridView);
        arr = getResources().getStringArray(R.array.language);

        final int[] icons = new int[]{
                R.drawable.ic_launcher,
                R.drawable.ic_launcher1,
                R.drawable.ic_launcher2,
                R.drawable.ic_launcher3,
        };


        adapter = new CustomAdapter(this, R.layout.item_gridview, arr, icons);

        mGridView.setAdapter(adapter);
    }


}

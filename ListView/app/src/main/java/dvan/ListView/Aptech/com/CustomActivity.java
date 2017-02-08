package dvan.ListView.Aptech.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class CustomActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] arr;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mListView = (ListView) findViewById(R.id.Lv_Custom);
        arr = getResources().getStringArray(R.array.language);

        final int[] icons = new int[]{
                R.drawable.ic_launcher,
                R.drawable.ic_launcher1,
                R.drawable.ic_launcher2,
                R.drawable.ic_launcher3,
        };


        adapter = new CustomAdapter(this, R.layout.item_listview, arr, icons);

        mListView.setAdapter(adapter);
    }


}

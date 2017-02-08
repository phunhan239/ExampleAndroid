package dvan.ListView.Aptech.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] arr;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mListView = (ListView) findViewById(R.id.bLv_ListLanguage_listview);

        arr = getResources().getStringArray(R.array.language);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, arr);

        mListView.setAdapter(adapter);

        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        mListView.setItemChecked(1, true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentIndex = position;
            }
        });
    }

    public void goToResult(View view) {
        Toast.makeText(getApplicationContext(), arr[currentIndex], Toast.LENGTH_LONG).show();
    }


}

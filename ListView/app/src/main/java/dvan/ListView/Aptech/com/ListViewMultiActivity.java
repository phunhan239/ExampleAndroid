package dvan.ListView.Aptech.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewMultiActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] arr;
    private boolean[] selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_multi);

        mListView = (ListView) findViewById(R.id.bLv_ListLanguage_listview);

        arr = getResources().getStringArray(R.array.language);
        selected = new boolean[arr.length];

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, arr);

        mListView.setAdapter(adapter);

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected[position] = !selected[position];
            }
        });
    }

    public void goToResult(View view) {
        String select = "";

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                select += arr[i] + " ";
            }
        }

        Toast.makeText(this, select, Toast.LENGTH_LONG).show();
    }


}

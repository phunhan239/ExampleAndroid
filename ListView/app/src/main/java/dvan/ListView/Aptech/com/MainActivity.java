package dvan.ListView.Aptech.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Listview_btn = (Button) findViewById(R.id.Listview_btn);
    }

    public void goToListViewBasic(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void goToMultiChoice(View view) {
        startActivity(new Intent(this, ListViewMultiActivity.class));
    }

    public void goToCustom(View view) {
        startActivity(new Intent(this, CustomActivity.class));
    }

    public void goToGridView(View view) {
        startActivity(new Intent(this, GridViewActivity.class));
    }

    public void goToCustomGridView(View view) {
        startActivity(new Intent(this, CustomGridViewActivity.class));
    }
}

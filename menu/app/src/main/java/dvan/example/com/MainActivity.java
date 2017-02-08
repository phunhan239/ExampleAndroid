package dvan.example.com;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button btnOk;
    private Button btnOk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = (Button) findViewById(R.id.ok_btn);
        btnOk2 = (Button) findViewById(R.id.ok_btn2);
        registerForContextMenu(btnOk);
        registerForContextMenu(btnOk2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();

        switch (v.getId()) {
            case R.id.ok_btn:
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View view = layoutInflater.inflate(R.layout.item_menu_header, null);
                TextView tvText = (TextView) view.findViewById(R.id.tvText);
                tvText.setText("hello world");

                menu.setHeaderView(view);
                menuInflater.inflate(R.menu.menu1, menu);
                break;
            case R.id.ok_btn2:
                menu.setHeaderTitle("menu2");
                menu.setHeaderIcon(R.drawable.ic_launcher);
                menuInflater.inflate(R.menu.menu2, menu);
                break;
        }


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_edit:
                Toast toast1 = Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT);
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View view = layoutInflater.inflate(R.layout.item_toast, null);
                TextView tvText = (TextView) view.findViewById(R.id.tvText);
                tvText.setText("hello world");
                toast1.setView(view);
                toast1.show();
                break;
            case R.id.meu_delete:
                Toast toast = Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 20);
                toast.show();
                break;
            case R.id.menu_edit2:
                Toast.makeText(getApplicationContext(), "Edit2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.meu_delete2:
                Toast.makeText(getApplicationContext(), "Delete2", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}

package aptech.storage.dvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void goToInternalStorage(View view) {
        startActivity(new Intent(this, StorageActivity.class));
    }

    public void goToExternalStorage(View view) {
        startActivity(new Intent(this, ExternalStorageActivity.class));
    }

}
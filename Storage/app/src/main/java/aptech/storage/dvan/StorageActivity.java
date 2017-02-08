package aptech.storage.dvan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StorageActivity extends AppCompatActivity {

    private EditText mEdtInput;
    private TextView mTvResult;
    private final String FILE_NAME = "internal_save.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mEdtInput = (EditText) findViewById(R.id.mEdtInputToSave);
        mTvResult = (TextView) findViewById(R.id.mTvResult);

        String path = "File path: " + getFilesDir().getAbsolutePath() + "\n";
        path += "Cache path : " + getCacheDir().getAbsolutePath();
        mTvResult.setText(path);
    }

    public void writeData(View view) {
        FileOutputStream fos = null;
        try {
            //String filePath = getFilesDir() + "/" + FILE_NAME;
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            String saveStr = mEdtInput.getText().toString();
            fos.write(saveStr.getBytes());
            mEdtInput.setText("");
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            try {
                if (fos != null) {

                    fos.close();

                }
            } catch (Exception ex) {

            }
            e.printStackTrace();
        }
    }

    public void readData(View view) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            int ch;
            String content = "";
            while ((ch = fis.read()) != -1) {
                content += (char) ch;
            }
            mTvResult.setText(content);

        } catch (Exception e) {
            try {
                if (fis != null)
                    fis.close();
            } catch (Exception ex) {

            }
            e.printStackTrace();
        }
    }
}
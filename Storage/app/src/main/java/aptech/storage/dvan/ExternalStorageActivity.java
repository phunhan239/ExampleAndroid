package aptech.storage.dvan;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class ExternalStorageActivity extends AppCompatActivity {

    private EditText mEdtInput;
    private TextView mTvResult;
    private final String FILE_NAME = "external_save.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mEdtInput = (EditText) findViewById(R.id.mEdtInputToSave);
        mTvResult = (TextView) findViewById(R.id.mTvResult);

        String path = "sdcard:" + Environment.getExternalStorageDirectory().getAbsolutePath() + "\n";
        path += "external File : " + getExternalFilesDir(null).getAbsolutePath() + "\n";
        path += "external Cache : " + getExternalCacheDir().getAbsolutePath() + "\n";
        mTvResult.setText(path);
    }

    public void writeData(View view) {
        FileWriter fw = null;
        try {
            String filePath = getExternalFilesDir(null) + "/" + FILE_NAME;
            File file = new File(filePath);
            fw = new FileWriter(file);
            String content = mEdtInput.getText().toString();
            fw.write(content);

            mEdtInput.setText("");
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            fw.close();

        } catch (Exception e) {
            try {
                if (fw != null) fw.close();
            } catch (Exception ex) {

            }
            e.printStackTrace();
        }
    }

    public void readData(View view) {
        FileReader fd = null;
        try {

            String filePath = getExternalFilesDir(null) + "/" + FILE_NAME;
            File file = new File(filePath);

            fd = new FileReader(file);
            int ch;
            String content = "";
            while ((ch = fd.read()) != -1) {
                content += (char) ch;
            }
            mTvResult.setText(content);
            fd.close();
        } catch (Exception e) {
            try {
                if (fd != null) fd.close();
            } catch (Exception ex) {

            }
            e.printStackTrace();
        }
    }
}
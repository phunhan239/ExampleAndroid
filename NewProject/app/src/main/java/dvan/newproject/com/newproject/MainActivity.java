package dvan.newproject.com.newproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mSingleChoiceIndex;
    private String[] mSubject;
    private int indexTemp;

    private boolean[] mSelectedMultiChoice;
    private boolean[] mSelectedMultiChoiceTemp;


    private ProgressDialog mWaitingDialog;
    private ProgressDialog mDownloadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubject = getResources().getStringArray(R.array.subject);
        mSingleChoiceIndex = 1;//default

        mSelectedMultiChoice = new boolean[mSubject.length];
        mSelectedMultiChoiceTemp = new boolean[mSubject.length];

        Button ratingbtn = (Button) findViewById(R.id.rating_btn);
        Button singleChoice_btn = (Button) findViewById(R.id.singleChoice_btn);
        Button login_dialog_btn = (Button) findViewById(R.id.login_dialog_btn);

        EditText edtusername = (EditText) findViewById(R.id.user_edt);
        EditText edtpassword = (EditText) findViewById(R.id.pass_edt);

        Button waitingbtn = (Button) findViewById(R.id.waiting_btn);
        Button downloadbtn = (Button) findViewById(R.id.download_dialog_btn);
    }

    public void goTorating(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Rating");
        builder.setMessage("Do yoi like My App?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn"));
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNeutralButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setCancelable(false);

        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (event.getKeyCode() == event.KEYCODE_BACK) {
                    dialog.dismiss();
                    return true;
                }

                return false;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void goTosingleChoice(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selection");

        builder.setSingleChoiceItems(mSubject, mSingleChoiceIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                indexTemp = which;
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSingleChoiceIndex = indexTemp;
            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void goTomultiChoice(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selection");

        for (int i = 0; i < mSelectedMultiChoice.length; i++) {
            mSelectedMultiChoiceTemp[i] = mSelectedMultiChoice[i];
        }


        builder.setMultiChoiceItems(mSubject, mSelectedMultiChoice, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                mSelectedMultiChoiceTemp[which] = isChecked;
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < mSelectedMultiChoice.length; i++) {
                    mSelectedMultiChoice[i] = mSelectedMultiChoiceTemp[i];
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void goToLoginDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");

        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View layout = layoutInflater.inflate(R.layout.dialoglogin, null);

        final EditText edtUser = (EditText) layout.findViewById(R.id.user_edt);
        final EditText edtPass = (EditText) layout.findViewById(R.id.pass_edt);
        final Button btnOk = (Button) layout.findViewById(R.id.ok_btn);

        builder.setView(layout);

        final AlertDialog dialog = builder.create();
        dialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Username: " + edtUser.getText().toString() +
                                "Password: " + edtPass.getText().toString(),
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void goToDatatimepicker(View view) {
        Intent intent = new Intent(getApplicationContext(), dtpickerActivity.class);
        startActivity(intent);
    }

    public void WaitingDialog(View view) {
        mWaitingDialog = new ProgressDialog(this);
        mWaitingDialog.setMessage("Waiting");
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();

        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mWaitingDialog.dismiss();
                } catch (Exception e) {
                    mWaitingDialog.dismiss();
                }
            }
        });
        mThread.start();

    }

    public void showDownloadDialog(View view) {
        mDownloadDialog = new ProgressDialog(this);
        mDownloadDialog.setTitle("Download");
        mDownloadDialog.setMax(100);
        mDownloadDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDownloadDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (mDownloadDialog.getProgress() < mDownloadDialog.getMax()) {
                    mDownloadDialog.incrementProgressBy(1);
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        mDownloadDialog.setMessage("Loading " + mDownloadDialog.getProgress());
                    }
                }
                if (mDownloadDialog.getProgress() == mDownloadDialog.getMax())
                    mDownloadDialog.dismiss();
            }
        }).start();
    }
}

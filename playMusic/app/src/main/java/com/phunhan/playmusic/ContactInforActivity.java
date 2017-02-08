package com.phunhan.playmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ContactInforActivity extends AppCompatActivity {

    private EditText edtname, edtphone;
    private RadioButton rdmale, rdfemale;
    private CheckBox ckVn, ckEng, ckJap;
    private Button btnOk;
    private TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactinfor);
        getWidgets();
        setWidgets();
        addWidgetListener();
    }

    public void getInfo(View view) {
        String content = getInfo();
        tvresult.setText(content);
    }

    public void sendInfo(View view) {
        //goi do dac
        Bundle bundle = new Bundle();
        bundle.putString("value: ", getInfo());

        Intent intent = new Intent(ContactInforActivity.this, InfoDetailActivity.class);
        startActivity(intent);
    }

    private String getInfo() {
        String info = "";
        info += "Name: " + edtname.getText() + "\n";
        info += "Phone: " + edtphone.getText() + "\n";
        info += "Gender: ";
        if (rdmale.isChecked()) {
            info += "Male" + "\n";
        }
        if (rdfemale.isChecked()) {
            info += "Female" + "\n";
        }

        info += "Language: ";
        if (ckVn.isChecked()) {
            info += "\n\t\t" + "Vietnamese";
        }
        if (ckEng.isChecked()) {
            info += "\n\t\t" + "English";
        }
        if (ckJap.isChecked()) {
            info += "\n\t\t" + "Japanese";
        }

        return info;
    }

    private void getWidgets() {
        edtname = (EditText) findViewById(R.id.edtName);
        edtphone = (EditText) findViewById(R.id.edtPhone);
        rdmale = (RadioButton) findViewById(R.id.rdMale);
        rdfemale = (RadioButton) findViewById(R.id.rdFeMale);
        ckVn = (CheckBox) findViewById(R.id.ckvn);
        ckEng = (CheckBox) findViewById(R.id.ckeng);
        ckJap = (CheckBox) findViewById(R.id.ckjap);
        tvresult = (TextView) findViewById(R.id.tvketqua);
        btnOk = (Button) findViewById(R.id.btnok);
    }

    private void setWidgets() {
    }

    private void addWidgetListener() {
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getInfo(view);
            }
        });
    }


    public void sendBackToMain(View view) {

        Intent intent = new Intent();

        String name = edtname.getText().toString();
        String phone = edtphone.getText().toString();
        String gender = rdmale.isChecked() ? "Male" : "FeMale";
        String language = "";
        if (ckVn.isChecked()) {
            language += "Vietnamese \t\t";
        }

        if (ckEng.isChecked()) {
            language += "English \t\t";
        }

        if (ckJap.isChecked()) {
            language += "Japanese \t\t";
        }

        Contact contact = new Contact(name,phone,gender,language);

        Bundle bundle = new Bundle();
        bundle.putSerializable("value",contact);
        intent.putExtra("data" , bundle);

        setResult(1, intent);
        finish();
    }
}

package com.phunhan.playmusic;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnshowContact;
    private Button btnGotoContactinfogetresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnshowContact = (Button) findViewById(R.id.btnShowContact);
        btnGotoContactinfogetresult = (Button) findViewById(R.id.btnGotoContactinfogetresult);

        btnshowContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoContact();
            }
        });

        btnGotoContactinfogetresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoContactInfoGetResult();
            }
        });
    }

    public void GotoGPT(View view) {
        Intent intent = new Intent(MainActivity.this, Gptb1Activity.class);
        startActivity(intent);
    }

    private void GotoContact() {
        Intent intent = new Intent(MainActivity.this, ContactInforActivity.class);
        startActivity(intent);
    }

    private void GotoContactInfoGetResult() {
        Intent intent = new Intent(MainActivity.this, ContactInforActivity.class);
        startActivityForResult(intent, 1000);
    }

    public void gotoGoogle(View view) {
        Uri uri = Uri.parse("https://www.google.com.vn");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openContact(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);

    }

    public void gotoSpinnerAutoComplete(View view) {
        Intent intent = new Intent(MainActivity.this, SpinnerAutoConpleteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == 1) {
                Bundle bundle = data.getBundleExtra("data");
                Contact contact = (Contact) bundle.getSerializable("value");

                Toast.makeText(getApplicationContext(), contact.getInfo(), Toast.LENGTH_LONG).show();

            }
        }
    }
}

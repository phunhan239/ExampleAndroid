package dvan.example.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ip_edt;
    private EditText pass_edt;
    private Button connect_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWidget();
        setWidget();
    }


    private void getWidget() {
        ip_edt = (EditText) findViewById(R.id.ip_edt);
        pass_edt = (EditText) findViewById(R.id.pass_edt);
        connect_btn = (Button) findViewById(R.id.connect_btn);
    }


    private void filterLogin() {

        //gia tri input hop le
        String valid_ip = "192.168.100.100";
        String valid_pass = "12345";

        //gettext tu edittext
        String input_ip = ip_edt.getText().toString().trim();
        String input_pass = pass_edt.getText().toString().trim();

        // input ip hop le khi co 3 dau cham && moi dau cham cach nhau 3 ki tu so && chuoi dai 15 ki tu
        int count = input_ip.length();

        if (count == 15 && input_ip.trim().equals(valid_ip) && input_pass.trim().equals(valid_pass)) {

            Toast.makeText(getApplicationContext(), " Login thành công", Toast.LENGTH_LONG).show();
            ip_edt.setText("");
            pass_edt.setText("");

            //chuyen sang tab thong tin hoa don
            Intent intent = new Intent(getApplicationContext(), ThongtinkhachhangActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Bạn đã nhập sai ip hoặc pass." + "\n" + "Lưu ý: Ip cần 12 kí tự số và 3 số cách nhau 1 dấu chấm", Toast.LENGTH_SHORT).show();
            ip_edt.setText("");
            pass_edt.setText("");
        }


    }

    //function main
    private void main(int key) {
        String chuoi = "";
        int len = 0;
        int k = kiemtrakitu(key);


        if (k == 1) {
            chuoi = ip_edt.getText().toString();
            len = chuoi.length();
            ip_edt.getText().replace(len - 1, len, "");
        } else if (k == 2) {

            chuoi = ip_edt.getText().toString();
            len = ip_edt.getText().length();

            String[] splits = chuoi.split("\\.");
            for (int i = 0; i < splits.length; i++) {
                if (Integer.valueOf(splits[i]) > 255 && splits[i].length() < 4) {
                    ip_edt.getText().replace(len - 1, len, "");
                }
            }

            int so = kiemtrachuoi(len);
            if (so == 1) {
                ip_edt.getText().insert(len - 1, ".");
            } else if (so == 2) {
                len = ip_edt.getText().length();
                ip_edt.getText().replace(len - 1, len, "");
            }

        }

    }

    //function check len of chuoi . this is attemp to define position to place dot digit
    private int kiemtrachuoi(int len) {
        switch (len) {
            case 4:
            case 8:
            case 12:
                return 1;
            case 16:
                return 2;
            default:
                return 5;
        }
    }

    //function check char which input
    private int kiemtrakitu(int key) {

        switch (key) {
            case 46:
                return 1;
            default:
                return 2;
        }
    }

    private void setWidget() {
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterLogin();
            }
        });

        ip_edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chuoi = ip_edt.getText().toString();
                int len = chuoi.length();
                if (len > 0) {
                    int keyCode = Integer.valueOf(chuoi.charAt(len - 1));
                    main(keyCode);
                }
            }
        });

        pass_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chuoi = pass_edt.getText().toString();
                int len = chuoi.length();

                if (len > 0) {
                    if (len == 6) {
                        pass_edt.getText().replace(len - 1, len, "");
                    }
                }
            }
        });


    }
}

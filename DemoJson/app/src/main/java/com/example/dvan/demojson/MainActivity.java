package com.example.dvan.demojson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView mLvContacts;
    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<HashMap<String, String>> mListContacts;
    private static final String jsonContacts = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvContacts = (ListView) findViewById(R.id.lvContacts);
        mListContacts = new ArrayList<>();
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler mHttpHandler = new HttpHandler();
            //Making a request to url and getting response
            String mUrl = "http://api.androidhive.info/contacts/";
            String jsonStr = mHttpHandler.makeServiceCall(mUrl);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    //Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray(jsonContacts);
                    //looping through all contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject contact = contacts.getJSONObject(i);
                        String id = contact.getString("id");
                        String name = contact.getString("name");
                        String email = contact.getString("email");
                        String address = contact.getString("address");
                        String gender = contact.getString("gender");
                        // Phone node is JSON Object
                        JSONObject phone = contact.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");
                        //tmp hash map for single contact
                        HashMap<String, String> c = new HashMap<>();
                        //adding each child node to Hashmap key => value
                        c.put("id", id);
                        c.put("name", name);
                        c.put("email", email);
                        c.put("mobile", mobile);
                        //adding contact to mListContacts
                        mListContacts.add(c);
                    }
                } catch (final Exception e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, mListContacts,
                    R.layout.item_list, new String[]{"email", "mobile"}
                    , new int[]{R.id.tvEmail, R.id.tvMobile});
            mLvContacts.setAdapter(adapter);
        }
    }
}

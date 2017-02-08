package contactmanagement.learn.aptech.contactmanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import contactmanagement.learn.aptech.contactmanagement.Adapter.ContactAdapter;
import contactmanagement.learn.aptech.contactmanagement.controller.ContactOperation;
import contactmanagement.learn.aptech.contactmanagement.model.Contact;
import contactmanagement.learn.aptech.contactmanagement.sqlhelper.SqliteHelper;

public class MainActivity extends AppCompatActivity {
    private ListView mLvContact;
    private ContactAdapter mAdapter;
    private ContactOperation mContactOperation;
    private ArrayList<Contact> mContactList;
    private int mCurrentContactIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getWidget();
        setWidget();
        addWidgetsListener();
//        ContactOperation contactOperation = ContactOperation.getInstance();
//        contactOperation.add("Nhan", "0905417560");
//        contactOperation.add("Nga", "01224768970");
//        ArrayList<Contact> list = contactOperation.get();
//        for (Contact c : list) {
//            System.out.println(c.toString());
//        }
//        Contact contact = list.get(0);
//        contact.setName("New Name");
//        contactOperation.update(contact);
//        list = contactOperation.get();
//        for (Contact c : list) {
//            System.out.println(c.toString());
//        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        Contact contact = mContactList.get(mCurrentContactIndex);
        menu.setHeaderTitle(contact.getName());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_edit:
                showEditContact();
                break;
            case R.id.menu_delete:
                System.out.println("delete");
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void setWidget() {
        mLvContact.setAdapter(mAdapter);
    }

    private void addWidgetsListener() {
        mLvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentContactIndex = position;
                return false;
            }
        });
        registerForContextMenu(mLvContact);
    }

    public void addContact(View view) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View layout = mLayoutInflater.inflate(R.layout.dialog_add_contact, null);
        final EditText edtName = (EditText) layout.findViewById(R.id.edtName);
        final EditText edtPhone = (EditText) layout.findViewById(R.id.edtPhone);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Contact");
        builder.setView(layout);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                mContactOperation.add(name, phone);
                //refresh list view
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public void showEditContact() {
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View layout = mLayoutInflater.inflate(R.layout.dialog_add_contact, null);
        final EditText edtName = (EditText) layout.findViewById(R.id.edtName);
        final EditText edtPhone = (EditText) layout.findViewById(R.id.edtPhone);
        final Contact contact = mContactList.get(mCurrentContactIndex);
        edtName.setText(contact.getName());
        edtPhone.setText(contact.getPhone());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");
        builder.setView(layout);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                contact.setName(name);
                contact.setPhone(phone);
                //update
                mContactOperation.update(contact);
                //refresh list view
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void getWidget() {
        mLvContact = (ListView) findViewById(R.id.lvContact);
    }

    private void init() {
        SqliteHelper.init(this);
        mContactOperation = ContactOperation.getInstance();
        mContactOperation.load();
        mContactList = mContactOperation.get();
        mAdapter = new ContactAdapter(getApplicationContext(), R.layout.item_contact, mContactList);
    }
}

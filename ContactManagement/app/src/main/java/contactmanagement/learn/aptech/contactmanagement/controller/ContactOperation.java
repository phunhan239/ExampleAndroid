package contactmanagement.learn.aptech.contactmanagement.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import contactmanagement.learn.aptech.contactmanagement.model.Contact;
import contactmanagement.learn.aptech.contactmanagement.sqlhelper.SqliteHelper;

/**
 * Created by D'van on 1/17/2017.
 */

public class ContactOperation {
    private static ContactOperation instance = null;

    private SqliteHelper mSqliteHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private ArrayList<Contact> mList;

    private String[] CONTACT_COLUMES = {
            Contact.FIELD_ID,
            Contact.FIELD_NAME,
            Contact.FIELD_PHONE
    };


    private ContactOperation() {
        mSqliteHelper = SqliteHelper.getInstance();
        mList = new ArrayList<>();
    }

    public static ContactOperation getInstance() {
        if (instance == null) {
            instance = new ContactOperation();
        }
        return instance;
    }


    //----------------------///

    public void load() {
        mList.clear();
        mSqLiteDatabase = mSqliteHelper.getReadableDatabase();

        Cursor cursor = mSqLiteDatabase.query(SqliteHelper.TABLE_CONTACT
                , CONTACT_COLUMES
                , null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(Contact.FIELD_ID));
                String name = cursor.getString(cursor.getColumnIndex(Contact.FIELD_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(Contact.FIELD_PHONE));
                Contact contact = new Contact(id, name, phone);

                mList.add(contact);

                cursor.moveToNext();
            }
            cursor.close();
        }
        mSqLiteDatabase.close();
        mSqliteHelper.close();
    }

    public ArrayList<Contact> get() {

        if (mList.isEmpty()) {
            load();
        }
        return mList;
    }

    public void add(String name, String phone) {

        mSqLiteDatabase = mSqliteHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Contact.FIELD_NAME, name);
        value.put(Contact.FIELD_PHONE, phone);

        int id = (int) mSqLiteDatabase.insert(SqliteHelper.TABLE_CONTACT, null, value);

        mSqLiteDatabase.close();
        mSqliteHelper.close();

        //Update list
        Contact contact = new Contact(id, name, phone);
        mList.add(contact);
    }

    public void update(Contact contact) {
        mSqLiteDatabase = mSqliteHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Contact.FIELD_NAME, contact.getName());
        value.put(Contact.FIELD_PHONE, contact.getPhone());

        mSqLiteDatabase.update(SqliteHelper.TABLE_CONTACT, value
                , Contact.FIELD_ID + " = ?"
                , new String[]{String.valueOf(contact.getId())});

        mSqLiteDatabase.close();
        mSqliteHelper.close();

        //update list  --> dang cap nhat mot doi tuong reference thi tu dong cap nhat vao list
    }

    public void delete(Contact contact) {
        mSqLiteDatabase = mSqliteHelper.getWritableDatabase();

        mSqLiteDatabase.delete(SqliteHelper.TABLE_CONTACT
                , Contact.FIELD_ID + " = ?"
                , new String[]{String.valueOf(contact.getId())});

        mSqLiteDatabase.close();
        mSqliteHelper.close();

        //update list
        mList.remove(contact);
    }
}

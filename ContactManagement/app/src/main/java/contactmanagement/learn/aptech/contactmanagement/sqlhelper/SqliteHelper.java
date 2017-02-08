package contactmanagement.learn.aptech.contactmanagement.sqlhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import contactmanagement.learn.aptech.contactmanagement.model.Contact;

/**
 * Created by D'van on 1/17/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static SqliteHelper instance = null;
    private static Context mContext;
    private static final String DATABASE_NAME = "ozawa";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACT = "Contact";

    public SqliteHelper() {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SqliteHelper getInstance() {
        if (instance == null) {
            instance = new SqliteHelper();
        }
        return instance;
    }

    public static void init(Context context) {
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteTables(db);
        createTables(db);
    }

    /**
     * @param db
     */

    private void createTables(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE " + TABLE_CONTACT
                + " ( "
                + Contact.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contact.FIELD_NAME + " TEXT, "
                + Contact.FIELD_PHONE + " TEXT "
                + " ) ";

        System.out.println(sqlStr);
        db.execSQL(sqlStr);
    }

    private void deleteTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CONTACT);
    }
}

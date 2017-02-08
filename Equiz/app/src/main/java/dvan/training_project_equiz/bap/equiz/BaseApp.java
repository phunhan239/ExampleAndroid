package dvan.training_project_equiz.bap.equiz;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;

/**
 * Created by D'van on 1/14/2017.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Stetho.initializeWithDefaults(this);
    }
}

package chilangolabs.androidbiachack;

import android.app.Application;

import chilangolabs.androidbiachack.api.Api;

/**
 * Created by Gorro on 20/06/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Api.initVolley(this);
    }
}

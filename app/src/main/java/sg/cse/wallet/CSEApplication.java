package sg.cse.wallet;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.Intent;

import sg.cse.wallet.pincode.PinCode;
import sg.cse.wallet.prefs.Prefs;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class CSEApplication extends Application implements LifecycleObserver {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        CSEApplication.context = getApplicationContext();
        Prefs.getInstance().init(this);

    }

    public static Context getAppContext() {
        return CSEApplication.context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onAppBackgrounded() {

    }

    @SuppressLint("ResourceType")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onAppForegrounded() {
        if (!Prefs.getInstance().getPinCode().equals("")) {
            if(PinCode.active == false){
                Intent intent = new Intent(this, PinCode.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
            }else {

            }
        }
    }
}

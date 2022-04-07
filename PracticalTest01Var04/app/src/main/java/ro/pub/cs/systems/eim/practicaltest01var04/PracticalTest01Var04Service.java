package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service  extends Service {
    private  ProcessingThread processingThread;

    @Override
    public void onCreate() {
        super.onCreate();
//            Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
//            Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
//            Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
//            Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
//            Log.d(Constants.TAG, "onDestroy() method was invoked");
        processingThread.stopThread();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
           Log.d(Constants.TAG, "onStartCommand() method was invoked");
        String s1 = intent.getStringExtra("Nume");
        String s2 = intent.getStringExtra("Grupa");

        processingThread = new ProcessingThread(this, s1, s2);
        processingThread.start();
//
        return START_REDELIVER_INTENT;
    }

}

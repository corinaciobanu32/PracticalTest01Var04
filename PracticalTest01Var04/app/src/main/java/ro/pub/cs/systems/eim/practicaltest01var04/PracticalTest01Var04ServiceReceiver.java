package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PracticalTest01Var04ServiceReceiver extends BroadcastReceiver {

    public PracticalTest01Var04ServiceReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Constants.NUME_ACTION.equals(action)) {
            Log.d(Constants.TAG, "ACTION 0 invoked");
        }
        if (Constants.GRUPA_ACTION.equals(action)) {
            Log.d(Constants.TAG, "ACTION 1 invoked");
        }

    }
}

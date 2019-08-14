package ch.hevs.vr.synchrovr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class FinishBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("TEST", "finish broadcast received");
        Log.e("TEST", "finish broadcast received2");
        Log.e("TEST", "finish broadcast received3");





/*
        Intent starter = new Intent(context, SynchroIntentService.class);
        context.startForegroundService(starter);
        SynchroIntentService.enqueueWork(context, starter);
*/

    }
}

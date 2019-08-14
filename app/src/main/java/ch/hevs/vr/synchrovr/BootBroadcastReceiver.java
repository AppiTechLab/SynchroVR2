package ch.hevs.vr.synchrovr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {

        Log.e("TEST", "boot broadcast received");



        Intent starter = new Intent(context, SynchroIntentService.class);
        context.startForegroundService(starter);
        SynchroIntentService.enqueueWork(context, starter);



    }
}

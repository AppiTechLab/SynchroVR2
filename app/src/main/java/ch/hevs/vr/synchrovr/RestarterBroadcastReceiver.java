package ch.hevs.vr.synchrovr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(RestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
        Intent starter = new Intent(context, SynchroIntentService.class);
        context.startForegroundService(starter);
        SynchroIntentService.enqueueWork(context, starter);

    }
}

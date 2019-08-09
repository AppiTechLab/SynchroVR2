package ch.hevs.vr.synchrovr;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {

        Log.e("TEST", "broadcast test");
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setComponent(new ComponentName("com.oculus.UnitySample","com.unity3d.player.UnityPlayerActivity"));
        context.startActivity(i);
    }
}

package ch.hevs.vr.synchrovr;

import androidx.legacy.content.WakefulBroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.content.ComponentName;

public class BootBroadcastReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.e("TEST", "broadcast test");
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setComponent(new ComponentName("com.oculus.UnitySample","com.unity3d.player.UnityPlayerActivity"));
        context.startActivity(i);
    }
}

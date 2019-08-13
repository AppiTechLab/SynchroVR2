package ch.hevs.vr.synchrovr;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;

import java.net.URISyntaxException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SynchroIntentService extends JobIntentService {


    static final int JOB_ID = 1000;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    private AsyncHttpClient aClient = new SyncHttpClient();
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://vrcransmontana.ehealth.hevs.ch/");
        } catch (URISyntaxException e) {}
    }

    public SynchroIntentService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, SynchroIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.e("TEST", "Intent active");
        String someUrlHere="https://vrcransmontana.ehealth.hevs.ch/";


        mSocket.on("begin-1", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        Log.e("TEST", "BEGIN 1");
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (launchIntent != null) {
                            startActivity(launchIntent);
                        } else {
                            Log.e("TEST", "There is no package available in android");
                        }

                    };

                }
        );

        mSocket.on("begin-2", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        Log.e("TEST", "BEGIN 2");
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (launchIntent != null) {
                            startActivity(launchIntent);
                        } else {
                            Log.e("TEST", "There is no package available in android");
                        }
                    };

                }
        );

        mSocket.on("begin-3", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        Log.e("TEST", "BEGIN 3");
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (launchIntent != null) {
                            startActivity(launchIntent);
                        } else {
                            Log.e("TEST", "There is no package available in android");
                        }
                    };

                }
        );

        mSocket.on("begin-4", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        Log.e("TEST", "BEGIN 4");
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (launchIntent != null) {
                            startActivity(launchIntent);
                        } else {
                            Log.e("TEST", "There is no package available in android");
                        }
                    };

                }
        );
        mSocket.connect();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent(this, RestarterBroadcastReceiver.class);

        sendBroadcast(broadcastIntent);
    }
}

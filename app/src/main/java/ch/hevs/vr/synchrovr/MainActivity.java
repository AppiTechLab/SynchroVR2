package ch.hevs.vr.synchrovr;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Intent mServiceIntent;
    private SynchroIntentService mSynchroService;
    Context ctx;
    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        mSynchroService = new SynchroIntentService();
        mServiceIntent = new Intent(getCtx(), mSynchroService.getClass());
        startForegroundService(mServiceIntent);
        SynchroIntentService.enqueueWork(this, mServiceIntent);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        /*Intent intent = new Intent(this, SynchroIntentService.class);
        startService(intent);*/

    }


}

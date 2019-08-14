package ch.hevs.vr.synchrovr;

import android.content.Context;
import android.hardware.Sensor;

import java.io.FileNotFoundException;
import java.io.IOException;

import ch.hevs.vr.synchrovr.Logger.control.LogsManager;
import ch.hevs.vr.synchrovr.Logger.control.PreferencesManager;
import ch.hevs.vr.synchrovr.Logger.control.Recorder;
import ch.hevs.vr.synchrovr.Logger.control.SensorsManager;
import ch.hevs.vr.synchrovr.Logger.model.sensors.AndroidSensor;

public class SensorsLogger {


    private static SensorsManager mSensorsManager;
    private static PreferencesManager mPreferencesManager;
    private static Recorder mRecorder;
    private static LogsManager mLogsManager;

    public static void startLog(Context ctx){

        mSensorsManager = new SensorsManager(ctx);
        mLogsManager = new LogsManager(ctx, mSensorsManager);
        mPreferencesManager = new PreferencesManager(ctx, mSensorsManager);


        ch.hevs.vr.synchrovr.Logger.model.sensors.Sensor accel = mSensorsManager.getSensorByType(Sensor.TYPE_ACCELEROMETER);
        mPreferencesManager.setChecked(accel, true);

        ch.hevs.vr.synchrovr.Logger.model.sensors.Sensor gyro = mSensorsManager.getSensorByType(Sensor.TYPE_GYROSCOPE);
        mPreferencesManager.setChecked(gyro, true);

        mRecorder = new Recorder(ctx, mLogsManager, mPreferencesManager);

        try {
            mRecorder.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void stopLog()
    {
        mRecorder.pause();
    }

    public static void save(){
        try {
            mRecorder.save("testFile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

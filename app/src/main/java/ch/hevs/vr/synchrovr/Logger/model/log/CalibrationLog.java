package ch.hevs.vr.synchrovr.Logger.model.log;

import android.content.Context;


import androidx.annotation.NonNull;

import org.ini4j.Wini;

import java.io.File;
import java.util.Set;

import ch.hevs.vr.synchrovr.Logger.model.WritableObject;
import ch.hevs.vr.synchrovr.Logger.model.sensors.Sensor;

public class CalibrationLog extends Log {

    public enum Type {GYROSCOPE, MAGNETOMETER, ACCELEROMETER}

    private transient Type mCalibrationType;

    public CalibrationLog(Type calibrationType, Set<Sensor> sensors) {
        super(sensors);
        mCalibrationType = calibrationType;
    }

    @Override
    public String toString() {
        return "CalibrationLog{" +
                "mCalibrationType=" + mCalibrationType +
                "} " + super.toString();
    }

    @Override
    public Wini generateIniFile(Context context, File file,
                                Set<WritableObject> writableObjects) {
        Wini wini = super.generateIniFile(context, file, writableObjects);
        wini.put("Settings", "Calibration", mCalibrationType);
        return wini;
    }


    public static CalibrationLog create(@NonNull Type calibrationType,
                                        @NonNull Set<Sensor> sensors) {

        switch (calibrationType) {
            case GYROSCOPE:
                return new CalibrationLog(Type.GYROSCOPE, sensors);
            case MAGNETOMETER:
                return new CalibrationLog(Type.MAGNETOMETER, sensors);
            case ACCELEROMETER:
                return new CalibrationLog(Type.ACCELEROMETER, sensors);
        }

        throw new RuntimeException("Should never happen");
    }
}
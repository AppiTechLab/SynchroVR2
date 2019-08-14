package ch.hevs.vr.synchrovr.Logger.model;

import android.content.Context;
import android.content.res.Resources;

import ch.hevs.vr.synchrovr.Logger.control.RecorderWriter;


public interface WritableObject {
    String getStorageFileName(Context context);

    String getWebPage(Resources resources);

    String getFileExtension();
}
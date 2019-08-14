package ch.hevs.vr.synchrovr.Logger.model;

import android.content.res.Resources;

import ch.hevs.vr.synchrovr.Logger.control.RecorderWriter;

/**
 * Object writable by {@link RecorderWriter}
 */
public interface FieldsWritableObject extends WritableObject {

    String getFieldsDescription(Resources resources);

    String[] getFields(Resources resources);

}
package ch.hevs.vr.synchrovr;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static void copy(File source, File destination)
    {
        try {


            if (source.exists()) {

                InputStream in = new FileInputStream(source);
                //File dest = new File(getExternalFilesDir(null), "DemoFile.jpg");
                OutputStream out = new FileOutputStream(destination);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                Log.v("copy", "Copy file successful.");

            } else {
                Log.v("copy", "Copy file failed. Source file missing.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

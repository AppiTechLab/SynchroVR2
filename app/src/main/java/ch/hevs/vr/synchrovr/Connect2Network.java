package ch.hevs.vr.synchrovr;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiManager;
import android.util.Log;

import ch.hevs.vr.synchrovr.retrofit.FileUploadService;
import ch.hevs.vr.synchrovr.retrofit.RetrofitApiClient;
import retrofit2.Retrofit;

public class Connect2Network {

    private static String networkSSIDHES = "test";
    private static String networkPasskey = "pass";

    private static String networkSSIDWPA = "AndroidHotspot3038";
    private static String networkPasskeyWPA = "pass";


    public static void connectHESNetwork(Context ctx)
    {

        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\"" + networkSSIDHES + "\"";
        //config.BSSID = Bssid;
        config.priority = 1;
        String networkIdentity = "";
        networkPasskey = "";
        config.status = WifiConfiguration.Status.ENABLED;
        WifiEnterpriseConfig enterpriseConfig = new WifiEnterpriseConfig();
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.IEEE8021X);
        enterpriseConfig.setIdentity(networkIdentity);
        enterpriseConfig.setPassword(networkPasskey);
        enterpriseConfig.setEapMethod(WifiEnterpriseConfig.Eap.PEAP);
        enterpriseConfig.setPhase2Method(WifiEnterpriseConfig.Phase2.MSCHAPV2);
        config.enterpriseConfig = enterpriseConfig;
        WifiManager myWifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        int id = myWifiManager.addNetwork(config);
        Log.d("addNetwork", "# addNetwork returned " + id);

        myWifiManager.enableNetwork(id, true);
    }

    public static void connectWPA2(Context ctx)
    {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "\"" + networkSSIDWPA + "\"";
        wifiConfiguration.preSharedKey = "\"" + networkPasskeyWPA + "\"";

        WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        int networkId = wifiManager.addNetwork(wifiConfiguration);
        if (networkId != -1)
        {
            wifiManager.enableNetwork(networkId, true);
            // Use this to permanently save this network
            // Otherwise, it will disappear after a reboot
            wifiManager.saveConfiguration();
        }
    }

    public static void sendFile(Context ctx, String fileName) {
        String path = ctx.getFilesDir()+ ctx.getString(R.string.sensors_record_dir);
        Intent mIntent = new Intent(ctx, FileUploadService.class);
        mIntent.putExtra("mFilePath", path + fileName);
        FileUploadService.enqueueWork(ctx, mIntent);
}


}

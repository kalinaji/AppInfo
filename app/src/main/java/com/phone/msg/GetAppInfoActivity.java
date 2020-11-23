package com.phone.msg;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 功能实现类
 */
public class GetAppInfoActivity extends AppCompatActivity {

    private TelephonyManager mPhoneManager;
    private WifiManager mWifiManager;
    private Display mDisplay;
    private DisplayMetrics mMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getappinfo);

        mPhoneManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mDisplay = getWindowManager().getDefaultDisplay();
        mMetrics = getResources().getDisplayMetrics();

        init();
    }


    private void init() {
        DisplayMetrics book = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(book);
        try {
            Class localClass = Class.forName("android.os.SystemProperties");
            Object localObject1 = localClass.newInstance();
            Object localObject2 = localClass.getMethod("get", new Class[]{String.class, String.class}).invoke(localObject1, new Object[]{"gsm.version.baseband", "no message"});
            Object localObject3 = localClass.getMethod("get", new Class[]{String.class, String.class}).invoke(localObject1, new Object[]{"ro.build.display.id", ""});
            setEditText(R.id.get, localObject2 + "");
            setEditText(R.id.osVersion, localObject3 + "");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 获取网络连接管理者
        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // 获取网络的状态信息，有下面三种方式
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

        if (null == networkInfo) {
            setEditText(R.id.lianwang, "没有网络");
            setEditText(R.id.lianwangname, "无网络");
        } else {
            setEditText(R.id.lianwang, networkInfo.getType() + "");
            setEditText(R.id.lianwangname, networkInfo.getTypeName());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1008);
                return;
            }
        }
        setEditText(R.id.imei, mPhoneManager.getDeviceId());
        setEditText(R.id.deviceversion, mPhoneManager.getDeviceSoftwareVersion());
        setEditText(R.id.imsi, mPhoneManager.getSubscriberId());
        setEditText(R.id.number, mPhoneManager.getLine1Number());
        setEditText(R.id.simserial, mPhoneManager.getSimSerialNumber());
        setEditText(R.id.simoperator, mPhoneManager.getSimOperator());
        setEditText(R.id.simoperatorname, mPhoneManager.getSimOperatorName());
        setEditText(R.id.simcountryiso, mPhoneManager.getSimCountryIso());
        setEditText(R.id.workType, mPhoneManager.getNetworkType() + "");
        setEditText(R.id.netcountryiso, mPhoneManager.getNetworkCountryIso());
        setEditText(R.id.netoperator, mPhoneManager.getNetworkOperator());
        setEditText(R.id.netoperatorname, mPhoneManager.getNetworkOperatorName());
        setEditText(R.id.radiovis, Build.getRadioVersion());
        setEditText(R.id.wifimac, mWifiManager.getConnectionInfo().getMacAddress());
        setEditText(R.id.getssid, mWifiManager.getConnectionInfo().getSSID());
        setEditText(R.id.getbssid, mWifiManager.getConnectionInfo().getBSSID());
        setEditText(R.id.ip, mWifiManager.getConnectionInfo().getIpAddress() + "");
        setEditText(R.id.bluemac, BluetoothAdapter.getDefaultAdapter().getAddress());
        setEditText(R.id.bluname, BluetoothAdapter.getDefaultAdapter().getName());
        setEditText(R.id.cpu, getCpuName());
        setEditText(R.id.andrlid_id, Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        setEditText(R.id.serial, Build.SERIAL);
        setEditText(R.id.brand, Build.BRAND);
        setEditText(R.id.tags, Build.TAGS);
        setEditText(R.id.device, Build.DEVICE);
        setEditText(R.id.fingerprint, Build.FINGERPRINT);
        setEditText(R.id.bootloader, Build.BOOTLOADER);
        setEditText(R.id.release, Build.VERSION.RELEASE);
        setEditText(R.id.sdk, Build.VERSION.SDK);
        setEditText(R.id.sdk_INT, Build.VERSION.SDK_INT + "");
        setEditText(R.id.codename, Build.VERSION.CODENAME);
        setEditText(R.id.incremental, Build.VERSION.INCREMENTAL);
        setEditText(R.id.cpuabi, Build.CPU_ABI);
        setEditText(R.id.cpuabi2, Build.CPU_ABI2);
        setEditText(R.id.board, Build.BOARD);
        setEditText(R.id.model, Build.MODEL);
        setEditText(R.id.product, Build.PRODUCT);
        setEditText(R.id.type, Build.TYPE);
        setEditText(R.id.user, Build.USER);
        setEditText(R.id.disply, Build.DISPLAY);
        setEditText(R.id.hardware, Build.HARDWARE);
        setEditText(R.id.host, Build.HOST);
        setEditText(R.id.changshang, Build.MANUFACTURER);
        setEditText(R.id.phonetype, mPhoneManager.getPhoneType() + "");
        setEditText(R.id.simstate, mPhoneManager.getSimState() + "");
        setEditText(R.id.b_id, Build.ID);
        setEditText(R.id.gjtime, Build.TIME + "");
        setEditText(R.id.width, mDisplay.getWidth() + "");
        setEditText(R.id.height, mDisplay.getHeight() + "");
        setEditText(R.id.dpi, book.densityDpi + "");
        setEditText(R.id.density, book.density + "");
        setEditText(R.id.xdpi, book.xdpi + "");
        setEditText(R.id.ydpi, book.ydpi + "");
        setEditText(R.id.scaledDensity, book.scaledDensity + "");

        //setEditText(R.id.wl,getNetworkState(this)+"");
        // 方法2
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        setEditText(R.id.xwidth, width + "");
        setEditText(R.id.xheight, height + "");

    }

    private void setEditText(int id, String s) {
        ((TextView) findViewById(id)).setText(s);
    }

    /**
     * 获取CPU型号
     */
    public static String getCpuName() {

        String str1 = "/proc/cpuinfo";
        String str2;

        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr);
            while ((str2 = localBufferedReader.readLine()) != null) {
                if (str2.contains("Hardware")) {
                    return str2.split(":")[1];
                }
            }
            localBufferedReader.close();
        } catch (IOException e) {

        }
        return null;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1008) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "已获取权限了", Toast.LENGTH_SHORT).show();
                init();
            }
        }
    }

    public void goToPhoneMessage(View view) {
        // 跳转获取手机信息页面
        startActivity(new Intent(this, PhoneMessageActivity.class));
    }
}

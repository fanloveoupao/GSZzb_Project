package app.gseasypro.com.utils.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.lang.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by fan-gk on 2017/4/14.
 */


public class DeviceUtil {

    public static String getDeviceDisplay(Context context) {
        WindowManager wMng = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wMng.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        String dd = metrics.widthPixels + "X" + metrics.heightPixels + "/"
                + metrics.scaledDensity;

        try {
            dd = URLEncoder.encode(dd, "utf8");
        } catch (UnsupportedEncodingException e) {
            // nothing
        }
        return dd;
    }

    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        String model = android.os.Build.MODEL;
        try {
            model = URLEncoder.encode(model, "utf8");
        } catch (UnsupportedEncodingException e) {
            // do nothing
        }
        return model;
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceName() {
        String manufacturer = getDeviceManufacturer();
        String model = getDeviceModel();
        if ((model != null) && model.startsWith(manufacturer)) {
            return StringUtil.capitalize(model);
        } else if (manufacturer != null) {
            return StringUtil.capitalize(manufacturer) + " " + model;
        } else {
            return "Unknown";
        }
    }
}


package app.gseasypro.com.utils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ConnectionStateChangedEvent;
import com.example.utils.EventBusUtil;

import app.gseasypro.com.utils.utils.ConnUtil;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class SystemReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ConnectivityManager.CONNECTIVITY_ACTION:
                onConnectivityAction(context);
                break;
        }
    }

    private void onConnectivityAction(Context context) {
        NetworkInfo networkInfo = ConnUtil.getActiveNetworkInfo(context);
        boolean isConnected = false;
        int type = -1;
        if (networkInfo != null) {
            isConnected = networkInfo.isConnected();
            type = networkInfo.getType();
        }
        EventBusUtil.getInstance().post(new ConnectionStateChangedEvent(type, isConnected));
    }
}

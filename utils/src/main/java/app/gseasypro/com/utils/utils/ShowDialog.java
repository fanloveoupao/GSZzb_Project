package app.gseasypro.com.utils.utils;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import app.gseasypro.com.utils.DialogUtil;

/**
 * Created by fan-gk on 2017/3/3.
 */


public class ShowDialog {

    public static void call(@NonNull final FragmentActivity context, @NonNull final CharSequence content, final String tel){
        DialogUtil.softTwoBtnDialog(context, content, "取消", "呼叫", new Runnable() {
            @Override
            public void run() {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
                context.startActivity(call);
            }
        });
    }

}
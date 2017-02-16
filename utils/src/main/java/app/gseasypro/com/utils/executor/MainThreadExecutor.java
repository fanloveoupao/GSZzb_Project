package app.gseasypro.com.utils.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by fan-gk on 2017/2/9.
 */

final class MainThreadExecutor implements Executor {
    private Handler handler;

    public MainThreadExecutor(){
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }
}

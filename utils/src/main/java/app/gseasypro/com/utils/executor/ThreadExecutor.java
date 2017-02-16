package app.gseasypro.com.utils.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by fan-gk on 2017/2/9.
 */


public final class ThreadExecutor {
    private static Executor asyncQueueExcutor;
    private static Executor asyncExcutor;
    private static Executor mainExcutor;

    static {
        asyncQueueExcutor = Executors.newSingleThreadExecutor();
        asyncExcutor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {
                    @Override
                    public void run() {
                        r.run();
                    }
                }, "AsyncExecutor Runnable");
            }
        });
        mainExcutor = new MainThreadExecutor();
    }

    public static Executor getAsyncExcutor(){
        return asyncExcutor;
    }

    public static void runInAsync(@NonNull Runnable runnable){
        asyncExcutor.execute(runnable);
    }

    public static void runInAsync(@NonNull Runnable runnable, long delayTime){
        asyncExcutor.execute(new DelayRunnable(runnable, delayTime));
    }

    public static void runInMain(@NonNull Runnable runnable){
        mainExcutor.execute(runnable);
    }

    public static void runInAsyncQueue(@NonNull Runnable runnable){
        asyncQueueExcutor.execute(runnable);
    }

    public static void runInAsyncQueue(@NonNull Runnable runnable, long delayTime){
        asyncQueueExcutor.execute(new DelayRunnable(runnable, delayTime));
    }

    private static class DelayRunnable implements Runnable{

        private final long delayTime;
        private final Runnable runnable;

        public DelayRunnable(@NonNull Runnable runnable, long delayTime) {
            this.runnable = runnable;
            this.delayTime = delayTime;
        }

        @Override
        public void run() {
            if(delayTime > 0) {
                try {
                    Thread.currentThread().sleep(delayTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runnable.run();
        }
    }
}
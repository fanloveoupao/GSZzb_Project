package app.gseasypro.com.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.ActionRequest;
import com.example.ActionRunnable;
import com.example.IView;
import com.example.exceptions.NetworkException;
import com.example.exceptions.TgnetException;
import com.example.exceptions.UnloginException;

import java.util.ArrayDeque;
import java.util.Queue;

import app.gseasypro.com.utils.executor.ThreadExecutor;
import app.gseasypro.com.utils.ui.KeyBoardUtils;
import app.gseasypro.com.utils.ui.widget.IIntentInterceptor;
import app.gseasypro.com.utils.utils.PermissionHelper;
import app.gseasypro.com.utils.widget.ActionLoadingDialogFragment;

/**
 * Created by fan-gk on 2017/2/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView {

    private PermissionHelper permissionHelper = PermissionHelper.createActivityHelper(this);

    public PermissionHelper getPermissionHelper() {
        return permissionHelper;
    }

    private final Queue<Runnable> saveInstanceStateRunnables = new ArrayDeque<>();
    private boolean isSavedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void launch(final Intent intent, final IIntentInterceptor intentInterceptor, final boolean finish) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                if (intentInterceptor != null)
                    intentInterceptor.intercept(intent);
                BaseActivity.this.startActivity(intent);
                if (finish)
                    finish();
            }
        });
    }

    public void launch(String action, final boolean finish) {
        Intent intent = new Intent(action);
        launch(intent, null, finish);
    }

    public void launch(final Class<?> activity, final boolean finish) {
        launch(activity, null, finish);
    }

    public void launch(Class<?> activity, IIntentInterceptor intentInterceptor, boolean finish) {
        Intent intent = new Intent(BaseActivity.this, activity);
        launch(intent, intentInterceptor, finish);
    }

    public void launch(final Class<?> activity, final IIntentInterceptor intentInterceptor) {
        Intent intent = new Intent(BaseActivity.this, activity);
        launch(intent, intentInterceptor, false);
    }

    public void launchForResult(final Class<?> activity, final int requestCode) {
        launchForResult(activity, requestCode, null);
    }

    public void launchForResult(final Class<?> activity, final int requestCode, final IIntentInterceptor intentInterceptor) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseActivity.this, activity);
                if (intentInterceptor != null)
                    intentInterceptor.intercept(intent);
                BaseActivity.this.startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    public void hideKeyBoard() {
        KeyBoardUtils.hideKeyBoard(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.getBaseActivityHelper().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    final public void runAction(final ActionRequest request) {
        if (request != null) {
            final ActionRunnable runnable = request.getRunnable();
            if (runnable != null) {
                if (request.getRunType() == ActionRequest.RUN_TYPE_LOCK) {
                    try {
                        runnable.run();
                        request.setResultSuccessful();
                    } catch (Exception ex) {
                        onException(request, ex);
                    }
                } else {
                    final boolean isShowProgress = request.getRunType() == ActionRequest.RUN_TYPE_LOADING;
                    if (isShowProgress)
                        request.getView().showLoadingView(request);
                    ThreadExecutor.runInAsync(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                runnable.run();
                                request.setResultSuccessful();
                            } catch (Exception e) {
                                if (request.getRunType() != ActionRequest.RUN_TYPE_BACKGROUND)
                                    onException(request, e);
                            } finally {
                                if (isShowProgress)
                                    request.getView().dismissLoadingView();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void showLoadingView(final ActionRequest request) {
        Log.d("safeRunAfter", "show loading");
        safeRunAfterSaveInstanceState(new Runnable() {
            @Override
            public void run() {
                ThreadExecutor.runInMain(new Runnable() {
                    @Override
                    public void run() {
                        ActionLoadingDialogFragment.singleShow(BaseActivity.this, request);
                    }
                });
            }
        });
    }

    @Override
    public void dismissLoadingView() {
        Log.d("safeRunAfter", "dismiss loading");
        safeRunAfterSaveInstanceState(new Runnable() {
            @Override
            public void run() {
                ThreadExecutor.runInMain(new Runnable() {
                    @Override
                    public void run() {
                        ActionLoadingDialogFragment.dismiss(BaseActivity.this);
                    }
                });
            }
        });
    }

    protected boolean onBeforeBackPressed() {
        if (ActivitiesHelper.size() == 1) {
            moveTaskToBack(true);
            return true;
        } else {
            return false;
        }
    }

    private void onException(ActionRequest request, Exception e) {
        if (e instanceof NetworkException) {
            if (request != null && request.getHandleOnExceptionType() == ActionRequest.HANDLE_ON_EXCEPTION_USER_RELOAD) {
                request.getView().onException(request, (NetworkException) e);
            } else {
                onException(e);
            }
        } else {
            onException(e);
        }
    }

    @Override
    public void onException(Exception e, boolean finish) {
        e.printStackTrace();
        if (e instanceof UnloginException) {

        } else if (e instanceof TgnetException)
            onException((TgnetException) e, finish);
        else if (e instanceof NetworkException)
            onException((NetworkException) e, finish);
        else
            showExceptionDialog(e.toString(), finish);
    }

    @Override
    public void onException(ActionRequest request, NetworkException e) {
        onException(e, request != null && request.getBackOnExceptionType() == ActionRequest.BACK_ON_EXCEPTION_TYPE_FINISH_PARENT);
    }

    @Override
    public void onException(Exception e) {
        onException(e, false);
    }

    protected void onException(TgnetException e, boolean finish) {
        switch (e.getErrorCode()) {
            case SERVER:
                showExceptionDialog("系统出现异常，有疑问请联系客服 020-66351168", finish);
                break;
            default:
                showExceptionDialog(e.getMessage(), finish);
                break;
        }
    }

    protected void onException(final NetworkException e, boolean finish) {
        if (!finish)
            ThreadExecutor.runInMain(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        else
            showExceptionDialog(e.getMessage(), true);
    }

    @Override
    public void onWarn(final String message) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort(BaseActivity.this, message);
            }
        });
    }

    private void showExceptionDialog(final String msg, final boolean finish) {
        safeRunAfterSaveInstanceState(new Runnable() {
            @Override
            public void run() {
                ThreadExecutor.runInMain(new Runnable() {
                    @Override
                    public void run() {

                        if (!finish)
                            DialogUtil.softOneBtnDialog(BaseActivity.this, msg);
                        else
                            DialogUtil.hardTwoBtnDialog(BaseActivity.this, msg, new Runnable() {
                                @Override
                                public void run() {
                                    BaseActivity.this.finish();
                                }
                            });
                    }
                });
            }
        });
    }

    /**
     * 在前一个界面还没关闭的时候后一个界面有可能是 After SaveInstanceState 的状态，
     * 如果此时调用后一个界面的界面操作会报Can not perform this action after onSaveInstanceState
     * 应调用这个方法来保证回能正确显示
     *
     * @param runnable
     */
    protected void safeRunAfterSaveInstanceState(Runnable runnable) {
        if (runnable != null) {
            if (isSavedInstanceState) {
                Log.d("safeRunAfter", "offer");
                saveInstanceStateRunnables.offer(runnable);
            } else {
                Log.d("safeRunAfter", "run");
                runnable.run();
            }
        }
    }
    public void onNeedLogin(boolean otherDevice) {

    }

}

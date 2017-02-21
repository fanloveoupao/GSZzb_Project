package app.gseasypro.com.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.fastaccess.permission.base.callback.OnPermissionCallback;

import app.gseasypro.com.utils.ToastUtils;

/**
 * Created by fan-gk on 2017/2/21.
 */


public abstract class PermissionHelper {

    public interface OnPermissionListener {
        void onPermissionGranted(@NonNull String[] permissionName);
        void onPermissionDeclined(@NonNull String[] permissionName);
    }


    private static class ActivityPermissionHelper extends PermissionHelper {
        private final com.fastaccess.permission.base.PermissionHelper helper;
        public ActivityPermissionHelper(final Activity activity) {
            helper = com.fastaccess.permission.base.PermissionHelper.getInstance(activity, new OnPermissionCallback() {
                @Override
                public void onPermissionGranted(@NonNull String[] permissionName) {
                    final OnPermissionListener listener = onPermissionListener;
                    if(listener != null)
                        listener.onPermissionGranted(permissionName);
                }

                @Override
                public void onPermissionDeclined(@NonNull String[] permissionName) {
                    final OnPermissionListener listener = onPermissionListener;
                    if(listener != null)
                        listener.onPermissionDeclined(permissionName);
                }

                @Override
                public void onPermissionPreGranted(@NonNull String permissionsName) {

                }

                @Override
                public void onPermissionNeedExplanation(@NonNull String permissionName) {
                    helper.requestAfterExplanation(permissionName);
                }

                @Override
                public void onPermissionReallyDeclined(@NonNull String permissionName) {
                    ToastUtils.showShort(activity, String.format("获取%1s权限失败", toChinesePermissionName(permissionName)));
                }

                @Override
                public void onNoPermissionNeeded() {

                }
            });
        }

        /**
         * 获取授权
         * @param force true 就算用户拒绝过依然申请
         * @param permission
         * @param permissions
         */
        public void request(boolean force, String permission, String... permissions){
            if(permissions == null || permissions.length == 0)
                helper.setForceAccepting(force).request(permission);
            else {
                String[] newPermissions = new String[permissions.length + 1];
                newPermissions[0] = permission;
                for (int i = 0, j = 1; i < permissions.length; i++, j++) {
                    newPermissions[j] = permissions[i];
                }
                helper.setForceAccepting(force).request(newPermissions);
            }
        }

        /**
         * 获取授权
         * @param force true，就算用户拒绝过依然申请
         * @param permission
         */
        public void request(boolean force, String permission){
            request(force, permission, new String[0]);
        }

        @Override
        public boolean isGranted(String permission) {
            return helper.isPermissionGranted(permission);
        }
    }
    private static class FragmentPermissionHelper extends PermissionHelper {
        private  final com.fastaccess.permission.base.PermissionFragmentHelper helper;

        public FragmentPermissionHelper(final Fragment fragment) {
            helper = com.fastaccess.permission.base.PermissionFragmentHelper.getInstance(fragment, new OnPermissionCallback() {


                @Override
                public void onPermissionGranted(@NonNull String[] permissionName) {
                    final OnPermissionListener listener = onPermissionListener;
                    if(listener != null)
                        listener.onPermissionGranted(permissionName);
                }

                @Override
                public void onPermissionDeclined(@NonNull String[] permissionName) {
                    final OnPermissionListener listener = onPermissionListener;
                    if(listener != null)
                        listener.onPermissionDeclined(permissionName);
                }

                @Override
                public void onPermissionPreGranted(@NonNull String permissionsName) {

                }

                @Override
                public void onPermissionNeedExplanation(@NonNull String permissionName) {
                    helper.requestAfterExplanation(permissionName);
                }

                @Override
                public void onPermissionReallyDeclined(@NonNull String permissionName) {
                    ToastUtils.showShort(fragment.getContext(), String.format("获取%1s权限失败", toChinesePermissionName(permissionName)));
                }

                @Override
                public void onNoPermissionNeeded() {

                }
            });
        }

        public void request(boolean force, String permission, String... permissions){
            if(permissions == null || permissions.length == 0)
                helper.setForceAccepting(force).request(permission);
            else {
                String[] newPermissions = new String[permissions.length + 1];
                newPermissions[0] = permission;
                for (int i = 0, j = 1; i < permissions.length; i++, j++) {
                    newPermissions[j] = permissions[i];
                }
                helper.setForceAccepting(force).request(newPermissions);
            }
        }

        public void request(boolean force, String permission){
            request(force, permission, new String[0]);
        }

        @Override
        public boolean isGranted(String permission) {
            return helper.isPermissionGranted(permission);
        }
    }

    public static PermissionHelper createActivityHelper(Activity activity){
        return new ActivityPermissionHelper(activity);
    }
    public static PermissionHelper createFragmentHelper(Fragment fragment){
        return new FragmentPermissionHelper(fragment);
    }

    protected OnPermissionListener onPermissionListener;

    /**
     * 获取授权
     * @param force true 就算用户拒绝过依然申请
     * @param permission
     * @param permissions
     */
    public abstract void request(boolean force, String permission, String... permissions);
    /**
     * 获取授权
     * @param force true，就算用户拒绝过依然申请
     * @param permission
     */
    public abstract void request(boolean force, String permission);
    public abstract boolean isGranted(String permission);

    public void setOnPermissionListener(OnPermissionListener onPermissionListener) {
        this.onPermissionListener = onPermissionListener;
    }


    String toChinesePermissionName(String permission){
        switch (permission){
            case Manifest.permission.RECORD_AUDIO:
                return "麦克风";
            default:
                return permission;
        }
    }

}


package app.gseasypro.com.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.ui.dialog.BaseListDialogFragment;
import app.gseasypro.com.utils.ui.dialog.CommonDialogFragment;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class DialogUtil {

    //单按钮自定义点击事件，点击外部不可取消
    public static void hardOneBtnDialog(final BaseActivity context, CharSequence content, Runnable runnable) {
        showNoTitleDialog(context, content, null, null, false, null, runnable);

    }

    public static void hardOneBtnDialog(final BaseActivity context, CharSequence content, String btnContent) {
        showNoTitleDialog(context, content, null, btnContent, false, null, null);
    }

    public static void hardOneBtnDialog(final BaseActivity context, CharSequence content, String btnContent, Runnable runnable) {
        showNoTitleDialog(context, content, null, btnContent, false, null, runnable);
    }

    //单按钮自定义点击事件,点击外部可消失
    public static void softOneBtnDialog(final BaseActivity context, CharSequence content, Runnable runnable) {
        showNoTitleDialog(context, content, null, null, true, null, runnable);
    }

    //单按钮默认点击消失
    public static void hardOneBtnDialog(final BaseActivity context, CharSequence content) {
        showNoTitleDialog(context, content, null, null, false, null, null);
    }

    //单按钮默认点击消失,点击外部可取消
    public static void softOneBtnDialog(final FragmentActivity context, CharSequence content) {
        showNoTitleDialog(context, content, null, null, true, null, null);
    }

    //双按钮自定义文案，点击外部不可取消
    public static void hardTwoBtnDialog(final BaseActivity context, @NonNull final CharSequence content, String leftText,
                                        String rightText, Runnable rightRunnable) {
        showNoTitleDialog(context, content, leftText, rightText, false, null, rightRunnable);
    }

    //双按钮自定义文案，点击外部不可取消
    public static void hardTwoBtnDialog(final BaseActivity context, @NonNull final CharSequence content, String leftText,
                                        String rightText, Runnable leftRunnable, Runnable rightRunnable) {
        showNoTitleDialog(context, content, leftText, rightText, false, leftRunnable, rightRunnable);
    }

    //双按钮自定义文案，点击外部可取消
    public static void softTwoBtnDialog(final FragmentActivity context, @NonNull final CharSequence content, String leftText,
                                        String rightText, Runnable rightRunnable) {
        showNoTitleDialog(context, content, leftText, rightText, true, null, rightRunnable);
    }

    public static void softTwoBtnDialog(final BaseActivity context, @NonNull final CharSequence content, String leftText,
                                        String rightText, Runnable leftRunnable, Runnable rightRunnable) {
        showNoTitleDialog(context, content, leftText, rightText, true, leftRunnable, rightRunnable);
    }


    //双按钮普通对话框，点击外部不可取消
    public static void hardTwoBtnDialog(final BaseActivity context, @NonNull final CharSequence content, Runnable rightRunnable) {
        showNoTitleDialog(context, content, "取消", "确定", false, null, rightRunnable);
    }


    //双按钮普通对话框，，点击外部可取消
    public static void softTwoBtnDialog(final BaseActivity context, @NonNull final CharSequence content, Runnable rightRunnable) {
        showNoTitleDialog(context, content, "取消", "确定", true, null, rightRunnable);
    }

    public static void softNotitleListDialog(BaseActivity context, @NonNull List<String> lists, BaseListDialogFragment
            .DialogItemClickListener listener) throws Exception {
        showListDialog(context, null, lists, true, listener);
    }

    public static void hardNotitleListDialog(BaseActivity context, @NonNull List<String> lists, BaseListDialogFragment
            .DialogItemClickListener listener) throws Exception {
        showListDialog(context, null, lists, false, listener);
    }

    public static void softNotitleListDialog(BaseActivity context, @NonNull String[] lists, BaseListDialogFragment
            .DialogItemClickListener listener) {
        List<String> stringList = new ArrayList<>();
        if (lists != null) {
            for (String string : lists) {
                stringList.add(string);
            }
        }
        showListDialog(context, null, stringList, true, listener);
    }


    public static void showNoTitleDialog(FragmentActivity context, CharSequence content, String leftBtn, String rightBtn, boolean
            canceledOnTouchOutside
            , Runnable leftRunnable, Runnable rightRunnable) {
        CommonDialogFragment dialogFragment = new CommonDialogFragment();
        dialogFragment.setContentText(content)
                .setLeftButtonText(leftBtn)
                .setDefultButtonText(rightBtn)
                .setCanceledOnTouchOutside(canceledOnTouchOutside)
                .setLeftRunnable(leftRunnable)
                .setRightRunnable(rightRunnable);
        dialogFragment.show(context.getSupportFragmentManager(), "DialogUtil");
    }


    public static void showListDialog(BaseActivity context, String title, List<String> lists, boolean cancleable, BaseListDialogFragment
            .DialogItemClickListener listener) {
        if (lists == null || lists.size() == 0) {
            return;
        } else {
            BaseListDialogFragment dialogFragment = new BaseListDialogFragment();
            dialogFragment.setTitle(title)
                    .setStringList(lists)
                    .setCancleOnTouchOutSide(cancleable)
                    .setCancleable(cancleable).setItemClickListener(listener);
            dialogFragment.show(context.getSupportFragmentManager(), "DialogUtil");
        }
    }


}

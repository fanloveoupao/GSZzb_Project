package app.gseasypro.com.utils.utils;

import android.Manifest;
import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import app.gseasypro.com.utils.BaseActivity;
import app.gseasypro.com.utils.ToastUtils;

/**
 * Created by fan-gk on 2017/3/2.
 */


public class FileHelper {
    private final static String basePath = "gszzb";
    private final static String recordPath = basePath + "/records";
    private final static String recordCacheFilename = "_cache.mp3";
    private static Application application;

    public static void init(Application application) {
        FileHelper.application = application;
    }

    public static boolean isSdCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static File getCacheDir() {
        if (isSdCardMounted())
            return application.getExternalCacheDir();
        else
            return application.getCacheDir();
    }

    public static File getRecordFile(String filename) {
        File file = new File(getCacheDir(), recordPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, filename);
    }

    public static String saveRecordFile(String filename, byte[] stream) {
        File file = getRecordFile(filename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(stream);
            return file.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileOutputStream != null)
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void saveImage(BaseActivity activity, String filename, Bitmap bitmap) throws IOException {
        if (!activity.getPermissionHelper().isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            activity.getPermissionHelper().request(false, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        assert bitmap != null;
        assert filename != null;

        String extension = FileUtil.getExtension(filename);
        String fn = String.valueOf(System.currentTimeMillis());

        long date = System.currentTimeMillis() / 1000;

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATE_ADDED, date);
        values.put(MediaStore.Images.Media.DATE_MODIFIED, date);
        values.put(MediaStore.Images.Media.TITLE, fn);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fn + extension);
        values.put(MediaStore.Images.Media.MIME_TYPE, FileUtil.getMimeType(extension));

        Uri url = null;
        try {
            url = application.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            OutputStream imageOut = application.getContentResolver().openOutputStream(url);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOut);
            } finally {
                imageOut.close();
            }
            application.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, url));
            ToastUtils.showShort(activity, "保存成功");
        } catch (Exception e) {
            if (url != null) {
                application.getContentResolver().delete(url, null, null);
                url = null;
            }
            throw new IOException("图片保存失败", e);
        }
    }

    public static Bitmap.CompressFormat parseCompressFormatFromExtension(String extension) {
        if (extension == null)
            return Bitmap.CompressFormat.JPEG;
        switch (extension.toLowerCase()) {
            case ".png":
                return Bitmap.CompressFormat.PNG;
            default:
                return Bitmap.CompressFormat.JPEG;
        }
    }
}


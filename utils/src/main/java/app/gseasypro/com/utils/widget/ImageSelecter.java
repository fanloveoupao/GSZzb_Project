package app.gseasypro.com.utils.widget;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.utils.PermissionHelper;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by fan-gk on 2017/2/21.
 */


public class ImageSelecter {
    public static class PhotoInfo {
        private final int photoId;
        private final String photoPath;
        private final int width;
        private final int height;

        public PhotoInfo(int photoId, String photoPath, int width, int height) {
            this.photoId = photoId;
            this.photoPath = photoPath;
            this.width = width;
            this.height = height;
        }

        public int getPhotoId() {
            return photoId;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

    }

    public interface OnSelectedListener {
        void successed(List<PhotoInfo> photoInfos);

        void error(String message);
    }

    private static class PicassoImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

        private Bitmap.Config mConfig;

        public PicassoImageLoader() {
            this(Bitmap.Config.RGB_565);
        }

        public PicassoImageLoader(Bitmap.Config config) {
            this.mConfig = config;
        }

        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Picasso.with(activity)
                    .load(new File(path))
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .config(mConfig)
                    .resize(width, height)
                    .centerInside()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {
        }
    }

    private static final int REQUEST_CODE = 1;

    public static void init(Application application) {
        ThemeConfig themeConfig = ThemeConfig.ORANGE;
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setMutiSelectMaxSize(9)
                .setEnableCrop(true)
                .setForceCropEdit(true)
                .setEnableRotate(true)
                .build();

        File takePhotoFolder = new File(Environment.getExternalStorageDirectory(), "/DCIM/" + "Gs/");
        if (!takePhotoFolder.exists())
            takePhotoFolder.mkdirs();
        File editPhotoCacheFolder = new File(Environment.getExternalStorageDirectory() + "/Gs/edittemp/");
        if (!editPhotoCacheFolder.exists())
            editPhotoCacheFolder.mkdirs();

        CoreConfig coreConfig = new CoreConfig.Builder(application, new PicassoImageLoader(), themeConfig)
                .setFunctionConfig(functionConfig)
                .setTakePhotoFolder(takePhotoFolder)
                .setEditPhotoCacheFolder(editPhotoCacheFolder)
                .build();

        GalleryFinal.init(coreConfig);
    }

    public static void selectMuit(int size, @NonNull OnSelectedListener listener) {
        GalleryFinal.openGalleryMuti(REQUEST_CODE, size, getGalleryFinalCallback(listener));
    }

    public static void openCamera(@NotNull OnSelectedListener listener, PermissionHelper helper) {
        if (!helper.isGranted(android.Manifest.permission.CAMERA)) {
            helper.request(true, android.Manifest.permission.CAMERA);
        } else {
            GalleryFinal.openCamera(REQUEST_CODE, getGalleryFinalCallback(listener));
        }
    }

    private static GalleryFinal.OnHanlderResultCallback getGalleryFinalCallback(@NotNull final OnSelectedListener listener) {
        return new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<cn.finalteam.galleryfinal.model.PhotoInfo> resultList) {
                List<PhotoInfo> result = new ArrayList<>();
                if (resultList != null) {
                    for (cn.finalteam.galleryfinal.model.PhotoInfo photoInfo : resultList) {
                        result.add(new PhotoInfo(photoInfo.getPhotoId(), photoInfo.getPhotoPath(), photoInfo.getWidth(), photoInfo.getHeight()));
                    }
                }
                listener.successed(result);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                listener.error(errorMsg);
            }
        };
    }
}


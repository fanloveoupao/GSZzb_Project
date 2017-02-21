package app.gseasypro.com.utils.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.github.yoojia.qrcode.camera.Cameras;
import com.github.yoojia.qrcode.camera.CaptureCallback;
import com.github.yoojia.qrcode.camera.DelayedFocusLooper;

import java.io.ByteArrayOutputStream;

/**
 * Created by fan-gk on 2017/2/21.
 */


public class LiveCameraView extends com.github.yoojia.qrcode.camera.CameraPreviewView {
    public static Bitmap previewCapture(Camera camera, byte[] data) {
        final Camera.Parameters parameters = camera.getParameters();
        final int width = parameters.getPreviewSize().width;
        final int height = parameters.getPreviewSize().height;
        final YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        yuv.compressToJpeg(new Rect(0, 0, width, height), 100, out);// Best
        final byte[] bytes = out.toByteArray();
        final Bitmap src = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        final Matrix matrix = new Matrix();
        matrix.setRotate(90);
//        final int originWidth = src.getWidth();
//        final int originHeight = src.getHeight();
//        final int targetWH = originWidth > originHeight ? originHeight : originWidth;
//        final int offsetX = originWidth > originHeight ? (originWidth - originHeight): 0;
//        final int offsetY = originWidth > originHeight ? 0 : (originHeight - originWidth);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private static final String TAG = LiveCameraView.class.getSimpleName();

    public LiveCameraView(Context context) {
        super(context);
    }

    public LiveCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiveCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Camera mCamera;
    private CaptureCallback mCaptureCallback;
    private OnPermissionDeniedListener mOnPermissionDeniedListener;

    private final Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            Log.i(TAG, "-> Got preview frame data");
            try {
                mCaptureCallback.onCaptured(previewCapture(camera, data));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private final DelayedFocusLooper mFocusLooper = new DelayedFocusLooper() {

        private final Camera.AutoFocusCallback mHandler = new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.setOneShotPreviewCallback(mPreviewCallback);
                } else {
                    Log.w(TAG, "-> Request focus, but fail !");
                }
            }
        };

        @Override
        public void callAutoFocus() {
            mCamera.autoFocus(mHandler);
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Cameras.openBackDefault();
        try{
            setCamera(mCamera);
            super.surfaceCreated(holder);
        }catch (Exception e){
            mCamera = null;
            mOnPermissionDeniedListener.onPermissionDenied();
            return;
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mCamera==null)return;
        else super.surfaceChanged(holder,format,width,height);
    }

    public interface OnPermissionDeniedListener{
        void onPermissionDenied();
    }
    public void setOnPermissionDeniedListener(OnPermissionDeniedListener onPermissionDeniedListener){
        mOnPermissionDeniedListener = onPermissionDeniedListener;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            super.surfaceDestroyed(holder);
        }catch (Exception e){
        }finally {
            mFocusLooper.stop();
            if (mCamera != null) {
                mCamera.release();
            }
        }
    }

    /**
     * 启动自动对焦拍摄
     *
     * @param delay           每次聚焦的延时时间，单位：毫秒
     * @param captureCallback 聚焦后的拍摄图片回调接口
     */
    public void startAutoCapture(int delay, CaptureCallback captureCallback) {
        mCaptureCallback = captureCallback;
        if (mCamera != null) {
            mFocusLooper.start(delay);
        } else {
            Toast.makeText(getContext(), "OPEN CAMERA FAIL", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 停止自动对焦拍摄
     */
    public void stopAutoCapture() {
        mFocusLooper.stop();
    }

    /**
     * @return 返回当前设备是否支持自动对焦拍摄功能
     */
    public boolean isAutoCaptureSupported() {
        return Cameras.isAutoFocusSupported(mCamera);
    }
}


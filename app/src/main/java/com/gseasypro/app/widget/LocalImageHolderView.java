package com.gseasypro.app.widget;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.resources.bean.AdvertisementsBean;
import com.gseasypro.app.picasso.ImageLoader;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class LocalImageHolderView implements Holder<AdvertisementsBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, AdvertisementsBean data) {
        if (data.image != null)
            ImageLoader.loadImage(Uri.parse(data.image), imageView);
    }
}

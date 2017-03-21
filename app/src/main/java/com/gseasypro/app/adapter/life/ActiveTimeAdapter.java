package com.gseasypro.app.adapter.life;

import android.net.Uri;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ActiveBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/13.
 */

public class ActiveTimeAdapter extends BaseQuickAdapter<ActiveBean> {
    public ActiveTimeAdapter(List<ActiveBean> data) {
        super(R.layout.item_activie_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ActiveBean activeBean) {
        baseViewHolder
                .setText(R.id.txt_name, activeBean.title)
                .setText(R.id.txt_title, activeBean.describe)
                .setText(R.id.txt_phone, activeBean.tel)
                .setText(R.id.txt_email, activeBean.email);
        ImageView imageView = (ImageView) baseViewHolder.convertView.findViewById(R.id.image_icon);
        ImageLoader.loadImage(Uri.parse(activeBean.url), imageView);
    }
}

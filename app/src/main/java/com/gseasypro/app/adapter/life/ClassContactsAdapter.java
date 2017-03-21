package com.gseasypro.app.adapter.life;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lang.StringUtil;
import com.example.resources.bean.ClassContactsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/14.
 */

public class ClassContactsAdapter extends BaseQuickAdapter<ClassContactsBean> {
    private List<ClassContactsBean> contacts;

    public ClassContactsAdapter(List<ClassContactsBean> data) {
        super(R.layout.item_classcontacts_list, data);
        this.contacts = data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClassContactsBean classContactsBean) {
        TextView tvIndex = (TextView) baseViewHolder.getConvertView().findViewById(R.id.tv_index);
        ImageView ivAvatar = (ImageView) baseViewHolder.getConvertView().findViewById(R.id.iv_avatar);
        int position = baseViewHolder.getAdapterPosition();
        if (position == 0 || !StringUtil.equals(contacts.get(position - 1).index, classContactsBean.index)) {
            tvIndex.setVisibility(View.VISIBLE);
            tvIndex.setText(classContactsBean.index);
        } else {
            tvIndex.setVisibility(View.GONE);
        }
        baseViewHolder.setText(R.id.tv_name, classContactsBean.name);
        ImageLoader.loadIcon(classContactsBean.url, ivAvatar, true, true);
    }
}

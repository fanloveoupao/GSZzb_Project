package com.gseasypro.app.adapter.mine;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.CollectionProjectBean;
import com.example.utils.DateUtil;
import com.gseasypro.app.R;

import java.util.List;

import app.gseasypro.com.utils.widget.LoadMoreAdapter;

/**
 * Created by fan-gk on 2017/3/2.
 */


public class CollectionProjectAdapter extends LoadMoreAdapter<CollectionProjectBean> {

    public CollectionProjectAdapter(List<CollectionProjectBean> data) {
        super(R.layout.item_collection_project, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CollectionProjectBean collectionProjectBean) {
        baseViewHolder.setText(R.id.tv_project_name, collectionProjectBean.name)
                .setText(R.id.tv_time, DateUtil.parseTime(collectionProjectBean.update_date));
        TextView tvType = baseViewHolder.getView(R.id.tv_type);
        switch (collectionProjectBean.type) {
            case CollectionProjectBean.GROUP_PROJECT:
                tvType.setText("广师校园");
                break;
            case CollectionProjectBean.INFORMATION_PROJECT:
                tvType.setText("广师学习");
                break;
            case CollectionProjectBean.COOPERATION_PROJECT:
                tvType.setText("广师生活");
                break;
            case CollectionProjectBean.OTHER:
                tvType.setText("其他");
                break;
        }
    }
}

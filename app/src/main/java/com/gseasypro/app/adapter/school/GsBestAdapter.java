package com.gseasypro.app.adapter.school;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ItemGsBestBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/23.
 */

public class GsBestAdapter extends BaseQuickAdapter<ItemGsBestBean> {
    public GsBestAdapter(List<ItemGsBestBean> data) {
        super(R.layout.item_gs_best, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemGsBestBean itemGsBestBean) {

    }
}

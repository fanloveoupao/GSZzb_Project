package com.gseasypro.app.adapter.life;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ItemLifeBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/21.
 */

public class IndexLifeAdapter extends BaseQuickAdapter<ItemLifeBean> {
    public IndexLifeAdapter(List<ItemLifeBean> data) {
        super(R.layout.item_life_grid, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemLifeBean itemLifeBean) {
        baseViewHolder.setText(R.id.tv_tag, itemLifeBean.itemTag)
                .setTextColor(R.id.tv_tag, itemLifeBean.tagColors)
                .setImageResource(R.id.iv_item, itemLifeBean.imageId);
    }
}

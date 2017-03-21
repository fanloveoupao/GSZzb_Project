package com.gseasypro.app.adapter.life;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ItemOneCardBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/15.
 */

public class OneCardSolutionAdapter extends BaseQuickAdapter<ItemOneCardBean> {
    public OneCardSolutionAdapter(List<ItemOneCardBean> data) {
        super(R.layout.item_onecard_lay, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemOneCardBean itemLifeBean) {
        baseViewHolder.setText(R.id.tv_tag, itemLifeBean.itemTag)
                .setImageResource(R.id.iv_item, itemLifeBean.imageId);
    }
}

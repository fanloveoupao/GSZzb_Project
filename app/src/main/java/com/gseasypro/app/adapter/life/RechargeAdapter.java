package com.gseasypro.app.adapter.life;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.RechargeBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/16.
 */

public class RechargeAdapter extends BaseQuickAdapter<RechargeBean> {
    public RechargeAdapter(List<RechargeBean> data) {
        super(R.layout.item_recharge_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RechargeBean rechargeBean) {
        baseViewHolder
                .setText(R.id.tv_number, rechargeBean.number)
                .setText(R.id.tv_discount, rechargeBean.discount);

    }
}

package com.gseasypro.app.adapter.life;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.RecordsConsumptionBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/16.
 */

public class RecordsConsumptionAdapter extends BaseQuickAdapter<RecordsConsumptionBean> {
    public RecordsConsumptionAdapter(List<RecordsConsumptionBean> data) {
        super(R.layout.item_records_consum_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecordsConsumptionBean recordsConsumptionBean) {
        ImageView mIvavatar = (ImageView) baseViewHolder.getConvertView().findViewById(R.id.iv_avatar);
        baseViewHolder
                .setText(R.id.tv_consum_type, recordsConsumptionBean.consume)
                .setText(R.id.tv_number, recordsConsumptionBean.number)
                .setText(R.id.tv_item_date, recordsConsumptionBean.date);
        ImageLoader.loadIcon(recordsConsumptionBean.image, mIvavatar, true, true);
    }
}

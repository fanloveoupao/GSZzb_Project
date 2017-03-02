package com.gseasypro.app.adapter.school;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.LinearBean;
import com.example.resources.bean.SubsBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class ProjectTypeAdapter<T extends LinearBean> extends BaseQuickAdapter<SubsBean<T>> {

    public ProjectTypeAdapter(List<SubsBean<T>> typeFirstBeanList) {
        super(R.layout.item_city, typeFirstBeanList);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final SubsBean<T> projectBaseBean) {
        baseViewHolder.setText(R.id.tv_name, projectBaseBean.getName());
        ImageView ivType = baseViewHolder.getView(R.id.iv_select);
        LinearLayout llCity = baseViewHolder.getView(R.id.ll_city);

        if (projectBaseBean.isCheck) {
            ivType.setBackgroundResource(R.mipmap.checkbox_green_square_selected_bg);
        } else {
            ivType.setBackgroundResource(R.mipmap.checkbox_green_square_normal_bg);
        }
        llCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectBaseBean.isCheck = !projectBaseBean.isCheck;
                notifyDataSetChanged();
            }
        });
    }


}
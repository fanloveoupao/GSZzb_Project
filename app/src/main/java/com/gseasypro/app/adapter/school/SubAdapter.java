package com.gseasypro.app.adapter.school;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.SubsBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class SubAdapter<T extends SubsBean> extends BaseQuickAdapter<T> {

    //    private OnCkChangeListener ckListener;
    private boolean mShowCheckBox;
    private OnItemClickListener<T> mListener;

    public SubAdapter(Context context, List<T> data) {
        this(context, data, true);
    }

    public SubAdapter(Context context, List<T> data, boolean showCheckBox) {
        super(R.layout.item_city, data);
        mShowCheckBox = showCheckBox;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final T subsBean) {
        baseViewHolder.setText(R.id.tv_name, subsBean.getName());
        ImageView ivType = baseViewHolder.getView(R.id.iv_select);
        LinearLayout llCity = baseViewHolder.getView(R.id.ll_city);

        if (mShowCheckBox) {
            if (subsBean.isCheck) {
                ivType.setBackgroundResource(R.mipmap.checkbox_green_square_selected_bg);
            } else {
                ivType.setBackgroundResource(R.mipmap.checkbox_green_square_normal_bg);
            }
        } else {
            ivType.setVisibility(View.INVISIBLE);
        }
        llCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(subsBean, baseViewHolder.getAdapterPosition());
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener<T> onClickListener) {
        mListener = onClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T Sub, int position);
    }
}


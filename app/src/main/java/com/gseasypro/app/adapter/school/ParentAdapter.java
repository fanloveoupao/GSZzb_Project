package com.gseasypro.app.adapter.school;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ParentBean;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class ParentAdapter<T extends ParentBean> extends BaseQuickAdapter<T> {

    private OnItemClickListener<T> mListener;
    private boolean mShowCheckCount;

    public ParentAdapter(Context context, List<T> data, boolean showCheckCount) {
        super(R.layout.item_province, data);
        mShowCheckCount = showCheckCount;
    }

    public ParentAdapter(Context context, List<T> data) {
        this(context, data, true);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final T parentBean) {
        baseViewHolder.setText(R.id.tv_name, parentBean.getName());

        if (parentBean.isCheck) {
            baseViewHolder.setBackgroundRes(R.id.ll_item, R.mipmap.item_bg_gray_select);
        } else {
            baseViewHolder.setBackgroundColor(R.id.ll_item, Color.parseColor("#eeeeee"));
        }
//        TextView tvCheckCount = baseViewHolder.getView(R.id.tv_check_count);
        ImageView ivCheckMark = baseViewHolder.getView(R.id.iv_check_mark);
        if (mShowCheckCount) {
            if (parentBean.getCheckCount() > 0) {
//            tvCheckCount.setVisibility(View.VISIBLE);
//            tvCheckCount.setText(parentBean.getCheckCount() + "");
                ivCheckMark.setVisibility(View.VISIBLE);
            } else {
//            tvCheckCount.setVisibility(View.GONE);
                ivCheckMark.setVisibility(View.INVISIBLE);
            }
        } else {
            ivCheckMark.setVisibility(View.GONE);
        }
        baseViewHolder.getView(R.id.ll_province).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(parentBean, baseViewHolder.getAdapterPosition());
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener<T> onClickListener) {
        mListener = onClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T ParentBean, int position);
    }
}


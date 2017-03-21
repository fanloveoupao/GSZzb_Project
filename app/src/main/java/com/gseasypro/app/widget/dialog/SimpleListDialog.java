package com.gseasypro.app.widget.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gseasypro.app.R;

import java.util.List;

/**
 * Created by fan-gk on 2017/3/14.
 */


public class SimpleListDialog extends SimpleDialog {
    private OnItmeClickListener mOnItmeClickListener;

    public SimpleListDialog(Context context, String title, final List<String> strs) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple_list, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        RecyclerView rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        tvTitle.setText(title);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rvList.setLayoutManager(manager);
        SimpleListAdapter adapter = new SimpleListAdapter(strs);
        rvList.setAdapter(adapter);
        setContentView(view);
        rvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                dismiss();
                mOnItmeClickListener.listener(strs.get(i),i);
            }
        });

    }

    class SimpleListAdapter extends BaseQuickAdapter<String> {

        public SimpleListAdapter(List<String> data) {
            super(R.layout.item_simple_list, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String str) {
            baseViewHolder.setText(R.id.tv_content, str);
        }
    }

    public  interface OnItmeClickListener {
        void listener(String str,int position);
    }

    public void setOnItemClickListener(OnItmeClickListener listener) {
        mOnItmeClickListener = listener;
    }


}

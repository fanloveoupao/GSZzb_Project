package app.gseasypro.com.utils.widget;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.gseasypro.com.utils.R;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class LoadMoreAdapter<T> extends BaseQuickAdapter<T> {


    public LoadMoreAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public LoadMoreAdapter(List<T> data) {
        super(data);
    }

    public LoadMoreAdapter(View contentView, List<T> data) {
        super(contentView, data);
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        if (layoutResId == com.chad.library.R.layout.def_loading) {
            layoutResId = R.layout.load_more_view;
            return super.createBaseViewHolder(parent, layoutResId);
        }
        return super.createBaseViewHolder(parent, layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, T t) {

    }
}
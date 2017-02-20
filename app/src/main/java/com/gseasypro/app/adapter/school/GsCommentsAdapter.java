package com.gseasypro.app.adapter.school;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.GsCommentsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class GsCommentsAdapter extends BaseQuickAdapter<GsCommentsBean> {
    public GsCommentsAdapter(List<GsCommentsBean> data) {
        super(R.layout.item_gscommebts_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GsCommentsBean gsCommentsBean) {
        ImageView iv_avatar = (ImageView) baseViewHolder.getConvertView().findViewById(R.id.ivUserAvatar);
        baseViewHolder.setText(R.id.tvComment, gsCommentsBean.comment_content);
        ImageLoader.loadIcon(gsCommentsBean.comment_avatar, iv_avatar, true, true);
    }
}

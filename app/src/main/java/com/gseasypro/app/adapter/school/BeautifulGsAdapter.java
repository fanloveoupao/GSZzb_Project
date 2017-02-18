package com.gseasypro.app.adapter.school;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.BeautifulGsItemBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/18.
 */

public class BeautifulGsAdapter extends BaseQuickAdapter<BeautifulGsItemBean> {


    private OnFeedItemOnClickListener listener;

    public BeautifulGsAdapter(List<BeautifulGsItemBean> data) {
        super(R.layout.item_gs_list, data);
    }

    public void setListener(OnFeedItemOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BeautifulGsItemBean beautifulGsItemBean) {
        View currentView = baseViewHolder.getConvertView();
        int position = baseViewHolder.getAdapterPosition();
        currentView.findViewById(R.id.ivFeedCenter).setOnClickListener(new OnClickListener(position));
        currentView.findViewById(R.id.btnMore).setOnClickListener(new OnClickListener(position));
        currentView.findViewById(R.id.btnComments).setOnClickListener(new OnClickListener(position));
        baseViewHolder.setImageResource(R.id.ivFeedCenter, R.mipmap.img_feed_center_1)
                .setImageResource(R.id.ivFeedBottom, R.mipmap.img_feed_bottom_1)
                .setImageResource(R.id.btnLike, beautifulGsItemBean.isLiked ? R.mipmap.ic_heart_red : R.mipmap.ic_heart_outline_grey);
        FrameLayout vImageRoot = (FrameLayout) currentView.findViewById(R.id.vImageRoot);
        TextSwitcher tsLikesCounter = (TextSwitcher) currentView.findViewById(R.id.tsLikesCounter);
        tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                R.plurals.likes_count, beautifulGsItemBean.likesCount, beautifulGsItemBean.likesCount
        ));

    }

    public interface OnFeedItemOnClickListener {
        void onImageClick(View view, int position);

        void onShareClick(View view, int position);

        void onCommentsClick(View view, int position);
    }

    class OnClickListener implements View.OnClickListener {
        private int position;

        public OnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivFeedCenter:
                    listener.onImageClick(view, position);
                    break;
                case R.id.btnMore:
                    listener.onShareClick(view, position);
                    break;
                case R.id.btnComments:
                    listener.onCommentsClick(view, position);
                    break;
            }
        }
    }
}

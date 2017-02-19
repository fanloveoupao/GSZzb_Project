package com.gseasypro.app.adapter.school;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.BeautifulGsItemBean;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.gseasypro.com.utils.widget.LoadingFeedItemView;
import app.gseasypro.com.utils.widget.SquaredFrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fan-gk on 2017/2/18.
 */

public class BeautifulGsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnFeedItemOnClickListener {
        void onImageClick(View view, int position);

        void onShareClick(View view, int position);

        void onCommentsClick(View view, int position);
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @BindView(R.id.ivFeedBottom)
        ImageView ivFeedBottom;
        @BindView(R.id.btnComments)
        ImageButton btnComments;
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        @BindView(R.id.btnMore)
        ImageButton btnMore;
        @BindView(R.id.vBgLike)
        View vBgLike;
        @BindView(R.id.ivLike)
        ImageView ivLike;
        @BindView(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @BindView(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @BindView(R.id.vImageRoot)
        FrameLayout vImageRoot;

        BeautifulGsItemBean feedItem;

        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(BeautifulGsItemBean feedItem) {
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
            ivFeedCenter.setOnClickListener(new OnClickListener(adapterPosition));
            btnMore.setOnClickListener(new OnClickListener(adapterPosition));
            btnComments.setOnClickListener(new OnClickListener(adapterPosition));
            ivFeedCenter.setImageResource(adapterPosition % 2 == 0 ? R.mipmap.img_feed_center_1 : R.mipmap.img_feed_center_2);
            ivFeedBottom.setImageResource(adapterPosition % 2 == 0 ? R.mipmap.img_feed_bottom_1 : R.mipmap.img_feed_bottom_2);
            btnLike.setImageResource(feedItem.isLiked ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
            tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                    R.plurals.likes_count, feedItem.likesCount, feedItem.likesCount
            ));
        }

        public BeautifulGsItemBean getFeedItem() {
            return feedItem;
        }
    }

    class LoadingCellFeedViewHolder extends RecyclerView.ViewHolder {
        LoadingFeedItemView loadingFeedItemView;

        protected LoadingCellFeedViewHolder(View view) {
            super(view);
            loadingFeedItemView = (LoadingFeedItemView) view;
        }
    }

    static class OnClickListener implements View.OnClickListener {
        private int position;

        public OnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (listener == null)
                return;
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

    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;
    private boolean showLoadingView = false;
    private static OnFeedItemOnClickListener listener;
    private Context context;
    private List<BeautifulGsItemBean> data = new ArrayList<>();

    public BeautifulGsAdapter(Context context, List<BeautifulGsItemBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setListener(OnFeedItemOnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gs_list, parent, false);
            CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);

            return cellFeedViewHolder;
        } else if (viewType == VIEW_TYPE_LOADER) {
            LoadingFeedItemView view = new LoadingFeedItemView(context);
            view.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            return new LoadingCellFeedViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CellFeedViewHolder) viewHolder).bindView(data.get(position));

        if (getItemViewType(position) == VIEW_TYPE_LOADER) {
            bindLoadingFeedItem((LoadingCellFeedViewHolder) viewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }


    public void updateItems(boolean animated) {
        if (animated) {
            notifyItemRangeInserted(0, data.size());
        } else {
            notifyDataSetChanged();
        }
    }

    private void bindLoadingFeedItem(final LoadingCellFeedViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    public List<BeautifulGsItemBean> getData() {
        return data;
    }

    public void setNewData(List<BeautifulGsItemBean> datas) {
        data = datas;
        notifyDataSetChanged();
    }

}


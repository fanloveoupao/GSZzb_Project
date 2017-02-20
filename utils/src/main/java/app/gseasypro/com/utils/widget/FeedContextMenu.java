package app.gseasypro.com.utils.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import app.gseasypro.com.utils.R;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class FeedContextMenu extends LinearLayout {
    private static final int CONTEXT_MENU_WIDTH = dpToPx(240);

    public interface OnFeedContextMenuItemClickListener {

        void onSharePhotoClick(int feedItem);

        void onCancelClick(int feedItem);
    }

    public class OnClickListener implements View.OnClickListener {
        private int feedItem;

        public OnClickListener(int feedItem) {
            this.feedItem = feedItem;
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener == null)
                return;
            int i = view.getId();
            if (i == R.id.btnSharePhoto) {
                onItemClickListener.onSharePhotoClick(feedItem);

            } else if (i == R.id.btnCancel) {
                onItemClickListener.onCancelClick(feedItem);

            }
        }
    }
    private int feedItem = -1;
    private Button mbtnSharePhoto, mbtnCancel;
    private OnFeedContextMenuItemClickListener onItemClickListener;
    public FeedContextMenu(Context context) {
        super(context);
        init();
        initListener();
    }
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_context_menu, this, true);
        mbtnSharePhoto = (Button) view.findViewById(R.id.btnSharePhoto);
        mbtnCancel = (Button) view.findViewById(R.id.btnCancel);
        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void initListener() {
        mbtnSharePhoto.setOnClickListener(new OnClickListener(feedItem));
        mbtnCancel.setOnClickListener(new OnClickListener(feedItem));
    }

    public void bindToItem(int feedItem) {
        this.feedItem = feedItem;
    }


    public void dismiss() {
        ((ViewGroup) getParent()).removeView(FeedContextMenu.this);
    }


    public void setOnFeedMenuItemClickListener(OnFeedContextMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

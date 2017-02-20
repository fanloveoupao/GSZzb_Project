package app.gseasypro.com.utils.ui.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.List;

import app.gseasypro.com.utils.R;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class BaseListDialogFragment extends SimpleDialogFragment {

    private String mTitle;
    private List<String> mLists;
    private double mWidth;
    public int mTitleColor = Color.parseColor("#333333");
    private boolean mCanceledOnTouchOutside = true;
    private boolean mCancelable = true;
    private DialogItemClickListener mItemClickListener;
    private RecyclerView mRecyclerView;


    @NonNull
    public BaseListDialogFragment setItemClickListener(@NonNull DialogItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        return this;
    }

    @NonNull
    public BaseListDialogFragment setStringList(List<String> lists) {
        mLists = lists;
        return this;
    }

    public BaseListDialogFragment setTitle(String title) {
        mTitle = title;
        return this;
    }

    public BaseListDialogFragment setWidth(double width) {
        mWidth = width;
        return this;
    }

    public BaseListDialogFragment setCancleOnTouchOutSide(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public BaseListDialogFragment setCancleable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public BaseListDialogFragment setTitleColor(int color) {
        mTitleColor = color;
        return this;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_list_fragment, null);
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        dialog.setCancelable(mCancelable);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        View titleLine = view.findViewById(R.id.title_line);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        if (mTitle != null) {
            tvTitle.setText(mTitle);
            tvTitle.setTextColor(mTitleColor);
        } else {
            tvTitle.setVisibility(View.GONE);
            titleLine.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        // window.getAttributes().windowAnimations = R.style.dialogAnim;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mLists != null && mLists.size() != 0)
            mRecyclerView.setAdapter(new ListAdapter(mLists));
    }

    public interface DialogItemClickListener {
        void itemClickCallBack(BaseListDialogFragment dialogFragment, String itemText, int position);
    }


    //===================================ListAdapter=======================================================================

    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mStringList;

        public ListAdapter(List<String> strings) {
            mStringList = strings;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_view, parent, false);
            return new DialogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            DialogViewHolder viewHolder = (DialogViewHolder) holder;
            viewHolder.mTextView.setText(mStringList.get(position));
            if (position == mStringList.size() - 1)
                viewHolder.mLine.setVisibility(View.GONE);
            viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null)
                        mItemClickListener.itemClickCallBack(BaseListDialogFragment.this, mStringList.get(position), position);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

        private class DialogViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTextView;
            private final View mLine;

            public DialogViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv_item);
                mLine = itemView.findViewById(R.id.line);
            }
        }

    }
}

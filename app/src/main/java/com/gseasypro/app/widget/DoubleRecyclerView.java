package com.gseasypro.app.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.resources.bean.LinearBean;
import com.example.resources.bean.ParentBean;
import com.example.resources.bean.SubsBean;
import com.example.resources.bean.TwoLevelTreeBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ParentAdapter;
import com.gseasypro.app.adapter.school.SubAdapter;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.BaseActivity;
import app.gseasypro.com.utils.DialogUtil;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class DoubleRecyclerView<T extends TwoLevelTreeBean, L extends LinearBean> extends RelativeLayout {

    private BaseActivity mContext;
    private RecyclerView mParentRv;
    private RecyclerView mSubRv;

    private ParentAdapter mParentAdapter;
    private SubAdapter mSubAdapter;
    private List<ParentBean<T, SubsBean<L>>> mTList = new ArrayList<>();
    private int mParentCheckPosition;
    private Integer mLimitCheckCount;
    private int mCheckCount;
    private boolean mShowCheckCount;
    private ParentBean<T, SubsBean<L>> mParent;
    private OnReachLimitCheckCountListener mOnReachLimitCheckCountListener;

    public DoubleRecyclerView(BaseActivity context) {
        super(context);
        mContext = context;
        initView();
    }

    public DoubleRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DoubleRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.doublerecyclerview_view, this, true);
        mParentRv = (RecyclerView) view.findViewById(R.id.rv_left);
        mSubRv = (RecyclerView) view.findViewById(R.id.rv_right);

    }
//
//    public void loadData(final List<ParentBean> parentBeanList) {
//        loadData(parentBeanList, null, true);
//    }

    //    public void loadData(final List<T> parentBeanList) {
//        loadData(parentBeanList, null, null, true);
//    }
//
//    public void loadData(final List<T> twoLevelBeanList, List<L> initialCheckedBeans) {
//        loadData(twoLevelBeanList, initialCheckedBeans, null, true);
//    }
//
//    public void loadData(final List<ParentBean> parentBeanList, Integer limitCheckCount) {
//        loadData(parentBeanList, limitCheckCount, true);
//
//    }
//
//    public void loadData(final List<T> twoLevelBeanList, List<L> initialCheckedBeans, Integer limitCheckCount) {
//        loadData(twoLevelBeanList, initialCheckedBeans, limitCheckCount, true);
//
//    }
//
//    public void loadData(final List<T> twoLevelBeanList, List<L> initialCheckedBeans, Integer limitCheckCount, boolean showCheckBox) {
//        List<ParentBean> parentBeanList = new ArrayList<>();
//        for (T t :
//                twoLevelBeanList) {
//            ParentBean parentBean = new ParentBean<T, SubsBean>(t);
//            for (Object l :
//                    t.getSubNodes()) {
//                SubsBean subsBean = new SubsBean((LinearBean) l);
//                parentBean.getSubs().add(subsBean);
//            }
//            parentBean.check(initialCheckedBeans);
//            parentBeanList.add(parentBean);
//        }
//        loadData(parentBeanList, limitCheckCount, showCheckBox);
//    }
    public void initData(final List<ParentBean<T, SubsBean<L>>> parentBeanList, Integer limitCheckCount) {
        initData(parentBeanList, limitCheckCount, false);
    }


    public void initData(final List<ParentBean<T, SubsBean<L>>> parentBeanList, Integer limitCheckCount, final boolean showCheckAll) {
        mLimitCheckCount = limitCheckCount;
        mShowCheckCount = !(mLimitCheckCount != null && mLimitCheckCount.equals(1));
        mCheckCount = 0;
        mParentCheckPosition = -1;
        int subsStartIndex = 0;
        for (ParentBean<T, SubsBean<L>> t :
                parentBeanList) {
            mCheckCount += t.getCheckCount();
            if (mParent == null) {
                mParentCheckPosition++;
                subsStartIndex = -1;
                for (SubsBean<L> s :
                        t.getSubs()) {
                    subsStartIndex++;
                    if (s.isCheck) {
                        mParent = t;
                        break;
                    }
                }
            }
        }
        if (mParent == null) {
            mParent = parentBeanList.get(0);
            mParentCheckPosition = 0;
            subsStartIndex = 0;
        }
        mParent.isCheck = true;
        LinearLayoutManager manager1 = new LinearLayoutManager(mContext);
        mParentRv.setLayoutManager(manager1);
        LinearLayoutManager manager2 = new LinearLayoutManager(mContext);
        mSubRv.setLayoutManager(manager2);
        mTList = parentBeanList;

        mParentAdapter = new ParentAdapter(mContext, parentBeanList, mShowCheckCount);
        mSubAdapter = new SubAdapter(mContext, mParent.getSubs(), mShowCheckCount);
        mParentRv.setAdapter(mParentAdapter);
        mSubRv.setAdapter(mSubAdapter);
        mParentRv.scrollToPosition(mParentCheckPosition);
        mSubRv.scrollToPosition(subsStartIndex);
        mParentRv.setItemAnimator(null);
        mParentRv.setLayoutAnimation(null);
        mParentRv.setAnimation(null);
        mSubRv.setItemAnimator(null);
        mSubRv.setLayoutAnimation(null);
        mSubRv.setAnimation(null);

        mParentAdapter.setOnItemClickListener(new ParentAdapter.OnItemClickListener<ParentBean>() {
            @Override
            public void onItemClick(ParentBean parentBean, int position) {
                if (parentBean.equals(mParent)) return;
                parentBean.isCheck = true;
                mParent.isCheck = false;
                mParentAdapter.notifyItemChanged(position);
                mParentAdapter.notifyItemChanged(mParentCheckPosition);
                mParentCheckPosition = position;
                mParent = parentBean;
                mSubAdapter.setNewData(mParent.getSubs());
            }
        });

        mSubAdapter.setOnItemClickListener(new SubAdapter.OnItemClickListener<SubsBean>() {
            @Override
            public void onItemClick(SubsBean sub, int i) {
                if (showCheckAll && i == 0) {
                    onCheckAllClick(sub);
                    return;
                }
                if (mLimitCheckCount != null && mLimitCheckCount > 1 && mLimitCheckCount < mCheckCount + (sub.isCheck ? -1 : 1)) {
                    onReachLimit();
                    return;
                }

                if (mLimitCheckCount != null && mLimitCheckCount == 1) {
                    onSingleSelection(i);
                    return;
                }
                mParent.toggleCheckSubNode(i);
                if (sub.isCheck) {
                    mCheckCount += 1;
                    if (showCheckAll && mParent.getSubs().size() - 1 == mParent.getCheckCount()) {
                        mParent.getSubs().get(0).isCheck = true;
                        mSubAdapter.notifyItemChanged(0);
                    }
                } else {
                    mCheckCount -= 1;
                    if (showCheckAll) {
                        mParent.getSubs().get(0).isCheck = false;
                        mSubAdapter.notifyItemChanged(0);
                    }
                }
                mSubAdapter.notifyItemChanged(i);
                mParentAdapter.notifyItemChanged(mParentCheckPosition);
            }
        });
    }

    private void onReachLimit() {
        DialogUtil.softOneBtnDialog(mContext, "最多选择" + mLimitCheckCount + "项");
    }

    private void onCheckAllClick(SubsBean sub) {
        if (sub.isCheck) {
            sub.isCheck = false;
            for (int index = 1; index < mTList.get(mParentCheckPosition).getSubs().size(); index++) {
                if (mTList.get(mParentCheckPosition).getSubs().get(index).isCheck) {
                    mTList.get(mParentCheckPosition).toggleCheckSubNode(index);
                    mCheckCount--;
                }
            }
        } else {
            if (mLimitCheckCount != null) {
                int countNew = 0;
                for (int index = 1; index < mTList.get(mParentCheckPosition).getSubs().size(); index++) {
                    if (mTList.get(mParentCheckPosition).getSubs().get(index).isCheck == false)
                        countNew++;
                }
                if (countNew + mCheckCount > mLimitCheckCount) {
                    onReachLimit();
                    return;
                }
            }
            sub.isCheck = true;
            for (int index = 1; index < mTList.get(mParentCheckPosition).getSubs().size(); index++) {
                if (!mTList.get(mParentCheckPosition).getSubs().get(index).isCheck) {
                    mTList.get(mParentCheckPosition).toggleCheckSubNode(index);
                    mCheckCount++;
                }
            }
        }
        mSubAdapter.notifyDataSetChanged();
        mParentAdapter.notifyItemChanged(mParentCheckPosition);
    }

    private void onSingleSelection(int index) {
        for (ParentBean<T, SubsBean<L>> p : mTList) {
            for (SubsBean<L> s : p.getSubs()) {
                s.isCheck = false;
            }
        }
        mParent.toggleCheckSubNode(index);
        mOnReachLimitCheckCountListener.onReachLimitCheckCount();
    }

    public void refreshView() {
        mParentAdapter.notifyDataSetChanged();
        mSubAdapter.notifyDataSetChanged();
    }

    public interface OnReachLimitCheckCountListener {
        void onReachLimitCheckCount();
    }

    public void setOnReachLimitCheckCountListener(OnReachLimitCheckCountListener onReachLimitCheckCountListener) {
        mOnReachLimitCheckCountListener = onReachLimitCheckCountListener;
    }

    public List<ParentBean<T, SubsBean<L>>> getBindData() {
        return mTList;
    }
}

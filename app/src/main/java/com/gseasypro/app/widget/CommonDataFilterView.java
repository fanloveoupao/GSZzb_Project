package com.gseasypro.app.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.utils.BeanUtils;
import com.example.utils.CollectionUtil;
import com.example.exceptions.NetworkException;
import com.example.lang.StringUtil;
import com.example.resources.CommonDataInterface;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.ParentBean;
import com.example.resources.bean.SubsBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.school.ProjectTypeAdapter;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.gseasypro.com.utils.executor.ThreadExecutor;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class CommonDataFilterView extends RelativeLayout implements View.OnClickListener {


    public final int AREA = 0;
    public final int TYPE = 1;
    public final int STAGE = 2;

    private Context mContext;
    private DoubleRecyclerView mAreaView;
    private Button mBtnCancel;
    private Button mBtnSubmit;
    private MultiStateView mMultiStateView;
    private List<ParentBean<BaseClassBean, SubsBean<BaseClassBean>>> mAreaParents = new ArrayList<>();
    private RecyclerView mTypeView;
    private RecyclerView mStageView;
    private List<View> mViewList;
    private List<BaseClassBean> mProviceBeanList = new ArrayList<>();
    private List<SubsBean<BaseClassBean>> mTypeBeanList;
    private List<SubsBean<BaseClassBean>> mStageBeanList;
    private ProjectTypeAdapter mTypeAdapter;
    private ProjectTypeAdapter mStageAdapter;
    private OnFilterListener mFilterListener;


    public CommonDataFilterView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CommonDataFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonDataFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //  View view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_commondata_popupwindow, null);
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_commondata_popupwindow, this, true);
        ;
        mAreaView = (DoubleRecyclerView) view.findViewById(R.id.area_view);
        mTypeView = (RecyclerView) view.findViewById(R.id.type_view);
        mStageView = (RecyclerView) view.findViewById(R.id.stage_view);
        mBtnCancel = (Button) view.findViewById(R.id.v_btn_cancel);
        mBtnSubmit = (Button) view.findViewById(R.id.v_btn_submit);
        TextView footer = (TextView) view.findViewById(R.id.bg);
        mBtnCancel.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);

        mViewList = new ArrayList<View>();
        mViewList.add(mAreaView);
        mViewList.add(mTypeView);
        mViewList.add(mStageView);
        mMultiStateView = (MultiStateView) view.findViewById(R.id.multiStateView);
        footer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(GONE);
                mFilterListener.viewGone();
            }
        });
    }


    public void loadData(final CommonDataInterface<BaseClassBean> commonDataInterface) {
        //获取地区
        ThreadExecutor.runInAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    mProviceBeanList = commonDataInterface.getAreaData();
                    ThreadExecutor.runInMain(new Runnable() {
                        @Override
                        public void run() {
                            initAreaData(mProviceBeanList);
                        }
                    });
                } catch (NetworkException e) {
                    ThreadExecutor.runInMain(new NetWorkRunnable());
                }
            }
        });
        //获取类型
        ThreadExecutor.runInAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    mTypeBeanList = BeanUtils.toSubsList(commonDataInterface.getProjectType(), null);
                    ThreadExecutor.runInMain(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager manager = new LinearLayoutManager(mContext);
                            mTypeView.setLayoutManager(manager);
                            mTypeAdapter = new ProjectTypeAdapter(mTypeBeanList);
                            mTypeView.setAdapter(mTypeAdapter);
                        }
                    });
                } catch (NetworkException e) {
                    ThreadExecutor.runInMain(new NetWorkRunnable());
                }
            }
        });

        //获取项目阶段
        ThreadExecutor.runInAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    mStageBeanList = BeanUtils.toSubsList(commonDataInterface.getProjectStage(), null);
                    ThreadExecutor.runInMain(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager manager = new LinearLayoutManager(mContext);
                            mStageView.setLayoutManager(manager);
                            mStageAdapter = new ProjectTypeAdapter(mStageBeanList);
                            mStageView.setAdapter(mStageAdapter);
                        }
                    });
                } catch (NetworkException e) {
                    ThreadExecutor.runInMain(new NetWorkRunnable());
                }
            }
        });
    }


    private void initAreaData(List<BaseClassBean> proviceBeanList) {
        if (!CollectionUtil.isNullOrEmpty(proviceBeanList)) {
            for (BaseClassBean p : proviceBeanList) {
                BaseClassBean allCheckCity = new BaseClassBean(p);
                allCheckCity.name = "全部";
                p.getSubNodes().add(0, allCheckCity);
            }
            mAreaParents = BeanUtils.toParentList(proviceBeanList, null);
            for (ParentBean<BaseClassBean, SubsBean<BaseClassBean>> p : mAreaParents) {
                if (p.getSubs().get(0).isCheck) {
                    for (SubsBean<BaseClassBean> s :
                            p.getSubs()) {
                        s.isCheck = true;
                    }
                    p.getSubs().get(0).isCheck = false;
                    p.init();
                    p.getSubs().get(0).isCheck = true;
                } else if (p.getCheckCount() == p.getSubs().size() - 1) {
                    p.getSubs().get(0).isCheck = true;
                }
            }
            mAreaView.initData(mAreaParents, null, true);
        } else {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }


    public void cancleChooseAreas() {
        for (ParentBean<BaseClassBean, SubsBean<BaseClassBean>> proviceBean : mAreaParents) {
            proviceBean.isCheck = false;
            for (SubsBean<BaseClassBean> cityBean : proviceBean.getSubs()) {
                cityBean.isCheck = false;
            }
            proviceBean.init();
        }
        mAreaView.refreshView();
    }


    public List<BaseClassBean> getChooseAreaBeans() {
        List<BaseClassBean> cityBeanList = new ArrayList<>();
        for (ParentBean<BaseClassBean, SubsBean<BaseClassBean>> p : mAreaParents) {
            int i = 0;
            for (SubsBean<BaseClassBean> cityBean : p.getSubs()) {
                if (cityBean.isCheck) {
                    BaseClassBean city = new BaseClassBean(cityBean.getNode());
                    cityBeanList.add(city);
                    if (i == 0) {
                        city.subs = null;
                        city.name = p.getName();
                        break;
                    }
                }
                i++;
            }
        }

        return cityBeanList;
    }

    public List<String> getChooseAreas() {
        List<String> list = new ArrayList<String>();
        for (BaseClassBean firstBean : getChooseAreaBeans()) {
            list.add(firstBean.no);
        }
        return list;
    }


    public String getAreaStr() {
        List<String> list = new ArrayList<String>();
        for (BaseClassBean firstBean : getChooseAreaBeans()) {
            list.add(firstBean.name);
        }
        return StringUtil.join(",", list);
    }


    public void cancleChooseType() {
        for (SubsBean<BaseClassBean> baseBean : mTypeBeanList) {
            baseBean.isCheck = false;
        }
        mTypeAdapter.notifyDataSetChanged();
    }


    public HashMap<String, List<String>> getChooseTypes() {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        List<SubsBean<BaseClassBean>> baseBeanList = new ArrayList<SubsBean<BaseClassBean>>();
        for (SubsBean<BaseClassBean> baseBean : mTypeBeanList) {
            if (baseBean.isCheck)
                baseBeanList.add(baseBean);
        }
        List<String> listNos = new ArrayList<String>();
        List<String> listName = new ArrayList<String>();
        for (SubsBean firstBean : baseBeanList) {
            listNos.add(firstBean.getNode().getNo());
            listName.add(firstBean.getNode().getName());
        }
        hashMap.put("typeNos", listNos);
        hashMap.put("typeName", listName);
        return hashMap;
    }

    public HashMap<String, List<String>> getChooseTStages() {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        List<SubsBean<BaseClassBean>> baseBeanList = new ArrayList<SubsBean<BaseClassBean>>();
        for (SubsBean<BaseClassBean> baseBean : mStageBeanList) {
            if (baseBean.isCheck)
                baseBeanList.add(baseBean);
        }
        List<String> listNos = new ArrayList<String>();
        List<String> listNames = new ArrayList<String>();
        for (SubsBean firstBean : baseBeanList) {
            listNos.add(firstBean.getNode().getNo());
            listNames.add(firstBean.getNode().getName());
        }
        hashMap.put("stageNos", listNos);
        hashMap.put("stageName", listNames);
        return hashMap;
    }


    public void cancleChooseStage() {
        for (SubsBean<BaseClassBean> baseBean : mStageBeanList) {
            baseBean.isCheck = false;
        }
        mStageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_btn_cancel:
                cancleChooseAreas();
                cancleChooseType();
                cancleChooseStage();
                break;

            case R.id.v_btn_submit:
                setVisibility(GONE);
                mFilterListener.submit(getChooseAreas(), getChooseTypes().get("typeNos"), getChooseTStages().get("stageNos"), getAreaStr(),
                        StringUtil.join(",", getChooseTypes().get("typeName")), StringUtil.join(",", getChooseTStages().get("stageName")));
                boolean isChoose = CollectionUtil.isNullOrEmpty(getChooseAreas()) && CollectionUtil.isNullOrEmpty(getChooseTypes().get("typeNos")) &&
                        CollectionUtil.isNullOrEmpty(getChooseTStages().get("stageNos"));
                if (isChoose)
                    mFilterListener.viewGone();

                break;
        }
    }


    class NetWorkRunnable implements Runnable {

        @Override
        public void run() {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }


    //设置显示界面
    public void setViewState(int state) {
        for (int i = 0; i < mViewList.size(); i++) {
            if (state == i) {
                mViewList.get(i).setVisibility(View.VISIBLE);
            } else {
                mViewList.get(i).setVisibility(View.GONE);
            }
        }
    }


    public int getViewState() {
        for (int i = 0; i < mViewList.size(); i++) {
            if (mViewList.get(i).getVisibility() == View.VISIBLE)
                return i;
        }
        return -1;
    }


    public interface OnFilterListener {
        void submit(List<String> areaList, List<String> typeList, List<String> stageList, String areaStr, String typeStr, String stageStr);

        void viewGone();
    }

    public void setOnSubmitListener(OnFilterListener submitListener) {
        mFilterListener = submitListener;

    }


}

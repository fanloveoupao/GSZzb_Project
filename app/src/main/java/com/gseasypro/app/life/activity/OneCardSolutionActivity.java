package com.gseasypro.app.life.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.presenter.OneCardSolutionPresenter;
import com.example.resources.bean.ItemOneCardBean;
import com.gseasypro.app.R;
import com.gseasypro.app.adapter.life.OneCardSolutionAdapter;
import com.gseasypro.app.life.fragment.RechargeFragment;
import com.gseasypro.app.life.fragment.RecordsConsumptionFragment;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OneCardSolutionActivity extends PresenterActivity<OneCardSolutionPresenter, OneCardSolutionPresenter.OneCardSolutionView>
        implements OneCardSolutionPresenter.OneCardSolutionView {

    public static final String CONSUME_RECORD = "consume_record";
    public static final String WATER_RECORD = "water_record";
    public static final String FOOD_RECORD = "food_record";
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_show_item)
    RecyclerView mRvShowItemList;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    private OneCardSolutionAdapter adapter;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_one_card_solution);
        ButterKnife.bind(this);
        mTitleBar.setBackClick(this);
        mTitleBar.setLeftText("校园一卡通");
        mRvShowItemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OneCardSolutionAdapter(new ArrayList<ItemOneCardBean>());
        mRvShowItemList.setAdapter(adapter);
        ImageLoader.loadIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg", mIvAvatar, true, true);
        getPresenter().initOneCardItem();
        initListener();
    }


    @Override
    public void onInitItemSuccess(List<ItemOneCardBean> data) {
        List<ItemOneCardBean> beanList = new ArrayList<>();
        ItemOneCardBean lifeBean = new ItemOneCardBean();
        lifeBean.imageId = R.mipmap.life_one;
        lifeBean.tag = "one";
        lifeBean.itemTag = "饮食费";
        //
        ItemOneCardBean lifeBean1 = new ItemOneCardBean();
        lifeBean1.imageId = R.mipmap.life_two;
        lifeBean1.tag = "two";
        lifeBean1.itemTag = "水费";
        //
        ItemOneCardBean lifeBean2 = new ItemOneCardBean();
        lifeBean2.imageId = R.mipmap.life_three;
        lifeBean2.tag = "three";
        lifeBean2.itemTag = "电费";
        //
        ItemOneCardBean lifeBean3 = new ItemOneCardBean();
        lifeBean3.imageId = R.mipmap.life_four;
        lifeBean3.tag = "four";
        lifeBean3.itemTag = "宽带费";
        //
        ItemOneCardBean lifeBean4 = new ItemOneCardBean();
        lifeBean4.imageId = R.mipmap.life_five;
        lifeBean4.tag = "five";
        lifeBean4.itemTag = "消费记录";
        //
        ItemOneCardBean lifeBean5 = new ItemOneCardBean();
        lifeBean5.imageId = R.mipmap.life_six;
        lifeBean5.tag = "six";
        lifeBean5.itemTag = "余额";

        beanList.add(lifeBean);
        beanList.add(lifeBean1);
        beanList.add(lifeBean2);
        beanList.add(lifeBean3);
        beanList.add(lifeBean4);
        beanList.add(lifeBean5);
        adapter.setNewData(beanList);
    }

    private void initListener() {
        mRvShowItemList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                switch (adapter.getData().get(position).tag) {
                    case "one":
                        if (getSupportFragmentManager().findFragmentByTag(FOOD_RECORD) == null)
                            RechargeFragment.createInstance("饭卡").show(getSupportFragmentManager(), FOOD_RECORD);
                        break;
                    case "two":
                        if (getSupportFragmentManager().findFragmentByTag(WATER_RECORD) == null)
                            RechargeFragment.createInstance("水费").show(getSupportFragmentManager(), WATER_RECORD);
                        break;
                    case "three":
                        break;
                    case "four":
                        break;
                    case "five":
                        if (getSupportFragmentManager().findFragmentByTag(CONSUME_RECORD) == null)
                            RecordsConsumptionFragment.creatInstance().show(getSupportFragmentManager(), CONSUME_RECORD);
                        break;
                    case "six":
                        break;

                }
            }
        });

    }
}

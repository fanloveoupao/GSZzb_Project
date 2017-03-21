package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/14.
 */

public class PublicRepairPresenter extends BasePresenter<PublicRepairPresenter.PublicRepairView> {


    public interface PublicRepairView extends IView {
        void onGetDegreeSuccess(List<String> typeList);
        void onGetDamageSuccess(List<String> typeList);
    }

    public PublicRepairPresenter(PublicRepairView viewer) {
        super(viewer);
    }

    public void getDegree() {
        List<String> typelist= new ArrayList<>();
        typelist.add("你猜我急不急");
        typelist.add("慢慢来喝杯茶");
        typelist.add("我想你想的睡不着觉");
        typelist.add("哥，快来我身边");
        getView().onGetDegreeSuccess(typelist);
    }

    public void getDamage() {
        List<String> typelist= new ArrayList<>();
        typelist.add("凑活能用");
        typelist.add("已经狗带不能工作");
        typelist.add("活跃度基本为零");
        typelist.add("可以拨打购买新的了");
        getView().onGetDamageSuccess(typelist);
    }
}

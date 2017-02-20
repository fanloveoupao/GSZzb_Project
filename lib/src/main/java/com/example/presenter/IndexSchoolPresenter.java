package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;
import com.example.resources.CommonDataInterface;
import com.example.resources.bean.AdvertisementsBean;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.ProjectBean;
import com.example.resources.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class IndexSchoolPresenter extends BasePresenter<IndexSchoolPresenter.IndexSchoolView> implements CommonDataInterface {
    public IndexSchoolPresenter(IndexSchoolView mView) {
        super(mView);
    }


    public interface IndexSchoolView extends IView {
        List<String> getAreaNos();

        List<String> getTypeNos();

        List<String> getStageNos();

        List<ProjectBean> getMProjectList();

        void onRefreshDataSuccess(boolean isShowAdvertisement);

        void onSetViewEmpty();

        void onSetViewContent();

        void onSetNetworkError();

        void onSetContentError();

        void onLoadDataException(String e);

        void onLoadMoreSuccess(List<ProjectBean> projectBeanList);

        void onGetAdevertiseSuccess(List<AdvertisementsBean> advertisementsBeen);
    }

    private int start = 0;
    private int limit = 30;


    public void refresh(final boolean reset, boolean runBackground) {
        List<ProjectBean> projectBeanList = new ArrayList<>();
        ProjectBean projectBean = new ProjectBean();
        UserBean userBean = new UserBean();
        userBean.name = "广技师";
        userBean.tag = "通知";
        projectBean.user = userBean;
        projectBean.name = "关于校本部后门车辆禁止通行的通知";
        projectBean.content = "由于我校校本部后门现正进行道路施工，导致道路无法适应车辆通行，现通知如下：即日起，校本部后门禁止车辆通行，所有进出车辆从前门通行，何时再次开通将另行通知。";
        List<String> keyWords = new ArrayList<>();
        keyWords.add("道路施工");
        keyWords.add("无法适应车辆通行");
        projectBean.keywords = keyWords;
        projectBean.view_count = 210;
        projectBean.chat_count = 5;
        projectBeanList.add(projectBean);
        getView().onLoadMoreSuccess(projectBeanList);
        getView().onRefreshDataSuccess(true);
    }

    //头部广告
    public void getHeaderListBean() {
        List<AdvertisementsBean> headerListBean = new ArrayList<>();
        AdvertisementsBean bean = new AdvertisementsBean();
        bean.url = "http://ossweb-img.qq.com/images/lol/web201310/skin/big122004.jpg";
        AdvertisementsBean bean1 = new AdvertisementsBean();
        bean1.url = "http://ossweb-img.qq.com/images/lol/web201310/skin/big86011.jpg";
        AdvertisementsBean bean2 = new AdvertisementsBean();
        bean2.url = "http://ossweb-img.qq.com/images/lol/web201310/skin/big75010.jpg";
        headerListBean.add(bean);
        headerListBean.add(bean1);
        headerListBean.add(bean2);
        getView().onGetAdevertiseSuccess(headerListBean);

    }

    public void loadMoreData() {

    }

    public int getLimit() {
        return limit;
    }

    @Override
    public List<BaseClassBean> getAreaData() throws NetworkException {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "教育技术与传播学院";
        List<BaseClassBean> beanList = new ArrayList<>();
        BaseClassBean bean0 = new BaseClassBean();
        bean0.name = "数字媒体";
        BaseClassBean bean1 = new BaseClassBean();
        bean1.name = "网络媒体";
        BaseClassBean bean2 = new BaseClassBean();
        bean2.name = "编导设计";
        BaseClassBean bean3 = new BaseClassBean();
        bean3.name = "教技师";
        beanList.add(bean0);
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
        bean.subs = beanList;
        areas.add(bean);
        return areas;
    }

    @Override
    public List<BaseClassBean> getProjectType() throws NetworkException {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "学校要闻";
        BaseClassBean bean1 = new BaseClassBean();
        bean1.name = "综合新闻";
        BaseClassBean bean2 = new BaseClassBean();
        bean2.name = "通知公告";
        BaseClassBean bean3 = new BaseClassBean();
        bean3.name = "科研信息";
        BaseClassBean bean4 = new BaseClassBean();
        bean4.name = "教学信息";
        BaseClassBean bean5 = new BaseClassBean();
        bean5.name = "招生就业";
        BaseClassBean bean6 = new BaseClassBean();
        bean6.name = "数字校园";
        BaseClassBean bean7 = new BaseClassBean();
        bean7.name = "图书信息";
        areas.add(bean);
        areas.add(bean1);
        areas.add(bean2);
        areas.add(bean3);
        areas.add(bean4);
        areas.add(bean5);
        areas.add(bean6);
        areas.add(bean7);
        return areas;
    }

    @Override
    public List<BaseClassBean> getProjectStage() throws NetworkException {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "校本部";
        BaseClassBean bean1 = new BaseClassBean();
        bean1.name = "白云校区";
        BaseClassBean bean2 = new BaseClassBean();
        bean2.name = "西校区";
        BaseClassBean bean3 = new BaseClassBean();
        bean3.name = "北校区";
        areas.add(bean);
        areas.add(bean1);
        areas.add(bean2);
        areas.add(bean3);
        return areas;
    }


}

package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.exceptions.NetworkException;
import com.example.resources.CommonDataInterface;
import com.example.resources.bean.AdvertisementsBean;
import com.example.resources.bean.BaseClassBean;
import com.example.resources.bean.ProjectBean;
import com.example.resources.bean.UserBean;
import com.example.utils.CollectionUtil;

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

        void onProgressBarGone();

        void onSetViewEmpty();

        void onSetViewContent();

        void onSetNetworkError();

        void onSetContentError();

        void onLoadDataException(String e);

        void onLoadMoreSuccess(List<ProjectBean> projectBeanList);

        void onGetAdevertiseSuccess(List<AdvertisementsBean> advertisementsBeen);
    }

    private int start = 0;
    private int limit = 20;


    public void refresh(final boolean reset, boolean runBackground) {
        final List<String> areaNos = getView().getAreaNos();
        final List<String> typeNos = getView().getTypeNos();
        final List<String> stageNos = getView().getStageNos();
        List<ProjectBean> projectBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProjectBean projectBean = new ProjectBean();
            UserBean userBean = new UserBean();
            userBean.image = "https://imgsa.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=68da02df3901213fdb3e468e358e5db4/9f510fb30f2442a73cf9cba3d143ad4bd01302c4.jpg";
            projectBean.user = userBean;
            if (i % 2 == 0) {
                userBean.name = "广技师";
                userBean.tag = "通知";
                projectBean.name = "关于调整各校区交通车安排的紧急通知";
                projectBean.content = "由于我校校本部后门现正进行道路施工，导致道路无法适应车辆通行，现通知如下：即日起，校本部后门禁止车辆通行，所有进出车辆从前门通行，何时再次开通将另行通知。";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("校本部");
                keyWords.add("道路施工");
                keyWords.add("无法适应车辆通行");
                projectBean.keywords = keyWords;
            } else {
                userBean.name = "传播学院";
                userBean.tag = "通知";
                userBean.image = "http://setc.gpnu.edu.cn/img/logo.png";
                projectBean.name = "关于调整各校区交通车安排的紧急通知";
                projectBean.content = "从2017年2月17日起，除保留各校区往返白云校区的交通车（详见附件）外，取消其它市内各校区的往返交通车。请各单位务必通知到本单位的全体员工，确保开学各项工作稳定有序运行。";
                List<String> keyWords = new ArrayList<>();
                keyWords.add("2017年2月17日");
                keyWords.add("白云校区");
                projectBean.keywords = keyWords;
            }
            projectBean.view_count = 210;
            projectBean.chat_count = 5;
            projectBeanList.add(projectBean);
        }
        boolean isShowAdvertisement = CollectionUtil.isNullOrEmpty(areaNos) && CollectionUtil.isNullOrEmpty(typeNos) &&
                CollectionUtil.isNullOrEmpty(stageNos);
        getView().onProgressBarGone();
        getView().onLoadMoreSuccess(projectBeanList);
        getView().onRefreshDataSuccess(isShowAdvertisement);
    }

    //头部广告
    public void getHeaderListBean() {
        List<AdvertisementsBean> headerListBean = new ArrayList<>();
        AdvertisementsBean bean = new AdvertisementsBean();
        bean.image = "http://www.gdin.edu.cn/images/1219.jpg";
        AdvertisementsBean bean1 = new AdvertisementsBean();
        bean1.image = "http://file.tgimg.cn/Image/Show?fid=42496275784-c699377f-985a-477b-a6ea-023359ce4330.jpg";
        AdvertisementsBean bean2 = new AdvertisementsBean();
        bean2.image = "http://file.tgimg.cn/Image/Show?fid=75236279058-dc8ea0ff-3fc7-4c7a-a53f-94e57e604b21.jpg";
        headerListBean.add(bean);
        headerListBean.add(bean1);
        headerListBean.add(bean2);
        getView().onGetAdevertiseSuccess(headerListBean);

    }

    public void loadMoreData() {
        refresh(false, false);

    }

    public int getLimit() {
        return limit;
    }

    @Override
    public List<BaseClassBean> getAreaData() throws NetworkException {
        List<BaseClassBean> areas = new ArrayList<>();
        BaseClassBean bean = new BaseClassBean();
        bean.name = "传播学院";
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

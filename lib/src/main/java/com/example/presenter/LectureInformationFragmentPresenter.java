package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ProjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/13.
 */

public class LectureInformationFragmentPresenter extends BasePresenter<LectureInformationFragmentPresenter.LectureInformationFragmentView> {


    public interface LectureInformationFragmentView extends IView {
        void oninitSuccess(List<ProjectBean> data);

        void onLoadMoreData(List<ProjectBean> data);

        void onNoMoreData();

        void onLoadFailed();

        void onNetErro();
    }

    private int start = 0;
    private int limit = 20;

    public LectureInformationFragmentPresenter(LectureInformationFragmentView viewer) {
        super(viewer);
    }

    public void initLectureInformation() {
        List<ProjectBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProjectBean bean = new ProjectBean();
            bean.name = "广师掌中宝见面会";
            bean.content = "2月27日，从广州市政府新闻办举行的发布会获悉，我院在2016年广州市发明专利申请量排名第六，同比增长1217.8%，这是我院首次在广州市发明专利申请量入围前十。";
            bean.update_date = "2017-02-26 12:44:56";
            bean.state_type = 0;
            if (i % 2 == 0)
                bean.state_type = 1;
            data.add(bean);
        }
        getView().oninitSuccess(data);
    }

    public void loadMoreData() {

    }

    public int getLimit() {
        return limit;
    }
}

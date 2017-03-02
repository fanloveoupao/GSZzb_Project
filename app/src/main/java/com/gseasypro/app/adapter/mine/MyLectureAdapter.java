package com.gseasypro.app.adapter.mine;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.resources.bean.ProjectBean;
import com.example.utils.DateUtil;
import com.gseasypro.app.R;

import java.util.List;

import app.gseasypro.com.utils.widget.LoadMoreAdapter;

import static com.example.resources.bean.ProjectBean.STATE_HAS_PUBLISH;
import static com.example.resources.bean.ProjectBean.STATE_NOT_PASS_CHECK;
import static com.example.resources.bean.ProjectBean.STATE_NO_PUBLISH;

/**
 * Created by fan-gk on 2017/3/1.
 */

public class MyLectureAdapter extends LoadMoreAdapter<ProjectBean> {

    public MyLectureAdapter(List<ProjectBean> data) {
        super(R.layout.item_mylecture_list, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectBean projectBean) {
        baseViewHolder.setText(R.id.tv_project_name, projectBean.name)
                .setText(R.id.tv_project_situation, projectBean.content)
                .setText(R.id.tv_project_date, DateUtil.parseTime(projectBean.update_date, DateUtil.MINUTE_FORMAT));

        TextView tv = baseViewHolder.getView(R.id.tv_project_stage);
        switch (projectBean.state_type) {
            case STATE_NO_PUBLISH: {
                tv.setBackgroundResource(R.drawable.shape_project_state_no_publish_bg);
                tv.setText("未发布");
            }
            break;
            case STATE_HAS_PUBLISH: {
                tv.setBackgroundResource(R.drawable.shape_project_state_examine_bg);
                tv.setText("已发布");
            }
            break;
            case STATE_NOT_PASS_CHECK: {
                tv.setBackgroundResource(R.drawable.shape_project_state_examine_bg);
                tv.setText("审核不通过");
            }
            break;
        }

    }
}

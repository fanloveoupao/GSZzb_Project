package com.gseasypro.app.adapter.school;

import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lang.StringUtil;
import com.example.resources.bean.ExperiencePredecessorsBean;
import com.example.utils.CollectionUtil;
import com.example.utils.DateUtil;
import com.example.utils.HtmlTextUtils;
import com.gseasypro.app.R;

import java.text.ParseException;
import java.util.List;

import app.gseasypro.com.utils.BaseActivity;
import app.gseasypro.com.utils.widget.LoadMoreAdapter;

import static com.example.lang.StringUtil.EMPTY;

/**
 * Created by fan-gk on 2017/4/12.
 */

public class ExperiencePredecessorsAdapter extends LoadMoreAdapter<ExperiencePredecessorsBean> {
    private final BaseActivity mActivity;

    public ExperiencePredecessorsAdapter(BaseActivity mActivity,List<ExperiencePredecessorsBean> data) {
        super(R.layout.item_tg_organization_project_first, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ExperiencePredecessorsBean bean) {
        LinearLayout llMemberContent = baseViewHolder.getView(R.id.ll_project_content);
        String timeAndPublisher = EMPTY;
        try {
            timeAndPublisher = String.format("%1$s%2$s发布", bean.user, DateUtil.getInterval(DateUtil
                    .stringToDate(bean.update_date, DateUtil.TIME_FORMAT)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        baseViewHolder.setText(R.id.tv_project_name, bean.name)
                .setText(R.id.tv_type, bean.project_stage)
                .setText(R.id.tv_publisher_and_time, timeAndPublisher);
        TextView tvProjectName = baseViewHolder.getView(R.id.tv_project_name);
        if (bean.recommend)
            tvProjectName.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(mActivity, R.mipmap.bottom_label_referral), null);
        else
            tvProjectName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        String content = bean.content;
        if (!StringUtil.isNullOrEmpty(content)) {
            if (!CollectionUtil.isNullOrEmpty(bean.keywords)) { //高亮关键字
                for (String keyword : bean.keywords) {
                    content = content.replace(keyword, HtmlTextUtils.color(HtmlTextUtils.COLOR_ORANGE, keyword));
                }
            }
            baseViewHolder.setText(R.id.tv_detail, Html.fromHtml(content));
        }


        llMemberContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

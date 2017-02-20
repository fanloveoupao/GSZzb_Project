package com.gseasypro.app.adapter.school;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.utils.CollectionUtil;
import com.example.lang.StringUtil;
import com.example.resources.bean.ProjectBean;
import com.example.resources.bean.UserBean;
import com.example.utils.HtmlTextUtils;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

import app.gseasypro.com.utils.widget.LoadMoreAdapter;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class ProjectListAdapter extends LoadMoreAdapter<ProjectBean> {

    public ProjectListAdapter(List<ProjectBean> data) {
        super(R.layout.item_circle_project_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProjectBean projectBean) {
        UserBean userBean = projectBean.user;
        if (userBean != null) {
            baseViewHolder.setText(R.id.tv_people_name, userBean.name);
            baseViewHolder.setText(R.id.tv_action, userBean.tag + "");
        }
        ImageView ivHead = baseViewHolder.getView(R.id.iv_head);
        ImageLoader.loadIcon("", ivHead, true, true);

        String content = projectBean.content;
        if (!StringUtil.isNullOrEmpty(content)) {
            if (!CollectionUtil.isNullOrEmpty(projectBean.keywords)) { //高亮关键字
                for (String keyword : projectBean.keywords) {
                    content = content.replace(keyword, HtmlTextUtils.color(HtmlTextUtils.COLOR_ORANGE, keyword));
                }
            }
            baseViewHolder.setText(R.id.tv_detail, Html.fromHtml(content));
        }
        baseViewHolder.setText(R.id.tv_people_name, projectBean.user.name)
                .setText(R.id.tv_project_name, projectBean.name)
                .setText(R.id.tv_exchange_count, projectBean.chat_count + "");

        TextView readCount = baseViewHolder.getView(R.id.tv_read_count);
        readCount.setText((projectBean.view_count >= 9999 ? 9999 + "" : projectBean.view_count + "") + "");

    }
}


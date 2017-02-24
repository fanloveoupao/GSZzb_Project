package com.gseasypro.app.adapter.mine;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lang.StringUtil;
import com.example.resources.bean.MyElderSisterBean;
import com.example.utils.CollectionUtil;
import com.example.utils.HtmlTextUtils;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.List;

/**
 * Created by fan-gk on 2017/2/24.
 */


public class MyEideSisterAdapter extends PagerAdapter {
    private Context context;
    private List<MyElderSisterBean> data;
    private LayoutInflater inflater;

    public MyEideSisterAdapter(Context context, List<MyElderSisterBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = null;
        if (position < data.size()) {
            view = inflater.inflate(R.layout.item_hongniang_project, container, false);
            ImageView mIvHead = (ImageView) view.findViewById(R.id.iv_head);
            TextView mTvName = (TextView) view.findViewById(R.id.tv_name);
            TextView mTvJob = (TextView) view.findViewById(R.id.tv_job);
            TextView mTvBusiness = (TextView) view.findViewById(R.id.tv_business);
            TextView mTvAddress = (TextView) view.findViewById(R.id.tv_address);
            TextView mTvFollowDetail = (TextView) view.findViewById(R.id.tv_follow_detail);
            TextView mBtnCheckDetail = (TextView) view.findViewById(R.id.btn_check_detail);
            TextView mBtnNotSuit = (TextView) view.findViewById(R.id.btn_not_suit);
            TextView mTvChat = (TextView) view.findViewById(R.id.tv_chat);
            TextView mTvExhangeProject = (TextView) view.findViewById(R.id.tv_exhange_project);
            TextView mTvOther = (TextView) view.findViewById(R.id.tv_other);

            final MyElderSisterBean bean = data.get(position);

            ImageLoader.loadIcon(Uri.parse(bean.image), mIvHead, true, true);
            mTvJob.setText(bean.college + "");
            mTvBusiness.setText(bean.signature);
            mTvName.setText(bean.name + "");

            mTvAddress.setText(Html.fromHtml(bean.gaduateWork));
            String content = bean.content;
            if (!StringUtil.isNullOrEmpty(content)) {
                if (!CollectionUtil.isNullOrEmpty(bean.keywords)) { //高亮关键字
                    for (String keyword : bean.keywords) {
                        content = content.replace(keyword, HtmlTextUtils.color(HtmlTextUtils.COLOR_ORANGE, keyword));
                    }
                }
                mTvFollowDetail.setText(content);
            }
            mTvExhangeProject.setText(bean.firstBlog);

        } else {
            //添加最后一个View
            view = inflater.inflate(R.layout.item_hongniang_project_last, null);

        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
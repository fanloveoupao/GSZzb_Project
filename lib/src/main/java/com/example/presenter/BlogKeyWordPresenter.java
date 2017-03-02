package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/1.
 */

public class BlogKeyWordPresenter extends BasePresenter<BlogKeyWordPresenter.BlogKeyWordIView> {
    public BlogKeyWordPresenter(BlogKeyWordIView viewer) {
        super(viewer);
    }

    public interface BlogKeyWordIView extends IView {

    }
}

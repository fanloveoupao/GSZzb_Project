package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/2/9.
 */

public class SearchNewsPresenter extends BasePresenter<SearchNewsPresenter.SearchNewsIView> {
    public SearchNewsPresenter(SearchNewsIView mView) {
        super(mView);
    }

    public interface SearchNewsIView extends IView {

    }
}

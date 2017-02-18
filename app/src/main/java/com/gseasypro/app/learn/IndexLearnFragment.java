package com.gseasypro.app.learn;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presenter.IndexLearnPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterFragment;

/**
 * Created by fan-gk on 2017/2/9.
 */
public class IndexLearnFragment extends PresenterFragment<IndexLearnPresenter, IndexLearnPresenter.IndexLearnIView>
        implements IndexLearnPresenter.IndexLearnIView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_learn, container, false);
    }

}

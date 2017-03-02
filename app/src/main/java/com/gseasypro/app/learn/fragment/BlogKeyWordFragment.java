package com.gseasypro.app.learn.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presenter.BlogKeyWordPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogKeyWordFragment extends DialogPresenterFragment<BlogKeyWordPresenter, BlogKeyWordPresenter.BlogKeyWordIView>
        implements BlogKeyWordPresenter.BlogKeyWordIView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;

    public static BlogKeyWordFragment createInstance() {
        return new BlogKeyWordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog_key_word, container, false);
        ButterKnife.bind(this, view);
        mTitleBar.setLeftText("博客关键字");
        mTitleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

}

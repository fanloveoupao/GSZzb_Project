package com.gseasypro.app.mine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presenter.SeatTableViewPresenter;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.DialogPresenterFragment;
import app.gseasypro.com.utils.ui.widget.SeatTableView;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeatTableViewFragment extends DialogPresenterFragment<SeatTableViewPresenter, SeatTableViewPresenter.SeatTableViewIView>
        implements SeatTableViewPresenter.SeatTableViewIView {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.seat_view)
    SeatTableView mSeatView;
    private boolean[][] isSold;

    public static SeatTableViewFragment createInstance() {
        return new SeatTableViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ActivityStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_seat_table_view, container, false);
        ButterKnife.bind(this, mView);
        mTitleBar.setLeftText("坐位查看");
        mTitleBar.setBackViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mSeatView.setScreenName("讲台");
        mSeatView.setMaxSelected(1);
        getPresenter().hasSold();
        initListener();
        return mView;
    }

    private void initListener() {
        mSeatView.setSeatChecker(new SeatTableView.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                //设置坐位不可用
                if (column == 4 || column == 9) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                //是否出售
                return isSold[row][column];
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
    }

    @Override
    public void onIsSold(final boolean[][] isSold, int row, int column) {
        this.isSold = isSold;
        mSeatView.setData(row, column);

    }
}

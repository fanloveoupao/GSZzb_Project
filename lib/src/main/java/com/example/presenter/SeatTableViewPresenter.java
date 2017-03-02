package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;

/**
 * Created by fan-gk on 2017/3/1.
 */

public class SeatTableViewPresenter extends BasePresenter<SeatTableViewPresenter.SeatTableViewIView> {
    public SeatTableViewPresenter(SeatTableViewIView viewer) {
        super(viewer);
    }

    public interface SeatTableViewIView extends IView {
        void onIsSold(boolean num[][], int row, int column);
    }

    public void hasSold() {
        boolean[][] num = new boolean[8][14];
        num[5][5] = true;
        num[1][7] = true;
        getView().onIsSold(num, num.length, num[0].length);
    }
}

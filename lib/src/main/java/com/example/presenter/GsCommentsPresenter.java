package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.GsCommentsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class GsCommentsPresenter extends BasePresenter<GsCommentsPresenter.GsCommentsView> {
    public GsCommentsPresenter(GsCommentsView viewer) {
        super(viewer);
    }

    public interface GsCommentsView extends IView {
        void initCommentsDatas(List<GsCommentsBean> list);

        void sendCommentSuccess();

        void sendCommentFail();
    }

    public void fillCommentList() {
        List<GsCommentsBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GsCommentsBean gsCommentsBean = new GsCommentsBean();
            gsCommentsBean.comment_avatar = "";
            gsCommentsBean.comment_content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit.";
            if (i % 2 == 0)
                gsCommentsBean.comment_content = "Cupcake ipsum dolor sit. Amet gingerbread cupcake. Gummies ice cream dessert icing marzipan apple pie dessert sugar plum.";
            list.add(gsCommentsBean);
        }
        getView().initCommentsDatas(list);
    }

    public void sendComments(String content) {
        getView().sendCommentSuccess();
    }
}

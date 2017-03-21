package com.example.presenter;

import com.example.BasePresenter;
import com.example.IView;
import com.example.resources.bean.ClassContactsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/14.
 */

public class ClassContactsPresenter extends BasePresenter<ClassContactsPresenter.ClassContactsView> {


    public interface ClassContactsView extends IView {
        void onInitContactData(List<ClassContactsBean> data);
    }

    public ClassContactsPresenter(ClassContactsView viewer) {
        super(viewer);
    }

    public void getContactData() {
        List<ClassContactsBean> contacts = new ArrayList<>();
        contacts.add(new ClassContactsBean("A", "Abbey", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2004859730,2853788453&fm=23&gp=0.jpg"));
        contacts.add(new ClassContactsBean("A", "Alex", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1529458221,721614667&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("A", "Amy", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2829709266,2452627864&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("A", "Anne", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3891163424,1720806704&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("B", "Betty", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4176430521,1443699849&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("B", "Bob", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3970603959,1320848728&fm=23&gp=0.jpg"));
        contacts.add(new ClassContactsBean("B", "Brian", ""));
        contacts.add(new ClassContactsBean("C", "Carl", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=531026102,1083003535&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("C", "Candy", ""));
        contacts.add(new ClassContactsBean("C", "Carlos", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2845730738,2053384597&fm=23&gp=0.jpg"));
        contacts.add(new ClassContactsBean("C", "Charles", ""));
        contacts.add(new ClassContactsBean("C", "Christina", ""));
        contacts.add(new ClassContactsBean("D", "David", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=665331586,498523324&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("D", "Daniel", ""));
        contacts.add(new ClassContactsBean("E", "Elizabeth", ""));
        contacts.add(new ClassContactsBean("E", "Eric", ""));
        contacts.add(new ClassContactsBean("E", "Eva", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=531026102,1083003535&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("F", "Frances", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3235514469,2474615309&fm=23&gp=0.jpg"));
        contacts.add(new ClassContactsBean("F", "Frank", ""));
        contacts.add(new ClassContactsBean("I", "Ivy", ""));
        contacts.add(new ClassContactsBean("J", "James", ""));
        contacts.add(new ClassContactsBean("J", "John", ""));
        contacts.add(new ClassContactsBean("J", "Jessica", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2874716783,525443833&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("K", "Karen", ""));
        contacts.add(new ClassContactsBean("K", "Karl", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=531026102,1083003535&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("K", "Kim", ""));
        contacts.add(new ClassContactsBean("L", "Leon", ""));
        contacts.add(new ClassContactsBean("L", "Lisa", ""));
        contacts.add(new ClassContactsBean("P", "Paul", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1529458221,721614667&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("P", "Peter", ""));
        contacts.add(new ClassContactsBean("S", "Sarah", ""));
        contacts.add(new ClassContactsBean("S", "Steven", ""));
        contacts.add(new ClassContactsBean("R", "Robert", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=731896501,4069272110&fm=23&gp=0.jpg"));
        contacts.add(new ClassContactsBean("T", "Tom", ""));
        contacts.add(new ClassContactsBean("T", "Tony", ""));
        contacts.add(new ClassContactsBean("W", "Wendy", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=531026102,1083003535&fm=11&gp=0.jpg"));
        contacts.add(new ClassContactsBean("W", "Will", ""));
        contacts.add(new ClassContactsBean("W", "William", ""));
        contacts.add(new ClassContactsBean("Z", "Zoe", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3816960686,91865076&fm=11&gp=0.jpg"));
        getView().onInitContactData(contacts);
    }
}

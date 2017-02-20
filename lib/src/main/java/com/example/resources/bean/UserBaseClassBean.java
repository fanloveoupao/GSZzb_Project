package com.example.resources.bean;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class UserBaseClassBean extends BaseClassBean implements LinearBean,LinearWithMainBean {
    public boolean main;

    @Override
    public boolean getMain() {
        return main;
    }

    @Override
    public void setMain(boolean main) {
        this.main = main;
    }
}

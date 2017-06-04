package com.example.ui;

import com.example.repositories.ClientRepositories;
import com.example.repositories.ISingleRepository;
import com.example.repositories.IWriteableSingleRepository;
import com.example.resources.bean.BaseUserBean;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by fan-gk
 * on 2017/5/24.
 */


public class UserSettings {
    private static String BASE_USER_BEAN = "base_user_bean";
    private BaseUserBean user;

    public static UserSettings getInstance() {
        return new UserSettings();
    }

    private <T> ISingleRepository<T> getSingleRepository(String key, Class<T> classOfT) {
        return ClientRepositories
                .getInstance()
                .getSharedPreferencesRepositoryProvider()
                .GetSingleRepository("UserSettings_", key, classOfT);
    }

    private IWriteableSingleRepository getWriteableSingleRepository(String key) {
        return getSingleRepository(key, Object.class);
    }

    public void setBaseUserBean(BaseUserBean baseUserBean) {
        getWriteableSingleRepository(BASE_USER_BEAN).addOrReplace(baseUserBean);
    }


    public BaseUserBean getBaseUserBean() {
        return getSingleRepository(BASE_USER_BEAN, BaseUserBean.class).get();
    }

    /*   public void saveKeymanList(List<BaseClassBean> keymanList) {
        getWriteableSingleRepository(KEY_MAN).addOrReplace(keymanList);
    }


    public List<BaseClassBean> getKeymanList() {
        BaseClassBean[] keymanSecondBeen = getSingleRepository(KEY_MAN, BaseClassBean[].class).get();
        return keymanSecondBeen == null ? null : asList(keymanSecondBeen);
    }*/

}

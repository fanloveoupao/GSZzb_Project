package com.example.resources.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class ProjectBean implements Serializable {

    public long pid;
    public long uid;
    public long cpid;
    public String name;

    public BaseClassBean area;
    public int view_count;
    public String update_date;
    public String content;
    public String hope;

    public BaseClassBean project_stage;
    public int chat_count;
    public String follow_up_situation;
    public int store_state;
    public int examined_state;
    public List<String> keywords;
    public List<String> tags;
    public UserBean user;
    public boolean viewed;
    public String purchasing_progress;
    public int size;
    public String cost;
    public List<String> constitutes;
    public PurchasingType purchasing_type;
    public int product_quality;
    public String examined_remark;


    public String show_date;
    public int exchange_count;

    public List<BaseClassBean> project_class;
    public List<UserBaseClassBean> key_mans;


    //附加字段
    public int state_type;//项目状态

    public final static int STATE_NO_PUBLISH = 1;
    public final static int STATE_HAS_PUBLISH = 2;
    public final static int STATE_NOT_PASS_CHECK = 3;

    public class PurchasingType implements Serializable {
        public int Key;
        public String Value;
    }

    public void proccessProjectState() {

        switch (this.store_state) {
            case 0: {
                switch (this.examined_state) {
                    case 0://未审核
                    case 1://审核通过
                        this.state_type = STATE_HAS_PUBLISH;
                        break;
                    case 2:
                        //审核不通过
                        this.state_type = STATE_NOT_PASS_CHECK;
                        break;

                }
            }
            break;
            case 1: {
                //未发布
                this.state_type = STATE_NO_PUBLISH;
            }
            break;
        }

    }

}
package com.example.resources.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fan-gk on 2017/3/2.
 */


public class UserInfoBean implements Serializable {

    public String mobile;
    public String email;
    public int project_count;
    public int quality_project_count;
    public int suited_cooperation_project_count;
    public int praise_count;
    public boolean tarento;
    public boolean excellent;
    public boolean has_face;
    public boolean is_opened_matchmaker;
    public int join_group_number;
    public String job;
    public String name;
    public String company;
    public String colleage;
    public String image;
    public int business_model;
    public BaseClassBean birth_area;
    public String birthday;
    public String sex;
    public int experience;

    public List<BaseClassBean> business_areas;
    public List<String> interests;
    public List<UserBaseClassBean> project_classes;
    public List<UserBaseClassBean> project_stages;
    public List<UserBaseClassBean> key_mans;

    public Integer relation;
    public int product_count;
    public boolean is_star;
    public String description;
    public String nick;
    public List<String> cooperation_points;
    public List<String> customer_resource;
    public String url;
}



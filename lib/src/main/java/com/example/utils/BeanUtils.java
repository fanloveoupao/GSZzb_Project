package com.example.utils;

import com.example.resources.bean.LinearBean;
import com.example.resources.bean.LinearWithMainBean;
import com.example.resources.bean.ParentBean;
import com.example.resources.bean.SubsBean;
import com.example.resources.bean.SubsBeanWithMain;
import com.example.resources.bean.SubsBeanWithMainLocal;
import com.example.resources.bean.TwoLevelTreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan-gk on 2017/2/20.
 */


public class BeanUtils {


    public static <T extends TwoLevelTreeBean<L>, L extends LinearBean, LL extends LinearBean> List<ParentBean<T , SubsBean<L>>> toParentList(List<T> items,
                                                                                                                                             List<LL> checkedItems) {
        List<ParentBean<T, SubsBean<L>>> parents = new ArrayList<>();
        for (T b : items) {
            ParentBean<T, SubsBean<L>> parent = new ParentBean<T, SubsBean<L>>(b);
            parents.add(parent);
            parent.getSubs().addAll(toSubsList(b.getSubNodes(), checkedItems));
            parent.init();
        }
        return parents;
    }

    public static <T extends TwoLevelTreeBean<L>, L extends LinearBean, LL extends LinearWithMainBean> List<ParentBean<T,
            SubsBeanWithMain<L>>> toParentWithSubsWithMainList(List<T> items, List<LL> checkedItems) {
        List<ParentBean<T, SubsBeanWithMain<L>>> parents = new ArrayList<>();
        for (T b : items) {
            ParentBean<T, SubsBeanWithMain<L>> parent = new ParentBean<T, SubsBeanWithMain<L>>(b);
            parents.add(parent);
            parent.getSubs().addAll(toSubsWithMainList(b.getSubNodes(), checkedItems));
            parent.init();
        }
        return parents;
    }

    public static <T extends TwoLevelTreeBean<L>, L extends LinearBean, LL extends LinearWithMainBean> List<ParentBean<T,
            SubsBeanWithMainLocal<L>>> toParentWithSubsWithMainLocalList(List<T> items,
                                                                         List<LL> checkedItems,
                                                                         List<L> localItems) {
        List<ParentBean<T, SubsBeanWithMainLocal<L>>> parents = new ArrayList<>();
        for (T b :
                items) {
            ParentBean<T, SubsBeanWithMainLocal<L>> parent = new ParentBean<T, SubsBeanWithMainLocal<L>>(b);
            parents.add(parent);
            parent.getSubs().addAll(toSubsWithMainLocalList(b.getSubNodes(), checkedItems, localItems));
            parent.init();
        }
        return parents;
    }

    public static <T extends LinearBean, L extends LinearBean> List<SubsBean<T>> toSubsList(List<T> items, List<L> checkedItems) {
        List<SubsBean<T>> subs = new ArrayList<>();
        for (T b : items) {
            SubsBean<T> sub = new SubsBean<>(b);
            subs.add(sub);
            if (checkedItems == null) continue;
            for (L u : checkedItems) {
                if (b.equals(u)) {
                    sub.isCheck = true;
                    break;
                }
            }
        }
        return subs;
    }

    public static <T extends LinearBean, L extends LinearWithMainBean> List<SubsBeanWithMain<T>> toSubsWithMainList(List<T> items,
                                                                                                                    List<L> checkedItems) {
        List<SubsBeanWithMain<T>> subs = new ArrayList<>();
        for (T b :
                items) {
            SubsBeanWithMain<T> sub = new SubsBeanWithMain<>(b);
            subs.add(sub);
            if (checkedItems == null) continue;
            for (L u :
                    checkedItems) {
                if (b.equals(u)) {
                    sub.isCheck = true;
                    sub.main = u.getMain();
                    break;
                }
            }
        }
        return subs;
    }

    public static <T extends LinearBean, L extends LinearWithMainBean> List<SubsBeanWithMainLocal<T>> toSubsWithMainLocalList(List<T> items
            , List<L> checkedItems, List<T> localItems) {
        List<SubsBeanWithMainLocal<T>> subs = new ArrayList<>();
        for (T b :
                items) {
            SubsBeanWithMainLocal<T> sub = new SubsBeanWithMainLocal<>(b);
            subs.add(sub);
            if (checkedItems != null) {
                for (L u :
                        checkedItems) {
                    if (b.equals(u)) {
                        sub.isCheck = true;
                        sub.main = u.getMain();
                        break;
                    }
                }
            }
            if (localItems != null) {
                for (T l :
                        localItems) {
                    if (b.equals(l)) {
                        sub.isLocal = true;
                        break;
                    }
                }
            }
        }
        return subs;
    }

//    public static String getKeymanString(List<KeymanSecondBean> keymanSecondBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(keymanSecondBeanList)) {
//            String str = "";
//            for (int i = 0; i < keymanSecondBeanList.size(); i++) {
//                String name = keymanSecondBeanList.get(i).name;
//                // if (!name.contains("其他")) {
//                if (i != keymanSecondBeanList.size() - 1)
//                    str = str + name + "，";
//                else
//                    str = str + name;
//                //   }
//            }
//            return str;
//        } else
//            return null;
//
//    }
//
//    public static String getSubString(List<SubsBean> projectTypeFirstBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(projectTypeFirstBeanList)) {
//            String str = "";
//            for (int i = 0; i < projectTypeFirstBeanList.size(); i++) {
//                String name = projectTypeFirstBeanList.get(i).name;
//                if (i != projectTypeFirstBeanList.size() - 1)
//                    str = str + name + "，";
//                else
//                    str = str + name;
//
//
//            }
//            return str;
//        } else
//            return null;
//    }

//    public static String getFirstTypeString(List<ProjectTypeFirstBean> projectTypeFirstBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(projectTypeFirstBeanList)) {
//            String str = "";
//            for (int i = 0; i < projectTypeFirstBeanList.size(); i++) {
//                if (i != projectTypeFirstBeanList.size() - 1)
//                    str = str + projectTypeFirstBeanList.get(i).name + "，";
//                else
//                    str = str + projectTypeFirstBeanList.get(i).name;
//
//
//            }
//            return str;
//        } else
//            return null;
//    }

//    public static String getListBaseClassNameString(List<BaseClassBean> baseClassBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(baseClassBeanList)) {
//            String str = "";
//            for (int i = 0; i < baseClassBeanList.size(); i++) {
//                if (i != baseClassBeanList.size() - 1)
//                    str = str + baseClassBeanList.get(i).name + "，";
//                else
//                    str = str + baseClassBeanList.get(i).name;
//
//
//            }
//            return str;
//        } else
//            return null;
//    }


//    public static String getCityString(List<CityBean> cityBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(cityBeanList)) {
//            String str = "";
//            for (int i = 0; i < cityBeanList.size(); i++) {
//                String name = cityBeanList.get(i).name;
//                if (!name.contains("全部")) {
//                    if (i != cityBeanList.size() - 1)
//                        str = str + name + "，";
//                    else
//                        str = str + name;
//                }
//            }
//            return str;
//        } else
//            return null;
//
//    }

//    public static String getFirstStageString(List<ProjectStageFirstBean> stageFirsteanList) {
//        if (!CollectionUtil.isNullOrEmpty(stageFirsteanList)) {
//            String str = "";
//            for (int i = 0; i < stageFirsteanList.size(); i++) {
//                String name = stageFirsteanList.get(i).name;
//                if (i != stageFirsteanList.size() - 1)
//                    str = str + stageFirsteanList.get(i).name + "，";
//                else
//                    str = str + stageFirsteanList.get(i).name;
//            }
//            return str;
//        } else
//            return null;
//    }


//    public static String getTypeString(List<SubsBean> subsBeanList) {
//        if (!CollectionUtil.isNullOrEmpty(subsBeanList)) {
//            String str = "";
//            for (int i = 0; i < subsBeanList.size(); i++) {
//                String name = subsBeanList.get(i).name;
//                // if (!name.contains("其他")) {
//                if (i != subsBeanList.size() - 1)
//                    str = str + name + "，";
//                else
//                    str = str + name;
//                //   }
//            }
//            return str;
//        } else
//            return null;
//
//    }


//    public  static  String getProductString(List<HongniangUserBean.ProductsBean> productsBeanList) {
//        String str = "";
//        if (!CollectionUtil.isNullOrEmpty(productsBeanList)) {
//            for (int i = 0; i < productsBeanList.size(); i++) {
//                if (i < productsBeanList.size() - 1)
//                    str = productsBeanList.get(i).name + ",";
//                else
//                    str = productsBeanList.get(i).name;
//            }
//        }
//        return  str;
//    }

//    public  static  String getUserProductString(List<UserProductBean> productsBeanList) {
//        String str = "";
//        if (!CollectionUtil.isNullOrEmpty(productsBeanList)) {
//            for (int i = 0; i < productsBeanList.size(); i++) {
//                if (i < productsBeanList.size() - 1)
//                    str = productsBeanList.get(i).name + ",";
//                else
//                    str = productsBeanList.get(i).name;
//            }
//        }
//        return  str;
//    }

}


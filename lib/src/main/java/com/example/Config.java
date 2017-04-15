package com.example;

/**
 * Created by fan-gk on 2017/4/14.
 */


public class Config {
    public final static String SCHEMA = "http://";

    public final static String WEB_HOST = "ywq.tgnet.com";

    public final static String HOST = "192.168.0.42";

    public final static String SEARCH_HOST = SCHEMA + "api.search.tgnet.com";

    public final static String BASE_URL = SCHEMA + HOST;

    public final static String getBaseUrl(String host) {
        return BASE_URL;
    }

    public final static String USER_FACE_BASE_URL = "http://user.tgnet.com/User/Face";

    public final static String TG_SOCIETY_ICON_URL = "http://statics.ywq.tgnet.com/api/Icon";

    public final static String FILE_BASE_URL = "http://file.tgimg.cn";


    public final static String NEWEST_CLIENT_DOWNLOAD_URL = BASE_URL + "/Api/ClientDownload?version=&invite=";


    public final static boolean openGroup = false; //是否开发圈子功能
}

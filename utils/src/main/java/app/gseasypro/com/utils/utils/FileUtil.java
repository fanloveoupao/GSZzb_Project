package app.gseasypro.com.utils.utils;

import com.example.lang.StringUtil;

/**
 * Created by fan-gk on 2017/3/2.
 */

public class FileUtil {
    public static String getExtension(String filename){
        if(filename == null)
            return StringUtil.EMPTY;

        int index = filename.lastIndexOf(".");
        if (index == -1)
            return StringUtil.EMPTY;

        return filename.substring(index);
    }

    public static String getMimeType(String extension){
        if(extension == null)
            return "image/jpeg";
        switch (extension.toLowerCase()){
            case ".png":
                return "image/png";
            case ".jpg":
            case ".jpeg":
            default:
                return "image/jpeg";
        }
    }
}
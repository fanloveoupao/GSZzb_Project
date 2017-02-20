package app.gseasypro.com.utils.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.ConvenientBanner;

import app.gseasypro.com.utils.R;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class AdvertisementBanner extends ConvenientBanner {
    private Context mContext;

    public AdvertisementBanner(Context context) {
        super(context);
        mContext = context;
        initConfiguration();

    }

    public AdvertisementBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initConfiguration();
    }

    public AdvertisementBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfiguration();
    }

    public AdvertisementBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initConfiguration();
    }


    private void initConfiguration() {
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        this.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        this.setManualPageable(true);//设置能手动影响
    }

    public void setParentCenter() {
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        this.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(PageIndicatorAlign.CENTER_HORIZONTAL);
        this.setManualPageable(true);//设置能手动影响
    }
}

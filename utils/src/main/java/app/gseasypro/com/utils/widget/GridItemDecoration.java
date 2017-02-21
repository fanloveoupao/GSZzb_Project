package app.gseasypro.com.utils.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fan-gk on 2017/2/21.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    // region Member Variables
    private final int mSpace;
    // endregion

    // region Constructors
    public GridItemDecoration(int space) {
        mSpace = space;
    }
    // endregion

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        outRect.top = mSpace;
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
    }
}
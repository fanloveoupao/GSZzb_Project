package app.gseasypro.com.utils.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by fan-gk on 2017/2/9.
 */


public class EnlargeCrossEditText extends EditText {
    private class EditKeyListenerManager{
        private final KeyListener listener;
        public  EditKeyListenerManager(){
            this.listener = getKeyListener();
        }

        public void removeKeyListener(){
            setKeyListener(null);
        }

        public void resetKeyListener(){
            setKeyListener(listener);
        }
    }


    private Drawable dRight;
    private Drawable dLeft;
    private Rect rBounds;
    private EditKeyListenerManager keyListenerManager;

    public EnlargeCrossEditText(Context context) {
        super(context);
        init();
    }

    public EnlargeCrossEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EnlargeCrossEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.keyListenerManager = new EditKeyListenerManager();
        this.setCompoundDrawables(this.getCompoundDrawables()[0],
                this.getCompoundDrawables()[1], null,
                this.getCompoundDrawables()[3]);
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                handleClearButton();

            }
        });

        onFocusedChanged(hasFocus());
    }

    private void onFocusedChanged(boolean focused){
        handleClearButton();
        if(getEllipsize() != null) {
            if (focused)
                keyListenerManager.resetKeyListener();
            else
                keyListenerManager.removeKeyListener();
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        onFocusedChanged(focused);
    }

    @Override
    public void setKeyListener(KeyListener input) {
        super.setKeyListener(input);
        if(input != null && input != keyListenerManager.listener)
            keyListenerManager = new EditKeyListenerManager();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && dRight != null) {
            rBounds = dRight.getBounds();
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            if (x >= (this.getWidth() - rBounds.width() - this.getCompoundDrawablePadding())
                    && x <= this.getWidth()
                    && y <= this.getHeight()) {
                this.setText("");
                handleClearButton();
                event.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top,
                                     Drawable right, Drawable bottom) {
        if (right != null) {
            int width = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 18, getResources()
                            .getDisplayMetrics());

            right.setBounds(0, 0, width, width);
            dRight = right;
        }
        if(left!=null){
            dLeft=left;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    protected void finalize() throws Throwable {
        dRight = null;
        rBounds = null;
        super.finalize();
    }

    public void handleClearButton() {
        if (this.getText().toString().trim().length() > 0 && this.hasFocus()) {
            this.setCompoundDrawables(null,
                    this.getCompoundDrawables()[1],  this.dRight,
                    this.getCompoundDrawables()[3]);

        } else {
            if(dLeft!=null){
                this.setCompoundDrawables(this.dLeft,
                        this.getCompoundDrawables()[1],null,
                        this.getCompoundDrawables()[3]);
            }else{
                this.setCompoundDrawables(this.getCompoundDrawables()[0],
                        this.getCompoundDrawables()[1],null,
                        this.getCompoundDrawables()[3]);
            }
        }
    }
}


package com.gseasypro.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Func1;
import com.example.ICloseable;
import com.example.lang.StringUtil;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.DialogUtil;
import app.gseasypro.com.utils.ui.KeyBoardUtils;
import app.gseasypro.com.utils.ui.dialog.SimpleDialogFragment;

import static android.R.attr.maxLines;
import static android.R.attr.minLines;
import static com.gseasypro.app.InputDialogFragment.Builder.dialogFragment;

/**
 * Created by fan-gk on 2017/3/2.
 */


public class InputDialogFragment extends SimpleDialogFragment {
    private static final String tag = "InputDialogFragment";

    public interface OnSaveListener {
        void onSave(String inputText, ICloseable self);
    }

    public static class Role {
        public CharSequence mMessage;
        public Func1<Boolean, String> mFilter;

        public Role(CharSequence msg, Func1<Boolean, String> filter) {
            mMessage = msg;
            mFilter = filter;
        }
    }

    public static class Builder extends SimpleDialogFragment.Builder<InputDialogFragment, Builder> {
        private String title;
        private String hint;
        private String content;
        private int maxEms;
        private int minEms;
        private OnSaveListener mOnSaveListener;
        private InputFilter[] mFilters;
        public static InputDialogFragment dialogFragment;
        public List<Role> mFilterRoles = new ArrayList<>();

        private int maxLines;
        private int minLines;

        public Builder setMaxLines(int maxLines) {
            this.maxLines = maxLines;
            return this;
        }

        public Builder setMinLines(int minLines) {
            this.minLines = minLines;
            return this;
        }

        public int getMaxLines() {
            return maxLines;
        }

        public int getMinLines() {
            return minLines;
        }


        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public String getContent() {
            return content;
        }

        public Builder setFilters(InputFilter[] filters) {
            mFilters = filters;
            return this;
        }

        public InputFilter[] getFilters() {
            return mFilters;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public Builder setMaxEms(int maxEms) {
            this.maxEms = maxEms;
            return this;
        }

        public Builder setMinEms(int minEms) {
            this.minEms = minEms;
            return this;
        }

        public Builder addRole(Role role) {
            mFilterRoles.add(role);
            return this;
        }

        public Builder setOnSaveListener(OnSaveListener onSaveListener) {
            mOnSaveListener = onSaveListener;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public OnSaveListener getOnSaveListener() {
            return mOnSaveListener;
        }

        public int getMaxEms() {
            return maxEms;
        }

        public String getHint() {
            return hint;
        }

        public int getMinEms() {
            return minEms;
        }


        @Override
        public InputDialogFragment build() {
            return createInstance(this);
        }
    }

    private final ICloseable closeable = new ICloseable() {
        @Override
        public void close() {
            dismiss();
        }
    };

    public static InputDialogFragment createInstance(Builder builder) {
        dialogFragment = new InputDialogFragment();
        dialogFragment.setBuilder(builder);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.InputDialogFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_input_fragment, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        final EditText editText = (EditText) view.findViewById(R.id.edit_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvSave = (TextView) view.findViewById(R.id.tv_save);

        final Builder builder = getBuilder();
        if (builder != null) {
            if (!StringUtil.isNullOrEmpty(builder.getTitle()))
                tvTitle.setText(builder.getTitle());
            if (!StringUtil.isNullOrEmpty(builder.getHint()))
                editText.setHint(builder.getHint());
            if (builder.getFilters() != null)
                editText.setFilters(builder.getFilters());
            if (builder.getMaxEms() != 0)
                editText.setMaxEms(builder.getMaxEms());
            if (builder.getMinEms() != 0)
                editText.setMinEms(builder.getMinEms());
            String content = builder.getContent();
            if (!StringUtil.isNullOrEmpty(content)) {
                editText.setText(content);
                editText.setSelection(content.length());
            }
            if (builder.getMaxLines() != 0)
                editText.setMaxLines(maxLines);
            if (builder.minLines != 0)
                editText.setMinLines(minLines);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    hideKeyBoard();
                }
            });
            tvSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String inputText = editText.getText().toString();
                    for (final Role filterRole : builder.mFilterRoles) {
                        if (filterRole != null && filterRole.mFilter != null) {
                            if(!filterRole.mFilter.run(inputText)) {
                                DialogUtil.softOneBtnDialog(getActivity(), filterRole.mMessage);
                                return;
                            }
                        }
                    }
                    final OnSaveListener listener = builder.getOnSaveListener();
                    if(listener != null)
                        listener.onSave(inputText, closeable);
                }
            });
        }
        KeyBoardUtils.showKeyBord(getActivity(), editText);

        return view;
    }

    public void show(FragmentActivity fragmentActivity){
        show(fragmentActivity.getSupportFragmentManager());
    }

    public static void dismiss(FragmentActivity fragmentActivity){
        dismiss(fragmentActivity.getSupportFragmentManager());
    }

    public void show(FragmentManager fragmentManager){
        show(fragmentManager, tag);
    }

    public static void dismiss(FragmentManager fragmentManager){
        final Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment != null && fragment instanceof InputDialogFragment){
            ((InputDialogFragment) fragment).dismiss();
        }
    }
}

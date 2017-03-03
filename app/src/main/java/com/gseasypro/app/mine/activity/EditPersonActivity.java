package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.Func1;
import com.example.ICloseable;
import com.example.lang.StringUtil;
import com.example.presenter.EditPersonPresenter;
import com.gseasypro.app.InputDialogFragment;
import com.gseasypro.app.R;
import com.gseasypro.app.picasso.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ToastUtils;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import app.gseasypro.com.utils.widget.ImageSelecter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPersonActivity extends PresenterActivity<EditPersonPresenter, EditPersonPresenter.EditPersonIView>
        implements EditPersonPresenter.EditPersonIView {
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_edit_personal_file_user_sign)
    ImageView mImgvIcon;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_person);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("个人信息编辑");
        mTitleBar.setBackClick(this);
        ImageLoader.loadIcon("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3344085265,3893608825&fm=23&gp=0.jpg", mImgvIcon, true, true);
    }

    @OnClick({R.id.ll_edit_personal_file_user_sign, R.id.ll_edit_personal_file_user_name, R.id.ll_edit_personal_file_gender
            , R.id.ll_edit_personal_file_compary, R.id.ll_edit_personal_file_position, R.id.ll_edit_personal_file_business_experience,
            R.id.ll_edit_personal_file_business_region, R.id.ll_project_style, R.id.ll_project_stage, R.id.ll_edit_personal_contractor,
            R.id.ll_edit_personal_file_operate_product, R.id.ll_edit_personal_file_native_place, R.id.ll_edit_personal_file_birthday,
            R.id.ll_edit_personal_file_hobby, R.id.ll_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_edit_personal_file_user_sign:
                ImageSelecter.selectMuit(1, onPhotoSelectedListener);
                break;
            case R.id.ll_edit_personal_file_user_name:
                new InputDialogFragment.Builder().setTitle("输入名字").setMaxLines(1).setContent("")
                        .addRole(new InputDialogFragment.Role("请输入您的真实姓名，方便您以后的业务合作", new
                                Func1<Boolean, String>() {
                                    @Override
                                    public Boolean run(String inputText) {
                                        return StringUtil.isTrueName(inputText);

                                    }
                                }))
                        .setOnSaveListener(new InputDialogFragment.OnSaveListener() {
                            @Override
                            public void onSave(final String inputText, ICloseable self) {

                            }
                        })
                        .build()
                        .show(this);
                break;
            case R.id.ll_edit_personal_file_gender:

                break;
            case R.id.ll_edit_personal_file_compary:
                new InputDialogFragment.Builder()
                        .setTitle("输入公司名称")
                        .setMaxLines(1)
                        .setContent("")
                        .addRole(new InputDialogFragment.Role("请输入公司全称", new Func1<Boolean, String>() {
                            @Override
                            public Boolean run(String inputText) {
                                return StringUtil.isTrueCompanyName(inputText);
                            }
                        }))
                        .setOnSaveListener(new InputDialogFragment.OnSaveListener() {
                            @Override
                            public void onSave(String inputText, ICloseable self) {

                            }
                        })
                        .build()
                        .show(this);
                break;
            case R.id.ll_edit_personal_file_position:
                new InputDialogFragment.Builder().setTitle("输入职位").setMaxLines(1).setContent("")
                        .addRole(new InputDialogFragment.Role("请输入你的职位", new Func1<Boolean, String>() {
                            @Override
                            public Boolean run(String inputText) {
                                return false;
                            }
                        })).setOnSaveListener(new InputDialogFragment.OnSaveListener() {
                    @Override
                    public void onSave(final String inputText, ICloseable self) {

                    }
                })
                        .build()
                        .show(this);
                break;

            case R.id.ll_edit_personal_file_business_experience:


                break;
            case R.id.ll_edit_personal_file_business_region:
                break;
            case R.id.ll_project_style:
                break;
            case R.id.ll_project_stage:
                break;
            case R.id.ll_edit_personal_contractor:
                break;
            case R.id.ll_edit_personal_file_operate_product:
                break;
            case R.id.ll_edit_personal_file_native_place:
                break;
            case R.id.ll_edit_personal_file_birthday:

                break;
            case R.id.ll_edit_personal_file_hobby:
                break;
            case R.id.ll_detail:
                break;
        }
    }

    public ImageSelecter.OnSelectedListener onPhotoSelectedListener = new ImageSelecter.OnSelectedListener() {
        @Override
        public void successed(List<ImageSelecter.PhotoInfo> photoInfos) {
            List<String> paths = new ArrayList<>();
            for (ImageSelecter.PhotoInfo photoInfo : photoInfos) {
                paths.add(photoInfo.getPhotoPath());
            }
            if (paths.size() > 0) {
                String userFace = paths.get(0);
                ImageLoader.loadIcon(userFace, mImgvIcon, true, true);
            }
        }

        @Override
        public void error(String message) {
            ToastUtils.showLong(EditPersonActivity.this, message);
        }
    };

}

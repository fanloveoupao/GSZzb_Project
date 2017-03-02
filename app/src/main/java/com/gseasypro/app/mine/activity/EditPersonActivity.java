package com.gseasypro.app.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.Func1;
import com.example.ICloseable;
import com.example.lang.StringUtil;
import com.example.presenter.EditPersonPresenter;
import com.gseasypro.app.InputDialogFragment;
import com.gseasypro.app.R;

import app.gseasypro.com.utils.PresenterActivity;
import app.gseasypro.com.utils.ui.widget.TitleBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPersonActivity extends PresenterActivity<EditPersonPresenter, EditPersonPresenter.EditPersonIView>
        implements EditPersonPresenter.EditPersonIView {
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_person);
        ButterKnife.bind(this);
        mTitleBar.setLeftText("个人信息编辑");
        mTitleBar.setBackClick(this);
    }

    @OnClick({R.id.ll_edit_personal_file_user_sign, R.id.ll_edit_personal_file_user_name, R.id.ll_edit_personal_file_gender
            , R.id.ll_edit_personal_file_compary, R.id.ll_edit_personal_file_position, R.id.ll_edit_personal_file_business_experience,
            R.id.ll_edit_personal_file_business_region, R.id.ll_project_style, R.id.ll_project_stage, R.id.ll_edit_personal_contractor,
            R.id.ll_edit_personal_file_operate_product, R.id.ll_edit_personal_file_native_place, R.id.ll_edit_personal_file_birthday,
            R.id.ll_edit_personal_file_hobby, R.id.ll_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_edit_personal_file_user_sign:

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
}

package com.bignerdranch.android.todoapp.ui.screens.LoginScreen;

import com.bignerdranch.android.todoapp.di.DataHolder;

public class LoginPresenterimpl implements LoginContract.Presenter {

    LoginContract.View view;
    private static final String T_USERNAME = "1";
    private static final String T_PASSWORD = "1";

    public LoginPresenterimpl (LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;

    }

    @Override
    public boolean loginUserCheck(DataHolder holder) {
        return holder.getDhUsername().equals(T_USERNAME);
    }

    @Override
    public boolean loginPasswordCheck(DataHolder holder) {
       return holder.getDhPassword().equals(T_PASSWORD);
    }


}

package com.bignerdranch.android.todoapp.ui.screens.LoginScreen;

import com.bignerdranch.android.todoapp.di.DataHolder;
import com.bignerdranch.android.todoapp.ui.base.BasePresenter;

public interface LoginContract {
    interface View {

        void checkLogin();
        void passwordError();
        void usernameError();
        void unlock();
    }

    interface Presenter extends BasePresenter<View> {

        void loginUserCheck(DataHolder holder);
        void loginPasswordCheck(DataHolder holder);
    }
}

package com.bignerdranch.android.todoapp.ui.screens.LoginScreen;

import com.bignerdranch.android.todoapp.di.DataHolder;
import com.bignerdranch.android.todoapp.ui.base.BasePresenter;

public interface LoginContract {
    interface View {

    }

    interface Presenter extends BasePresenter<View> {

        boolean loginMetod(DataHolder holder);
    }
}

package com.bignerdranch.android.todoapp.di.Injectors;

import com.bignerdranch.android.todoapp.ui.screens.LoginScreen.LoginContract;
import com.bignerdranch.android.todoapp.ui.screens.LoginScreen.LoginPresenterimpl;


public class LoginInjector {

    private static LoginContract.Presenter presenter;

    public static LoginContract.Presenter providePresenter(LoginContract.View view) {
        if (presenter == null)
            presenter = new LoginPresenterimpl(view);
        return presenter;
    }

}

package com.bignerdranch.android.todoapp.di.Injectors;

import android.app.Activity;
import android.content.Context;

import com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen.NewActivity;
import com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen.NewContract;
import com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen.NewPresenter;

public class NewInjector {

    private static NewContract.Presenter presenter;
    private static NewActivity newActivity;

    public static NewContract.Presenter providePresenter(NewContract.View view) {
        if(presenter == null) {
            presenter = new NewPresenter(view);
        }
        presenter.setView(view);
        return presenter;

    }

    public static NewActivity getNewActivity() {
        return newActivity;
    }

    public static NewActivity provideNewActivity(NewActivity nA) {
        return newActivity = nA;
    }
}

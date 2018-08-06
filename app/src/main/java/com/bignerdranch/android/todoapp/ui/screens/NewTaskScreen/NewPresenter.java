package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

import android.app.Activity;
import android.content.Intent;

import com.bignerdranch.android.todoapp.di.Injectors.NewInjector;
import com.bignerdranch.android.todoapp.ui.screens.ViewAdapter.Task;


public class NewPresenter implements NewContract.Presenter{

    private NewContract.View view;

    public NewPresenter(NewContract.View view) { this.view = view; }

    @Override
    public boolean newToDoTask(String name, int color) {

        return (name.length() > 10);
    }
}

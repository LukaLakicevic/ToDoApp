package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

import android.app.Activity;
import android.content.Intent;

import com.bignerdranch.android.todoapp.di.Injectors.NewInjector;
import com.bignerdranch.android.todoapp.ui.screens.ViewAdapter.Task;


public class NewPresenter implements NewContract.Presenter{

    private NewContract.View view;

    public NewPresenter(NewContract.View view) { this.view = view; }

    int minChar = 10;

    @Override
    public void checkTask(String text) {
        if (text.length() < minChar)
            view.nameError();
        else
            view.finishWithNewTask();
    }

    @Override
    public void setView(NewContract.View view) {
        this.view = view;
    }
}

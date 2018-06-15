package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

import android.app.Activity;
import android.content.Intent;

import com.bignerdranch.android.todoapp.di.Injectors.NewInjector;
import com.bignerdranch.android.todoapp.di.ViewAdapter.Task;

import static com.bignerdranch.android.todoapp.di.Injectors.NewInjector.getNewActivity;


public class NewPresenter implements NewContract.Presenter{

    private NewContract.View view;

    public NewPresenter(NewContract.View view) { this.view = view; }
    Task newTask;

    @Override
    public boolean newToDoTask(String name, int color) {
        boolean temp = name.length() > 10 ;
        if(temp) {
            newTask = new Task(name, color);
            Intent intent = new Intent();
            intent.putExtra("ParcelKey", newTask);
            NewInjector.getNewActivity().setResult(Activity.RESULT_OK, intent);
            NewInjector.getNewActivity().finish();
        }
        return temp;
    }
}

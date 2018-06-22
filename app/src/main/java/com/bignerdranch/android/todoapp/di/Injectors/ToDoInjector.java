package com.bignerdranch.android.todoapp.di.Injectors;

import com.bignerdranch.android.todoapp.ui.screens.ListScreen.ToDoActivity;
import com.bignerdranch.android.todoapp.ui.screens.ListScreen.ToDoContract;
import com.bignerdranch.android.todoapp.ui.screens.ListScreen.ToDoPresenter;

public class ToDoInjector {

    private static ToDoContract.Presenter presenter;
    private static ToDoActivity activity;

    public static ToDoContract.Presenter provideToDoPresenter(ToDoActivity toDoActivity) {
        if (presenter == null)
            presenter = new ToDoPresenter(toDoActivity);
        return presenter;
    }

    public static ToDoActivity provideToDoActivity(ToDoActivity toDoActivity) {
        return activity = toDoActivity;
    }

    public static ToDoContract.Presenter getPresenter() {
        return presenter;
    }

    public static ToDoActivity getActivity() {
        return activity;
    }
}

package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

public class NewContract {

    public interface View {

        void findViewsById();
        void provide();
        void prepareToolbar();
        void setOnClickListeners();
        void resetBtns();
    }

    public interface Presenter {

        boolean newToDoTask(String name, int color);
    }
}

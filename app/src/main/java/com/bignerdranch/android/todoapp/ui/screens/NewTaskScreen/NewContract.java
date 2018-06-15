package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

public class NewContract {

    public interface View {

        void resetBtns();
    }

    public interface Presenter {

        boolean newToDoTask(String name, int color);
    }
}

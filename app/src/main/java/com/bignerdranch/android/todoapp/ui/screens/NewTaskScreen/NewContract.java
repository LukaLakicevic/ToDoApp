package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

public class NewContract {

    public interface View {

        void findViewsById();
        void provide();
        void prepareToolbar();
        void setOnClickListeners();
        void resetBtns();
        void nameError();
        void finishWithNewTask();
    }

    public interface Presenter {
        void checkTask(String text);

        void setView(View view);
    }
}

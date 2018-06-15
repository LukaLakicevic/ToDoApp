package com.bignerdranch.android.todoapp.ui.screens.ListScreen;

public class ToDoContract {

    interface View {

    }

    interface Presenter {

        void select();
        void selected();
        void delete();
    }
}

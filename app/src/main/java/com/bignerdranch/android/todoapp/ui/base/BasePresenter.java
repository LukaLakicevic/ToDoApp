package com.bignerdranch.android.todoapp.ui.base;

public interface BasePresenter<T> {
    void takeView(T t);
    void dropView();
}

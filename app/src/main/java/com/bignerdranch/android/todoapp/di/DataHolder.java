package com.bignerdranch.android.todoapp.di;

import com.bignerdranch.android.todoapp.di.ViewAdapter.Task;

public class DataHolder {
    public static DataHolder holder;
    private static String dhUsername;
    private static String dhPassword;
    private static int which;
    private static Task task;

    public static int getWhich() {
        return which;
    }

    public static Task getTask() {
        return task;
    }

    public static String getDhUsername() {
        return dhUsername;
    }

    public static String getDhPassword() {
        return dhPassword;
    }

    public static DataHolder getHolder() {
        return holder;
    }

    public static void setHolder(String user, String pass) {
        holder.dhUsername = user;
        holder.dhPassword = pass;
    }

    public static void setTask(Task task) {
        holder.task = task;
    }

    public static void setWhichPressed(int which) {
        holder.which = which;
    }
}

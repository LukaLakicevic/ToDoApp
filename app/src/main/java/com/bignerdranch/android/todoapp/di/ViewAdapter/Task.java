package com.bignerdranch.android.todoapp.di.ViewAdapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.android.todoapp.di.DataHolder;

public class Task implements Parcelable {

    private String name;
    private int color;
    private boolean isFinished;

    public Task(String name, int color) {
        this.name = name;
        this.color = color;
        this.isFinished = false;
    }

    public Task(Parcel in) {
        this.name = in.readString();
        this.color = in.readInt();
        this.isFinished = in.readByte() != 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(color);
        dest.writeByte((byte) (isFinished ? 1 : 0));
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}

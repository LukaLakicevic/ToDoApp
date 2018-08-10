package com.bignerdranch.android.todoapp.ui.screens.ListScreen;

import android.widget.LinearLayout;

import com.bignerdranch.android.todoapp.ui.screens.ViewAdapter.Task;
import com.bignerdranch.android.todoapp.ui.screens.ViewAdapter.TaskAdapter;

import java.util.ArrayList;

public class ToDoContract {

    interface View {

        void findViewsById();

        void prepare();

        void prepareActionBar();

        void prepareRecyclerView();

        void setActionListeners();

        void enableDeletionMode(boolean isEnabled);

        void enableDeleteOnlyMode();

        void enableReturnOnlyMode();

        void ToggleSelectOption(LinearLayout todoLayout, Task todo);
    }

    public interface Presenter {

        void select(ArrayList<ToDoActivity.SelectedItem> selectedItems,
                    LinearLayout layout, Task todo);

        boolean selected(ArrayList<ToDoActivity.SelectedItem> selectedItems, Task todo);

        void delete(ArrayList<ToDoActivity.SelectedItem> selectedItems, Task todo);

        void addNewTask(ToDoActivity activity);

        void applyDeletions(TaskAdapter todoAdapter, ArrayList<Task> todoList,
                            ArrayList<ToDoActivity.SelectedItem> selectedIndexList);
    }
}

package com.bignerdranch.android.todoapp.ui.screens.ListScreen;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.ViewAdapter.Task;
import com.bignerdranch.android.todoapp.di.ViewAdapter.TaskAdapter;
import com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen.NewActivity;

import java.util.ArrayList;

import static com.bignerdranch.android.todoapp.di.DataHolder.holder;

public class ToDoPresenter implements ToDoContract.Presenter {

    private ToDoContract.View view;

    public ToDoPresenter(ToDoContract.View view) { this.view = view; }


    @Override
    public void applyDeletions(TaskAdapter todoAdapter, ArrayList<Task> todoList,
                               ArrayList<ToDoActivity.SelectedItem> selectedIndexList) {
        for (int i = 0; i < selectedIndexList.size(); i++) {
            ToDoActivity.SelectedItem item = selectedIndexList.get(i);
            int index = todoList.indexOf(item.getTask());
            todoList.remove(item.getTask());
            todoAdapter.notifyItemRemoved(index);
            item.getLayout().setBackgroundResource(R.color.bg_default);
        }
    }


    @Override
    public boolean selected(ArrayList<ToDoActivity.SelectedItem> selectedItems, Task todo) {
        for (int i = 0; i < selectedItems.size(); i++) {
            if(selectedItems.get(i).getTask() == todo) return true;
        }
        return false;
    }

    @Override
    public void delete(ArrayList<ToDoActivity.SelectedItem> selectedItems, Task todo) {
        for (int i = 0; i < selectedItems.size(); i++) {
            if (selectedItems.get(i).getTask() == todo) selectedItems.remove(i);
        }
    }

    public void select(ArrayList<ToDoActivity.SelectedItem> selectedItems,
                       LinearLayout layout, Task todo) {
        selectedItems.add(new ToDoActivity.SelectedItem(todo, layout));
    }

    @Override
    public void addNewTask(ToDoActivity activity) {

        holder.setTask(null);
        holder.setWhichPressed(1);
        Toast.makeText(activity, "Going to Task maker", Toast.LENGTH_SHORT).show();
        activity.startActivityForResult((new Intent(activity, NewActivity.class)), 1);
    }
}

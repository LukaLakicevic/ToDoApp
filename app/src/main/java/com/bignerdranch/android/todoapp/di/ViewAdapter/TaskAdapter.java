package com.bignerdranch.android.todoapp.di.ViewAdapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.DataHolder;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView colorCircle;
        public CheckBox checkBox;
        public int ViewPosition;

        public ViewHolder (View itemView)
        {

            super(itemView);
            colorCircle = itemView.findViewById(R.id.color_text_view);
            name = itemView.findViewById(R.id.item_name);
            checkBox = itemView.findViewById(R.id.button_check);
        }

        public void bind(final Task task,final OnItemClickListener listener) {

            which();
            name.setText(task.getName());
            String temp = task.getName().substring(0,1).toUpperCase();
            colorCircle.setText(temp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(task, getLayoutPosition());
                }
            });
        }

        private void which() {
            if(DataHolder.getWhich() == 3) {
                colorCircle.setBackgroundResource(R.drawable.circle3);
            }
            else if(DataHolder.getWhich() == 2) {
                colorCircle.setBackgroundResource(R.drawable.circle2);
            }
            else
                colorCircle.setBackgroundResource(R.drawable.circle);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Task item,int pos);
    }

    private List<Task> mTaskList;
    private OnItemClickListener listener;

    public TaskAdapter(List<Task> taskList) {
        mTaskList = taskList;
    }

    public TaskAdapter(List<Task> taskList, OnItemClickListener listener) {
        mTaskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View taskView = inflater.inflate(R.layout.text_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TaskAdapter.ViewHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}


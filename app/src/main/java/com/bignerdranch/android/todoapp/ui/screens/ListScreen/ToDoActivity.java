package com.bignerdranch.android.todoapp.ui.screens.ListScreen;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bignerdranch.android.todoapp.CistoNesto;
import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.DataHolder;
import com.bignerdranch.android.todoapp.di.ViewAdapter.Task;
import com.bignerdranch.android.todoapp.di.ViewAdapter.TaskAdapter;
import com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen.NewActivity;

import java.util.ArrayList;

import static com.bignerdranch.android.todoapp.di.DataHolder.holder;

public class ToDoActivity extends AppCompatActivity {


    public ArrayList<Task> taskList;
    public FloatingActionButton addButton;
    public Toolbar toolbar;
    public RecyclerView recycler;
    public TaskAdapter adapter;
    public TaskAdapter.OnItemClickListener listener;
    public Task newTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        findViewsById();

        prepareActionBar();

        prepareRecyclerView();

        setActionListeners();

    }

    public void findViewsById() {

        toolbar = findViewById(R.id.toolbar);
        recycler = findViewById(R.id.list_recycler);
        addButton = findViewById(R.id.add_button);
    }

    public void prepareActionBar() {

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title);
    }

    public void prepareRecyclerView() {

        taskList = new ArrayList<Task>();


/*
        listener = new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item, int pos) {
                position = pos;
                holder.setTask(item);
                holder.setDhPosition(item.getPosition());
                startActivity(new Intent(MainActivity.this,ShowToDo.class));
            }
        };
            Postaviti kad se Delete selektuje da se aktivira opcija za klikanje na textView */
        adapter = new TaskAdapter(taskList, listener);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        taskList.add(new Task("jsaf", R.drawable.chosen_circle));
        adapter.notifyDataSetChanged();
    }

    public void setActionListeners() {

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.setTask(null);
                holder.setWhichPressed(1);
                Toast.makeText(ToDoActivity.this, "Going to Task maker", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ToDoActivity.this,NewActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                //Delete selected items
                //Toast.makeText(MainActivity.this, R.string.delete_cliked, Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(ToDoActivity.this, R.string.nothing, Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            newTask = data.getParcelableExtra("ParcelKey");
            taskList.add(new Task("blablabla", R.drawable.chosen_circle3));
            adapter.notifyDataSetChanged();
        }
    }
}
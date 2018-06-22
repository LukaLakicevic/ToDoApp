package com.bignerdranch.android.todoapp.ui.screens.ListScreen;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.Injectors.ToDoInjector;
import com.bignerdranch.android.todoapp.di.ViewAdapter.Task;
import com.bignerdranch.android.todoapp.di.ViewAdapter.TaskAdapter;

import java.util.ArrayList;


public class ToDoActivity extends AppCompatActivity implements ToDoContract.View {


    public ArrayList<Task> taskList;
    public FloatingActionButton addButton;
    public Toolbar toolbar;
    public RecyclerView recycler;
    public TaskAdapter adapter;
    public TaskAdapter.OnItemClickListener listener;
    public Task newTask;
    private int mMenuResource;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<SelectedItem> tempDeleteList;

    private ToDoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        findViewsById();

        prepareActionBar();

        prepare();

        prepareRecyclerView();

        setActionListeners();
    }

    @Override
    public void findViewsById() {

        toolbar = findViewById(R.id.toolbar);
        recycler = findViewById(R.id.list_recycler);
        addButton = findViewById(R.id.add_button);
    }

    @Override
    public void prepare() {

        mPresenter = ToDoInjector.provideToDoPresenter(this);
        ToDoInjector.provideToDoActivity(this);
    }

    @Override
    public void prepareActionBar() {

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title);
        mMenuResource = R.menu.menu_delete;
    }

    @Override
    public void prepareRecyclerView() {

        taskList = new ArrayList<Task>();

        listener = new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LinearLayout layout, Task item) {

            }
        };

        adapter = new TaskAdapter(taskList, listener);
        recycler.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setActionListeners() {

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewTask(ToDoActivity.this);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(mMenuResource, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                if(taskList.size()>0) {
                    //  enableDeletionMode(true);
                    enableDeleteOnlyMode();
                }
                else
                    Toast.makeText(this, "No tasks available for deleting",
                            Toast.LENGTH_SHORT).show();
                return true;
            case R.id.confirm:
                mPresenter.applyDeletions(adapter, taskList, tempDeleteList);
              //  enableDeletionMode(false);
                enableReturnOnlyMode();
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
            taskList.add(newTask);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void enableDeletionMode(final boolean isEnabled) {
                                                                        /* Ne upotrebljava se...
                                                                           zbog nefunkcionalnosti.
                                                                           razlozen na 2 funkcije */
        int isVisible = (!isEnabled) ? View.INVISIBLE : View.VISIBLE;
        addButton.setVisibility(isVisible);                             // NE ZNAMO ZASTO MENE RADI
        adapter.setCheckBoxesHidden(!isEnabled);                         // OVAKO A MIRKU KONTRA
        Toast.makeText(this, "adgfhdffsdfgh", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

        // I OVDJE JE KONTRA... ISPITATI
        mMenuResource = !isEnabled ? R.menu.menu_confirm : R.menu.menu_delete;
        invalidateOptionsMenu();

        if(isEnabled) {

            tempDeleteList = new ArrayList<>();
            ToggleSelectOption((LinearLayout) mLayoutManager.findViewByPosition(0), taskList.get(0));

            listener = new TaskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(LinearLayout layout, Task item) {

                    ToggleSelectOption(layout, item);
                }
            };
        }
        else {
            tempDeleteList.clear();
        }
        adapter.setListener(listener);
    }

    @Override
    public void enableDeleteOnlyMode() {

        addButton.setVisibility(View.INVISIBLE);
        adapter.setCheckBoxesHidden(true);
        Toast.makeText(this, "all invisible", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

        mMenuResource = R.menu.menu_confirm;
        invalidateOptionsMenu();

        tempDeleteList = new ArrayList<>();
        ToggleSelectOption((LinearLayout) mLayoutManager.findViewByPosition(0), taskList.get(0));

        listener = new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LinearLayout layout, Task item) {

                ToggleSelectOption(layout, item);
            }
        };
        adapter.setListener(listener);
    }

    @Override
    public void enableReturnOnlyMode() {

        addButton.setVisibility(View.VISIBLE);
        adapter.setCheckBoxesHidden(false);
        Toast.makeText(this, "all visible", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

        mMenuResource = R.menu.menu_delete;
        invalidateOptionsMenu();

        tempDeleteList.clear();
        adapter.setListener(listener);
    }

    @Override
    public void ToggleSelectOption(LinearLayout todoLayout, Task todo) {


        SelectedItem task = new SelectedItem(todo, todoLayout);

            //if(todoLayout.getSolidColor() == getResources().getColor(R.color.yellow)){
            // nisam sazna kako da se uzme boja od ViewHoldera pa sam radio kao mirko preko niza
        if (mPresenter.selected(tempDeleteList, todo)) {
            todoLayout.setBackgroundResource(R.color.bg_default);
            mPresenter.delete(tempDeleteList, todo);
        }
        else {
            todoLayout.setBackgroundResource(R.color.yellow);
            mPresenter.select(tempDeleteList, todoLayout, todo);
        }
    }

    public static class SelectedItem {
        private Task task;
        private LinearLayout layout;

        public SelectedItem(Task task, LinearLayout layout) {
            this.task = task;
            this.layout = layout;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public void setLayout(LinearLayout layout) {
            this.layout = layout;
        }
    }
}
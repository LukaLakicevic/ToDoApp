package com.bignerdranch.android.todoapp.ui.screens.NewTaskScreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.Injectors.NewInjector;

import static com.bignerdranch.android.todoapp.di.DataHolder.holder;

public class NewActivity extends AppCompatActivity implements NewContract.View {

    TextView mBtn1;
    TextView mBtn2;
    TextView mBtn3;
    EditText newTask;
    Button finish;
    NewContract.Presenter presenter;
    Context context;
    Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        findViewsById();
        prepareToolbar();
        provide();
        setOnClickListeners();
    }
    @Override
    public void findViewsById() {

        toolbar2 = findViewById(R.id.toolbar2);

        mBtn1 = findViewById(R.id.btn1);
        mBtn1.setBackgroundResource(R.drawable.chosen_circle);

        mBtn2 = findViewById(R.id.btn2);
        mBtn2.setBackgroundResource(R.drawable.circle2);

        mBtn3 = findViewById(R.id.btn3);
        mBtn3.setBackgroundResource(R.drawable.circle3);

        newTask = findViewById(R.id.newTaskName);
        finish = findViewById(R.id.btnNewTask);
    }
    @Override
    public void provide() {
        presenter = NewInjector.providePresenter(this);
        context = NewInjector.provideNewActivity(this);

    }

    @Override
    public void prepareToolbar() {
        setSupportActionBar(toolbar2);
        toolbar2.setTitle(R.string.title2);
    }

    @Override
    public void setOnClickListeners() {

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBtns();
                mBtn1.setBackgroundResource(R.drawable.chosen_circle);
                holder.setWhichPressed(1);
            }
        });


        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBtns();
                mBtn2.setBackgroundResource(R.drawable.chosen_circle2);
                holder.setWhichPressed(2);
            }
        });


        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBtns();
                mBtn3.setBackgroundResource(R.drawable.chosen_circle3);
                holder.setWhichPressed(3);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!presenter.newToDoTask(newTask.getText().toString(), holder.getWhich())) {
                    newTask.setError(getString(R.string.error_name));
                }
            }
        });
    }
    @Override
    public void resetBtns() {

    if(holder.getWhich() == 1)
            mBtn1.setBackgroundResource(R.drawable.circle);
    if(holder.getWhich() == 2)
            mBtn2.setBackgroundResource(R.drawable.circle2);
    if (holder.getWhich() == 3)
            mBtn3.setBackgroundResource(R.drawable.circle3);
    }
}

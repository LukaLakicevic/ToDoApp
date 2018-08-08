package com.bignerdranch.android.todoapp.ui.screens.LoginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.todoapp.R;
import com.bignerdranch.android.todoapp.di.Injectors.LoginInjector;
import com.bignerdranch.android.todoapp.ui.screens.ListScreen.ToDoActivity;

import static com.bignerdranch.android.todoapp.di.DataHolder.holder;


public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    TextView mUsername;
    TextView mPassword;


    LoginContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewsById();

        presenter = LoginInjector.providePresenter(this);

        onKeyListener();
    }

    public void findViewsById() {

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
    }

    public void onKeyListener() {

        mPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == event.KEYCODE_ENTER) {

                    holder.setHolder(mUsername.getText().toString(), mPassword.getText().toString());

                    if (presenter.loginUserCheck(holder)) {
                        if (presenter.loginPasswordCheck(holder))
                            startActivity(new Intent(LoginActivity.this, ToDoActivity.class));
                        else
                            mPassword.setError(getString(R.string.error_password));
                    } else
                        mUsername.setError(getString(R.string.error_username));
                }
                return false;
            }
        });
    }


}


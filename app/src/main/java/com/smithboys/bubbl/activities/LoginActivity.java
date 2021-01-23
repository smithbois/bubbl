package com.smithboys.bubbl.activities;


import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.utils.LoginUtils;

public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout loginLayout;
    private Button loginButton;
    private TextInputLayout emailInputLayout;
    private TextInputLayout passwordInputLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view to login layout
        setContentView(R.layout.login_layout);

        emailInputLayout = findViewById(R.id.email_edit_text_layout);
        passwordInputLayout = findViewById(R.id.password_edit_text_layout);

        // transition login layout into view
        loginLayout = findViewById(R.id.login_layout);
        ((ViewGroup) findViewById(R.id.login_layout_parent)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGE_APPEARING);
        ((ViewGroup) findViewById(R.id.login_layout_parent)).getLayoutTransition().setDuration(750);

        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                loginLayout.setVisibility(View.VISIBLE);
            }
        };
        h.postDelayed(r, 1000);

        // log in button
        loginButton = findViewById(R.id.login_button);
        final Intent intent = new Intent(this, DashboardActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInputLayout.getEditText().getText().toString();
                String password = passwordInputLayout.getEditText().getText().toString();
                if (LoginUtils.validateLogin(email, password)) {
                    System.out.println("Email: " + CurrentUser.currentUser.getEmail());
                    startActivity(intent);
                } else {
                    emailInputLayout.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake));
                    passwordInputLayout.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake));
                    Toast.makeText(LoginActivity.this, "Log in failed, please check your info", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}


package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity {
    TextInputLayout username_textInputLayout, password_textInputLayout;
    Button login_button, signup_button, forgot_button;
    String mail, password, password_encriypted;
    Thread thread;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = username_textInputLayout.getEditText().getText().toString();
                password = password_textInputLayout.getEditText().getText().toString();
                //mail = "mail60";
                //password = "1234";

                try {
                    password_encriypted = Encriptions.encText(password);
                    Log.e("Enc", password_encriypted+"---"+password+"---"+mail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                api = new API(mail, password_encriypted, SignInActivity.this);
                api.signIn(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail() {
                        Log.e("Fail", "Fail");
                        username_textInputLayout.setError("Wrong username or password");
                        password_textInputLayout.setError("Wrong username or password");
                    }
                });


                Log.e("Result", api.isSignedIn+"");


            }
        });



    }



    public void init(){
        username_textInputLayout = findViewById(R.id.username_txtinput);
        password_textInputLayout = findViewById(R.id.password_txtinput);
        login_button = findViewById(R.id.go_btn);
        signup_button = findViewById(R.id.signup_newuser_btn);
        forgot_button = findViewById(R.id.forgetpassword_btn);




    }


}
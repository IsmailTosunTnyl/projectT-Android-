package com.example.projectt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout first_name_text, last_name_text, email_text, password_text, password_confirm_text, phone_text, address_text, national_id_text;
    Button sign_up_btn;
    String first_name, last_name, email, password, password_confirm, phone, address, national_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        init();

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean isAllValid = true;
                first_name = first_name_text.getEditText().getText().toString();
                last_name = last_name_text.getEditText().getText().toString();
                email = email_text.getEditText().getText().toString();
                password = password_text.getEditText().getText().toString();
                password_confirm = password_confirm_text.getEditText().getText().toString();
                phone = phone_text.getEditText().getText().toString();
                address = address_text.getEditText().getText().toString();
                national_id = national_id_text.getEditText().getText().toString();


                if (first_name.isEmpty()) {
                    first_name_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (last_name.isEmpty()) {
                    last_name_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (email.isEmpty()) {
                    email_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (password.isEmpty()) {
                    password_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (password_confirm.isEmpty()) {
                    password_confirm_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (phone.isEmpty()) {
                    phone_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (address.isEmpty()) {
                    address_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }
                if (national_id.isEmpty()) {
                    national_id_text.setError("Please fill in the blanks");
                    isAllValid = false;
                }

                if (!password_confirm.equals(password)) {
                    password_confirm_text.setError("Passwords do not match");
                    password_text.setError("Passwords do not match");
                    isAllValid = false;
                }
                try {

                    if (isAllValid) {
                        API api = new API(email, password, SignUpActivity.this);
                        api.signUp(new VolleyCallBack() {
                            @Override
                            public void onSuccess() {
                                Log.e("SignUp", "-------Success------");
                            }

                            @Override
                            public void onFail() {
                                Log.e("SignUp", "-------Fail------");
                                email_text.setError("Email already exists");
                            }
                        }, first_name, last_name, password,email,  address, phone, national_id);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }





            }



    });


}


    private void init() {
        first_name_text = findViewById(R.id.FirstName_txtinput);
        last_name_text = findViewById(R.id.LastName_txtinput);
        email_text = findViewById(R.id.Mail_txtinput);
        password_text = findViewById(R.id.Password_txtinput);
        password_confirm_text = findViewById(R.id.AgainPassword_txtinput);
        phone_text = findViewById(R.id.PhoneNumber_txtinput);
        address_text = findViewById(R.id.Adress_txtinput);
        national_id_text = findViewById(R.id.nationalId_txtinput);
        sign_up_btn = findViewById(R.id.SignUp_btn);
    }


}

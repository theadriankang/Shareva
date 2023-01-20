package com.example.shareva;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTxt_name, editTxt_email, editTxt_password;

    private MaterialButton btn_CreateAcc;

    private TextView createNewAcc;

    private ImageView btn_Google, btn_Facebook, btn_Twitter;

    private String mName, mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        btn_CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mName = editTxt_name.getText().toString();
                mEmail = editTxt_email.getText().toString();
                mPassword = editTxt_password.getText().toString();

                validateName();
                validateEmail();
                validatePassword();
                validateInput();
            }
        });

        createNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void validateInput() {

        if (!validateName() | !validateEmail() | !validatePassword())
            return;
        else
        {
            // Transfer data to the next page
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("Name", mName);
            intent.putExtra("Email", mEmail);
            intent.putExtra("Password", mPassword);
            startActivity(intent);
        }
    }

    private boolean validatePassword() {

        //Regex pattern to require alphanumeric and special characters
        Pattern regexPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcher = regexPassword.matcher(mPassword);

        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_password.setError("Your password's not strong enough");
            return false;
        }
        else
            return true;
    }

    private boolean validateEmail() {

        if (mEmail.isEmpty())
        {
            editTxt_email.setError("Required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_email.setError("Invalid Email Address");
            return false;
        }
        else
            return true;
    }

    private boolean validateName() {

        //Regex pattern to allow only alphabets
        Pattern regexName = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = regexName.matcher(mName);

        if (mName.isEmpty())
        {
            editTxt_name.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_name.setError("Invalid input");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        // EditText
        editTxt_name = findViewById(R.id.editTxt_name);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_password = findViewById(R.id.editTxt_password);

        // Material Button
        btn_CreateAcc = findViewById(R.id.btn_CreateAccount);

        // ImageView Button
        btn_Google = findViewById(R.id.btn_GoogleIcon);
        btn_Facebook = findViewById(R.id.btn_FacebookIcon);
        btn_Twitter = findViewById(R.id.btn_TwitterIcon);

        //TextView
        createNewAcc = findViewById(R.id.createNewAcc);
    }
}
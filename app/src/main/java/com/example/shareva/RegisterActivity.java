package com.example.shareva;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTxt_name, editTxt_email, editTxt_password;

    private MaterialButton btn_CreateAcc;

    private TextView createNewAcc;

    private ImageView btn_Google, btn_Facebook, btn_Twitter;

    private String mName, mEmail, mPassword;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    CallbackManager callbackManager;
    AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

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

        btn_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        btn_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(RegisterActivity.this, Arrays.asList("email"));
            }
        });
    }

    private void googleSignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 1000);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                //Get visual value on the user
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                mName = acct.getDisplayName();
                mEmail = acct.getEmail();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Name", mName);
                intent.putExtra("Email", mEmail);
                startActivity(intent);

                System.out.println(mName);
                System.out.println(mEmail);
            }catch (ApiException e){
                e.printStackTrace();
            }
        }else
        {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code

                            try {
                                mName = object.getString("name");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }
}
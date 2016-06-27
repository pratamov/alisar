package com.dual.ideaction.alisar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dual.ideaction.alisar.exception.ConnectionException;
import com.dual.ideaction.alisar.exception.GeneralException;
import com.dual.ideaction.alisar.exception.LoginException;
import com.dual.ideaction.alisar.object.Terminal;
import com.dual.ideaction.alisar.object.User;
import com.dual.ideaction.alisar.service.ServiceSyncImpl;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    ServiceSyncImpl service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(clickHandler);
        try {
            service = ServiceSyncImpl.getInstance();
        } catch (ConnectionException e) {
        }

    }
    View.OnClickListener clickHandler = new View.OnClickListener() {

        public void onClick(View v) {
            String username = ((EditText) findViewById(R.id.username)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();

            try {
                String userId = service.getUserId(username, password);
                HashMap<String, Terminal> terminals = service.getTerminals(userId);

                User user = User.getInstance();
                user.setUserId(userId);
                user.setTerminals(terminals);
                setMainActivity();
            } catch (LoginException e) {
                Toast.makeText(LoginActivity.this, LoginException.MESSAGE, Toast.LENGTH_LONG).show();
            } catch (GeneralException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    };

    private void setMainActivity(){
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
    }

    public String getUserId() {
        SharedPreferences pref = this.getSharedPreferences("Session Data", MODE_PRIVATE);
        String username = pref.getString("username", "");
        String password = pref.getString("password", "");
        return "1";
    }

    private void setUserId(String username, String password){
        SharedPreferences pref = this.getSharedPreferences("Session Data", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("username", username);
        edit.putString("password", password);
        edit.commit();

    }

}

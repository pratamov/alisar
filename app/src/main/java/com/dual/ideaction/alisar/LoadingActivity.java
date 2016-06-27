package com.dual.ideaction.alisar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dual.ideaction.alisar.exception.ConnectionException;
import com.dual.ideaction.alisar.exception.GeneralException;
import com.dual.ideaction.alisar.exception.LoginException;
import com.dual.ideaction.alisar.service.ServiceSyncImpl;
import com.lambdaworks.redis.RedisConnectionException;

public class LoadingActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 500;
    private ServiceSyncImpl service;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_loading);
        testConnection();
        setLoginActivity();
    }

    private void setLoginActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingActivity.this, LoginActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private boolean testConnection(){
        try {
            service = ServiceSyncImpl.getInstance();
            service.getUserId("admin", "admin");
            return true;
        } catch (ConnectionException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
            }
            testConnection();
            return false;
        } catch (GeneralException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        } catch (LoginException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

}

package com.smithboys.bubbl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.smithboys.bubbl.utils.PushNoti;

import com.smithboys.bubbl.activities.QRActivity;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configBtn();
        configNotiBtn();
    }

    private void configBtn() {
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRActivity.class));
            }
        });
    }

    private void configNotiBtn() {
        final Context context = this;
        Button notiBtn = (Button) findViewById(R.id.noti);
        notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushNoti.noti("You have covid", "Rip", context);
            }
        });
    }
}


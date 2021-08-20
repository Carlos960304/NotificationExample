/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.example;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.apache.cordova.*;

public class MainActivity extends CordovaActivity {

    private final Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);

        startNotification();
        //stopNotification();
    }

    public void startNotification() {
        notificationRunnable.run();
    }

    public void stopNotification() {
        handler.removeCallbacks(notificationRunnable);
    }

    private final Runnable notificationRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, MyService.class);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
            int startTime = 20000;
            handler.postDelayed(this, startTime);
        }
    };

    @Override
    public void onDestroy() {
        //NotificationManager notificationManager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
        //notificationManager.cancelAll();
        super.onDestroy();
        Log.d("My Service", "Activity destroyed...");
    }
}

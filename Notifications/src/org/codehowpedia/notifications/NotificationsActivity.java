package org.codehowpedia.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NotificationsActivity extends Activity {
    int notifId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
    }

    public void showNotification(View v) {
        Intent notifIntent = new Intent("org.codehowpedia.notifications.systemupdatenotif");
        // or it can be written as
        // Intent notifIntent = new Intent(this, NotificationsView.class);

        notifIntent.putExtra("notificationId", notifId);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, notifIntent, 0);
        NotificationManager notifMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")
        Notification notif = new Notification(R.drawable.notif, "update completed successfully!", System.currentTimeMillis());
        CharSequence from = "update service";
        CharSequence msg = "scheduled update : 120 minutes left..";
        notif.setLatestEventInfo(this, from, msg, pIntent);
        notif.vibrate = new long[] {100, 250, 100, 500};
        notifMgr.notify(notifId, notif);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

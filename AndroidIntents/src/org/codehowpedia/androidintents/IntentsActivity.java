package org.codehowpedia.androidintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class IntentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
    }

    public void openBrowser(View v) {
        Intent browserIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://codehowpedia.blogspot.in"));
        startActivity(browserIntent);
    }

    public void showDialer(View v) {
        Intent makeCallIntent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+919787027732"));
        startActivity(makeCallIntent);
    }

    public void callHome(View v) {
        Intent browserIntent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:+919600087147"));
        startActivity(browserIntent);
    }

    public void callFriend(View v) {
        Intent browserIntent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:+919790778788"));
        startActivity(browserIntent);
    }

    public void showMap(View v) {
        Intent showMapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:8.764166,78.134836"));
        startActivity(showMapIntent);
    }

    public void launchBrowser(View v) {
        // Intent launchBrowser = new Intent("org.codehowpedia.androidintents.mybrowser");
        Intent launchBrowser = new Intent(android.content.Intent.ACTION_VIEW);
        launchBrowser.setData(Uri.parse("http://www.stackoverflow.com"));
        launchBrowser.addCategory("android.myapps");
        launchBrowser.addCategory("android.otherApps");
        startActivity(launchBrowser);
        // startActivity(Intent.createChooser(launchBrowser, "Open URL using..."));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intents, menu);
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

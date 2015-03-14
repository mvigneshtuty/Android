package org.codehowpedia.usingintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class UsingIntentActivity extends Activity {
    String tag = "INTENTS";
    private int request_token = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.using_intent, menu);
        return true;
    }

    public void showSecondActivity(View v) {
        // startActivity(new Intent("org.codehowpedia.SecondActivity"));
        // startActivity(new Intent("org.codehowpedia.usingintent.SecondActivity"));
        Log.d(tag, "invoking 2ndActivity by using class name");
        // startActivity(new Intent("2ndActivity"));
        // startActivity(new Intent(this, SecondActivity.class));
        // startActivity(new Intent("externalActivity"));
        request_token = 1;
        startActivityForResult(new Intent("org.codehowpedia.usingintent.2ndactivity"), request_token);
        /** even if the intent is registered with out full package name, it is getting detected */
    }

    public void passUserDetails(View v) {
        Log.d(tag, "invoking pass data activity..");
        Intent passData = new Intent("org.codehowpedia.usingintent.displaydetails");
        passData.putExtra("user_id", "353453");
        passData.putExtra("user_name", "Vignesh Muthuthurai");
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("age", 26);
        dataBundle.putString("location", "TCS - Digital Zone,Karapakkam");
        passData.putExtras(dataBundle);
        request_token = 2;
        Log.d(tag, "starting pass data activity!..");
        startActivityForResult(passData, request_token);
        // startActivityForResult(new Intent("org.codehowpedia.usingintent.displaydetails"), request_token);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getBaseContext(), "Welcome " + data.getData().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == 2) {
            // TODO
            if (resultCode == RESULT_FIRST_USER) {
                Bundle dataBundle = data.getExtras();
                Toast.makeText(getBaseContext(), "Welcome to " + dataBundle.getString("account_name") + "!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), data.getData().toString(), Toast.LENGTH_SHORT).show();
            }
        }
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

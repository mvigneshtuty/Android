package org.codehowpedia.usingintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);
        Toast.makeText(getBaseContext(), getIntent().getStringExtra("user_id"), Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), getIntent().getStringExtra("user_name"), Toast.LENGTH_SHORT).show();
        // getting the data bundle from Intent
        Bundle dataBundle = getIntent().getExtras();
        Toast.makeText(getBaseContext(), dataBundle.getInt("age"), Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), dataBundle.getString("location"), Toast.LENGTH_SHORT).show();
    }

    public void goToUsingIntentActivity(View v) {
        Intent userData = new Intent();
        Bundle dataBundle = new Bundle();
        dataBundle.putString("account_name", "Star Alliance");
        userData.putExtras(dataBundle);
        userData.setData(Uri.parse("Now watching the using intent activity!"));
        setResult(RESULT_FIRST_USER, userData);
        finish();
    }
}

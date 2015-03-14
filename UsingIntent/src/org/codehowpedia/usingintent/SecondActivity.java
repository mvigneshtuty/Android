package org.codehowpedia.usingintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
    }

    public void displayUserName(View v) {
        Intent inputData = new Intent();
        EditText user_name = (EditText) findViewById(R.id.user_name);
        inputData.setData(Uri.parse(user_name.getText().toString()));
        setResult(RESULT_OK, inputData);
        finish();
    }
}

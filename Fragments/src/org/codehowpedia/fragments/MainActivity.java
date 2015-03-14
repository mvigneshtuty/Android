package org.codehowpedia.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    String tag = "Fragments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*FragmentManager fManager = getFragmentManager();
        FragmentTransaction fTrans = fManager.beginTransaction();
        Log.d(tag, "Adding fragments dynamically!");
        // getting the display details
        WindowManager wManager = getWindowManager();
        Display disp = wManager.getDefaultDisplay();
        if (disp.getWidth() > disp.getHeight()) {
            // landscape mode
            FragmentsMain fragMain = new FragmentsMain();
            fTrans.replace(android.R.id.content, fragMain);
        }
        else {
            // potrait mode
            FragmentsMainSecond fragMainSecond = new FragmentsMainSecond();
            fTrans.replace(android.R.id.content, fragMainSecond);
        }
        fTrans.commit();*/
        setContentView(R.layout.activity_main);

    }

    public void getDataFromFragOne(View v) {
        Log.d(tag, "Displaying fragment #1 message from main activity!");
        TextView txtView = (TextView) findViewById(R.id.text_frag1);
        Toast.makeText(this, txtView.getText(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}

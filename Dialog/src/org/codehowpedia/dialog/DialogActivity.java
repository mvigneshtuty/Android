package org.codehowpedia.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends Activity {
    CharSequence[] technologies = {"Java", "Android", "Oracle", ".Net"};
    boolean[] itemsChecked = new boolean[technologies.length];
    String tag = "DialogActivity";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @SuppressWarnings("deprecation")
    public void getDialog(View v) {
        showDialog(0);
    }

    public void showAnotherActivity(View v) {
        startActivity(new Intent("externalActivity")); // calling activities in another project(UsingIntent project)
    }

    public void showGmail(View v) {
        Intent mailClient = new Intent(Intent.ACTION_VIEW);
        mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
        startActivity(mailClient);
    }
    public void showProgressDialog(View v) {
        // progressDialog = new ProgressDialog(this);
        // progressDialog.setTitle("Info");
        // progressDialog.setIcon(R.drawable.icon1_android);
        final ProgressDialog dialog = ProgressDialog.show(this, "Please wait..", "gathering statistics..", true);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Log.d(tag, "sleep start..");
                    Thread.sleep(5000);
                    dialog.dismiss();
                    Log.d(tag, "sleep over!");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(tag, "about to dismiss the dialog..");

                Log.d(tag, "dialog dismiss!");
            }
        }).start();
    }

    public void showDownloadProgress(View v) {
        showDialog(1);
        progressDialog.setProgress(0);

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    progressDialog.incrementProgressBy((100 / 5));
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("Select any technology");
                builder.setPositiveButton(R.string.ok_diag_btn, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), R.string.ok_clicked, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton(R.string.cancel_diag_btn, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), R.string.cancel_clicked, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setMultiChoiceItems(technologies, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String toastMsg = null;
                        if (itemsChecked[which]) {
                            toastMsg = technologies[which] + " clicked!";
                        }
                        else {
                            toastMsg = technologies[which] + " not clicked!";
                        }
                        Toast.makeText(getBaseContext(), toastMsg, Toast.LENGTH_SHORT).show();
                    }
                });
                return builder.create();
            case 1:
                progressDialog = new ProgressDialog(this);
                // Log.d(tag, "thread stop addded..");
                progressDialog.setTitle("Downloading artifacts..");
                progressDialog.setIcon(R.drawable.icon1_android);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // progressDialog.setProgress(0);
                        // Thread.currentThread().stop();
                        Toast.makeText(getBaseContext(), R.string.ok_clicked, Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // progressDialog.setProgress(0);
                        // Thread.currentThread().stop();
                        Toast.makeText(getBaseContext(), R.string.cancel_clicked, Toast.LENGTH_SHORT).show();
                    }
                });
                return progressDialog;
            default:
                // TODO
                break;
        }
        return null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dialog, menu);
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

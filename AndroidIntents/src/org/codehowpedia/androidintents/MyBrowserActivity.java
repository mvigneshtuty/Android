package org.codehowpedia.androidintents;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowserActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mybrowser);
        Uri uri = getIntent().getData();
        WebView wView = (WebView) findViewById(R.id.id_webview);
        wView.setWebViewClient(new viewClient());
        wView.loadUrl(uri.toString());
    }

    private class viewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return false;
        }
    }
}

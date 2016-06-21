package test.com.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ScheduleActivity extends AppCompatActivity {

    WebView mWebView;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_webview);

        pb = (ProgressBar) findViewById(R.id.progress_bar);
        pb.setVisibility(View.VISIBLE);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setInitialScale(100);

        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100)
                    pb.setVisibility(View.GONE);
            }
        });

        // pdf: https://docs.google.com/spreadsheets/d/1KZ_HCw9hqwHVMX9oYz56X9JdGMaGv3WK8kfoUJskiM8/pub?gid=1397911395&single=true&output=pdf
        // CSV: https://docs.google.com/spreadsheets/d/1KZ_HCw9hqwHVMX9oYz56X9JdGMaGv3WK8kfoUJskiM8/pub?gid=1397911395&single=true&output=csv
        // Currently not rendering a PDF, but eventually either read pdf, or generate custom view from CVS.
        String url = "https://docs.google.com/spreadsheets/d/1KZ_HCw9hqwHVMX9oYz56X9JdGMaGv3WK8kfoUJskiM8/pub";
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }
}

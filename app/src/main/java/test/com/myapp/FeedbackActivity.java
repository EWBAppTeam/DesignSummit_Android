package test.com.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class FeedbackActivity extends AppCompatActivity {


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

        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100)
                    pb.setVisibility(View.GONE);
            }
        });

        String url = "https://docs.google.com/forms/d/1s7EmxD16wymf-F3cDkRTWZBdAL2rvD7eaVrl2Oj0XFs/viewform";
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

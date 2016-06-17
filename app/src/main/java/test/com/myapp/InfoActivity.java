package test.com.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.webkit.WebChromeClient;
import android.view.View;

public class InfoActivity extends AppCompatActivity {
    WebView mWebView;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_webview);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setInitialScale(100);

        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100)
                    mProgressBar.setVisibility(View.GONE);
            }
        });

        String url = "https://drive.google.com/open?id=0B1m0ykpmY4rjaTVwYWhtaTZzR0k";
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        //mWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }
}
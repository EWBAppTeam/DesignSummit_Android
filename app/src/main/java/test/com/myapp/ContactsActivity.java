package test.com.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ContactsActivity extends AppCompatActivity {

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

        String url = "https://docs.google.com/document/d/1efPo7cA4XV3AqeDRu6_Wp3vdskOQ8SYKmYEQ4S5mCW0/pub";
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

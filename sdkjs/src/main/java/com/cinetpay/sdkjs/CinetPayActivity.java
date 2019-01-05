package com.cinetpay.sdkjs;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CinetPayActivity extends AppCompatActivity {

    public static final String KEY_API_KEY = "api_key";
    public static final String KEY_SITE_ID = "site_id";
    public static final String KEY_NOTIFY_URL = "notify_url";
    public static final String KEY_TRANS_ID = "trans_id";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_DESIGNATION = "designation";
    public static final String KEY_CUSTOM = "custom";

    protected WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cinetpay);

        mWebView = findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        CookieManager cookieManager = CookieManager.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(mWebView,true);
        } else {
            cookieManager.setAcceptCookie(true);
        }

        mWebView.loadUrl("file:///android_asset/cinetpay.html");
    }

}
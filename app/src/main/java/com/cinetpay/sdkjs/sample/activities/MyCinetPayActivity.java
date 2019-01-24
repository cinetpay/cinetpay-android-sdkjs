package com.cinetpay.sdkjs.sample.activities;

import android.content.Intent;
import android.os.Bundle;

import com.cinetpay.sdkjs.CinetPayActivity;
import com.cinetpay.sdkjs.sample.MyCinetPayWebAppInterface;

public class MyCinetPayActivity extends CinetPayActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String api_key = intent.getStringExtra(KEY_API_KEY);
        int site_id = intent.getIntExtra(KEY_SITE_ID, 0);
        String notify_url = intent.getStringExtra(KEY_NOTIFY_URL);
        String trans_id = intent.getStringExtra(KEY_TRANS_ID);
        int amount = intent.getIntExtra(KEY_AMOUNT, 0);
        String currency = intent.getStringExtra(KEY_CURRENCY);
        String designation = intent.getStringExtra(KEY_DESIGNATION);
        String custom = intent.getStringExtra(KEY_CUSTOM);

        mWebView.addJavascriptInterface(new MyCinetPayWebAppInterface(this, api_key, site_id, notify_url,
                trans_id, amount, currency, designation, custom, true), "Android");
    }

}

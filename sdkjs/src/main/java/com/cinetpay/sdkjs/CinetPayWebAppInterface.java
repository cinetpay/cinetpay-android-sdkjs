package com.cinetpay.sdkjs;

import android.webkit.JavascriptInterface;

public abstract class CinetPayWebAppInterface {

    private String mApiKey;
    private int mSiteId;
    private String mNotifyUrl;
    private String mTransId;
    private int mAmount;
    private String mCurrency;
    private String mDesignation;
    private String mCustom;


    public CinetPayWebAppInterface(String api_key, int site_id, String notify_url,
                                   String trans_id, int amount, String currency, String designation,
                                   String custom) {
        mApiKey = api_key;
        mSiteId = site_id;
        mNotifyUrl = notify_url;
        mTransId = trans_id;
        mAmount = amount;
        mCurrency = currency;
        mDesignation = designation;
        mCustom = custom;
    }

    @JavascriptInterface
    public final String getApiKey() {
        return mApiKey;
    }

    @JavascriptInterface
    public final int getSiteId() {
        return  mSiteId;
    }

    @JavascriptInterface
    public final String getNotifyUrl() {
        return mNotifyUrl;
    }

    @JavascriptInterface
    public final String getTransId() {
        return mTransId;
    }

    @JavascriptInterface
    public final int getAmount() {
        return mAmount;
    }

    @JavascriptInterface
    public final String getCurrency() {
        return mCurrency;
    }

    @JavascriptInterface
    public final String getDesignation() {
        return mDesignation;
    }

    @JavascriptInterface
    public final String getCustom() {
        return mCustom;
    }

    public abstract void onPaymentCompleted(String payment_info);

    public abstract void onError(String code, String message);

}

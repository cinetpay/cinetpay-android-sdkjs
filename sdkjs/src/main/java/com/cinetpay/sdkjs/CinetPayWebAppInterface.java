package com.cinetpay.sdkjs;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.webkit.JavascriptInterface;

import static android.content.Context.TELEPHONY_SERVICE;

public abstract class CinetPayWebAppInterface {

    private Context mContext;
    private String mApiKey;
    private int mSiteId;
    private String mNotifyUrl;
    private String mTransId;
    private int mAmount;
    private String mCurrency;
    private String mDesignation;
    private String mCustom;
    private boolean mShouldCheckPayment;


    public CinetPayWebAppInterface(Context c, String api_key, int site_id, String notify_url,
                                   String trans_id, int amount, String currency, String designation,
                                   String custom, boolean should_check_payment) {
        mContext = c;
        mApiKey = api_key;
        mSiteId = site_id;
        mNotifyUrl = notify_url;
        mTransId = trans_id;
        mAmount = amount;
        mCurrency = currency;
        mDesignation = designation;
        mCustom = custom;
        mShouldCheckPayment = should_check_payment;
    }

    public final Context getContext() {
        return mContext;
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

    @JavascriptInterface
    public final boolean getShouldCheckPayment() {
        return mShouldCheckPayment;
    }

    @JavascriptInterface
    public final boolean isCinetPayContext() {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE);
        return tm != null && (
                tm.getNetworkCountryIso().equalsIgnoreCase("ci")
                        || tm.getSimCountryIso().equalsIgnoreCase("ci")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("cm")
                        || tm.getSimCountryIso().equalsIgnoreCase("cm")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("ml")
                        || tm.getSimCountryIso().equalsIgnoreCase("ml")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("sn")
                        || tm.getSimCountryIso().equalsIgnoreCase("sn")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("tg")
                        || tm.getSimCountryIso().equalsIgnoreCase("tg")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("bf")
                        || tm.getSimCountryIso().equalsIgnoreCase("bf")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("bj")
                        || tm.getSimCountryIso().equalsIgnoreCase("bj")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("gm")
                        || tm.getSimCountryIso().equalsIgnoreCase("gm")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("gh")
                        || tm.getSimCountryIso().equalsIgnoreCase("gh")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("gn")
                        || tm.getSimCountryIso().equalsIgnoreCase("gn")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("gw")
                        || tm.getSimCountryIso().equalsIgnoreCase("gw")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("cv")
                        || tm.getSimCountryIso().equalsIgnoreCase("cv")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("lr")
                        || tm.getSimCountryIso().equalsIgnoreCase("lr")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("mr")
                        || tm.getSimCountryIso().equalsIgnoreCase("mr")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("ne")
                        || tm.getSimCountryIso().equalsIgnoreCase("ne")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("ng")
                        || tm.getSimCountryIso().equalsIgnoreCase("ng")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("sl")
                        || tm.getSimCountryIso().equalsIgnoreCase("sl")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("ao")
                        || tm.getSimCountryIso().equalsIgnoreCase("ao")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("ga")
                        || tm.getSimCountryIso().equalsIgnoreCase("ga")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("gq")
                        || tm.getSimCountryIso().equalsIgnoreCase("gq")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("cf")
                        || tm.getSimCountryIso().equalsIgnoreCase("cf")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("cd")
                        || tm.getSimCountryIso().equalsIgnoreCase("cd")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("cg")
                        || tm.getSimCountryIso().equalsIgnoreCase("cg")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("st")
                        || tm.getSimCountryIso().equalsIgnoreCase("st")
                        || tm.getNetworkCountryIso().equalsIgnoreCase("td")
                        || tm.getSimCountryIso().equalsIgnoreCase("td")
        );
    }

    public abstract void onPaymentCompleted(String payment_info);

    public abstract void onError(String code, String message);

    public abstract void terminatePending(String apikey, int cpm_site_id, String cpm_trans_id);

    public abstract void terminateSuccess(String payment_info);

    public abstract void terminateFailed(String apikey, int cpm_site_id, String cpm_trans_id);

    public abstract void checkPayment(String apikey, int cpm_site_id, String cpm_trans_id);

}

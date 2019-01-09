package com.cinetpay.sdkjs.sample;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.cinetpay.sdkjs.CinetPayWebAppInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class MyCinetPayWebAppInterface extends CinetPayWebAppInterface {

    public MyCinetPayWebAppInterface(Context c, String api_key, int site_id, String notify_url,
                                     String trans_id, int amount, String currency, String designation,
                                     String custom) {
        super(c, api_key, site_id, notify_url, trans_id, amount, currency, designation, custom);
    }

    @Override
    @JavascriptInterface
    public void onPaymentCompleted(String payment_info) {

        Log.d("onPaymentCompleted", "payment_info: " + payment_info);

        try {
            JSONObject paymentInfo = new JSONObject(payment_info);

            String cpm_result = paymentInfo.getString("cpm_result");
            String cpm_trans_status = paymentInfo.getString("cpm_trans_status");
            String cpm_error_message = paymentInfo.getString("cpm_error_message");
            String cpm_custom = paymentInfo.getString("cpm_custom");

            Log.d("onPaymentCompleted", "cpm_result: " + cpm_result);
            Log.d("onPaymentCompleted", "cpm_trans_status: " + cpm_trans_status);
            Log.d("onPaymentCompleted", "cpm_error_message: " + cpm_error_message);
            Log.d("onPaymentCompleted", "cpm_custom: " + cpm_custom);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    @JavascriptInterface
    public void onError(String code, String message) {
        Log.d("onError", code + " " + message);
    }

}

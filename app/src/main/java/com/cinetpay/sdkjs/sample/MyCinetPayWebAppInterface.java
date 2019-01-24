package com.cinetpay.sdkjs.sample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.cinetpay.sdkjs.CinetPayWebAppInterface;
import com.cinetpay.sdkjs.sample.activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MyCinetPayWebAppInterface extends CinetPayWebAppInterface {

    public MyCinetPayWebAppInterface(Context c, String api_key, int site_id, String notify_url,
                                     String trans_id, int amount, String currency, String designation,
                                     String custom, boolean should_check_payment) {
        super(c, api_key, site_id, notify_url, trans_id, amount, currency, designation, custom,
                should_check_payment);
    }

    @Override
    @JavascriptInterface
    public void onPaymentCompleted(String payment_info) {

        Log.d("MyCinetPayWebApp", "payment_completed: " + payment_info);

        try {
            JSONObject paymentInfo = new JSONObject(payment_info);

            String cpm_result = paymentInfo.getString("cpm_result");
            String cpm_trans_status = paymentInfo.getString("cpm_trans_status");
            String cpm_error_message = paymentInfo.getString("cpm_error_message");
            String cpm_custom = paymentInfo.getString("cpm_custom");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    @JavascriptInterface
    public void onError(String code, String message) {
        Log.d("MyCinetPayWebApp", "error: " + code + " " + message);
    }

    @Override
    @JavascriptInterface
    public void terminatePending(String apikey, int cpm_site_id, String cpm_trans_id) {

        Log.d("MyCinetPayWebApp", "terminate_pending");

        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    @JavascriptInterface
    public void terminateSuccess(String payment_info) {

        Log.d("MyCinetPayWebApp", "terminate_success: " + payment_info);

        try {
            JSONObject paymentInfo = new JSONObject(payment_info);

            String cpm_result = paymentInfo.getString("cpm_result");
            String cpm_trans_status = paymentInfo.getString("cpm_trans_status");
            String cpm_error_message = paymentInfo.getString("cpm_error_message");
            String cpm_custom = paymentInfo.getString("cpm_custom");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    @JavascriptInterface
    public void terminateFailed(String apikey, int cpm_site_id, String cpm_trans_id) {

        Log.d("MyCinetPayWebApp", "terminate_failed");

        Intent intent = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    @JavascriptInterface
    public void checkPayment(String apikey, int cpm_site_id, String cpm_trans_id) {

        Log.d("MyCinetPayWebApp", "checkPayment");

        /*
        Pour vérifier le statut du paiement, vous devez envoyer par POST les paramètres suivants:
        apikey, cpm_site_id et cpm_trans_id à cette URL: https://api.cinetpay.com/v1/?method=checkPayStatus.
        */
    }

}

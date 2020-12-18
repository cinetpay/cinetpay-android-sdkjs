package com.cinetpay.sdkjs.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cinetpay.sdkjs.CinetPayActivity;
import com.cinetpay.sdkjs.sample.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText mAmountView;
    private Button mPayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAmountView = findViewById(R.id.amount);
        mPayView = findViewById(R.id.pay);

        mPayView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String amount = mAmountView.getText().toString();

                if (amount.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un montant SVP",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String api_key = "API_KEY"; // TODO A remplacer par votre clé API
                    int site_id = 445160; // TODO A remplacer par votre Site ID
                    String notify_url = "";
                    String trans_id = String.valueOf(new Date().getTime());
                    String currency = "XAF";
                    String designation = "Achat test";
                    String custom = "";

                    Intent intent = new Intent(MainActivity.this, MyCinetPayActivity.class);

                    intent.putExtra(CinetPayActivity.KEY_API_KEY, api_key);
                    intent.putExtra(CinetPayActivity.KEY_SITE_ID, site_id);
                    intent.putExtra(CinetPayActivity.KEY_NOTIFY_URL, notify_url);
                    intent.putExtra(CinetPayActivity.KEY_TRANS_ID, trans_id);
                    intent.putExtra(CinetPayActivity.KEY_AMOUNT, Integer.valueOf(amount));
                    intent.putExtra(CinetPayActivity.KEY_CURRENCY, currency);
                    intent.putExtra(CinetPayActivity.KEY_DESIGNATION, designation);
                    intent.putExtra(CinetPayActivity.KEY_CUSTOM, custom);

                    startActivity(intent);
                }
            }
        });

    }
}

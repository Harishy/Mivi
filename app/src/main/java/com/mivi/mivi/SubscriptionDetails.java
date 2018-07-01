package com.mivi.mivi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ANDROID on 7/2/2018.
 */

public class SubscriptionDetails extends AppCompatActivity {
    String jsonData = "{\\r\\n  \\\"data\\\": {\\r\\n    \\\"type\\\": \\\"accounts\\\",\\r\\n    \\\"id\\\": \\\"2593177\\\",\\r\\n    \\\"attributes\\\": {\\r\\n      \\\"payment-type\\\": \\\"prepaid\\\",\\r\\n      \\\"unbilled-charges\\\": null,\\r\\n      \\\"next-billing-date\\\": null,\\r\\n      \\\"title\\\": \\\"Ms\\\",\\r\\n      \\\"first-name\\\": \\\"Joe\\\",\\r\\n      \\\"last-name\\\": \\\"Bloggs\\\",\\r\\n      \\\"date-of-birth\\\": \\\"1985-01-01\\\",\\r\\n      \\\"contact-number\\\": \\\"0404000000\\\",\\r\\n      \\\"email-address\\\": \\\"test@mivi.com\\\",\\r\\n      \\\"email-address-verified\\\": false,\\r\\n      \\\"email-subscription-status\\\": false\\r\\n    },\\r\\n    \\\"links\\\": {\\r\\n      \\\"self\\\": \\\"http:\\/\\/localhost:3000\\/accounts\\/2593177\\\"\\r\\n    },\\r\\n    \\\"relationships\\\": {\\r\\n      \\\"services\\\": {\\r\\n        \\\"links\\\": {\\r\\n          \\\"related\\\": \\\"http:\\/\\/localhost:3000\\/services\\/0468874507\\\"\\r\\n        }\\r\\n      }\\r\\n    }\\r\\n  },\\r\\n  \\\"included\\\": [\\r\\n    {\\r\\n      \\\"type\\\": \\\"services\\\",\\r\\n      \\\"id\\\": \\\"0468874507\\\",\\r\\n      \\\"attributes\\\": {\\r\\n        \\\"msn\\\": \\\"0468874507\\\",\\r\\n        \\\"credit\\\": 1200,\\r\\n        \\\"credit-expiry\\\": \\\"2016-11-20\\\",\\r\\n        \\\"data-usage-threshold\\\": false\\r\\n      },\\r\\n      \\\"links\\\": {\\r\\n        \\\"self\\\": \\\"http:\\/\\/localhost:3000\\/services\\/0468874507\\\"\\r\\n      },\\r\\n      \\\"relationships\\\": {\\r\\n        \\\"subscriptions\\\": {\\r\\n          \\\"links\\\": {\\r\\n            \\\"related\\\": \\\"http:\\/\\/localhost:3000\\/services\\/0468874507\\/subscriptions\\\"\\r\\n          },\\r\\n          \\\"data\\\": [\\r\\n            {\\r\\n              \\\"type\\\": \\\"subscriptions\\\",\\r\\n              \\\"id\\\": \\\"0468874507-0\\\"\\r\\n            }\\r\\n          ]\\r\\n        }\\r\\n      }\\r\\n    },\\r\\n    {\\r\\n      \\\"type\\\": \\\"subscriptions\\\",\\r\\n      \\\"id\\\": \\\"0468874507-0\\\",\\r\\n      \\\"attributes\\\": {\\r\\n        \\\"included-data-balance\\\": 52400,\\r\\n        \\\"included-credit-balance\\\": null,\\r\\n        \\\"included-rollover-credit-balance\\\": null,\\r\\n        \\\"included-rollover-data-balance\\\": null,\\r\\n        \\\"included-international-talk-balance\\\": null,\\r\\n        \\\"expiry-date\\\": \\\"2016-11-19\\\",\\r\\n        \\\"auto-renewal\\\": true,\\r\\n        \\\"primary-subscription\\\": true\\r\\n      },\\r\\n      \\\"links\\\": {\\r\\n        \\\"self\\\": \\\"http:\\/\\/localhost:3000\\/services\\/0468874507\\/subscriptions\\/0468874507-0\\\"\\r\\n      },\\r\\n      \\\"relationships\\\": {\\r\\n        \\\"service\\\": {\\r\\n          \\\"links\\\": {\\r\\n            \\\"related\\\": \\\"http:\\/\\/localhost:3000\\/services\\/0468874507\\\"\\r\\n          }\\r\\n        },\\r\\n        \\\"product\\\": {\\r\\n          \\\"data\\\": {\\r\\n            \\\"type\\\": \\\"products\\\",\\r\\n            \\\"id\\\": \\\"0\\\"\\r\\n          }\\r\\n        },\\r\\n        \\\"downgrade\\\": {\\r\\n          \\\"data\\\": null\\r\\n        }\\r\\n      }\\r\\n    },\\r\\n    {\\r\\n      \\\"type\\\": \\\"products\\\",\\r\\n      \\\"id\\\": \\\"4000\\\",\\r\\n      \\\"attributes\\\": {\\r\\n        \\\"name\\\": \\\"UNLIMITED 7GB\\\",\\r\\n        \\\"included-data\\\": null,\\r\\n        \\\"included-credit\\\": null,\\r\\n        \\\"included-international-talk\\\": null,\\r\\n        \\\"unlimited-text\\\": true,\\r\\n        \\\"unlimited-talk\\\": true,\\r\\n        \\\"unlimited-international-text\\\": false,\\r\\n        \\\"unlimited-international-talk\\\": false,\\r\\n        \\\"price\\\": 3990\\r\\n      }\\r\\n    }\\r\\n  ]\\r\\n}\\r\\n";
    TextView tvSubscriptionName;
    TextView tvSubscriptionNameText;
    TextView tvCreditType;
    TextView tvCreditTypeDetails;
    TextView tv_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        tvSubscriptionName = (TextView) findViewById(R.id.tv_subscription);
        tvSubscriptionNameText = (TextView) findViewById(R.id.tv_subscriptionText);
        tvCreditType = (TextView) findViewById(R.id.tv_credit);
        tvCreditTypeDetails = (TextView) findViewById(R.id.tv_credittext);
        tv_Details = (TextView) findViewById(R.id.tv_Details);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonData);

            JSONArray jsonArray = jsonObject.getJSONArray("included");
            tvSubscriptionNameText.setText(jsonArray.getJSONObject(0).getString("type"));
            tvCreditTypeDetails.setText(jsonArray.getJSONObject(0).getJSONObject("attributes").getString("credit"));
            tv_Details.setText(jsonArray.getJSONObject(1).getString("type")+" : Balance :"+
                    jsonArray.getJSONObject(1).getJSONObject("attributes").getString("included-data-balance"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

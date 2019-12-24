package ps.gov.mtit.govqr;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ps.gov.mtit.govqr.utils.PrefsUtils;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    CountDownTimer cdt;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pb = findViewById(R.id.progressBar);

        cdt = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
//                if (!PrefsUtils.getToken(SplashActivity.this).isEmpty()) {
//                    ssoLogin();
//                }else{
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                //}
            }
        }.start();
    }

    private void ssoLogin() {
        pb.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, AppZone.TOKEN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "response : " + response);
                try {
                    JSONObject obj = new JSONObject(response.substring(response.indexOf("{")));
                    if (obj.getString("status").equalsIgnoreCase("success")) {

                        PrefsUtils.saveToken(SplashActivity.this, obj.getString("access_token"));
                        PrefsUtils.saveRefreshToken(SplashActivity.this, obj.getString("refresh_token"));
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);


                    } else {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error : " + error.toString());
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", AppZone.SSO_CLIENTID);
                params.put("client_secret", AppZone.SSO_SECRET);
                params.put("refresh_token", PrefsUtils.getRefreshToken(SplashActivity.this));
                Log.d(TAG, "params : " + params.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Authorization", reqHeader);
                // headers.put("Content-Type","application/x-www-form-urlencoded");
                Log.d(TAG, "headers : " + headers.toString());
                return headers;
            }
        };
        // add the request object to the queue to be executed
        MyRequestQueue.getInstance(this).getRequestQueue().add(request);

    }
}

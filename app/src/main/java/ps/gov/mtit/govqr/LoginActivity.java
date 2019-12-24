package ps.gov.mtit.govqr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ps.gov.mtit.govqr.utils.PrefsUtils;


public class LoginActivity extends AppCompatActivity {

    public static String TAG = "SSOLOGIN";

    EditText txtUser, txtPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.userEditText);
        txtPw = findViewById(R.id.pwEditText);


    }

    private void ssoLogin(final String user, final String pw) {
        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "جاري تسجيل الدخول");


        StringRequest request = new StringRequest(Request.Method.POST, AppZone.TOKEN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Log.d(TAG, "response : " + response);
                try {
                    JSONObject obj = new JSONObject(response.substring(response.indexOf("{")));
                    if (obj.getString("status").equalsIgnoreCase("success")) {

                        PrefsUtils.saveToken(LoginActivity.this, obj.getString("access_token"));
                        PrefsUtils.saveRefreshToken(LoginActivity.this, obj.getString("refresh_token"));
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                        //checkPermissionRequest(obj.getString("access_token"), obj.getString("refresh_token"));
                    } else {
                        Toast.makeText(LoginActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.d(TAG, "error : " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", AppZone.SSO_CLIENTID);
                params.put("client_secret", AppZone.SSO_SECRET);

                params.put("username", user);
                params.put("password", pw);
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

    private void checkPermissionRequest(final String token, final String refresh_token) {

        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "");

        String url = AppZone.BASE_URL + "/GET_SUPERMARKET_USER_PR";
        Log.d(TAG, "Url : " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pd.dismiss();
                Log.d(TAG, "Response : " + response.toString());
                try {
                    if (response.getString("status").equalsIgnoreCase("success")) {
                        PrefsUtils.saveToken(LoginActivity.this, token);
                        PrefsUtils.saveRefreshToken(LoginActivity.this, refresh_token);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-sso-authorization", token);
                Log.d(TAG, "headers : " + headers.toString());
                return headers;
            }
        };

        MyRequestQueue.getInstance(this).getRequestQueue().add(request);

    }

    public void login(View view) {
        if (!txtUser.getText().toString().isEmpty() && !txtPw.getText().toString().isEmpty()) {
            //ssoLogin(txtUser.getText().toString(), AppZone.passMd5Encryption(txtPw.getText().toString()));
            ssoLogin(txtUser.getText().toString(), AppZone.passMd5Encryption(txtPw.getText().toString()));
        } else {
            Toast.makeText(this, "أدخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show();
        }
    }

    public void forgetPassword(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://elogin.gov.ps/new/forgotPasswordNew")));
    }
}

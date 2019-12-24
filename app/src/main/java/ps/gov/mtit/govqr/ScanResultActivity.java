package ps.gov.mtit.govqr;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ps.gov.mtit.govqr.models.BondClass;
import ps.gov.mtit.govqr.utils.PrefsUtils;

public class ScanResultActivity extends AppCompatActivity {

    public static final String TAG = "ScanResultActivity";

    BondClass mBond;
    TextView tvDate, tvBondNo, tvId, tvName,tvMinis, tvAmount, tvAmountText;
    Button btnSend;
    TableLayout lContent;

    TextView tvMsg;
    LinearLayout lEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        String qrText = getIntent().getStringExtra("QRCODE");

        lContent = findViewById(R.id.contentLayout);
        tvDate = findViewById(R.id.dateTextView);
        tvBondNo = findViewById(R.id.bondNoTextView);
        tvId = findViewById(R.id.idTextView);
        tvName = findViewById(R.id.nameTextView);
        tvMinis = findViewById(R.id.minisTextView);

        tvAmount = findViewById(R.id.amountTextView);
        tvAmountText = findViewById(R.id.amountTextTextView);

        btnSend = findViewById(R.id.sendButton);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendRequest(mRecipt.getID());
                showAmountDialog();
            }
        });

        lEmpty = findViewById(R.id.emptyLinearLayout);
        tvMsg = findViewById(R.id.msgTextView);

        Log.d(TAG, "QR : " + qrText);

        Uri uri = Uri.parse(qrText);
        Log.d(TAG, "QR code : " + uri.getLastPathSegment());

        String code = uri.getLastPathSegment();

        loadData(code);
        //loadData("201934");
    }

    private void showAmountDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_amount);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);


        final EditText txtAmount = dialog.findViewById(R.id.amountEditText);

        Button btnAdd = dialog.findViewById(R.id.addButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtAmount.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    sendRequest(txtAmount.getText().toString());

                } else {
                    Toast.makeText(ScanResultActivity.this, "أدخل القيمة", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dialog.show();
    }



    //    private void loadData(String code) {
//
//        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "");
//
//        String url = AppZone.BASE_URL + "/GET_PURCHASE_BOND_BYBOND/" + code;
//        Log.d(TAG, "Url : " + url);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                pd.dismiss();
//                Log.d(TAG, "Response : " + response.toString());
//                Gson gson = new Gson();
//                try {
//                    if (response.getString("status").equalsIgnoreCase("success")) {
//                        lContent.setVisibility(View.VISIBLE);
//                        mRecipt = gson.fromJson(response.getString("data"), BondClass.class);
//                        fillData();
//                    } else {
//                        lEmpty.setVisibility(View.VISIBLE);
//                        tvMsg.setText(response.getString("message"));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
    private void loadData(final String code) {

        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "");

       // String url = AppZone.BASE_URL + "/GET_PURCHASE_BOND_BYBOND/" + code;
        String url = AppZone.BASE_URL + "/user";
        Log.d(TAG, "Url : " + url);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseStr) {
                pd.dismiss();

                Log.d(TAG, "Response : " + responseStr);
                Gson gson = new Gson();
                try {
                    JSONObject response = new JSONObject(responseStr);
                    if (response.getJSONObject("status").getInt("code")==1) {
                        lContent.setVisibility(View.VISIBLE);
                        mBond = gson.fromJson(response.getString("user"), BondClass.class);
                        fillData();
                    } else {
                        lEmpty.setVisibility(View.VISIBLE);
                        Toast.makeText(ScanResultActivity.this, response.getJSONObject("status").getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                lEmpty.setVisibility(View.VISIBLE);
                tvMsg.setText("لا يوجد نتائج");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("id", code);
                Log.d(TAG, "params : " + params.toString());
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-sso-authorization", PrefsUtils.getToken(ScanResultActivity.this));
                Log.d(TAG, "headers : " + headers.toString());
                return headers;
            }
        };

        MyRequestQueue.getInstance(this).getRequestQueue().add(request);
    }

//    private void loadData(final String code) {
//
//        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "");
//
//        String url = AppZone.BASE_URL + "/GET_PURCHASE_BOND_BYBOND";
//        Log.d(TAG, "Url : " + url);
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String resp) {
//                pd.dismiss();
//                Log.d(TAG, "Response : " + resp.toString());
//                Gson gson = new Gson();
//                try {
//                    JSONObject response = new JSONObject(resp);
//                    if (response.getString("status").equalsIgnoreCase("success")) {
//
//                        if (response.getString("status").equalsIgnoreCase("success")) {
//                            lContent.setVisibility(View.VISIBLE);
////                            mRecipt = gson.fromJson(response.getString("data"), BondClass.class);
////                            fillData();
//                        } else {
//                            lEmpty.setVisibility(View.VISIBLE);
//                            tvMsg.setText(response.getString("message"));
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                pd.dismiss();
//                lEmpty.setVisibility(View.VISIBLE);
//                tvMsg.setText("لا يوجد نتائج");
//
//            }
//        }) {
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("BOND_NO", code);
//                Log.d(TAG, "params : " + params.toString());
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("x-sso-authorization", PrefsUtils.getToken(ScanResultActivity.this));
//                Log.d(TAG, "headers : " + headers.toString());
//                return headers;
//            }
//        };
//
//        MyRequestQueue.getInstance(this).getRequestQueue().add(request);
//    }
//

    private void sendRequest(final String amount) {

        final ProgressDialog pd = ProgressDialog.show(this, getString(R.string.app_name), "");

        //String url = AppZone.BASE_URL + "/MARK_AS_SOLD_PR/";
        String url = AppZone.BASE_URL + "/trans";
        Log.d(TAG, "Url : " + url);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String resp) {
                pd.dismiss();
                Log.d(TAG, "Response : " + resp);
                Gson gson = new Gson();
                try {
                    JSONObject response = new JSONObject(resp);

                    if (response.getJSONObject("status").getInt("code")==1) {
                            new SweetAlertDialog(ScanResultActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(getString(R.string.app_name))
                                    .setContentText(response.getJSONObject("status").getString("msg"))
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            finish();
                                        }
                                    })
                                    .show();
                        } else {
                            new SweetAlertDialog(ScanResultActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(getString(R.string.app_name))
                                    .setContentText(response.getJSONObject("status").getString("msg"))
                                    .show();
                        }


//                    if (response.getString("status").equalsIgnoreCase("success")) {
//                        JSONObject mData = response.getJSONObject("data");
//                        if (mData.getInt("o_success") == 1) {
//                            new SweetAlertDialog(ScanResultActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setTitleText(getString(R.string.app_name))
//                                    .setContentText(mData.getString("o_msg_txt"))
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                            finish();
//                                        }
//                                    })
//                                    .show();
//                        } else {
//                            new SweetAlertDialog(ScanResultActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                    .setTitleText(getString(R.string.app_name))
//                                    .setContentText(mData.getString("o_msg_txt"))
//                                    .show();
//                        }

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
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("id", mBond.getACC_NO());
                params.put("serial", "123");
                params.put("amount", amount);
                Log.d(TAG, "params : " + params.toString());
                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("x-sso-authorization", PrefsUtils.getToken(ScanResultActivity.this));
//                Log.d(TAG, "headers : " + headers.toString());
//                return headers;
//            }
        };

        MyRequestQueue.getInstance(this).getRequestQueue().add(request);
    }

    private void fillData() {
        if (mBond != null) {
//            tvDate.setText(mBond.getINSERTED_AT());
//            tvBondNo.setText(mBond.getBOND_NO());
            tvId.setText(mBond.getEMP_SSN());
            tvName.setText(mBond.getEMP_NAME());
            tvMinis.setText(mBond.getMINS_NAME());

//            tvAmount.setText(mBond.getAMOUNT());
//            tvAmountText.setText(mBond.getAMOUNT_DESC());
//
//            btnSend.setVisibility(mRecipt.getRECIVED_CD().equals("0") ? View.VISIBLE : View.GONE);
        }
    }

    public void back(View view) {
        onBackPressed();
    }
}

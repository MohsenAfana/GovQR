package ps.gov.mtit.govqr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class QRScannerActivity extends AppCompatActivity {

    private DecoratedBarcodeView mBarcodeView;
    private BeepManager mBeepManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        mBarcodeView = findViewById(R.id.barcode_view);

    }

    public void onResume() {
        super.onResume();
        doPreRequisites();
        mBarcodeView.resume();
        doScan();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBarcodeView.pause();
    }

    private void doPreRequisites() {
        mBeepManager = new BeepManager(this);
        mBeepManager.setVibrateEnabled(true);
        mBeepManager.setBeepEnabled(true);
        mBarcodeView.setStatusText("");
    }

    private void doScan() {
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                mBarcodeView.pause();
                mBeepManager.playBeepSoundAndVibrate();

                if (result != null
                        && !TextUtils.isEmpty(result.getText())
                        && !TextUtils.isEmpty(result.getBarcodeFormat().name())) {

//                    Intent intent = new Intent(mContext, ScanResultActivity.class);
//                    intent.putExtra(IntentKey.MODEL, code);
//                    startActivity(intent);

                    Intent i = new Intent(QRScannerActivity.this, ScanResultActivity.class);
                    i.putExtra("QRCODE", result.getText());
                    startActivity(i);
                    //setResult(RESULT_OK,i);
                    finish();
                    //Toast.makeText(QRScannerActivity.this, result.getText(),Toast.LENGTH_SHORT).show();
                    Log.d("QR", result.getText());


                } else {
                    mBarcodeView.resume();
                    doScan();
                    Toast.makeText(QRScannerActivity.this, "Error scanning", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}

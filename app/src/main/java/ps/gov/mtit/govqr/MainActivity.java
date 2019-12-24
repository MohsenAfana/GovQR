package ps.gov.mtit.govqr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1000;
    private static final int CAMERA_PERMISSION_REQUEST = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void scan(View v) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        } else {
            Intent intent = new Intent(MainActivity.this, QRScannerActivity.class);
            startActivityForResult(intent, BARCODE_READER_ACTIVITY_REQUEST);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Toast.makeText(this, data.getStringExtra("QRCODE"), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    Intent intent = new Intent(MainActivity.this, QRScannerActivity.class);
                    startActivityForResult(intent, BARCODE_READER_ACTIVITY_REQUEST);

                } else {
                    // permission denied
                    Toast.makeText(this, "يجب اعطاء صلاحية الكاميرا", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }
}

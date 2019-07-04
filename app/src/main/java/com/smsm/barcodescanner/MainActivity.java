package com.smsm.barcodescanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerview;
    private static final int x = 457;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerview=new ZXingScannerView(this);
        setContentView(scannerview);

        if ((int) Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, x);
                return;
            }
        }
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
       // scannerview.resumeCameraPreview(this);
        result.getText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerview.setResultHandler(this);
        scannerview.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerview.stopCamera();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case x:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                    Toast.makeText(this, "Permession Garanted", Toast.LENGTH_SHORT).show();
                }else{

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }
}

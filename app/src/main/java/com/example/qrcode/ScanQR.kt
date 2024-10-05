package com.example.qrcode

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity

import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class ScanQR : AppCompatActivity() {

    private lateinit var scannedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scan_qr)

        scannedText=findViewById(R.id.idTVSCannedData)

        registerUIListener()
        }

    private fun registerUIListener() {

            scannerLauncher.launch(ScanOptions().setDesiredBarcodeFormats(ScanOptions.QR_CODE))

    }

    private val scannerLauncher= registerForActivityResult<ScanOptions, ScanIntentResult>(ScanContract())
    {
        results->

        if(results.contents==null){
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        else{
            scannedText.text = results.contents
        }
    }
}


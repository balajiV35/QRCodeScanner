package com.example.qrcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import com.journeyapps.barcodescanner.CaptureActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val generateQRBtn = findViewById<Button>(R.id.idBtnGenerateQR)
        val scanQRBtn = findViewById<Button>(R.id.idBtnScanQR)

        generateQRBtn.setOnClickListener{
            val intent = Intent(this, GenerateQR::class.java)
            startActivity(intent)
        }

        scanQRBtn.setOnClickListener{
            val intent = Intent(this, ScanQR::class.java)
            startActivity(intent)
        }


    }
}
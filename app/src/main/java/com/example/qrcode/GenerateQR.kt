package com.example.qrcode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class GenerateQR : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generate_qr)

        val qrcodeTV = findViewById<TextView>(R.id.idTVGenerateQR)
        val qrcodeIV = findViewById<ImageView>(R.id.idTVQRCode)
        val dataEdit = findViewById<TextInputEditText>(R.id.idEditData)
        val generateQRBtn = findViewById<Button>(R.id.idBtnGenerateQR)

        generateQRBtn.setOnClickListener {
            val data = dataEdit.text.toString()
            if (data.isEmpty()) {
                Toast.makeText(this, "Please Enter some data to Generate QR Code", Toast.LENGTH_SHORT).show()
            } else {
                val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = windowManager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y

                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4

                try {
                    val qrCodeWriter = QRCodeWriter()
                    val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, dimen, dimen)

                    // Create a bitmap from the bit matrix
                    val bitmap = Bitmap.createBitmap(bitMatrix.width, bitMatrix.height, Bitmap.Config.RGB_565)
                    for (x in 0 until bitMatrix.width) {
                        for (y in 0 until bitMatrix.height) {
                            bitmap.setPixel(x, y, if (bitMatrix[x, y]) -0x1000000 else -0x1) // Black or white
                        }
                    }

                    // Show the QR code image and update the TextView
                    qrcodeIV.setImageBitmap(bitmap)
                    qrcodeTV.visibility= View.GONE // Show the entered text below the QR code
                    qrcodeIV.visibility = View.VISIBLE
                } catch (e: WriterException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error generating QR Code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

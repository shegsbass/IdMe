package com.shegs.idme

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

fun generateQRCode(text: String, size: Int): Bitmap {
    val barcodeEncoder = BarcodeEncoder()
    val bitmap = barcodeEncoder.encodeBitmap(text, BarcodeFormat.QR_CODE, size, size)
    return bitmap
}
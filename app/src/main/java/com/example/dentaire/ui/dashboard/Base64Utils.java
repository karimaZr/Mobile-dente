package com.example.dentaire.ui.dashboard;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Base64Utils {
    public static String encodeBitmapToBase64(Bitmap bitmap) {
        // Convert the Bitmap to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // Encode the byte array to Base64
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String decodeBase64ToString(String base64Input) {
        // Decode the Base64 string to a byte array
        byte[] decodedBytes = Base64.decode(base64Input, Base64.DEFAULT);

        // Convert the byte array to a string
        return new String(decodedBytes);
    }
}

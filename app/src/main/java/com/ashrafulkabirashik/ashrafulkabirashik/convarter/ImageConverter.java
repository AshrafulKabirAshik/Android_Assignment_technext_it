package com.ashrafulkabirashik.ashrafulkabirashik.convarter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    public static byte[] convertImageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToImage(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}

package com.example.georgekaraolanis.project10_inventoryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    /*tag for logging*/
    private static final String LOG_TAG = Utils.class.getSimpleName();

    /*Get a bitmap from an uri*/
    public static Bitmap getBitmapFromUri(Uri uri, ImageView view, Context context) {

        if (uri == null || uri.toString().isEmpty())
            return null;

        /* Get the dimensions of the View*/
        int targetW = view.getWidth();
        int targetH = view.getHeight();

        if (targetW == 0){
            targetW = (int) context.getResources().getDimension(R.dimen.image_dimension);
        }

        if (targetH == 0){
            targetH = (int) context.getResources().getDimension(R.dimen.image_dimension);
        }

        InputStream input = null;
        try {
            input =  context.getContentResolver().openInputStream(uri);

            /* Get the dimensions of the bitmap*/
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            /* Determine how much to scale down the image*/
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            /* Decode the image file into a Bitmap sized to fill the View*/
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;

            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bmOptions);
            input.close();
            return bitmap;

        } catch (FileNotFoundException fne) {
            Log.e(LOG_TAG, "Failed to load image.", fne);
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Log.e(LOG_TAG, "Failed to close stream", ex);
            }
        }
    }
}

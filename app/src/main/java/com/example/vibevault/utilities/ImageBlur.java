package com.example.vibevault.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;

public class ImageBlur {

    public static void loadBlurredImage(ImageView imageView, String imageUrl, Context context, float blurRadius) {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                        Bitmap blurredBitmap = blurBitmap(context, resource, blurRadius); // Aqui ajustem el borros que vulguem, ej: 10.0f
                        imageView.setImageBitmap(blurredBitmap);
                        imageView.setAlpha(0.6f); // Establecer opacidad
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }
                });
    }

    private static Bitmap blurBitmap(Context context, Bitmap bitmap, float blurRadius) {
        Bitmap outputBitmap = Bitmap.createBitmap(bitmap);

        RenderScript renderScript = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, bitmap);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);
        theIntrinsic.setRadius(blurRadius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}


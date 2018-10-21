package com.example.josericardo.gamespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

public class Explosion {

    int posX, posY;
    Bitmap bitmap;


    public Explosion(Context context, Point point){
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.boom);

        posX = point.x;
        posY = point.y;
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, posX, posY, null);
    }
}

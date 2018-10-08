package com.example.josericardo.gamespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {

    private int x;
    private int y;

    private int speed = 0;

    private Bitmap bitmap;


    public Player(Context context){

        x = 75;
        y = 50;

        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.player);
    }


    public void update(){
        y++;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }
}

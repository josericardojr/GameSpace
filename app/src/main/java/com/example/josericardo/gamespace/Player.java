package com.example.josericardo.gamespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {

    private int x;
    private int y;

    private int max_x;
    private int max_y;

    private int speed = 0;

    private Bitmap bitmap;
    private boolean boosting;

    private final int GRAVITY = -10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect collisionRect = new Rect();


    public Player(Context context, int sw, int sh){

        x = 75;
        y = 50;

        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.player);

        boosting = false;

        max_x = sw;
        max_y = sh;
    }


    public void update(){

        if (boosting){
            speed += 2;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) speed = MAX_SPEED;
        if (speed < MIN_SPEED) speed = MIN_SPEED;

        y -= speed + GRAVITY;


        if (y < 0) y = 0;

        if (y + bitmap.getHeight() > max_y) y = max_y - bitmap.getHeight();

        collisionRect.left = x;
        collisionRect.top = y;
        collisionRect.right = x + bitmap.getWidth();
        collisionRect.bottom = y + bitmap.getHeight();

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public void setBoosting(){
        boosting = true;
    }

    public void stopBoosting(){
        boosting = false;
    }

    public int getSpeed(){
        return speed;
    }

    public Rect getCollisionRect() {
        return collisionRect;
    }
}

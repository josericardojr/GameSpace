package com.example.josericardo.gamespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {

    private int x;
    private int y;

    private int max_x;
    private int max_y;
    private Rect collisionRect = new Rect();

    private int speed = 0;

    private Bitmap bitmap;

    private final int GRAVITY = -10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;


    public Enemy(Context context, int sw, int sh){


        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.enemy);


        max_x = sw;
        max_y = sh - bitmap.getHeight();

        Random generator = new Random();
        speed = generator.nextInt(10) + 10;
        x = max_x;
        y = generator.nextInt(max_y);
    }


    public void update(int playerSpeed){

        x -= playerSpeed;
        x -= speed;

        if (x < -bitmap.getWidth()){
            respawn();
        }

        collisionRect.left = x;
        collisionRect.top = y;
        collisionRect.right = x + bitmap.getWidth();
        collisionRect.bottom = y + bitmap.getHeight();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public int getSpeed(){
        return speed;
    }

    public Rect getCollisionRect(){ return collisionRect; }

    public void respawn(){
        Random generator = new Random();
        speed = generator.nextInt(10) + 10;
        x = max_x;
        y = generator.nextInt(max_y);
    }

    public Point getPosition(){
        return new Point(x, y);
    }
}

package com.example.josericardo.gamespace;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Star {

    int posX, posY;
    int speed;

    private int maxX, maxY;
    private int minX, minY;

    public Star(int screenX, int screenY){
        maxX = screenX;
        maxY = screenY;

        Random generator = new Random();
        speed = generator.nextInt(10);

        posX = generator.nextInt(maxX);
        posY = generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        posX -= playerSpeed;
        posX -= speed;

        if (posX < 0){
            posX = maxX;

            Random generator = new Random();
            posY = generator.nextInt(maxY);
            speed = generator.nextInt(10);
        }
    }

    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(6);
        canvas.drawPoint(posX, posY, p);
    }
}

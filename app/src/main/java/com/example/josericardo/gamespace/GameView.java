package com.example.josericardo.gamespace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class GameView extends SurfaceView
        implements Runnable, View.OnTouchListener {

    private final int TOTAL_ENEMIES = 3;

    // Verificar se o jogador ainda esta jogando
    boolean isPlaying;
    private int screen_width, screen_height;
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private Enemy[] enemies;

    // Game Thread
    private Thread gameThread = null;
    SurfaceHolder surfaceHolder;
    Player player;

    public GameView(Context context, int sw, int sh){
        super(context);

        screen_width = sw;
        screen_height = sh;

        surfaceHolder = getHolder();
        player = new Player(context, screen_width, screen_height);

        int n_stars = 100;
        for (int i = 0; i < n_stars; i++){
            Star s = new Star(screen_width, screen_height);
            stars.add(s);
        }

        enemies = new Enemy[TOTAL_ENEMIES];

        for (int i = 0; i < enemies.length; i++)
            enemies[i] = new Enemy(context,
                    screen_width, screen_height);

        setOnTouchListener(this);
    }

    @Override
    public void run() {

        while (isPlaying){
            // Atualizar o jogo
            update();

            // Desenhar
            draw();

            // Controlar
            control();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;

            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
        }

        return true;
    }

    private void update(){

        for (Star s : stars){
            s.update(player.getSpeed());
        }

        player.update();

        for (int i = 0; i < enemies.length; i++) {
            enemies[i].update(player.getSpeed());

            if (player.getCollisionRect().intersect(
                    enemies[i].getCollisionRect())){
                Explosion exp = new Explosion(getContext(),
                        enemies[i].getPosition());
                explosions.add(exp);
                enemies[i].respawn();
            }
        }





    }

    private void draw(){

        if (!surfaceHolder.getSurface().isValid())
            return;

        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);

        for (Star s : stars)
            s.draw(canvas);

        for (int i = 0; i < enemies.length; i++)
            enemies[i].draw(canvas);

        for (Explosion e : explosions){
            e.draw(canvas);
        }
        explosions.clear();

        player.draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    private void control(){
        try {
            gameThread.sleep(17);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public void pause(){
        isPlaying = false;

        try {
            gameThread.join();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public void resume(){
        isPlaying = true;

        gameThread = new Thread(this);
        gameThread.start();
    }
}

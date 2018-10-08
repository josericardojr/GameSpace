package com.example.josericardo.gamespace;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    // Verificar se o jogador ainda esta jogando
    boolean isPlaying;

    // Game Thread
    private Thread gameThread = null;
    SurfaceHolder surfaceHolder;
    Player player;

    public GameView(Context context){
        super(context);

        surfaceHolder = getHolder();
        player = new Player(context);
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


    private void update(){

    }

    private void draw(){

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

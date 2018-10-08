package com.example.josericardo.gamespace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }
}

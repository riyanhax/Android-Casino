package com.example.mutan.newkurs.GameScenes;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.example.mutan.newkurs.GameManagers.GameFieldManager;
import com.example.mutan.newkurs.MyInterfaces.Scene;

public class GameScene implements Scene {

    private GameFieldManager gameFieldManager;

    public GameScene(Resources resources){
        gameFieldManager = new GameFieldManager(resources);
    }

    @Override
    public void update() {
        gameFieldManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        gameFieldManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        gameFieldManager.receiveTouch(event);
    }
}

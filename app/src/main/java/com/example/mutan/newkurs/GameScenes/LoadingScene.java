package com.example.mutan.newkurs.GameScenes;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyInterfaces.Scene;


public class LoadingScene implements Scene {

    public LoadingScene(Resources resources){

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        int key = event.getAction();

        switch (key){
            case MotionEvent.ACTION_UP:
                ManagerScene.ACTIVE_SCENE = 1;
                break;
        }
    }

}

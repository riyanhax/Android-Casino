package com.example.mutan.newkurs.GameScenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyInterfaces.Scene;

import java.util.ArrayList;

public class ManagerScene {
    private ArrayList<GameScene> scenes = new ArrayList<GameScene>();

    public static int ACTIVE_SCENE;
    private Resources resources;

    public ManagerScene(Context context){
        ACTIVE_SCENE = 0;

        this.resources = context.getResources();

        //scenes.add(new LoadingScene(resources));
        scenes.add(new GameScene(resources));
    }

    public void receiveTouch(MotionEvent event){
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update(){
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }


}

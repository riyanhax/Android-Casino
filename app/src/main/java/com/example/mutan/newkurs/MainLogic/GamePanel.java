package com.example.mutan.newkurs.MainLogic;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.mutan.newkurs.GameScenes.ManagerScene;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread mainThread;
    private ManagerScene managerScene;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        managerScene = new ManagerScene(context);
    }

    public void update(){
        managerScene.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        managerScene.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread = new MainThread(getHolder(), this);
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mainThread.setRunning(false);
        while(retry) {
            try{
                mainThread.join();
                retry = false;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        managerScene.receiveTouch(event);
        return true;
    }
}

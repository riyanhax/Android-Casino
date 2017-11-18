package com.example.mutan.newkurs.GameManagers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.mutan.newkurs.GameObjects.GameField;
import com.example.mutan.newkurs.MyInterfaces.GameManager;
import com.example.mutan.newkurs.R;

public class GameFieldManager implements GameManager {
    private FieldManager fieldManager;

    public GameFieldManager(Resources resources){
        fieldManager = new FieldManager(resources);
    }

    @Override
    public void update() {
        fieldManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        fieldManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        fieldManager.receiveTouch(event);
    }
}

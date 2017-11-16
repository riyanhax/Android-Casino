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

    private Bitmap chip;
    private ChipManager chipManager;
    private FieldManager fieldManager;

    public GameFieldManager(Resources resources){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        chip = BitmapFactory.decodeResource(resources, R.drawable.casinochip, options);
        fieldManager = new FieldManager(resources);
        chipManager = new ChipManager(chip);
    }

    @Override
    public void update() {
        fieldManager.setSelected(chipManager.selectedChip());
        chipManager.update();
        fieldManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        chipManager.draw(canvas);
        fieldManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        chipManager.receiveTouch(event);
        fieldManager.receiveTouch(event);
    }
}

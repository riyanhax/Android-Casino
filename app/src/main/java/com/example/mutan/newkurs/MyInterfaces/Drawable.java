package com.example.mutan.newkurs.MyInterfaces;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Drawable {
    public void update();
    public void draw(Canvas canvas);
    public void receiveTouch(MotionEvent event);
}

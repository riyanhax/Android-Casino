package com.example.mutan.newkurs.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;

public class Player implements GameObject{

    private Rect rectDst;
    private Paint paint;

    public int getCount() {
        return count;
    }

    private int count = 100;

    public Player(){
        rectDst = new Rect(
                (int)((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH * Constants.MIDDLE_RIGHT_HORIZONTAL) * 0.5),
                0,
                (int)(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH * Constants.MIDDLE_RIGHT_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL));

        paint = new Paint();
        paint.setColor(Color.rgb(0, 255, 0));
    }

    @Override
    public void update() {
        paint.setTextScaleX(1);
        paint.setTextSize(rectDst.height());
        paint.setTextScaleX(rectDst.width()/paint.measureText("x " + count));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText("x " + count, rectDst.left, rectDst.bottom, paint);
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}
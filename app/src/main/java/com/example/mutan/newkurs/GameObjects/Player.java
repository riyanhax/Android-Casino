package com.example.mutan.newkurs.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;

import java.util.ArrayList;

public class Player implements GameObject{

    private Rect rectDst;
    private Paint paint;
    private int count = 100;

    private int minus = 0;
    private int plus = 0;

    private boolean whenStart1 = false;
    private volatile boolean stopAdd = true;


    private int tempCount = 0;

    private long time = 0;

    public int getCount() {
        return count;
    }

    public void addCount(ArrayList<Integer> count){
        if(stopAdd && count.size() > 0) {
            minus = count.get(1);
            plus = count.get(0);
            time = System.currentTimeMillis();
            tempCount = this.count;

            stopAdd = false;
            this.count = this.count + count.get(0) - count.get(1);

            Log.i("check", tempCount + " " + this.count + " " + stopAdd);
        }
    }

    public Player(){
        rectDst = new Rect(
                (int)((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH * Constants.MIDDLE_RIGHT_HORIZONTAL) * 0.5),
                0,
                (int)(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH * Constants.MIDDLE_RIGHT_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL));

        paint = new Paint();
        paint.setColor(Color.rgb(255, 255, 255));
    }

    @Override
    public void update() {
        paint.setTextScaleX(1);
        paint.setTextSize(rectDst.height());

        if (System.currentTimeMillis() - time < 3000) {
            paint.setTextScaleX(rectDst.width() / paint.measureText("chips: " + tempCount + " - " + minus));
        }
        if(System.currentTimeMillis() - time < 6000 && System.currentTimeMillis() - time >= 3000){

            if(!whenStart1) {
                tempCount -= minus;
                whenStart1 = true;
            }

            paint.setTextScaleX(rectDst.width() / paint.measureText("chips: " + tempCount + " + " + plus));

            stopAdd = true;
        }
        else if(stopAdd){
            whenStart1 = false;
            paint.setTextScaleX(rectDst.width() / paint.measureText("chips: " + count));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (System.currentTimeMillis() - time < 3000) {
            canvas.drawText("chips: " + tempCount + " - " + minus, rectDst.left, rectDst.bottom, paint);
        }
        if(System.currentTimeMillis() - time < 6000 && System.currentTimeMillis() - time >= 3000){
            canvas.drawText("chips: " + tempCount + " + " + plus, rectDst.left, rectDst.bottom, paint);
        }
        else if(stopAdd){
            canvas.drawText("chips: " + count, rectDst.left, rectDst.bottom, paint);
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}
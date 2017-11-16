package com.example.mutan.newkurs.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;

public class Chip implements GameObject{

    private Bitmap chip;

    private Rect rectSrc;
    private Rect rectDst;

    private boolean isSelected = false;
    private Paint paint;

    public Chip(Bitmap chip, int row, int column, int count){
        this.chip = chip;

        paint = new Paint();
        paint.setColor(Color.RED);

        rectSrc = new Rect(
                Constants.CHIP_X + row * Constants.CHIP_StepHorizontalAndVertical,
                Constants.CHIP_Y + column * Constants.CHIP_StepHorizontalAndVertical,
                Constants.CHIP_X1 + row * Constants.CHIP_StepHorizontalAndVertical,
                Constants.CHIP_Y1 + column * Constants.CHIP_StepHorizontalAndVertical);

        rectDst = new Rect(
                (int)(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH*Constants.MIDDLE_RIGHT_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT*Constants.CHIP_HEIGHT*count),
                (int)(Constants.SCREEN_WIDTH),
                (int)(Constants.SCREEN_HEIGHT*Constants.CHIP_HEIGHT
                        + Constants.SCREEN_HEIGHT*Constants.CHIP_HEIGHT*count));


    }

    public Rect getRectDst(){
        return rectDst;
    }

    public void offset(int dy){
        rectDst.offset(0, dy);
    }

    public void select(){
        isSelected = true;
    }

    public void unselect(){
        isSelected = false;
    }

    public boolean isSelected(){
        return isSelected;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(chip, rectSrc, rectDst, null);
        if(isSelected){

            for(int i = 0; i < rectDst.width()/16; i++){
                canvas.drawLine(rectDst.left, rectDst.top + i, rectDst.right, rectDst.top + i, paint);
                canvas.drawLine(rectDst.right - i, rectDst.top, rectDst.right - i, rectDst.bottom, paint);
                canvas.drawLine(rectDst.right, rectDst.bottom - i, rectDst.left, rectDst.bottom - i, paint);
                canvas.drawLine(rectDst.left + i, rectDst.bottom, rectDst.left + i, rectDst.top, paint);
            }
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}

package com.example.mutan.newkurs.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;


public class Bonus implements GameObject{

    private Rect rectDst;
    private Paint paint;

    private String text = "";
    private int millis = 60000;
    private int bonus = 50;
    private boolean when = false;

    public Bonus(){
        rectDst = new Rect(
                0,
                0,
                (int)((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH * Constants.MIDDLE_RIGHT_HORIZONTAL) * 0.5),
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL));

        paint = new Paint();
        paint.setColor(Color.rgb(255, 255, 255));

        this.startTimer();
    }

    public int getBonus(){
        if(when){
            when = false;
            return bonus;
        } else
            return 0;
    }

    private void startTimer(){
        new CountDownTimer(millis, 1000) {
            public void onTick(long millisUntilFinished) {
                text = ("" + (int)(bonus + bonus * 0.5) + "xCHIPS in " +
                        + millisUntilFinished / 1000 + " | ");
            }

            public void onFinish() {
                //text = ("YOU GET IT! | ");
                millis *= 2;
                bonus += bonus * 0.5;
                when = true;

                startTimer();
            }
        }.start();
    }

    @Override
    public void update() {
        paint.setTextScaleX(1);
        paint.setTextSize(rectDst.height());
        paint.setTextScaleX(rectDst.width() / paint.measureText(text));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, rectDst.left, rectDst.bottom, paint);
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}

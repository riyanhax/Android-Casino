package com.example.mutan.newkurs.GameObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;
import com.example.mutan.newkurs.R;

public class ReturnButton implements GameObject {

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap;
    private Rect rectSrc;
    private Rect rectDst;

    public ReturnButton(Resources resources) {
        bitmap = bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.returnbutton1);
        bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.returnbutton2);

        rectSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rectDst = new Rect(
                (int) (Constants.SCREEN_WIDTH * Constants.BOTTOM_LEFT1_HORIZONTAL),
                (int) (Constants.SCREEN_HEIGHT * (Constants.MIDDLE_VERTICAL + Constants.TOP_VERTICAL)),
                (int) (Constants.SCREEN_WIDTH * (Constants.BOTTOM_LEFT1_HORIZONTAL + Constants.BOTTOM_LEFT2_HORIZONTAL)),
                (int) (Constants.SCREEN_HEIGHT));
    }

    public Rect getRectDst() {
        return rectDst;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, rectSrc, rectDst, null);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        int key = event.getAction();

        float x = event.getX();
        float y = event.getY();

        switch (key){
            case MotionEvent.ACTION_DOWN:
                if(rectDst.contains((int)x, (int)y))
                    bitmap = bitmap2;
                break;

            case MotionEvent.ACTION_UP:
                bitmap = bitmap1;
                break;

            case MotionEvent.ACTION_MOVE:
                if(!rectDst.contains((int)x, (int)y))
                    bitmap = bitmap1;
                break;
        }
    }
}
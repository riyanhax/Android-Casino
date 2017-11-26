package com.example.mutan.newkurs.Object;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class NumberOnField {

    public static ArrayList<LogNumberOnField> logNumberOnField;

    static {
        logNumberOnField = new ArrayList<>();
    }

    private int i;
    private int j;
    private int count;
    private float height;
    private int width;
    private Paint paint;

    public NumberOnField(int i, int j, int count, float height, int width){
        this.i = i;
        this.j = j;
        this.count = count;
        this.height = height;
        this.width = width;

        paint = new Paint();
        paint.setColor(Color.rgb(0, 255, 0));
        paint.setTextSize(height);
        paint.setTextScaleX(width/paint.measureText("x" + count + ""));

        logNumberOnField.add(new LogNumberOnField(LogNumberOnField.TYPE.CREATED,  this, count));
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    public String getCount() {
        return "x" + count + "";
    }

    public int getCountInt(){
        return  count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(int add){
        logNumberOnField.add(new LogNumberOnField(LogNumberOnField.TYPE.ADD,  this, count));

        this.count += add;

        paint.setTextScaleX(1);
        paint.setTextSize(height);
        paint.setTextScaleX(width/paint.measureText("x" + count + ""));
    }

    public Paint getPaint(){
        return paint;
    }

}

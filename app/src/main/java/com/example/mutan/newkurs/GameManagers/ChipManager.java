package com.example.mutan.newkurs.GameManagers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.mutan.newkurs.GameObjects.Chip;
import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameManager;
import com.example.mutan.newkurs.R;

import java.util.ArrayList;

public class ChipManager implements GameManager {

    private ArrayList<Chip> chipList;
    private Bitmap chip;
    private boolean isDrag = false;
    private int y = 0;
    private long checkms = 0;

    public ChipManager(Bitmap chip){

        chipList = new ArrayList<>();
        int row = 0, column = 0;
        for(int i = 0; i < Constants.CHIP_COUNT; i++){
            Chip chipToList = new Chip(chip, row, column, i);
            row++;
            if(row >= 4) {
                column++;
                row = 0;
            }
            chipList.add(chipToList);
        }

    }

    public int selectedChip(){
        for (int i = 0; i < chipList.size(); i++) {
            if(chipList.get(i).isSelected())
                return i;
        }
        return -1;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < chipList.size(); i++) {
            if(chipList.get(i).getRectDst().bottom > 0 && chipList.get(i).getRectDst().top < Constants.SCREEN_HEIGHT)
                chipList.get(i).draw(canvas);
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        int key = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch(key){

            case MotionEvent.ACTION_DOWN:
                this.y  = y;
                checkms = System.currentTimeMillis();
                {
                    int i = 0;
                    while(!isDrag && i<chipList.size()){
                        if(chipList.get(i).getRectDst().bottom > 0
                                &&
                                chipList.get(i).getRectDst().top < Constants.SCREEN_HEIGHT
                                &&
                                chipList.get(i).getRectDst().contains(x, y)){
                            isDrag = true;
                        }
                    i++;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDrag){
                    if(y - this.y > 0){
                        if(chipList.get(0).getRectDst().top + y - this.y <= 0){
                            for (int i = 0; i < chipList.size(); i++) {
                                chipList.get(i).offset(y - this.y);
                            }
                        }
                    }
                    else{
                        if(chipList.get(chipList.size() - 1).getRectDst().top + y - this.y >= Constants.SCREEN_HEIGHT){
                            for (int i = 0; i < chipList.size(); i++) {
                                chipList.get(i).offset(y - this.y);
                            }
                        }
                    }
                    this.y = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDrag && System.currentTimeMillis() - checkms < 200){
                    for (int i = 0; i < chipList.size(); i++) {
                        if(chipList.get(i).getRectDst().contains(x, y)){
                            chipList.get(i).select();
                        }
                        else
                            chipList.get(i).unselect();
                    }
                }
                isDrag = false;
                break;
        }
    }
}


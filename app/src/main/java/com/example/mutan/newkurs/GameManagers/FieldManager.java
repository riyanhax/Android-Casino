package com.example.mutan.newkurs.GameManagers;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.mutan.newkurs.GameObjects.CancelButton;
import com.example.mutan.newkurs.GameObjects.GameField;
import com.example.mutan.newkurs.GameObjects.ReturnButton;
import com.example.mutan.newkurs.GameObjects.SpinButton;
import com.example.mutan.newkurs.MyInterfaces.GameManager;


public class FieldManager implements GameManager{

    private CancelButton cancelButton;
    private ReturnButton returnButton;
    private SpinButton spinButton;
    private NumberManager numberManager;
    private GameField gameField;

    private int selected = -1;

    public FieldManager(Resources resources){
        cancelButton = new CancelButton(resources);
        returnButton = new ReturnButton(resources);
        spinButton = new SpinButton(resources);
        numberManager = new NumberManager();

        gameField = new GameField(resources);
    }

    public void setSelected(int s){
        selected = s;
    }

    @Override
    public void update() {
        numberManager.setSelected(selected);
        gameField.update();
    }

    @Override
    public void draw(Canvas canvas) {
        gameField.draw(canvas);

        cancelButton.draw(canvas);
        returnButton.draw(canvas);
        spinButton.draw(canvas);
        numberManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {

        cancelButton.receiveTouch(event);
        returnButton.receiveTouch(event);
        spinButton.receiveTouch(event);
        numberManager.receiveTouch(event);

        int key = event.getAction();
        switch (key) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();

                if(cancelButton.getRectDst().contains((int)x, (int)y))
                {
                    numberManager.clear();
                }

                if(returnButton.getRectDst().contains((int)x, (int)y))
                {
                    numberManager.revertAction();
                }

                if(spinButton.getRectDst().contains((int)x, (int)y))
                {
                    //Проверить поставили ли ставки
                    gameField.goMove();
                }

                break;
        }


    }
}

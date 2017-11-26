package com.example.mutan.newkurs.GameManagers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.mutan.newkurs.GameObjects.CancelButton;
import com.example.mutan.newkurs.GameObjects.GameField;
import com.example.mutan.newkurs.GameObjects.Player;
import com.example.mutan.newkurs.GameObjects.ReturnButton;
import com.example.mutan.newkurs.GameObjects.SpinButton;
import com.example.mutan.newkurs.MyInterfaces.GameManager;
import com.example.mutan.newkurs.R;


public class FieldManager implements GameManager{
    private GameField gameField;

    private ChipManager chipManager;
    private NumberManager numberManager;

    private CancelButton cancelButton;
    private ReturnButton returnButton;
    private SpinButton spinButton;

    private boolean isMove = false;

    Player player;


    public FieldManager(Resources resources){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap chip = BitmapFactory.decodeResource(resources, R.drawable.casinochip, options);

        player = new Player();
        cancelButton = new CancelButton(resources);
        returnButton = new ReturnButton(resources);
        spinButton = new SpinButton(resources);
        numberManager = new NumberManager();
        gameField = new GameField(resources);
        chipManager = new ChipManager(chip);
    }


    @Override
    public void update() {
        numberManager.setSelected(chipManager.selectedChip());
        numberManager.setTotal(player.getCount());

        gameField.update();
        chipManager.update();
        numberManager.update();
        player.update();

        if(gameField.isEnd()){
            isMove = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        gameField.draw(canvas);
        player.draw(canvas);

        if(!isMove) {
            cancelButton.draw(canvas);
            returnButton.draw(canvas);
            spinButton.draw(canvas);
            numberManager.draw(canvas);
        }

        chipManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {

        if(!isMove) {
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
                        isMove = true;
                        gameField.goMove();
                    }

                    break;
            }
        }

        chipManager.receiveTouch(event);

    }
}

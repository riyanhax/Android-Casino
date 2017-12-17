package com.example.mutan.newkurs.GameObjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameObject;
import com.example.mutan.newkurs.R;

import java.util.Random;

public class GameField implements GameObject {

    private Bitmap roulette;
    private Rect rectSrcRoulette;
    private Rect rectDstRoulette;

    private Bitmap gameField;
    private Rect rectSrcGameField;
    private Rect rectDstGameField;

    private Rect rectNumbers;
    private String number;

    private boolean isEndOfEnd = false;

    private boolean isMove = false;
    private boolean isSpin = false;
    private boolean isStop = false;
    private boolean isBack = false;
    private boolean isDrawBall = false;

    public boolean isEnd() {
        return !isMove && !isSpin && !isStop && !isBack;
    }

    private boolean isEnd = false;

    private int realWidth;
    private int step;

    private int centerX;
    private int centerY;

    private double rotateSpeedBall = 0;
    private double rotateSpeedField = 0;

    private double degrees = 360.0 / 37.0;
    private double degreesBall = - 360.0 + 360.0 / 37.0 ;

    private float ballX;
    private float ballY;

    private Paint paint;

    private long previousTime;

    public GameField(Resources resources){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        gameField = BitmapFactory.decodeResource(resources, R.drawable.gamefield, options);
        rectSrcGameField = new Rect(
                0,
                0,
                gameField.getWidth(),
                gameField.getHeight());

        rectDstGameField = new Rect(
                0,
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL),
                (int)(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH*Constants.MIDDLE_RIGHT_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT * Constants.BOTTOM_VERTICAL));


        roulette = BitmapFactory.decodeResource(resources, R.drawable.roulette, options);
        rectSrcRoulette = new Rect(0, 0, roulette.getWidth(), roulette.getHeight());

        if(Constants.SCREEN_HEIGHT
                <= Constants.SCREEN_WIDTH ) {
            rectDstRoulette = new Rect(
                    0,
                    (int) (Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL),
                    0,
                    (int) (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT * Constants.BOTTOM_VERTICAL));
            rectDstRoulette.right = rectDstRoulette.height();
        } else {
            // МОЖНО УБРАТЬ
            rectDstRoulette = new Rect(
                    0,
                    0,
                    (int)(Constants.SCREEN_WIDTH * Constants.MIDDLE_LEFT_HORIZONTAL),
                    0);
            rectDstRoulette.right = rectDstRoulette.width();
        }

        centerX = rectDstRoulette.left + rectDstRoulette.width() / 2;
        centerY = rectDstRoulette.bottom - rectDstRoulette.height() / 2;

        ballX = rectDstRoulette.centerX() + rectDstRoulette.width() / 200.0f;
        ballY = rectDstRoulette.centerY() - rectDstRoulette.width() / 2.9f;

        realWidth = rectDstRoulette.width();
        rectDstRoulette.offset(-realWidth, 0);
        rectDstRoulette.left -= rectDstRoulette.width();
        rectDstRoulette.bottom += rectDstRoulette.height();

        step = (int) (realWidth * 0.02f);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        rectNumbers = new Rect(realWidth,
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL + realWidth / 2),
                (int)(Constants.SCREEN_WIDTH * Constants.MIDDLE_LEFT_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL + realWidth));

        paint.setTextSize(rectNumbers.height());
        previousTime = System.currentTimeMillis();
    }

    public void goMove() {
        if(!isMove && !isSpin && !isStop && !isBack)
            this.isMove = true;
    }

    private boolean updateMove(){
        int drx = rectDstRoulette.left;
        int dry = rectDstRoulette.bottom;
        int dgx = rectDstGameField.left;
        int dgy = rectDstGameField.bottom;

        if (rectDstRoulette.left < 0) {
            rectDstRoulette.offset(step, 0);
            rectDstGameField.left += step;
        }

        if (rectDstGameField.bottom > Constants.SCREEN_HEIGHT * Constants.TOP_VERTICAL + realWidth / 2) {
            rectDstGameField.bottom -= step / 2;
        }

        if (rectDstRoulette.width() > realWidth) {
            rectDstRoulette.left += step;
        }

        if (rectDstRoulette.height() > realWidth) {
            rectDstRoulette.bottom -= step;
        }

        if (rectDstRoulette.left - drx == 0
                && rectDstRoulette.bottom - dry == 0
                && rectDstGameField.left - dgx == 0
                && rectDstGameField.bottom - dgy == 0) {

            rotateSpeedField = 360.0 / 37.0 + new Random().nextInt(3) + 360.0 / 37.0 * new Random().nextDouble();
            rotateSpeedBall = rotateSpeedField * (new Random().nextDouble() + 1.0);

            //rotateSpeedBall = 360.0 / 37.0;

//            rouletteNumber = new Random().nextInt(Constants.ROULETTE_VALUE_COUNT);
//            int searchNum = -1;
//            int pr = 0;
//
//            for(int i = 0; i < Constants.ROULETTE_VALUE.length; i++){
//                if(Constants.ROULETTE_VALUE[i] == rouletteNumber){
//                      searchNum = pr = i;
//                }
//            }

//            searchNum -= roulettePreviousNumber;

//            degreesBallTo = 3.0 * 360.0 + (360.0 / 37.0) + degreesBall;
//            degreesTo = - 3.0 * 360 + (360.0 / 37.0) + degrees;

//            searchNum -= roulettePreviousNumber;
//            degreesBallTo = 3.0 * 360.0 + searchNum * (360.0 / 37.0) + degreesBall;
//            degreesTo = - 3.0 * 360 + (Constants.ROULETTE_VALUE_COUNT - searchNum) * (360.0 / 37.0) + degrees;
//            Log.i("deg", degreesBallTo + " " + degreesTo);
//            roulettePreviousNumber = pr;

            //Log.i("tag1", "" + searchNum);

            return true;
        }

        return false;
    }

    private boolean updateSpin(){

//        boolean check1 = false;
//        boolean check2 = false;
//
//        double localDelta = degrees;
//        double localDeltaBall = degreesBall;
//
//        if(degrees > degreesTo){
//            degrees -= (degrees - degreesTo) * 0.0001;
//        }
//
//        if((degrees - degreesTo) * 0.0001 < 0.0001){
//            degrees = degreesTo;
//            check1 = true;
//        }
//
//        if(degreesBall < degreesBallTo){
//            degreesBall += (degreesBallTo - degreesBall) * 0.0001;
//        }
//
//        if((degreesBallTo - degreesBall) * 0.0001 < 0.0001){
//            degreesBall = degreesBallTo;
//            check2 = true;
//        }
//
//        localDelta = Math.abs(localDelta - degrees);
//        localDeltaBall = Math.abs(localDeltaBall - degreesBall);
//
//        deltaDegrees += localDelta;
//        deltaDegreesBall += localDeltaBall;
//
//        if(deltaDegrees / (360.0/37.0) >= 1){
//            rouletteNumber += deltaDegrees / (360.0/37.0);
//
//            deltaDegrees -= (360.0/37.0) * (deltaDegrees / (360.0/37.0));
//        }
//
//        if(deltaDegreesBall / (360.0/37.0) >= 1){
//            rouletteNumber += deltaDegreesBall / (360.0/37.0);
//
//            deltaDegreesBall -= (360.0/37.0) * (deltaDegreesBall / (360.0/37.0));
//        }
//
//        if(rouletteNumber >= Constants.ROULETTE_VALUE_COUNT){
//            rouletteNumber = rouletteNumber - Constants.ROULETTE_VALUE_COUNT;
//        }
//
//
//
//        //Log.i("deg", localDelta + " " + localDeltaBall);
//
//        if(check1 && check2)
//            return true;
//
//        return false;
//        if(degrees > degreesTo){
//            degrees -= (degrees - degreesTo) * 0.01;
//        }
//
//        if((degrees - degreesTo) * 0.01 < 0.01){
//            degrees = degreesTo;
//            return true;
//        }

//        if(degreesBall > degreesBallTo){
//            degreesBall -= (degreesBall - degreesBallTo) * 0.01;
//        }
//
//        if((degreesBall - degreesBallTo) * 0.01 < 0.01){
//            degreesBall = degreesBallTo;
//            return true;
//        }
        degreesBall -= rotateSpeedBall;
        degrees += rotateSpeedField;

        boolean check1 = false;
        boolean check2 = false;

        if (rotateSpeedBall > 0)
            rotateSpeedBall -= rotateSpeedBall * 0.01;

        if(rotateSpeedField > 0){
            rotateSpeedField -= rotateSpeedField * 0.01;
        }

        if (rotateSpeedBall * 0.01 < 0.001) {
            rotateSpeedBall = 0;
            check1 = true;
        }

        if (rotateSpeedField * 0.01 < 0.001) {
            rotateSpeedField = 0;
            check2 = true;
        }

        if(check1 && check2) {

            return true;
        }

        return false;
    }

    private boolean updateCorrectStop(){
        degreesBall = (360.0 / 37.0) * (Math.round(degreesBall / (360.0 / 37.0) ));
        degrees = (360.0 / 37.0) * (Math.round(degrees / (360.0 / 37.0) ));

//        boolean check1 = false;
//        boolean check2 = false;
//
//        double deltaball = Math.abs(degreesBallTo - degreesBall) * 0.1;
//        double delta = Math.abs(degreesTo - degrees) * 0.1;
//
//        if(degreesBall < degreesBallTo){
//            degreesBall += Math.abs(degreesBallTo - degreesBall) * 0.1;
//        }else if(degreesBall > degreesBallTo){
//            degreesBall -= Math.abs(degreesBallTo - degreesBall) * 0.1;
//        }
//
//        if(degrees < degreesTo){
//            degrees += Math.abs(degreesTo - degrees) * 0.1;
//        }else if(degrees > degreesTo){
//            degrees -= Math.abs(degreesTo - degrees) * 0.1;
//        }
//
//        if(Math.abs(deltaball - degreesBall) < 0.001){
//            degreesBall = degreesBallTo;
//            check1 = true;
//        }
//
//        if(Math.abs(delta - degrees) < 0.001){
//            degrees = degreesTo;
//            check2 = true;
//        }
//
//        if(check1 && check2){
//            return true;
//        }
//        else

        return true;
    }

    private boolean updateBackMove(){
        int rr = rectDstRoulette.right;
        int gb = rectDstGameField.bottom;
        int rw = rectDstRoulette.width();
        int rh = rectDstRoulette.height();

        if (rectDstRoulette.right > 0) {
            rectDstRoulette.offset(-step, 0);
            rectDstGameField.left -= step;
        }

        if (rectDstGameField.bottom < Constants.SCREEN_HEIGHT * (Constants.TOP_VERTICAL + Constants.MIDDLE_VERTICAL)) {
            rectDstGameField.bottom += step / 2;
        }

        if (rectDstRoulette.width() < realWidth * 2) {
            rectDstRoulette.left -= step;
        }

        if (rectDstRoulette.height() < realWidth * 2) {
            rectDstRoulette.bottom += step;
        }

        if(rr - rectDstRoulette.right == 0
                && gb - rectDstGameField.bottom == 0
                && rw - rectDstRoulette.width() == 0
                && rh - rectDstRoulette.height() == 0){
            return true;
        }

        return false;
    }

    public String getNum(){
        if(isEndOfEnd) {
            isEndOfEnd = false;

            //Log.i("forcheck", "im here " + number);

            return number;
        }
        else
            return "";

    }

    private void searchNumber(){

//        number = "" + Constants.ROULETTE_VALUE[rouletteNumber];

        number = "";
        int numd = (int) Math.abs(Math.round(degrees / (360.0 / 37.0)));
        int numdb = (int) Math.abs(Math.round(degreesBall / (360.0 / 37.0)));

        int value = Constants.ROULETTE_VALUE_COUNT - (numdb) % Constants.ROULETTE_VALUE_COUNT;
        int value2 = Constants.ROULETTE_VALUE_COUNT - (numd) % Constants.ROULETTE_VALUE_COUNT;

        int counted = (value + value2) % Constants.ROULETTE_VALUE_COUNT;

        if(counted >= 0 && counted < Constants.ROULETTE_VALUE_COUNT)
            number = "" + Constants.ROULETTE_VALUE[counted];

//        if(Math.abs(counted) >= 0 && Math.abs(counted) < Constants.ROULETTE_VALUE_COUNT)
//            number = "" + Constants.ROULETTE_VALUE[Math.abs(- value + value2)];

        //Log.i("tag", "" + number + " " + value + " " + value2);
    }

    @Override
    public void update() {
        centerX = rectDstRoulette.left + rectDstRoulette.width() / 2;
        centerY = rectDstRoulette.bottom - rectDstRoulette.height() / 2;

        searchNumber();
        paint.setTextScaleX(1);
        paint.setTextScaleX(rectNumbers.width()/paint.measureText("" + number));

        if(isMove){
            isMove = !this.updateMove();
            isSpin = !isMove;

            previousTime = System.currentTimeMillis();
        }

        if(isSpin && System.currentTimeMillis() - previousTime >= 3000){
            isSpin = !this.updateSpin();
            isStop = !isSpin;
        }

        if(isStop){
            isStop = !this.updateCorrectStop();
            isBack = isDrawBall = !isStop;

            previousTime = System.currentTimeMillis();
        }

        if(isBack && System.currentTimeMillis() - previousTime >= 3000){
            isDrawBall = false;

            isBack = !this.updateBackMove();
            isEnd = !isBack;

            isEndOfEnd = true;
        }

        if(isEnd){
            isMove = false;
            isSpin = false;
            isStop = false;
            isBack = false;
            isEnd = false;
        }

//        if(isMove && updateMove()){
//            isMove = false;
//            isSpin = true;
//
//            previousTime = System.currentTimeMillis();
//        }
//
//        if(System.currentTimeMillis() - previousTime >= 5000 && isSpin && updateSpin()){
//            isSpin = false;
//            isStop = true;
//        }
//
//        if(isStop && updateCorrectStop()){
//            isStop = false;
//            isBack = true;
//            isDrawBall = true;
//
//            previousTime = System.currentTimeMillis();
//        }
//
//        if(System.currentTimeMillis() - previousTime >= 5000 && !isStop && isBack && isDrawBall){
//            isDrawBall = false;
//        }
//
//        if(System.currentTimeMillis() - previousTime >= 5000 && isBack && updateBackMove()){
//            isMove = false;
//            isSpin = false;
//            isStop = false;
//            isBack = false;
//        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameField, rectSrcGameField, rectDstGameField, null);

        canvas.save();
        canvas.rotate((float) degrees, centerX, centerY);
        canvas.drawBitmap(roulette, rectSrcRoulette, rectDstRoulette, null);
        canvas.restore();

        if(isSpin || isStop || isDrawBall) {
            canvas.save();
            canvas.rotate((float) degreesBall, centerX, centerY);
            canvas.drawCircle(ballX, ballY, rectDstRoulette.width() / 35, paint);
            canvas.restore();

            canvas.drawText(number, rectNumbers.left, rectNumbers.bottom, paint);
        }

    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}

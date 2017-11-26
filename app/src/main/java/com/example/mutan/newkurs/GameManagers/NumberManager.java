package com.example.mutan.newkurs.GameManagers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.mutan.newkurs.GameObjects.Player;
import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.MyInterfaces.GameManager;
import com.example.mutan.newkurs.Object.LogNumberOnField;
import com.example.mutan.newkurs.Object.NumberOnField;

import java.util.ArrayList;

public class NumberManager implements GameManager {

    private Paint paint;

    private ArrayList<ArrayList<Rect>> rectNumbers;
    private ArrayList<ArrayList<Rect>> rectSideNumbersH;
    private ArrayList<ArrayList<Rect>> rectSideNumbersV;
    private ArrayList<ArrayList<Rect>> rectCrossNumbers;

    private ArrayList<NumberOnField> arrNumberOnField;
    private ArrayList<NumberOnField> arrNumberOnFieldH;
    private ArrayList<NumberOnField> arrNumberOnFieldV;
    private ArrayList<NumberOnField> arrNumberOnFieldCross;
    private int totalOnField = 0;

    public void setTotal(int total) {
        this.total = total;
    }

    private int total = 0;

    private int selected = -1;

    public NumberManager(){

        arrNumberOnField = new ArrayList<>();
        arrNumberOnFieldH = new ArrayList<>();
        arrNumberOnFieldV = new ArrayList<>();
        arrNumberOnFieldCross = new ArrayList<>();

        paint = new Paint();
        rectNumbers = new ArrayList<>();
        rectSideNumbersH = new ArrayList<>();
        rectSideNumbersV = new ArrayList<>();
        rectCrossNumbers = new ArrayList<>();

        this.initialization();
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(rectSideNumbersH.get(0).get(0).width());
//        paint.setTextScaleX(rectSideNumbersH.get(0).get(0).height()/paint.measureText("x1"));
    }

    public void revertAction(){

        if(NumberOnField.logNumberOnField.size()>=1){

            int index = -1;
            int last = NumberOnField.logNumberOnField.size() - 1;

            if(arrNumberOnField.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField()) != -1){
                index = arrNumberOnField.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField());

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.CREATED) {
                    totalOnField -= arrNumberOnField.get(index).getCountInt();

                    arrNumberOnField.remove(index);
                }

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.ADD){
                    totalOnField -= arrNumberOnField.get(index).getCountInt() - NumberOnField.logNumberOnField.get(last).getCount();

                    arrNumberOnField.get(index).setCount(NumberOnField.logNumberOnField.get(last).getCount());
                }

                NumberOnField.logNumberOnField.remove(last);

                //totalOnField -= last;
            }
            else
            if(NumberOnField.logNumberOnField.size()>=1 && arrNumberOnFieldH.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField()) != -1){
                index = arrNumberOnFieldH.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField());

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.CREATED){
                    totalOnField -= arrNumberOnFieldH.get(index).getCountInt();

                    arrNumberOnFieldH.remove(index);
                }

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.ADD){
                    totalOnField -= arrNumberOnFieldH.get(index).getCountInt() - NumberOnField.logNumberOnField.get(last).getCount();

                    arrNumberOnFieldH.get(index).setCount(NumberOnField.logNumberOnField.get(last).getCount());
                }

                NumberOnField.logNumberOnField.remove(last);

                //totalOnField -= last;
            }
            else
            if(NumberOnField.logNumberOnField.size()>=1 && arrNumberOnFieldV.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField()) != -1){
                index = arrNumberOnFieldV.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField());

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.CREATED) {
                    totalOnField -= arrNumberOnFieldV.get(index).getCountInt();

                    arrNumberOnFieldV.remove(index);
                }

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.ADD){
                    totalOnField -= arrNumberOnFieldV.get(index).getCountInt() - NumberOnField.logNumberOnField.get(last).getCount();

                    arrNumberOnFieldV.get(index).setCount(NumberOnField.logNumberOnField.get(last).getCount());
                }

                NumberOnField.logNumberOnField.remove(last);

                //totalOnField -= last;
            }
            else
            if(NumberOnField.logNumberOnField.size()>=1 && arrNumberOnFieldCross.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField()) != -1){
                index = arrNumberOnFieldCross.indexOf(NumberOnField.logNumberOnField.get(NumberOnField.logNumberOnField.size() - 1).getNumberOnField());

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.CREATED) {
                    totalOnField -= arrNumberOnFieldCross.get(index).getCountInt();

                    arrNumberOnFieldCross.remove(index);
                }

                if(NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.ADD){
                    totalOnField -= arrNumberOnFieldCross.get(index).getCountInt() - NumberOnField.logNumberOnField.get(last).getCount();
                    arrNumberOnFieldCross.get(index).setCount(NumberOnField.logNumberOnField.get(last).getCount());
                }

                NumberOnField.logNumberOnField.remove(last);

                //totalOnField -= last;
            }
        }
    }

    private void initialization(){
        Rect startRect = new Rect(
                (int)(Constants.SCREEN_WIDTH
                        * Constants.MIDDLE_LEFT_HORIZONTAL
                        * Constants.CHIP_IN_FIELD_START_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT
                        * Constants.TOP_VERTICAL
                        + Constants.SCREEN_HEIGHT
                        * Constants.MIDDLE_VERTICAL
                        * Constants.CHIP_IN_FIELD_START_VERTICAL),
                (int)(Constants.SCREEN_WIDTH
                        * Constants.MIDDLE_LEFT_HORIZONTAL
                        * Constants.CHIP_IN_FIELD_START_HORIZONTAL
                        + Constants.SCREEN_WIDTH
                        * Constants.MIDDLE_LEFT_HORIZONTAL
                        * Constants.CHIP_IN_FIELD_STEP_HORIZONTAL),
                (int)(Constants.SCREEN_HEIGHT
                        * Constants.TOP_VERTICAL
                        + Constants.SCREEN_HEIGHT
                        * Constants.MIDDLE_VERTICAL
                        * Constants.CHIP_IN_FIELD_START_VERTICAL
                        + Constants.SCREEN_HEIGHT
                        * Constants.MIDDLE_VERTICAL
                        * Constants.CHIP_IN_FIELD_STEP_VERTICAL
                ));

        for(int i = 0; i < Constants.CHIP_IN_FIELD_ROW; i++){
            ArrayList<Rect> arrayList = new ArrayList<>();
            Rect r = new Rect(startRect);
            r.offset(0, i*(int)(Constants.SCREEN_HEIGHT
                    * Constants.MIDDLE_VERTICAL
                    * Constants.CHIP_IN_FIELD_STEP_VERTICAL
                    + Constants.SCREEN_HEIGHT
                    * Constants.MIDDLE_VERTICAL
                    * Constants.CHIP_IN_FIELD_LINE_VERTICAL));
            arrayList.add(r);
            for(int j = 1; j < Constants.CHIP_IN_FIELD_COLUMN; j++){
                Rect rect = new Rect(arrayList.get(j - 1));
                rect.offset((int)(Constants.SCREEN_WIDTH
                        * Constants.MIDDLE_LEFT_HORIZONTAL
                        * Constants.CHIP_IN_FIELD_STEP_HORIZONTAL
                        + Constants.SCREEN_WIDTH
                        * Constants.MIDDLE_LEFT_HORIZONTAL
                        * Constants.CHIP_IN_FIELD_LINE_HORIZONTAL), 0);
                arrayList.add(rect);
            }
            rectNumbers.add(arrayList);
        }

        for(int i = 0; i < rectNumbers.size(); i ++) {
            for (int j = 0; j < rectNumbers.get(i).size(); j++) {
                float length = (rectNumbers.get(i).get(j).right - rectNumbers.get(i).get(j).left)/6.0f;
                rectNumbers.get(i).get(j).left = rectNumbers.get(i).get(j).left + (int)length;
                rectNumbers.get(i).get(j).right = rectNumbers.get(i).get(j).right - (int)length;
                rectNumbers.get(i).get(j).top = rectNumbers.get(i).get(j).top + (int)length;
                rectNumbers.get(i).get(j).bottom = rectNumbers.get(i).get(j).bottom - (int)length;
            }
        }

        for(int i = 0; i < Constants.CHIP_IN_FIELD_ROW; i++){
            ArrayList<Rect> arrayList = new ArrayList<>();
            for(int j = 0; j < Constants.CHIP_IN_FIELD_COLUMN - 1; j++){
                Rect r = new Rect(
                        rectNumbers.get(i).get(j).right,
                        rectNumbers.get(i).get(j).top,
                        rectNumbers.get(i).get(j + 1).left,
                        rectNumbers.get(i).get(j).bottom
                );
                arrayList.add(r);
            }
            rectSideNumbersH.add(arrayList);
        }

        for(int i = 0; i < Constants.CHIP_IN_FIELD_ROW - 1; i++){
            ArrayList<Rect> arrayList = new ArrayList<>();
            for(int j = 0; j < Constants.CHIP_IN_FIELD_COLUMN; j++){
                Rect r = new Rect(
                        rectNumbers.get(i).get(j).left,
                        rectNumbers.get(i).get(j).bottom,
                        rectNumbers.get(i).get(j).right,
                        rectNumbers.get(i + 1).get(j).top
                );
                arrayList.add(r);
            }
            rectSideNumbersV.add(arrayList);
        }

        for(int i = 0; i < Constants.CHIP_IN_FIELD_ROW - 1; i++){
            ArrayList<Rect> arrayList = new ArrayList<>();
            for(int j = 0; j < Constants.CHIP_IN_FIELD_COLUMN - 1; j++){
                Rect r = new Rect(
                        rectNumbers.get(i).get(j).right,
                        rectNumbers.get(i).get(j).bottom,
                        rectNumbers.get(i).get(j + 1).left,
                        rectNumbers.get(i + 1).get(j).top
                );
                arrayList.add(r);
            }
            rectCrossNumbers.add(arrayList);
        }
    }

    public void setSelected(int s){
        selected = s;
    }

    public void clear(){
        arrNumberOnField.clear();
        arrNumberOnFieldCross.clear();
        arrNumberOnFieldH.clear();
        arrNumberOnFieldV.clear();

        totalOnField = 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {

        //        paint.setColor(Color.BLUE);
        //        for(int i = 0; i < rectNumbers.size(); i ++){
        //            for(int j = 0; j < rectNumbers.get(i).size(); j++)
        //            canvas.drawRect(rectNumbers.get(i).get(j), paint);
        //        }
        //
        //        paint.setColor(Color.BLACK);
        //        for(int i = 0; i < rectSideNumbersH.size(); i ++){
        //            for(int j = 0; j < rectSideNumbersH.get(i).size(); j++)
        //                canvas.drawRect(rectSideNumbersH.get(i).get(j), paint);
        //        }
        //
        //        paint.setColor(Color.GRAY);
        //        for(int i = 0; i < rectSideNumbersV.size(); i ++){
        //            for(int j = 0; j < rectSideNumbersV.get(i).size(); j++)
        //                canvas.drawRect(rectSideNumbersV.get(i).get(j), paint);
        //        }
        //
        //        paint.setColor(Color.WHITE);
        //        for(int i = 0; i < rectCrossNumbers.size(); i ++){
        //            for(int j = 0; j < rectCrossNumbers.get(i).size(); j++)
        //                canvas.drawRect(rectCrossNumbers.get(i).get(j), paint);
        //        }

        for(int i = 0; i < arrNumberOnField.size(); i++) {
            int ni = arrNumberOnField.get(i).getI();
            int nj = arrNumberOnField.get(i).getJ();
            canvas.drawText(arrNumberOnField.get(i).getCount(),
                    rectNumbers.get(ni).get(nj).left,
                    rectNumbers.get(ni).get(nj).bottom, arrNumberOnField.get(i).getPaint());
        }

        for(int i = 0; i < arrNumberOnFieldH.size(); i++) {
            canvas.save();
            int ni = arrNumberOnFieldH.get(i).getI();
            int nj = arrNumberOnFieldH.get(i).getJ();
            canvas.rotate(-90f, rectSideNumbersH.get(ni).get(nj).right, rectSideNumbersH.get(ni).get(nj).bottom);
            canvas.drawText(arrNumberOnFieldH.get(i).getCount(),
                    rectSideNumbersH.get(ni).get(nj).right,
                    rectSideNumbersH.get(ni).get(nj).bottom, arrNumberOnFieldH.get(i).getPaint());
            canvas.restore();
        }

        for(int i = 0; i < arrNumberOnFieldV.size(); i++) {
            int ni = arrNumberOnFieldV.get(i).getI();
            int nj = arrNumberOnFieldV.get(i).getJ();
            canvas.drawText(arrNumberOnFieldV.get(i).getCount(),
                    rectSideNumbersV.get(ni).get(nj).left,
                    rectSideNumbersV.get(ni).get(nj).bottom, arrNumberOnFieldV.get(i).getPaint());
        }

        for(int i = 0; i < arrNumberOnFieldCross.size(); i++) {
            int ni = arrNumberOnFieldCross.get(i).getI();
            int nj = arrNumberOnFieldCross.get(i).getJ();
            canvas.drawText(arrNumberOnFieldCross.get(i).getCount(),
                    rectCrossNumbers.get(ni).get(nj).left,
                    rectCrossNumbers.get(ni).get(nj).bottom, arrNumberOnFieldCross.get(i).getPaint());
        }
    }

    @Override
    public void receiveTouch(MotionEvent event){

        int key = event.getAction();

        if(selected != -1)
            switch (key){
                case MotionEvent.ACTION_DOWN:
                    float x = event.getX();
                    float y = event.getY();
                    //RectNumbers
                {
                    int i = 0, j = 0;
                    boolean isFind = false;

                    while (!isFind && i < rectNumbers.size() && j < rectNumbers.get(i).size()) {
                        if (rectNumbers.get(i).get(j).contains((int) x, (int) y)) {
                            boolean findValue = false;
                            int ni = 0;
                            while (!findValue && ni < arrNumberOnField.size()) {
                                if (arrNumberOnField.get(ni).getI() == i && arrNumberOnField.get(ni).getJ() == j) {

                                    if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                        arrNumberOnField.get(ni).add(Constants.CHIP_VALUE[selected]);
                                        totalOnField += Constants.CHIP_VALUE[selected];
                                    }
                                    else if(total - totalOnField > 0){
                                        arrNumberOnField.get(ni).add(total - totalOnField);
                                        totalOnField += total - totalOnField;
                                    }
                                    findValue = true;
                                }
                                ni++;
                            }
                            if (!findValue) {
                                if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                    arrNumberOnField.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectNumbers.get(i).get(j).height() / 4, rectNumbers.get(i).get(j).width()));
                                    totalOnField += Constants.CHIP_VALUE[selected];
                                }
                                else if(total - totalOnField > 0){
                                    arrNumberOnField.add(new NumberOnField(i, j, total - totalOnField, rectNumbers.get(i).get(j).height() / 4, rectNumbers.get(i).get(j).width()));
                                    totalOnField += total - totalOnField;
                                }
                            }
                            isFind = true;
                        }
                        j++;
                        if (j >= rectNumbers.get(i).size()) {
                            i++;
                            j = 0;
                        }
                    }
                }
                //RectNumbersH
                {
                    {
                        int i = 0, j = 0;
                        boolean isFind = false;

                        while (!isFind && i < rectSideNumbersH.size() && j < rectSideNumbersH.get(i).size()) {
                            if (rectSideNumbersH.get(i).get(j).contains((int) x, (int) y)) {
                                boolean findValue = false;
                                int ni = 0;
                                while (!findValue && ni < arrNumberOnFieldH.size()) {
                                    if (arrNumberOnFieldH.get(ni).getI() == i && arrNumberOnFieldH.get(ni).getJ() == j) {

                                        if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                            arrNumberOnFieldH.get(ni).add(Constants.CHIP_VALUE[selected]);
                                            totalOnField += Constants.CHIP_VALUE[selected];
                                        }
                                        else if(total - totalOnField > 0){
                                            arrNumberOnFieldH.get(ni).add(total - totalOnField);
                                            totalOnField += total - totalOnField;
                                        }

                                        findValue = true;
                                    }
                                    ni++;
                                }
                                if (!findValue) {
                                    //arrNumberOnFieldH.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectSideNumbersH.get(i).get(j).width(), rectSideNumbersH.get(i).get(j).height()));

                                    if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                        arrNumberOnFieldH.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectSideNumbersH.get(i).get(j).width(), rectSideNumbersH.get(i).get(j).height()));
                                        totalOnField += Constants.CHIP_VALUE[selected];
                                    }
                                    else if(total - totalOnField > 0){
                                        arrNumberOnFieldH.add(new NumberOnField(i, j, total - totalOnField, rectSideNumbersH.get(i).get(j).width(), rectSideNumbersH.get(i).get(j).height()));
                                        totalOnField += total - totalOnField;
                                    }
                                }
                                isFind = true;
                            }
                            j++;
                            if (j >= rectSideNumbersH.get(i).size()) {
                                i++;
                                j = 0;
                            }
                        }
                    }
                }

                //RectNumbersV
                {
                    int i = 0, j = 0;
                    boolean isFind = false;

                    while (!isFind && i < rectSideNumbersV.size() && j < rectSideNumbersV.get(i).size()) {
                        if (rectSideNumbersV.get(i).get(j).contains((int) x, (int) y)) {
                            boolean findValue = false;
                            int ni = 0;
                            while (!findValue && ni < arrNumberOnFieldV.size()) {
                                if (arrNumberOnFieldV.get(ni).getI() == i && arrNumberOnFieldV.get(ni).getJ() == j) {

                                    if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                        arrNumberOnFieldV.get(ni).add(Constants.CHIP_VALUE[selected]);
                                        totalOnField += Constants.CHIP_VALUE[selected];
                                    }
                                    else if(total - totalOnField > 0){
                                        arrNumberOnFieldV.get(ni).add(total - totalOnField);
                                        totalOnField += total - totalOnField;
                                    }

                                    findValue = true;
                                }
                                ni++;
                            }
                            if (!findValue) {
                                //arrNumberOnFieldH.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectSideNumbersH.get(i).get(j).width(), rectSideNumbersH.get(i).get(j).height()));

                                if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                    arrNumberOnFieldV.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectSideNumbersV.get(i).get(j).height(), rectSideNumbersV.get(i).get(j).width()));
                                    totalOnField += Constants.CHIP_VALUE[selected];
                                }
                                else if(total - totalOnField > 0){
                                    arrNumberOnFieldV.add(new NumberOnField(i, j, total - totalOnField, rectSideNumbersV.get(i).get(j).height(), rectSideNumbersV.get(i).get(j).width()));
                                    totalOnField += total - totalOnField;
                                }
                            }
                            isFind = true;
                        }
                        j++;
                        if (j >= rectSideNumbersV.get(i).size()) {
                            i++;
                            j = 0;
                        }
                    }
                }

                //RectNumberCross
                {
                    int i = 0, j = 0;
                    boolean isFind = false;

                    while (!isFind && i < rectCrossNumbers.size() && j < rectCrossNumbers.get(i).size()) {
                        if (rectCrossNumbers.get(i).get(j).contains((int) x, (int) y)) {
                            boolean findValue = false;
                            int ni = 0;
                            while (!findValue && ni < arrNumberOnFieldCross.size()) {
                                if (arrNumberOnFieldCross.get(ni).getI() == i && arrNumberOnFieldCross.get(ni).getJ() == j) {

                                    if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                        arrNumberOnFieldCross.get(ni).add(Constants.CHIP_VALUE[selected]);
                                        totalOnField += Constants.CHIP_VALUE[selected];
                                    }
                                    else if(total - totalOnField > 0){
                                        arrNumberOnFieldCross.get(ni).add(total - totalOnField);
                                        totalOnField += total - totalOnField;
                                    }

                                    findValue = true;
                                }
                                ni++;
                            }
                            if (!findValue) {
                                //arrNumberOnFieldH.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectSideNumbersH.get(i).get(j).width(), rectSideNumbersH.get(i).get(j).height()));

                                if(totalOnField + Constants.CHIP_VALUE[selected] <= total) {
                                    arrNumberOnFieldCross.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectCrossNumbers.get(i).get(j).height(), rectCrossNumbers.get(i).get(j).width()));
                                    totalOnField += Constants.CHIP_VALUE[selected];
                                }
                                else if(total - totalOnField > 0){
                                    arrNumberOnFieldCross.add(new NumberOnField(i, j, total - totalOnField, rectCrossNumbers.get(i).get(j).height(), rectCrossNumbers.get(i).get(j).width()));
                                    totalOnField += total - totalOnField;
                                }
                            }
                            isFind = true;
                        }
                        j++;
                        if (j >= rectCrossNumbers.get(i).size()) {
                            i++;
                            j = 0;
                        }
                    }
                }

                break;
            }
    }
}

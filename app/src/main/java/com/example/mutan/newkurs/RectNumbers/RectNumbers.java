package com.example.mutan.newkurs.RectNumbers;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.mutan.newkurs.MyConstants.Constants;
import com.example.mutan.newkurs.Object.LogNumberOnField;
import com.example.mutan.newkurs.Object.NumberOnField;

import java.util.ArrayList;

public class RectNumbers implements RectNumber{
    private ArrayList<ArrayList<Rect>> rectNumbers;
    private ArrayList<NumberOnField> arrNumberOnField;

    public RectNumbers(){
        arrNumberOnField = new ArrayList<>();
        rectNumbers = new ArrayList<>();


    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        for(int i = 0; i < arrNumberOnField.size(); i++) {
            int ni = arrNumberOnField.get(i).getI();
            int nj = arrNumberOnField.get(i).getJ();
            canvas.drawText(arrNumberOnField.get(i).getCount(),
                    rectNumbers.get(ni).get(nj).left,
                    rectNumbers.get(ni).get(nj).bottom, arrNumberOnField.get(i).getPaint());
        }
    }

//    public ArrayList<Integer> receiveTouch(MotionEvent event, int selected) {
//        int key = event.getAction();
//
//        ArrayList<Integer> arrayList = new ArrayList<>();
//
//        if(selected != -1)
//            switch (key) {
//                case MotionEvent.ACTION_DOWN:
//                    float x = event.getX();
//                    float y = event.getY();
//                    //RectNumbers
//                {
//                    int i = 0, j = 0;
//                    boolean isFind = false;
//
//                    while (!isFind && i < rectNumbers.size() && j < rectNumbers.get(i).size()) {
//                        if (rectNumbers.get(i).get(j).contains((int) x, (int) y)) {
//                            boolean findValue = false;
//                            int ni = 0;
//                            while (!findValue && ni < arrNumberOnField.size()) {
//                                if (arrNumberOnField.get(ni).getI() == i && arrNumberOnField.get(ni).getJ() == j) {
//
//                                    if (totalOnField + Constants.CHIP_VALUE[selected] <= total) {
//                                        arrNumberOnField.get(ni).add(Constants.CHIP_VALUE[selected]);
//                                        totalOnField += Constants.CHIP_VALUE[selected];
//                                    } else if (total - totalOnField > 0) {
//                                        arrNumberOnField.get(ni).add(total - totalOnField);
//                                        totalOnField += total - totalOnField;
//                                    }
//                                    findValue = true;
//                                }
//                                ni++;
//                            }
//                            if (!findValue) {
//                                if (totalOnField + Constants.CHIP_VALUE[selected] <= total) {
//                                    arrNumberOnField.add(new NumberOnField(i, j, Constants.CHIP_VALUE[selected], rectNumbers.get(i).get(j).height() / 4, rectNumbers.get(i).get(j).width()));
//                                    totalOnField += Constants.CHIP_VALUE[selected];
//                                } else if (total - totalOnField > 0) {
//                                    arrNumberOnField.add(new NumberOnField(i, j, total - totalOnField, rectNumbers.get(i).get(j).height() / 4, rectNumbers.get(i).get(j).width()));
//                                    totalOnField += total - totalOnField;
//                                }
//                            }
//                            isFind = true;
//                        }
//                        j++;
//                        if (j >= rectNumbers.get(i).size()) {
//                            i++;
//                            j = 0;
//                        }
//                    }
//                }
//            }
//
//    }

    @Override
    public int revertAction() {
        int totalOnField = -1;
        if(NumberOnField.logNumberOnField.size()>=1) {

            int index = -1;
            int last = NumberOnField.logNumberOnField.size() - 1;

            if (arrNumberOnField.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField()) != -1) {
                index = arrNumberOnField.indexOf(NumberOnField.logNumberOnField.get(last).getNumberOnField());

                if (NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.CREATED) {
                    totalOnField -= arrNumberOnField.get(index).getCountInt();

                    arrNumberOnField.remove(index);
                }

                if (NumberOnField.logNumberOnField.get(last).getType() == LogNumberOnField.TYPE.ADD) {
                    totalOnField -= arrNumberOnField.get(index).getCountInt() - NumberOnField.logNumberOnField.get(last).getCount();

                    arrNumberOnField.get(index).setCount(NumberOnField.logNumberOnField.get(last).getCount());
                }

                NumberOnField.logNumberOnField.remove(last);

                //totalOnField -= last;
            }
        }

        return totalOnField;

    }

    @Override
    public void clear() {
        arrNumberOnField.clear();
    }

    @Override
    public int sum() {
        int sum = 0;

        for(int i = 0; i < arrNumberOnField.size(); i++){
            sum += arrNumberOnField.get(i).getCountInt();
        }

        return sum;
    }

    @Override
    public ArrayList<Integer>  getAdd(String num) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int count  = 0;

        if(num  != ""){

            int number = Integer.parseInt(num);

            for(int i = 0; i < arrNumberOnField.size(); i++){
                if(Constants.ROULETTE_FIELD[arrNumberOnField.get(i).getJ() + 12 * arrNumberOnField.get(i).getI()] == number){
                    count = count + arrNumberOnField.get(i).getCountInt() * 35 + arrNumberOnField.get(i).getCountInt();
                }
            }

            arrayList.add(count);
            arrayList.add(this.sum());

            this.clear();
        }

        return arrayList;
    }
}

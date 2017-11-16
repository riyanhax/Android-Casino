package com.example.mutan.newkurs.MyConstants;

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static final float TOP_VERTICAL = 0.10f;
    public static final float MIDDLE_VERTICAL = 0.80f;
    public static final float BOTTOM_VERTICAL = 0.10f;

    public static final float MIDDLE_RIGHT_HORIZONTAL = 0.08f;
    public static final float MIDDLE_LEFT_HORIZONTAL = 0.92f;

    public static final float BOTTOM_RIGHT1_HORIZONTAL = MIDDLE_RIGHT_HORIZONTAL;
    public static final float BOTTOM_RIGHT2_HORIZONTAL = 0.12f;

    public static final float BOTTOM_MIDDLE_HORIZONTAL = 0.68f;
    public static final float BOTTOM_LEFT1_HORIZONTAL = 0.06f;
    public static final float BOTTOM_LEFT2_HORIZONTAL = 0.06f;

    public static final int[] CHIP_VALUE = {1, 2, 5, 10, 25, 50, 100, 250, 1000, 2000, 5000};

    public static final int ROULETTE_VALUE_COUNT = 37;
    public static final int[] ROULETTE_VALUE = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};

    public static final int CHIP_COUNT_IN_VIEW = 6;
    public static final float CHIP_HEIGHT = 1.00f/CHIP_COUNT_IN_VIEW;

    public static final int CHIP_COUNT = 12; //0-12 = 13
    public static final int CHIP_StepHorizontalAndVertical = 119;
    public static final int CHIP_X = 48;
    public static final int CHIP_Y = 156;
    public static final int CHIP_X1 = 151;
    public static final int CHIP_Y1 = 259;

    public static final float CHIP_IN_FIELD_START_HORIZONTAL = 0.1033f;
    public static final float CHIP_IN_FIELD_START_VERTICAL = 0.0556f;
    public static final float CHIP_IN_FIELD_STEP_HORIZONTAL = 0.05922f;
    public static final float CHIP_IN_FIELD_STEP_VERTICAL   = 0.18280f;

    public static final float CHIP_IN_FIELD_LINE_HORIZONTAL = 0.0081f;
    public static final float CHIP_IN_FIELD_LINE_VERTICAL = 0.01f;

    public static final int CHIP_IN_FIELD_ROW = 3; // 0..2 = 3
    public static final int CHIP_IN_FIELD_COLUMN = 12; // 0..11 = 12
}

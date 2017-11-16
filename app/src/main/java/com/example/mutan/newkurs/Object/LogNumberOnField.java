package com.example.mutan.newkurs.Object;

public class LogNumberOnField {
    public enum TYPE {CREATED, ADD}

    private TYPE type;
    private NumberOnField numberOnField;
    private int count;

    public TYPE getType() {
        return type;
    }

    public NumberOnField getNumberOnField() {
        return numberOnField;
    }

    public int getCount() {
        return count;
    }

    public LogNumberOnField(TYPE type, NumberOnField numberOnField, int count) {
        this.type = type;
        this.numberOnField = numberOnField;
        this.count = count;
    }


}

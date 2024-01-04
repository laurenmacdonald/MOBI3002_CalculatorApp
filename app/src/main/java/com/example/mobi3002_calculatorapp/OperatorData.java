package com.example.mobi3002_calculatorapp;

/**
 * Class to be used to get the operator index and operator character from the appended String of
 * user input data from the calculator app.
 */
public class OperatorData {

    private final int operatorIndex;
    private final char operatorChar;

    /**
     *
     * @param operatorIndex index of the operator in a String
     * @param operatorChar character of the operator
     */
    public OperatorData(int operatorIndex, char operatorChar){
        this.operatorIndex = operatorIndex;
        this.operatorChar = operatorChar;
    }

    /**
     * @return operatorIndex
     */
    public int getOperatorIndex(){
        return operatorIndex;
    }

    /**
     * @return operatorChar
     */
    public char getOperatorChar(){
        return operatorChar;
    }
}

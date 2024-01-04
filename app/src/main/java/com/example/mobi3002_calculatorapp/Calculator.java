package com.example.mobi3002_calculatorapp;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

/**
 * Class to handle simple calculator functions such as:
 * <br>
 * - building calculation from user button clicks
 * <br>
 * - addition, subtraction, multiply, divide calculations
 * <br>
 * - clear calculation, deletion of part of a calculation
 * <br>
 * - take decimal input
 * <br>
 * - displaying calculation result
 * <br>
 * Error handling is used where appropriate (i.e., divide by 0).
 */
public class Calculator {
    /**
     * @param view   takes a view to use a MaterialButton to get data to use in calculator
     * @param result takes a TextView object to update the TextView result used to display
     *               user input and results to the user
     *               <br>
     *               String buttonData gets the text from the button pressed (i.e., 1, +, 4, -, etc.).
     *               String dataToCalculate will be an appending of buttonData to present the calculation as it
     *               is created by the user.
     *               <br>
     *               If the 'AC' button clicked, will set the result TextView to empty. If 'del'
     *               button clicked, will remove the last value input in the dataToCalculate string.
     *               If the user clicks any other button than '=', will concatenate the button data
     *               to the dataToCalculate.
     *               <br>
     *               The setResult() method is called to get the final result of the calculation.
     */
    public void setDataToCalculate(View view, TextView result) {
        MaterialButton button = (MaterialButton) view;
        String buttonData = button.getText().toString();
        String dataToCalculate = result.getText().toString();

        if (buttonData.equals("AC")) {
            result.setText("");
            return;
        }
        if (buttonData.equals("del")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else if (!buttonData.equals("=")) {
            dataToCalculate = dataToCalculate + buttonData;
        }
        result.setText(dataToCalculate);

        if (buttonData.equals("=")) {
            result.setText(setResult(dataToCalculate));
        }
    }

    /**
     * @param dataToCalculate String of data from user input on button press within the app,
     *                        to be parsed and calculated.
     * @return String result
     *                        <br>
     *                        Method to be used with setDataToCalculate method. Takes dataToCalculate,
     *                        runs the calculate method, casts the result from double to String
     *                        and returns the String as either the finalResult, or error if the
     *                        exception is caught.
     */
    public String setResult(String dataToCalculate) {
        String finalResultStr;
        try {
            double finalResult = calculate(dataToCalculate);
            finalResultStr = String.valueOf(finalResult);
            return finalResultStr;
        } catch (ArithmeticException ae) {
            finalResultStr = "Err";
            return finalResultStr;
        }
    }


    /**
     * @param dataToCalculate String of data from user input on button press within the app,
     *                        to be parsed and calculated.
     * @return double result
     * <br>
     * Using an operator object of OperatorData, gets the operator from the dataToCalculate String
     * via the getOperator method (returns object that holds the character value of the operator
     * (+, -, /, x) and the index in the String of the operator).
     * <br>
     * Uses the substring method to get the first and second numbers of the calculation as Strings,
     * with the index of the operator as defining where to separate the numbers. The number1Str and
     * number2Str are parsed to double to be calculated.
     * <br>
     * A switch statement that uses the operator to define the operation case (+, -, *, /) handles
     * the calculation. Result is returned as a double value.
     * <br>
     * If a number is divided by zero, an ArithmeticException is thrown (to be caught and handled
     * in the setResult method.
     */
    public static double calculate(String dataToCalculate) {
        OperatorData operator = getOperator(dataToCalculate);
        String number1Str = dataToCalculate.substring(0, operator.getOperatorIndex());
        String number2Str = dataToCalculate.substring(operator.getOperatorIndex() + 1);
        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);
        double result = 0.0;

        // If operator found, extract the numbers and perform the operation
        if (operator.getOperatorIndex() != -1) {
            switch (operator.getOperatorChar()) {
                case '+':
                    result = number1 + number2;
                    break;
                case '-':
                    result = number1 - number2;
                    break;
                case 'x':
                    result = number1 * number2;
                    break;
                case '/':
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    break;
                default:
                    result = number1;
            }
        }
        return result;
    }

    /**
     * @param dataToCalculate String of data from user input on button press within the app,
     *                        to be parsed and calculated.
     * @return OperatorData
     * <br>
     * Method to iterate through a dataToCalculate String to get the operator (+, -, /, x) and the
     * index position of the operator in the String. Returns an OperatorData object, constructed
     * with the operator and operatorIndex values.
     */
    public static OperatorData getOperator(String dataToCalculate) {
        int operatorIndex = -1;
        char operator = ' ';

        for (int i = 0; i < dataToCalculate.length(); i++) {
            char currentChar = dataToCalculate.charAt(i);
            if (currentChar == '+' || currentChar == '-' || currentChar == 'x' || currentChar == '/') {
                operator = currentChar;
                operatorIndex = i;
                break;
            }
        }
        return new OperatorData(operatorIndex, operator);
    }
}


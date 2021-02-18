package gui;

import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private TextField text;
    @FXML
    private Text text2;

    private String operator = "";
    private Double n1;
    private Double n2;
    boolean result = false;

    @FXML
    private void processNumpad(ActionEvent event) {
        /*
         * If the result was printed, means that
         * the screen can be cleared after a new number is typed.
         * After that, boolean is defined back to false.
         */
        if (result) {
            text.setText("");
            result = false;
        }
        String value = ((Button)event.getSource()).getText();
        text.setText(text.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        //Assigning the String value of the operation button pressed.
        String value = ((Button)event.getSource()).getText();

        /*
         * Checking if the button clicked was "Clean"
         * if it was, then the operator is set to its
         * default value, which is empty, then both
         * TextField and Text are set to empty.
         */
        if (value.equals("Clean")) {
            operator = "";
            text.setText("");
            text2.setText("");
            return;
        }

        /*
         * Checking if the value is not equal to '='.
         * If it is, jumps to else clause where the calculation is done.
         * After that, checks if the operator is not empty.
         * If is not, attribution of its value is unnecessary. Returns;
         * After that, checks if the TextField is empty.
         * This occurs when user presses any operation button except "=",
         * and the TextField is empty.
         * If it is, returns;
         */
        if (!value.equals("=")) {
            if (!operator.isEmpty() || text.getText().isEmpty()) {
                return;
            }
            operator = value;
            n1 = Double.parseDouble(text.getText());
            String x = text.getText();
            text2.setText(x + " " + value);
            text.setText("");
        } else {
            /*
             * Checking if the operator is empty.
             * This occurs when the user press "=" button without informing the operation;
             * Checks if the TextField is empty.
             * This occurs when the user specifies the operation but press "=" with empty TextField;
             * If one or other condition is true, returns;
             */
            if (operator.isEmpty() || text.getText().isEmpty()) {
                return;
            }
            n2 = Double.parseDouble(text.getText());
            BigDecimal bd = BigDecimal.valueOf(calculator(n1, n2, operator));
            BigInteger bi;
            /*
             * Checking if its possible to convert BigDecimal(allows double numbers to be bigger,
             * into BigInteger, because it is unnecessary the Decimal value if true.
             * If the exception is caught, means that is not possible to convert without losing data,
             * that means that the numeric value must have floating points, hence the necessity to keep it as BigInteger.
             * If the exception is not caught, then it is possible and the conversion is made;
             */
            try {
                bi = bd.toBigIntegerExact();
            } catch (ArithmeticException e) {
                text.setText(bd.toPlainString());
                operator = "";
                text2.setText("");
                result = true;
                return;
            }

            text.setText(bi.toString());
            operator = "";
            text2.setText("");
            //Boolean set to true, attesting that the result of the operation was printed.
            result = true;
        }
    }

    private Double calculator(Double number1, Double number2, String operator) {
        switch(operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "x":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                    break;
                }
                return number1 / number2;
        }
        return 0.0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldDouble(text);
    }
}

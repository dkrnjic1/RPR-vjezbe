package ba.unsa.etf.rpr.lv6;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CaclController {
    @FXML private Label lblResult;
    private String num1 = "";

    private String currentNumber = "";

    private String calculationType;

    @FXML
    void addAction(ActionEvent event) {
        calculationSetup("+");
    }

    @FXML
    void minusAction(ActionEvent event) {
        calculationSetup("-");
    }

    @FXML
    void divideAction(ActionEvent event) {
        calculationSetup("/");
    }

    @FXML
    void multiplicationAction(ActionEvent event) {
        calculationSetup("x");
    }
    @FXML
    void dotAction(ActionEvent event) {
        currentNumber = currentNumber + ".";
        lblResult.setText(currentNumber);
    }
    @FXML
    public void moduleAction(ActionEvent actionEvent) {
        calculationSetup("%");
    }

    public void calculationSetup(String calculationType){
        this.calculationType = calculationType;
        num1 = currentNumber;
        currentNumber = "";
        lblResult.setText(num1);
    }

    @FXML
    void calculate(ActionEvent event) {
        double firstNumber = Double.parseDouble(num1);
        double secondNumber = Double.parseDouble(currentNumber);

        switch (calculationType) {
            case "+" -> {
                double calculatedNumber = firstNumber + secondNumber;
                lblResult.setText(String.valueOf(calculatedNumber));
            }
            case "-" -> {
                double calculatedNumber = firstNumber - secondNumber;
                lblResult.setText(String.valueOf(calculatedNumber));
            }
            case "/" -> {
                double calculatedNumber = firstNumber / secondNumber;
                lblResult.setText(String.valueOf(calculatedNumber));
            }
            case "x" -> {
                double calculatedNumber = firstNumber * secondNumber;
                lblResult.setText(String.valueOf(calculatedNumber));
            }
            case "%" -> {
                double calculatedNumber = firstNumber % secondNumber;
                lblResult.setText(String.valueOf(calculatedNumber));
            }
        }
    }

    @FXML
    void button0Clicked(ActionEvent event) {
        if(!currentNumber.isEmpty()){
            addNumber("0");
        }
    }

    @FXML
    void button1Clicked(ActionEvent event) {
        addNumber("1");
    }

    @FXML
    void button2Clicked(ActionEvent event) {
        addNumber("2");
    }

    @FXML
    void button3Clicked(ActionEvent event) {
        addNumber("3");
    }

    @FXML
    void button4Clicked(ActionEvent event) {
        addNumber("4");
    }

    @FXML
    void button5Clicked(ActionEvent event) {
        addNumber("5");
    }

    @FXML
    void button6Clicked(ActionEvent event) {
        addNumber("6");
    }

    @FXML
    void button7Clicked(ActionEvent event) {
        addNumber("7");
    }

    @FXML
    void button8Clicked(ActionEvent event) {
        addNumber("8");
    }

    @FXML
    void button9Clicked(ActionEvent event) {
        addNumber("9");
    }

    public void updateTextField(){
        lblResult.setText(currentNumber);
    }

    public void addNumber(String number){
        currentNumber += number;
        updateTextField();
    }

}

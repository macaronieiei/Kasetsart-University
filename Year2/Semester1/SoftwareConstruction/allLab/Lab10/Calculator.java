package Lab10;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.awt.*;

public class Calculator extends JFrame implements ActionListener {
    JButton[] buttons;
    String[] cal = {"%","C","CE","/","7","8","9","x","4","5","6","-","1","2","3","+","+/-","0",".","="};
    JPanel buttonPanel;
    JPanel numPanel;
    JTextField displayTextField;

    Double num1, num2, sum, temp1, temp2  ;
    String operator = "";
    DecimalFormat df = new DecimalFormat("#.##");
    boolean startNewNumber = true;

    public Calculator(){
        super("Calculator");

        getContentPane().add(setBottonPanel());
        getContentPane().add(setNumPanel(),BorderLayout.NORTH);
        setSize(650,450);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private JPanel setBottonPanel(){
        if(buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(5,4,5,5));
            buttons = new JButton[20];
            for(int i = 0; i < cal.length; i++){
                buttons[i] = new JButton(cal[i]);
                buttonPanel.add(buttons[i]);
                buttons[i].setFont(new Font("JetBrains Mono", Font.PLAIN, 20));
                buttons[i].addActionListener(this);
            }
        }
        return buttonPanel;
    }

    private JPanel setNumPanel(){
        if(numPanel == null){
            numPanel = new JPanel();
            displayTextField = new JTextField();
            displayTextField.setHorizontalAlignment(JTextField.RIGHT);
            displayTextField.setFont(new Font("JetBrains Mono", Font.PLAIN, 40));
            numPanel.setLayout(new BorderLayout());
            numPanel.add(displayTextField);
        }
        return numPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        
        // number
        if (buttonText.matches("[0-9]")) { 
            if (startNewNumber) {
                displayTextField.setText(buttonText);
                startNewNumber = false;
            } else {
                displayTextField.setText(displayTextField.getText() + buttonText);
            }
            
        }
        else if (buttonText.equals("x") || buttonText.equals("/") || buttonText.equals("-") || buttonText.equals("+")) {
            if (!startNewNumber) {
                if (num1 == null) {
                    num1 = Double.parseDouble(displayTextField.getText());
                } else {
                    num2 = Double.parseDouble(displayTextField.getText());
                    Calculate();
                    displayTextField.setText(df.format(sum));
                }
            }
            operator = buttonText;
            startNewNumber = true;
        }

        //  sum
        else if (buttonText.equals("=")) {
            if (!startNewNumber) {
                num2 = Double.parseDouble(displayTextField.getText());
                Calculate();
                displayTextField.setText(df.format(sum));
                operator = "";
                startNewNumber = true;
            }
        }

        //  delete one
        else if (buttonText.equals("C")) {      
            String nowNumber = displayTextField.getText();
            nowNumber = nowNumber.substring(0, nowNumber.length() - 1);
            displayTextField.setText(nowNumber);
        }
        
        // delete all
        else if (buttonText.equals("CE")) {     
            num1 = null;
            num2 = null;
            operator = "";
            displayTextField.setText("");

        }

        // make percent
        else if (buttonText.equals("%")) {     
            String nowNumber = displayTextField.getText();
            double now = Double.parseDouble(nowNumber);
            displayTextField.setText(String.valueOf((df.format(now))));
            if (operator.isEmpty()) {
                num1 = now;   
            } else {
                num2 = now;
            }
        }

        // input point
        else if (buttonText.equals(".")) {      // make double
            if (!displayTextField.getText().contains(".")) {
                displayTextField.setText(displayTextField.getText() + buttonText);
            }
        }

        // input +/-
        else if (buttonText.equals("+/-")) {
            String nowNumber = displayTextField.getText();
            double now = Double.parseDouble(nowNumber);
            if (operator.isEmpty()) {
                num1 = -now;   
                displayTextField.setText(df.format(num1));  
            } else {
                num2 = -now;
                displayTextField.setText(df.format(num2));
            }
        }
    }
    
    public void Calculate() {
        if (num2 == null) {
            num2 = num1;
        }
        switch (operator) {
            case "+":
                sum = num1 + num2;
                break;
            case "-":
                sum = num1 - num2;
                break;
            case "x":
                sum = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    sum = num1 / num2;
                } else {
                    displayTextField.setText("Error: Division by zero");
                    return;
                }
                break;
        }
        num1 = sum;
        num2 = null;
        operator = "";
    }
}

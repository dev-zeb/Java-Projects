package calculator_app;

import java.util.*;

public class Calculator extends javax.swing.JFrame {

    static boolean isOn = false;
    static boolean isSignAdded = false;
    static boolean isDotFound = false;
    static boolean isCalculated = false;
    static double result;
    static boolean continueEvaluation = true;
    
    public Calculator() {
        initComponents();
    }

    // The following method is used to change the states of the buttons based on 'On' & 'Off' buttons
    public void changeState(boolean state){
        buttonZero.setEnabled(state);
        buttonOne.setEnabled(state);
        buttonTwo.setEnabled(state);
        buttonThree.setEnabled(state);
        buttonFour.setEnabled(state);
        buttonFive.setEnabled(state);
        buttonSix.setEnabled(state);
        buttonSeven.setEnabled(state);
        buttonEight.setEnabled(state);
        buttonNine.setEnabled(state);
        
        buttonPlus.setEnabled(state);
        buttonMinus.setEnabled(state);
        buttonDivision.setEnabled(state);
        buttonMultiplication.setEnabled(state);
        buttonPower.setEnabled(state);
        buttonDot.setEnabled(state);
        
        buttonOpenParentheses.setEnabled(state);
        buttonCloseParentheses.setEnabled(state);
        buttonBackspace.setEnabled(state);
        buttonClearAll.setEnabled(state);
        buttonEqual.setEnabled(state);
        
        if(isOn){
            buttonOn.setEnabled(false);
            buttonOff.setEnabled(true);
        }
        else{
            buttonOff.setEnabled(false);
            buttonOn.setEnabled(true);
        }
    }
    
    
    // The following method checks if 'op1' has higher precedence than 'op2' or not
    public static boolean hasPrecedence(char op1, char op2){
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    // The following method is used to evaluate to numbers
    public double applyOp(char op, double b, double a)
    {
        switch (op){
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (a == 0)
                    continueEvaluation = false;
                else
                    return a / b;
            case '^':
                return Math.pow(a, b); 
        }
        return 0;
    }
    
    public String evaluateExpression(String expression){
        char[] tokens = expression.toCharArray();
 
        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();
        if(isCalculated){
            values.push(result);
        }
        
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++) {
            if(!continueEvaluation){
                return "Math Error!";
            }
            
            // Skip whitespaces
            if (tokens[i] == ' ')
                continue;
            
            // If the ith char is a digit
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuffer sbuf = new StringBuffer();
                
                // Check if the number has more than 1 digits
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')){
                    if(tokens[i] == '.'){
                        if(isDotFound){
                            return "Math Error!";
                        }
                        else{
                            isDotFound = true;
                        }
                    }
                    sbuf.append(tokens[i++]);
                }
                
                isDotFound = false;
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }
            // If the ith token is '(', then push it to 'ops'
            else if (tokens[i] == '('){
                ops.push(tokens[i]);
            }
            // If the ith token is an ')', then solve the entire brace
            else if (tokens[i] == ')') {
                if(values.empty())
                    return "Math Error!";
                while(!values.empty() && ops.peek() != '('){
                    try{
                        char op = ops.pop();
                        double num1 = values.pop();
                        double num2 = values.pop();
                        values.push(applyOp(op, num1, num2));  
                        
                    }
                    catch(Exception e){
                        return "Math Error!";
                    }
                }                
                ops.pop();
            }
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '^') {
                // While top of 'ops' has same or greater precedence to current token, which is an operator.
                // Apply operator on top of 'ops' to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
 
        // Entire expression has been parsed at this point,
        // Apply remaining ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        // Update the value of the static property 'result' & 'isCalculated'
        result = values.peek();
        isCalculated = true;
        
        // Top of 'values' contains result, return it
        return Double.toString(values.pop());

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        displayField = new javax.swing.JTextField();
        buttonBackspace = new javax.swing.JButton();
        buttonSeven = new javax.swing.JButton();
        buttonEight = new javax.swing.JButton();
        buttonMinus = new javax.swing.JButton();
        buttonNine = new javax.swing.JButton();
        buttonFour = new javax.swing.JButton();
        buttonFive = new javax.swing.JButton();
        buttonMultiplication = new javax.swing.JButton();
        buttonSix = new javax.swing.JButton();
        buttonThree = new javax.swing.JButton();
        buttonTwo = new javax.swing.JButton();
        buttonDivision = new javax.swing.JButton();
        buttonOne = new javax.swing.JButton();
        buttonEqual = new javax.swing.JButton();
        buttonZero = new javax.swing.JButton();
        buttonDot = new javax.swing.JButton();
        buttonClearAll = new javax.swing.JButton();
        buttonPlus = new javax.swing.JButton();
        buttonPower = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        buttonOpenParentheses = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        buttonCloseParentheses = new javax.swing.JButton();
        buttonOn = new javax.swing.JButton();
        buttonOff = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setBackground(new java.awt.Color(204, 204, 204));
        setLocation(new java.awt.Point(500, 200));
        setPreferredSize(new java.awt.Dimension(375, 562));
        setType(java.awt.Window.Type.UTILITY);

        displayField.setEditable(false);
        displayField.setBackground(new java.awt.Color(204, 204, 255));
        displayField.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        displayField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        displayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayFieldActionPerformed(evt);
            }
        });

        buttonBackspace.setBackground(new java.awt.Color(255, 102, 102));
        buttonBackspace.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        buttonBackspace.setForeground(new java.awt.Color(255, 255, 0));
        buttonBackspace.setText("DEL");
        buttonBackspace.setMaximumSize(new java.awt.Dimension(59, 25));
        buttonBackspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackspaceActionPerformed(evt);
            }
        });

        buttonSeven.setBackground(new java.awt.Color(0, 102, 102));
        buttonSeven.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonSeven.setForeground(new java.awt.Color(255, 255, 0));
        buttonSeven.setText("7");
        buttonSeven.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonSeven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSevenActionPerformed(evt);
            }
        });

        buttonEight.setBackground(new java.awt.Color(0, 102, 102));
        buttonEight.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonEight.setForeground(new java.awt.Color(255, 255, 0));
        buttonEight.setText("8");
        buttonEight.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonEight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEightActionPerformed(evt);
            }
        });

        buttonMinus.setBackground(new java.awt.Color(102, 102, 255));
        buttonMinus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonMinus.setForeground(new java.awt.Color(255, 255, 0));
        buttonMinus.setText("-");
        buttonMinus.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinusActionPerformed(evt);
            }
        });

        buttonNine.setBackground(new java.awt.Color(0, 102, 102));
        buttonNine.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonNine.setForeground(new java.awt.Color(255, 255, 0));
        buttonNine.setText("9");
        buttonNine.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonNine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNineActionPerformed(evt);
            }
        });

        buttonFour.setBackground(new java.awt.Color(0, 102, 102));
        buttonFour.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonFour.setForeground(new java.awt.Color(255, 255, 0));
        buttonFour.setText("4");
        buttonFour.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonFour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFourActionPerformed(evt);
            }
        });

        buttonFive.setBackground(new java.awt.Color(0, 102, 102));
        buttonFive.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonFive.setForeground(new java.awt.Color(255, 255, 0));
        buttonFive.setText("5");
        buttonFive.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonFive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFiveActionPerformed(evt);
            }
        });

        buttonMultiplication.setBackground(new java.awt.Color(102, 102, 255));
        buttonMultiplication.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonMultiplication.setForeground(new java.awt.Color(255, 255, 0));
        buttonMultiplication.setText("*");
        buttonMultiplication.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonMultiplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMultiplicationActionPerformed(evt);
            }
        });

        buttonSix.setBackground(new java.awt.Color(0, 102, 102));
        buttonSix.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonSix.setForeground(new java.awt.Color(255, 255, 0));
        buttonSix.setText("6");
        buttonSix.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonSix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSixActionPerformed(evt);
            }
        });

        buttonThree.setBackground(new java.awt.Color(0, 102, 102));
        buttonThree.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonThree.setForeground(new java.awt.Color(255, 255, 0));
        buttonThree.setText("3");
        buttonThree.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThreeActionPerformed(evt);
            }
        });

        buttonTwo.setBackground(new java.awt.Color(0, 102, 102));
        buttonTwo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonTwo.setForeground(new java.awt.Color(255, 255, 0));
        buttonTwo.setText("2");
        buttonTwo.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTwoActionPerformed(evt);
            }
        });

        buttonDivision.setBackground(new java.awt.Color(102, 102, 255));
        buttonDivision.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonDivision.setForeground(new java.awt.Color(255, 255, 0));
        buttonDivision.setText("/");
        buttonDivision.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDivisionActionPerformed(evt);
            }
        });

        buttonOne.setBackground(new java.awt.Color(0, 102, 102));
        buttonOne.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonOne.setForeground(new java.awt.Color(255, 255, 0));
        buttonOne.setText("1");
        buttonOne.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOneActionPerformed(evt);
            }
        });

        buttonEqual.setBackground(new java.awt.Color(0, 0, 51));
        buttonEqual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonEqual.setForeground(new java.awt.Color(255, 255, 0));
        buttonEqual.setText("=");
        buttonEqual.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEqualActionPerformed(evt);
            }
        });

        buttonZero.setBackground(new java.awt.Color(0, 102, 102));
        buttonZero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonZero.setForeground(new java.awt.Color(255, 255, 0));
        buttonZero.setText("0");
        buttonZero.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonZeroActionPerformed(evt);
            }
        });

        buttonDot.setBackground(new java.awt.Color(0, 102, 102));
        buttonDot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonDot.setForeground(new java.awt.Color(255, 255, 0));
        buttonDot.setText(".");
        buttonDot.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDotActionPerformed(evt);
            }
        });

        buttonClearAll.setBackground(new java.awt.Color(255, 51, 0));
        buttonClearAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonClearAll.setForeground(new java.awt.Color(255, 255, 0));
        buttonClearAll.setText("C");
        buttonClearAll.setPreferredSize(new java.awt.Dimension(59, 25));
        buttonClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearAllActionPerformed(evt);
            }
        });

        buttonPlus.setBackground(new java.awt.Color(102, 102, 255));
        buttonPlus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonPlus.setForeground(new java.awt.Color(255, 255, 0));
        buttonPlus.setText("+");
        buttonPlus.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlusActionPerformed(evt);
            }
        });

        buttonPower.setBackground(new java.awt.Color(102, 102, 255));
        buttonPower.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonPower.setForeground(new java.awt.Color(255, 255, 0));
        buttonPower.setText("^");
        buttonPower.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPowerActionPerformed(evt);
            }
        });

        jLabel3.setText("A Basic Calculator");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setText("Developed by - Sufi Aurangzeb Hossain (BSc in CSE, UIU)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PLUS");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CLEAR");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("BACKSPACE");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SEVEN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("EIGHT");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NINE");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MINUS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("FOUR");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("FIVE");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SIX");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MULTIPLY");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ONE");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TWO");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("THREE");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("DIVISION");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("ZERO");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("DOT");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("POWER");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("CALCULATE");

        buttonOpenParentheses.setBackground(new java.awt.Color(102, 102, 255));
        buttonOpenParentheses.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonOpenParentheses.setForeground(new java.awt.Color(255, 255, 0));
        buttonOpenParentheses.setText("(");
        buttonOpenParentheses.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonOpenParentheses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenParenthesesActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("OPEN BRACE");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("CLOSING BRACE");

        buttonCloseParentheses.setBackground(new java.awt.Color(102, 102, 255));
        buttonCloseParentheses.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonCloseParentheses.setForeground(new java.awt.Color(255, 255, 0));
        buttonCloseParentheses.setText(")");
        buttonCloseParentheses.setPreferredSize(new java.awt.Dimension(55, 25));
        buttonCloseParentheses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseParenthesesActionPerformed(evt);
            }
        });

        buttonOn.setBackground(new java.awt.Color(0, 255, 0));
        buttonOn.setText("ON");
        buttonOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOnActionPerformed(evt);
            }
        });

        buttonOff.setBackground(new java.awt.Color(255, 0, 0));
        buttonOff.setForeground(new java.awt.Color(255, 255, 0));
        buttonOff.setText("OFF");
        buttonOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOffActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("TURN ON");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("TURN OFF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(buttonZero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonSeven, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(buttonEight, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(buttonNine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(77, 77, 77))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(buttonFive, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(buttonSix, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(buttonTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(buttonThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(buttonPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonMultiplication, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(buttonDot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(buttonEqual, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonOn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(buttonOff, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(buttonBackspace, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonPower, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonOpenParentheses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(buttonCloseParentheses, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(buttonDivision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayField, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonBackspace, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonPower, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonOn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(16, 16, 16)
                        .addComponent(buttonOff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonOpenParentheses, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCloseParentheses, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMultiplication, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNine, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEight, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSix, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFive, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFour, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonThree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonOne, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonZero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEqual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayFieldActionPerformed

    private void buttonOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOneActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "1";
            displayField.setText(text);
        }
        else{
            displayField.setText("1");
        }
    }//GEN-LAST:event_buttonOneActionPerformed

    private void buttonTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTwoActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "2";
            displayField.setText(text);
        }
        else{
            displayField.setText("2");
        }
    }//GEN-LAST:event_buttonTwoActionPerformed

    private void buttonZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonZeroActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "0";
            displayField.setText(text);
        }
        else{
            displayField.setText("0");
        }
    }//GEN-LAST:event_buttonZeroActionPerformed

    private void buttonDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDotActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += ".";
            displayField.setText(text);
        }
        else{
            displayField.setText(".");
        }
    }//GEN-LAST:event_buttonDotActionPerformed

    private void buttonThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThreeActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "3";
            displayField.setText(text);
        }
        else{
            displayField.setText("3");
        }
    }//GEN-LAST:event_buttonThreeActionPerformed

    private void buttonDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDivisionActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "/";
            displayField.setText(text);
        }
        else{
            displayField.setText("/");
        }
    }//GEN-LAST:event_buttonDivisionActionPerformed

    private void buttonMultiplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMultiplicationActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "*";
            displayField.setText(text);
        }
        else{
            displayField.setText("*");
        }
    }//GEN-LAST:event_buttonMultiplicationActionPerformed

    private void buttonSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSixActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "6";
            displayField.setText(text);
        }
        else{
            displayField.setText("6");
        }
    }//GEN-LAST:event_buttonSixActionPerformed

    private void buttonFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFiveActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "5";
            displayField.setText(text);
        }
        else{
            displayField.setText("5");
        }
    }//GEN-LAST:event_buttonFiveActionPerformed

    private void buttonFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFourActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "4";
            displayField.setText(text);
        }
        else{
            displayField.setText("4");
        }
    }//GEN-LAST:event_buttonFourActionPerformed

    private void buttonSevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSevenActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "7";
            displayField.setText(text);
        }
        else{
            displayField.setText("7");
        }
    }//GEN-LAST:event_buttonSevenActionPerformed

    private void buttonEightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEightActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "8";
            displayField.setText(text);
        }
        else{
            displayField.setText("8");
        }
    }//GEN-LAST:event_buttonEightActionPerformed

    private void buttonNineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNineActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "9";
            displayField.setText(text);
        }
        else{
            displayField.setText("9");
        }
    }//GEN-LAST:event_buttonNineActionPerformed

    private void buttonMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinusActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "-";
            displayField.setText(text);
        }
        else{
            displayField.setText("-");
        }
    }//GEN-LAST:event_buttonMinusActionPerformed

    private void buttonClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearAllActionPerformed
        // TODO add your handling code here:
        displayField.setText("");
    }//GEN-LAST:event_buttonClearAllActionPerformed

    private void buttonPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPowerActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "^";
            displayField.setText(text);
        }
        else{
            displayField.setText("^");
        }
    }//GEN-LAST:event_buttonPowerActionPerformed

    private void buttonPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlusActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "+";
            displayField.setText(text);
        }
        else{
            displayField.setText("+");
        }
        
    }//GEN-LAST:event_buttonPlusActionPerformed

    private void buttonEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEqualActionPerformed
        // TODO add your handling code here:
        continueEvaluation = true;
        String expression = displayField.getText();
        
        String result = evaluateExpression(expression);
        displayField.setText(result);
    }//GEN-LAST:event_buttonEqualActionPerformed

    private void buttonBackspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackspaceActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(text.length() >= 1)
            displayField.setText(text.substring(0, text.length()-1));
    }//GEN-LAST:event_buttonBackspaceActionPerformed

    private void buttonOpenParenthesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenParenthesesActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += "(";
            displayField.setText(text);
        }
        else{
            displayField.setText("(");
        }
    }//GEN-LAST:event_buttonOpenParenthesesActionPerformed

    private void buttonCloseParenthesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseParenthesesActionPerformed
        // TODO add your handling code here:
        String text = displayField.getText();
        if(!text.equals("Math Error!") || !text.equals("Infinity")){
            text += ")";
            displayField.setText(text);
        }
        else{
            displayField.setText(")");
        }
    }//GEN-LAST:event_buttonCloseParenthesesActionPerformed

    private void buttonOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOnActionPerformed
        // TODO add your handling code here:
        isOn = true;
        changeState(isOn);
    }//GEN-LAST:event_buttonOnActionPerformed

    private void buttonOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOffActionPerformed
        // TODO add your handling code here:
        isCalculated = false;
        isOn = false;
        changeState(isOn);
    }//GEN-LAST:event_buttonOffActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Calculator c = new Calculator();
                c.setVisible(true);
                c.changeState(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBackspace;
    private javax.swing.JButton buttonClearAll;
    private javax.swing.JButton buttonCloseParentheses;
    private javax.swing.JButton buttonDivision;
    private javax.swing.JButton buttonDot;
    private javax.swing.JButton buttonEight;
    private javax.swing.JButton buttonEqual;
    private javax.swing.JButton buttonFive;
    private javax.swing.JButton buttonFour;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonMinus;
    private javax.swing.JButton buttonMultiplication;
    private javax.swing.JButton buttonNine;
    private javax.swing.JButton buttonOff;
    private javax.swing.JButton buttonOn;
    private javax.swing.JButton buttonOne;
    private javax.swing.JButton buttonOpenParentheses;
    private javax.swing.JButton buttonPlus;
    private javax.swing.JButton buttonPower;
    private javax.swing.JButton buttonSeven;
    private javax.swing.JButton buttonSix;
    private javax.swing.JButton buttonThree;
    private javax.swing.JButton buttonTwo;
    private javax.swing.JButton buttonZero;
    private javax.swing.JTextField displayField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}

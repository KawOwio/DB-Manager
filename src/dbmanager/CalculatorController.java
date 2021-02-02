package dbmanager;

import java.lang.invoke.MethodHandles;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CalculatorController {

//	calculator
	
	@FXML
	private AnchorPane idcalculator;
	
	@FXML
	private Button idcalculator1;
	
	@FXML
	private Button idcalculator2;
	
	@FXML
	private Button idcalculator3;
	
	@FXML
	private Button idcalculator4;
	
	@FXML
	private Button idcalculator5;
	
	@FXML
	private Button idcalculator6;
	
	@FXML
	private Button idcalculator7;
	
	@FXML
	private Button idcalculator8;
	
	@FXML
	private Button idcalculator9;
	
	@FXML
	private Button idcalculator0;
	
	@FXML
	private Button idcalculatorplus;
	
	@FXML
	private Button idcalculatorminus;
	
	@FXML
	private Button idcalculatormultiply;
	
	@FXML
	private Button idcalculatorequals;
	
	@FXML
	private Button idcalculatorcl;
	
	@FXML
	private Button idcalculatordivide;
	
	@FXML
	private Button idcalculatorcoma;
	
	@FXML
	private JFXTextField idcalculatortext;
	
	@FXML
	private Button iddisplaycalculator;
	
    Float data = 0f;
    Float inputNumber = 0f;
    Float all = 0f;
    int operation = -1;
    boolean start = true;
    boolean coma = false;
    boolean firstnumber = true;
    boolean isnumber = true;
    
    @FXML
    void calculatorbutton(ActionEvent event) {
    	
    	if (start == true) {
    		
    		idcalculatortext.setText("");
    		start = false;
    		
    	} else {
    		
    		if (event.getSource() == idcalculator1) {

    			idcalculatortext.setText(idcalculatortext.getText() + "1");
	    		
	    	} else if (event.getSource() == idcalculator2) {
	    		
	    		idcalculatortext.setText(idcalculatortext.getText() + "2");
	    		
	    	} else if (event.getSource() == idcalculator3) {
	    		
	    		idcalculatortext.setText(idcalculatortext.getText() + "3");
	    		
	    	} else if (event.getSource() == idcalculator4) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "4");
	    		
	    	} else if (event.getSource() == idcalculator5) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "5");

	    	} else if (event.getSource() == idcalculator6) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "6");

	    	} else if (event.getSource() == idcalculator7) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "7");
	    		
	    	} else if (event.getSource() == idcalculator8) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "8");
	    		
	    	} else if (event.getSource() == idcalculator9) {
	    		
	    		idcalculatortext.setText(idcalculatortext.getText() + "9");
	    		
	    	} else if ((!"".equals(idcalculatortext.getText())) && (event.getSource() == idcalculator0)) {

	    		idcalculatortext.setText(idcalculatortext.getText() + "0");
	    		
	    	} else if ((coma == false) && (event.getSource() == idcalculatorcoma)) {
	    		
	    		if ("".equals(idcalculatortext.getText())) {	    			
	    			idcalculatortext.setText("0.");
	    			
	    			coma = true;
	    			
	    		} else {	    			
	    			idcalculatortext.setText(idcalculatortext.getText() + ".");
	    			
	    			coma = true;
	    			
	    		}
	    		
	    	} else if (event.getSource() == idcalculatorcl) {	    		
	    		data = 0f;
	    		all = 0f;
	    		idcalculatortext.setText("");
	    		
	    		coma = false;
	    		operation = -1;
	    		
	    	} else if (event.getSource() == idcalculatorplus) {
	    		data = Float.parseFloat(idcalculatortext.getText());
	    		
	    		count(operation);
	    		
	    		operation = 1; // Addition
	    		idcalculatortext.setText("");
	    		coma = false;
	    		
	    	} else if (event.getSource() == idcalculatorminus) {	    		
	    		data = Float.parseFloat(idcalculatortext.getText());
	    		
	    		count(operation);
	    		
	    		operation = 2; // Subtraction
	    		idcalculatortext.setText("");
	    		coma = false;
	    		
	    	} else if (event.getSource() == idcalculatormultiply) {	    		
	    		data = Float.parseFloat(idcalculatortext.getText());
	    		
	    		count(operation);
	    		
	    		operation = 3; // Multiplication
	    		idcalculatortext.setText("");
	    		coma = false;
	    		
	    		
	    	} else if (event.getSource() == idcalculatordivide) {	    		
	    		data = Float.parseFloat(idcalculatortext.getText());
	    		
	    		count(operation);
	    		
	    		operation = 4; // Division
	    		idcalculatortext.setText("");
	    		coma = false;
	    	
	    	} else if (event.getSource() == idcalculatorequals) {	    		
	    		data = Float.parseFloat(idcalculatortext.getText());
	    		
	    		count(operation);
	    		
	    		idcalculatortext.setText(String.valueOf(all));
	    		operation = -1;
	    		coma = true;
	    	}
    	}
    }	
    
    public void count(int operation) {
    	
		if (operation == 1) {
		
			all = all + data;
			
		
		} else if (operation == 2) {

			all = all - data;
			
		} else if (operation == 3) {

			all = all * data;
			
		} else if (operation == 4) {

			all = all / data;
			
		} else if (operation == -1) {
			
			all = data;
			
		}		
    }
}

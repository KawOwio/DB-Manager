package dbmanager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class DisplayExcelController {
	
	@FXML
	private AnchorPane idfillme;
	
    @FXML
    private Text idsettitle;
	
	@FXML
	private Button idsave;
	
	@FXML
	private Button idaddcolumn;
	
	String idExcel;
	String excelMainCol;
	
//	Functions

	// excel
	
	double layoutXa1 = 34.0d;
	double layoutYa1 = 9.0d;
	double prefWidtha1 = 529.0d;
	double prefHeighta1 = 73.0d;
	
	double layoutXt1 = 27.0d;
	double layoutYt1 = 19.0;
	double prefWidtht1 = 529.0d;
	double prefHeightt1 = 73.0d;
	double fontSizet1 = 16.0d;
	double strokeWidth = 0.0d;
	double wrappingWidth = 470.7294921875d;
	
	double layoutXa2 = 27.0d;
	double layoutYa2 = 32.0d;
	double prefWidtha2 = 471.0d;
	double prefHeighta2 = 30.0d;
	
	double layoutXt2 = 27.0d;
	double layoutYt2 = 3.0d;
	double prefWidtht2 = 417.0d;
	double prefHeightt2 = 8.0d;
	double fontSizet2 = 12.0d;
	
	double layoutXa3 = 27.0d;
	double layoutYa3 = 29.0d;
	double prefWidtha3 = 30.0d;
	double prefHeighta3 = 30.0d;
	
	double layoutXc = 0.0d;
	double layoutYc = 0.0d;
	double prefWidthc = 30.0d;
	double prefHeightc = 30.0d;
	
	public void displayData() {
		
		idsettitle.setText(idExcel);
			
		for (int i = 0; i < ; i++) {
			
			// 1st
			
			AnchorPane anchorPanea1 = new AnchorPane();
			idfillme.getChildren().add(anchorPanea1);
			
			// FIXME:
//			anchorPanea1.setId(anchorPanea1);
			anchorPanea1.setLayoutX(layoutXa1);
			anchorPanea1.setLayoutY(layoutYa1);
			anchorPanea1.setPrefWidth(prefWidtha1);
			anchorPanea1.setPrefHeight(prefHeighta1);
			
			layoutYa1 += 73.0;
			
			// 2nd
			
			Text text = new Text();
			//FIXME: have to get the right ID!!!
			anchorPanea1.getChildren().add(text);
			
			// FIXME:
//			text.setId();
			// FIXME:
			text.setText();
			text.setFill(Color.rgb(75, 75, 75));
			text.setStrokeType(StrokeType.OUTSIDE);
			text.setStrokeWidth(strokeWidth);
			text.setWrappingWidth(wrappingWidth);
			text.setLayoutX(layoutXt1);
			text.setLayoutY(layoutYt1);
			text.getFont().font(fontSizet1);
			
			if () {
				
				// 3rd
				
				AnchorPane anchorPanea2 = new AnchorPane();
				anchorPanea1.getChildren().add(anchorPanea2);
				
				anchorPanea2.setLayoutX(layoutXa2);
				anchorPanea2.setLayoutY(layoutYa2);
				anchorPanea2.setPrefWidth(prefWidtha2);
				anchorPanea2.setPrefHeight(prefHeighta2);
				anchorPanea2.setStyle("-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;");
				
				// 4th
				
				JFXTextField jfxTextField = new JFXTextField();
				anchorPanea2.getChildren().add(jfxTextField);
				
				jfxTextField.getFont().font(fontSizet2);
				jfxTextField.setFocusColor(Color.WHITE);
				jfxTextField.setLayoutX(layoutXt2);
				jfxTextField.setLayoutY(layoutYt2);
				jfxTextField.setPrefWidth(prefWidtht2);
				jfxTextField.setPrefHeight(prefHeightt2);
				jfxTextField.setUnFocusColor(Color.WHITE);
				jfxTextField.setText();
			
			} else {
				
				// 3rd
				
				AnchorPane anchorPanea3 = new AnchorPane();
				anchorPanea1.getChildren().add(anchorPanea3);
				
				anchorPanea3.setLayoutX(layoutXa3);
				anchorPanea3.setLayoutY(layoutYa3);
				anchorPanea3.setPrefWidth(prefWidtha3);
				anchorPanea3.setPrefHeight(prefHeighta3);
				anchorPanea3.setStyle("-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;");
				
				// 4th
				
				JFXCheckBox jfxCheckBox = new JFXCheckBox();
				anchorPanea3.getChildren().add(jfxCheckBox);
				
				// FIXME:
	//			jfxCheckBox.setId();
				// FIXME: checkedColor="#92bbba"
//				jfxCheckBox.setCheckedColor(Color.rgb(92, bb, ba));
				jfxCheckBox.setLayoutX(layoutXc);
				jfxCheckBox.setLayoutY(layoutYc);
				jfxCheckBox.setPrefWidth(prefWidthc);
				jfxCheckBox.setPrefHeight(prefHeightc);
				jfxCheckBox.setUnCheckedColor(Color.WHITE);
				
			}
		}
	}
	
	// Save button on click
	
    @FXML
    void savebutton(ActionEvent event) {

    }
    
    // Main

	public void initDataExcel(String idExcel, String excelMainCol) {
		// TODO Auto-generated method stub
		this.idExcel = idExcel;
		this.excelMainCol = excelMainCol;
		
		displayData();
	}
	
	
}

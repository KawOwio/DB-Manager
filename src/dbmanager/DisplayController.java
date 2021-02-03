package dbmanager;

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
import javafx.scene.text.Text;

public class DisplayController {
	
	@FXML
	private AnchorPane idfillme;
	
    @FXML
    private Text idsettitle;
	
	@FXML
	private Button idsave;
	
	String idMySQL;
	String mysqlMainCol;
	MySQL mysql;
	
//	Functions
	
	// String
	
		//	 <AnchorPane fx:id="idreplacestringholder" layoutX="34.0" layoutY="9.0" prefHeight="73.0" prefWidth="529.0" style="visibility: true;">
		//     <children>
		//        <Text fx:id="idreplacetext" fill="#757575" layoutX="27.0" layoutY="19.0" strokeType="OUTSIDE" strokeWidth="0.0" text="ReplaceMeString" wrappingWidth="470.7294921875">
		//           <font>
		//              <Font size="14.0" />
		//           </font>
		//        </Text>
		//        <AnchorPane layoutX="27.0" layoutY="32.0" prefHeight="30.0" prefWidth="471.0" style="-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;">
		//           <children>
		//              <JFXTextField fx:id="idreplacestring" focusColor="#ffffff00" layoutX="27.0" layoutY="3.0" prefHeight="8.0" prefWidth="417.0" text="sasdasdadasad" unFocusColor="#ffffff00">
		//                 <font>
		//                    <Font size="12.0" />
		//                 </font>
		//              </JFXTextField>
		//           </children>
		//        </AnchorPane>
		//     </children>
		//  </AnchorPane>
	
	//	CheckBox
	
		//	<AnchorPane fx:id="idreplacestringholder1" layoutX="34.0" prefHeight="73.0" prefWidth="529.0" style="visibility: false;">
		//    <children>
		//       <Text fx:id="idreplacecheck" fill="#757575" layoutX="27.0" layoutY="19.0" strokeType="OUTSIDE" strokeWidth="0.0" text="ReplaceMeString" wrappingWidth="470.7294921875">
		//          <font>
		//             <Font size="14.0" />
		//          </font>
		//       </Text>
		//       <AnchorPane layoutX="27.0" layoutY="29.0" prefHeight="30.0" prefWidth="30.0" style="-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;">
		//          <children>
		//             <JFXCheckBox fx:id="idcheckbox" checkedColor="#92bbba" layoutY="-1.0" prefHeight="30.0" prefWidth="30.0" text=" " unCheckedColor="#ffffff00" />
		//          </children>
		//       </AnchorPane>
		//    </children>
		// </AnchorPane>

	// mysql
	
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
		
		idsettitle.setText(idMySQL);

		ArrayList<ArrayList<String>> list = mysql.getRowContent(mysqlMainCol, idMySQL);
		
		//		list.get(2).get(i)
		
		//		allTheInfo.add(columnNames);
		//		allTheInfo.add(columnTypes);
		//		allTheInfo.add(rowContent);

		// 		System.out.println(allTheInfo.get(0).get(i));
		// 		System.out.println(allTheInfo.get(1).get(i));
		// 		System.out.println(allTheInfo.get(2).get(i));
		
		
		for (int i = 0; i < mysql.getColumnNames().size(); i++) {
			
			Pattern stringPattern = Pattern.compile("VARCHAR", Pattern.CASE_INSENSITIVE);
			Pattern intPattern = Pattern.compile("INT", Pattern.CASE_INSENSITIVE);
			Pattern decimalPattern = Pattern.compile("DECIMAL", Pattern.CASE_INSENSITIVE);
			Matcher stringMatcher = stringPattern.matcher(list.get(0).get(i));
			Matcher intMatcher = intPattern.matcher(list.get(0).get(i));
			Matcher decimalMatcher = decimalPattern.matcher(list.get(0).get(i));
			
			// 1st
			
			AnchorPane anchorPanea1 = new AnchorPane();
			idfillme.getChildren().add(anchorPanea1);
			
			// FIXME:
//			anchorPanea1.setId(anchorPanea1);
			anchorPanea1.setLayoutX(layoutXa1);
			anchorPanea1.setLayoutY(layoutYa1);
			anchorPanea1.setPrefWidth(prefWidtha1);
			anchorPanea1.setPrefHeight(prefHeighta1);
			
			layoutYa1 += 9.0;
			
			// 2nd
			
			Text text = new Text();
			//FIXME: have to get the right ID!!!
			anchorPanea1.getChildren().add(text);
			
			// FIXME:
//			text.setId();
			// FIXME:
			text.setText(list.get(0).get(i));
			text.setFill(Color.rgb(75, 75, 75));
			// FIXME:
			text.setStrokeType(StrokeType.OUTSIDE);
			text.setStrokeWidth(strokeWidth);
			text.setWrappingWidth(wrappingWidth);
			text.setLayoutX(layoutXt1);
			text.setLayoutY(layoutYt1);
			// FIXME:
			text.getFont().font(fontSizet1);
			
			if (stringMatcher.find() || intMatcher.find() || decimalMatcher.find()) {
				
				// 3rd
				
				AnchorPane anchorPanea2 = new AnchorPane();
				// FIXME: have to get the right ID!!!
				anchorPanea1.getChildren().add(anchorPanea2);
				
				// FIXME:
	//			anchorPanea2.setId();
				anchorPanea2.setLayoutX(layoutXa2);
				anchorPanea2.setLayoutY(layoutYa2);
				anchorPanea2.setPrefWidth(prefWidtha2);
				anchorPanea2.setPrefHeight(prefHeighta2);
				anchorPanea2.setStyle("-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;");
				
				// 4th
				
				JFXTextField jfxTextField = new JFXTextField();
				// FIXME: have to get the right ID!!!
				anchorPanea2.getChildren().add(jfxTextField);
				
				// FIXME:
//				jfxTextField.setId();
				jfxTextField.getFont().font(fontSizet2);
				jfxTextField.setFocusColor(Color.WHITE);
				jfxTextField.setLayoutX(layoutXt2);
				jfxTextField.setLayoutY(layoutYt2);
				jfxTextField.setPrefWidth(prefWidtht2);
				jfxTextField.setPrefHeight(prefHeightt2);
				jfxTextField.setUnFocusColor(Color.WHITE);
				// FIXME: insert what you get from the function
				jfxTextField.setText(list.get(2).get(i));
			
			} else {
				
				// 3rd
				
				AnchorPane anchorPanea3 = new AnchorPane();
				// FIXME: have to get the right ID!!!
				anchorPanea1.getChildren().add(anchorPanea3);
				
				// FIXME:
	//			anchorPanea3.setId();
				anchorPanea3.setLayoutX(layoutXa3);
				anchorPanea3.setLayoutY(layoutYa3);
				anchorPanea3.setPrefWidth(prefWidtha3);
				anchorPanea3.setPrefHeight(prefHeighta3);
				anchorPanea3.setStyle("-fx-background-color: #fff; -fx-background-radius: 5em; -fx-border-radius: 5em;");
				
				// 4th
				
				JFXCheckBox jfxCheckBox = new JFXCheckBox();
				// FIXME: have to get the right ID!!!
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

	public void initData(String idMySQL, String mysqlMainCol, MySQL mysql) {
		// TODO Auto-generated method stub
		this.idMySQL = idMySQL;
		this.mysqlMainCol = mysqlMainCol;
		this.mysql = mysql;
		
		displayData();
	}
	
	
}

package dbmanager;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXTextField;

public class Controller {
	
//	Images
	
	@FXML
    private ImageView idimgmysql;

    @FXML
    private ImageView idimgword;

    @FXML
    private ImageView idimgexcel;

    @FXML
    private ImageView idimgcalc;

    @FXML
    private ImageView idimgwriter;
    
    @FXML
    private ImageView idnoexcel;

    @FXML
    private ImageView idyesexcel;
	
//	Side bars buttons
	
	@FXML
	private Button import_mysql_button;
	
	@FXML
	private Button import_excel_button;
	
	@FXML
	private Button import_calc_button;
	
	@FXML
	private Button dbm_button;
	
	@FXML
	private Button import_myfiles_button;
	
//	DB Manager windows buttons
	
	@FXML
	private Button import_mysql_button_main;
	
	@FXML
	private Button import_excel_button_main;
	
	@FXML
	private Button import_calc_button_main;
	
	@FXML
	private Button import_word_button_main;
	
	@FXML
	private Button import_writer_button_main;
	
//	When side bars button is pressed
	
	@FXML
	private AnchorPane idmyfiles;
	
	@FXML
	private AnchorPane idcalc;
	
	@FXML
	private AnchorPane idexcel;
	
	@FXML
	private AnchorPane idmysql;
	
	@FXML
	private AnchorPane iddbm;
	
//	Button id
	
	@FXML
	private Button idword;
	
	@FXML
	private Button idcalcimgbutton;
	
	@FXML
	private Button idexcelimgbutton;
	
	@FXML
	private Button idmysqlbutton;
	
	@FXML
	private Button idwordimgbutton;
	
	@FXML
	private Button idwriterimgbutton;
	
	@FXML
	private Button idapplymysql;
	
	@FXML
	private Button idcancelmysql;
	
	@FXML
	private Button idexcelsearchyes;
	
	@FXML
	private Button idexcelsearchno;
	
	@FXML
	private Button idapplyexcel;
	
	@FXML
	private Button idcancelexcel;
	
	@FXML
	private Button idmysqlsearchyes;
	
	@FXML
	private Button idemysqlsearchno;
	
	@FXML
	private Button idcalcsearchyes;
	
	@FXML
	private Button idcalcsearchno;
	
	@FXML
	private Button idapplycalc;
	
	@FXML
	private Button idcancelcalc;
	
	// make f on click
	@FXML
	private Button idsavechangesexcel;
	
	// make f on click
	@FXML
	private Button idexporttodocexcel;
	
	// make f on click
	@FXML
	private Button idsavechangesmysql;
	
	// make f on click
	@FXML
	private Button idexporttodocmysql;
	
	// make f on click
	@FXML
	private Button idsavechangescalc;
	
	// make f on click
	@FXML
	private Button idexporttodoccalc;
	
//	Main windows
	
	@FXML
	private AnchorPane idexcelwindow;
	
	@FXML
	private AnchorPane idcalcwindow;
	
	@FXML
	private AnchorPane idmysqlwindow;
	
	@FXML
	private AnchorPane iddbmwindow;
	
	@FXML
	private AnchorPane idcalctext;
	
	@FXML
	private AnchorPane idexceltext;
	
	@FXML
	private AnchorPane idmysqltext;
	
	@FXML
	private AnchorPane idwordtext;
	
	@FXML
	private AnchorPane idwritertext;
	
	@FXML
	private AnchorPane idtopexcel;
	
	@FXML
	private AnchorPane idtopmysql;
	
	@FXML
	private AnchorPane idtopcalc;
	
	@FXML
	private AnchorPane idfillwindowmysql;
	
//	Setting up fields
	
	@FXML
	private JFXTextField idsetcol;
	
	@FXML
	private JFXTextField idsetrow;
	
	@FXML
	private JFXTextField idsetcolmysql;
	
	@FXML
	private JFXTextField idsettablenamemysql;
	
	@FXML
	private JFXTextField iddbnameinput;
	
	@FXML
	private JFXTextField idlogininput;
	
	@FXML
	private JFXTextField idpasswordinput;
	
	@FXML
	private JFXTextField idsearchexcel;
	
	@FXML
	private JFXTextField idsetexcelcol;
	
	@FXML
	private JFXTextField idsetexcelrow;
	
	@FXML
	private JFXTextField idsearchmysql;
	
	@FXML
	private JFXTextField idsetcalccol;
	
	@FXML
	private JFXTextField idsetcalcrow;
	
	@FXML
	private JFXTextField idsearchcalc;
	
//	calculator

	@FXML
	private Button iddisplaycalculator;
	
//	Files
	
	File myExcelFile;
	File myWordFile;
	
//	File repeated imports
	
	boolean impExcel = true;
	boolean impCalc = true;
	boolean colInsertedExcel = false;
	boolean colInsertedCalc = false;
	boolean colInsertedMySQL = false;
	
//	Class connections
	
	MySQL mysql = new MySQL();
	
	double layoutXMySQL = 320.0d;
	double layoutYMySQL = 16.0d;
	double prefWidthMySQL = 267.0d;
	double prefHeightMySQL = 32.0d;
	double fontSizeMySQL = 16.0d;
	
	String mysqlMainCol;
	
//	Functions
	
	//	calc
	
	@FXML
	void applycalc(ActionEvent event) {
//		if ((idsavechangescalc.getText() != null && !idsavechangescalc.getAccessibleText().isEmpty()) ||
//			(idexporttodoccalc.getText() != null && !idexporttodoccalc.getAccessibleText().isEmpty())) {
//			
//			calcMainCol = idsetcalccol.getText();
//			calcMainRow = idsetcalcrow.getText();
		
		idsavechangescalc.setVisible(true);
		idexporttodoccalc.setVisible(true);
		
		impCalc = false;
		
//			
//		} else {
//			// should add error message
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error Dialog");
//			alert.setHeaderText("ERROR");
//			alert.setContentText("Invalid input!");
//
//			alert.showAndWait();
//		
//			idsavechangescalc.setVisible(false);
//			idexporttodoccalc.setVisible(false);
		
		impCalc = true;
//		
//		}
	}
	
	@FXML
	void cancelcalc(ActionEvent event) {
		idsetcalccol.clear();
		idsetcalcrow.clear();
		
		idsavechangescalc.setVisible(false);
		idexporttodoccalc.setVisible(false);
		
		impCalc = true;
		
//		colCalc = "";
//		rowCalc = "";
	}
	
	@FXML
	void calcsearchyes(ActionEvent event) {
		//		display calc col with the inserted values in - idsearchcalc
//		String searchCalc = idsearchcalc.getText();
		//		find rows where fields match with searchCalc
	}
	
	@FXML
	void calcsearchno(ActionEvent event) {
		//		clear - idsearchcalc and display all
		idsearchcalc.clear();
		//		display all again
	}
	
	//	excel
	
	@FXML
	void applyexcel(ActionEvent event) {
//		if ((idsetexcelcol.getText() != null && !idsetexcelcol.getAccessibleText().isEmpty()) ||
//			(idsetexcelrow.getText() != null && !idsetexcelrow.getAccessibleText().isEmpty())) {
//			
//			excelMainCol = idsetexcelcol.getText();
//			excelMainRow = idsetexcelrow.getText();
		
		idsavechangesexcel.setVisible(true);
		idexporttodocexcel.setVisible(true);
		
		impExcel = false;
		
//			
//		} else {
//			// should add error message
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error Dialog");
//			alert.setHeaderText("ERROR");
//			alert.setContentText("Invalid input!");
//
//			alert.showAndWait();
//		
//			idsavechangesexcel.setVisible(false);
//			idexporttodocexcel.setVisible(false);
		
		impExcel = true;
//		
//		}
	}
	
	@FXML
	void cancelexcel(ActionEvent event) {
		idsetexcelcol.clear();
		idsetexcelrow.clear();
		
		idsavechangesexcel.setVisible(false);
		idexporttodocexcel.setVisible(false);
		
		impExcel = true;
		
//		colExcel = "";
//		rowExcel = "";
	}
	
	@FXML
	void excelsearchyes(ActionEvent event) {
		//		display ecxel col with the inserted values in - idsearchexcel
//		String searchExcel = idsearchexcel.getText();
		//		find rows where fields match with searchexcel
	}
	
	@FXML
	void excelsearchno(ActionEvent event) {
		//		clear - idsearchexcel and display all
		idsearchexcel.clear();
		//		display all again
	}
	
	// mysql
	
	@FXML
	void applymysql(ActionEvent event) {
//		if ((idsetcolmysql.getText() != null && !idsetcolmysql.getAccessibleText().isEmpty()) ||
//			(idsettablenamemysql.getText() != null && !idsettablenamemysql.getAccessibleText().isEmpty()) ||
//			(iddbnameinput.getText() != null && !iddbnameinput.getAccessibleText().isEmpty()) ||
//			(idlogininput.getText() != null && !idlogininput.getAccessibleText().isEmpty()) ||
//			(idpasswordinput.getText() != null && !idpasswordinput.getAccessibleText().isEmpty())) {
		
		idexporttodocmysql.setVisible(true);
		idsavechangesmysql.setVisible(true);
		
		mysql.databaseName = iddbnameinput.getText();
		mysql.username = idlogininput.getText();
		mysql.password = idpasswordinput.getText();
		mysql.tableName = idsettablenamemysql.getText();
		
		mysqlMainCol = idsetcolmysql.getText();
		
		int rowCountMySQL = mysql.getValues(mysqlMainCol).size();
		int colCountMySQL = mysql.getColumnNames().size(); 
		
		//	    Display MySQL INSERT main col info
	    
		colInsertedMySQL = true;
		int row = 0;
		
	    for (int i = 0; i < rowCountMySQL; i++) {
	    	
//	    	<JFXButton fx:id="idcolvaluesmysql" layoutX="316.0" layoutY="16.0" prefHeight="32.0" prefWidth="267.0" style="visibility: true; -fx-background-radius: 5em; -fx-border-radius: 5em; visibility: false;" text="FillMe" textFill="#545454">
//	            <font>
//	               <Font size="16.0" />
//	            </font>
//	         </JFXButton>
	    	
	    	Button mysqlButton = new Button("Button" + row);
	    	idfillwindowmysql.add(mysqlButton);
	    	
	    	// Add the same name that you extract from table
	    	// There could be problems if there are spaces
	    	String idMySQL = mysql.getValues(mysqlMainCol).get(i);
	    	
	    	mysqlButton.setId(idMySQL);
	    	mysqlButton.setLayoutX(layoutXMySQL);
	    	mysqlButton.setLayoutY(layoutYMySQL);
	    	mysqlButton.setPrefWidth(prefWidthMySQL);
	    	mysqlButton.setPrefHeight(prefHeightMySQL);
	    	mysqlButton.setStyle("-fx-background-radius: 5em; -fx-border-radius: 5em");
	    	mysqlButton.setText(idMySQL);
	    	mysqlButton.setTextFill(Color.rgb(54, 54, 54));
	    	// FIXME:
	    	mysqlButton.getFont().font(fontSizeMySQL);
	    	
	    	row++;
	    	
	    	layoutYMySQL += 32.0;
	    	
	    }
	    
//		} else {
//			// should add error message
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error Dialog");
//			alert.setHeaderText("ERROR");
//			alert.setContentText("Invalid input!");
//
//			alert.showAndWait();
//		
//		idexporttodocmysql.setVisible(false);
//		idsavechangesmysql.setVisible(false);
//		
//		}	
	}
	
	@FXML
	void cancelmysql(ActionEvent event) {
		
//	    Display MySQL DELETE main col info
	    
		if (colInsertedMySQL == true) {
			
			int colCountMySQL = 0; // TODO: add col count!!!!
		    for (int i = 0; i < colCountMySQL; i++) {
		    	
		    	// TODO: take it as a text field not a string
		    	String idMySQL = mysql.getValues(mysqlMainCol).get(i);
		    	
		    	idfillwindowmysql.getChildren().remove(idMySQL);
		    	
		    }
		    
		}
		
		idsettablenamemysql.clear();
		iddbnameinput.clear();
		idlogininput.clear();
		idpasswordinput.clear();
		
		
		mysql.databaseName = "";
		mysql.username = "";
		mysql.password = "";
		mysql.tableName = "";
		
		idexporttodocmysql.setVisible(false);
		idsavechangesmysql.setVisible(false);
		
	}
	
	@FXML
	void mysqlsearchyes(ActionEvent event) {
		//		display MySQL col with the inserted values in - idsearchmysql
//		String searchMySQL = idsearchmysql.getText();
		//		find rows where fields match with searchMySQL
	}
	
	@FXML
	void mysqlsearchno(ActionEvent event) {
		//		clear - idsearchmysql and display all
		idsearchmysql.clear();
		//		display all again
	}
	
	// all
	
		@FXML
	void clickword(ActionEvent event) {
		FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Word File", "*.docx"), 
        		new ExtensionFilter("OpenDocument File", "*.odt"));
        myWordFile = chooser.showOpenDialog(iddbm.getScene().getWindow());
        
        WriteToWord.replaceValuesFromExcel(myExcelFile, myWordFile);
	}
		
	@FXML
	void calcimgbuttonchange(ActionEvent event) {
		idcalcimgbutton.setStyle("-fx-border-color:  #92bbba; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idexcelimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idmysqlbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwordimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwriterimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalctext.setVisible(true);
		idexceltext.setVisible(false);
		idmysqltext.setVisible(false);
		idwordtext.setVisible(false);
		idwritertext.setVisible(false);
	}
	
	@FXML
	void excelimgbuttonchange(ActionEvent event) {
		idexcelimgbutton.setStyle("-fx-border-color:  #92bbba; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalcimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idmysqlbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwordimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwriterimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalctext.setVisible(false);
		idexceltext.setVisible(true);
		idmysqltext.setVisible(false);
		idwordtext.setVisible(false);
		idwritertext.setVisible(false);
	}
	
	@FXML
	void mysqlimgbuttonchange(ActionEvent event) {
		idmysqlbutton.setStyle("-fx-border-color:  #92bbba; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idexcelimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalcimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwordimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwriterimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalctext.setVisible(false);
		idexceltext.setVisible(false);
		idmysqltext.setVisible(true);
		idwordtext.setVisible(false);
		idwritertext.setVisible(false);
	}
	
	@FXML
	void wordimgbuttonchange(ActionEvent event) {
		idwordimgbutton.setStyle("-fx-border-color:  #92bbba; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idexcelimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalcimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idmysqlbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwriterimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalctext.setVisible(false);
		idexceltext.setVisible(false);
		idmysqltext.setVisible(false);
		idwordtext.setVisible(true);
		idwritertext.setVisible(false);
	}
	
	@FXML
	void writerimgbuttonchange(ActionEvent event) {
		idwriterimgbutton.setStyle("-fx-border-color:  #92bbba; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idexcelimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalcimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idmysqlbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idwordimgbutton.setStyle("-fx-border-color:  #fff; -fx-background-radius:  5em; -fx-border-radius:  5em; -fx-border-width:  3px");
		idcalctext.setVisible(false);
		idexceltext.setVisible(false);
		idmysqltext.setVisible(false);
		idwordtext.setVisible(false);
		idwritertext.setVisible(true);
	}
	
    @FXML
    void clickdbm(ActionEvent event) {
    	System.out.println("Main");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(true);
    	idexcelwindow.setVisible(false);
    	iddbmwindow.setVisible(true);
    	idcalcwindow.setVisible(false);
    	idmysqlwindow.setVisible(false);
    	idtopexcel.setVisible(false);
    	idtopmysql.setVisible(false);
    	idtopcalc.setVisible(false);
    }
	
    @FXML
    void clickconnectmysql1(ActionEvent event) {
    	System.out.println("MySQL");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(true);
    	iddbm.setVisible(false);
    	idexcelwindow.setVisible(false);
    	iddbmwindow.setVisible(false);
    	idcalcwindow.setVisible(false);
    	idmysqlwindow.setVisible(true);
    	idtopexcel.setVisible(false);
    	idtopmysql.setVisible(true);
    	idtopcalc.setVisible(false);
    }
    
    @FXML
    void clickimportexcel1(ActionEvent event) {
    	System.out.println("Excel");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(true);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
        idexcelwindow.setVisible(true);
        iddbmwindow.setVisible(false);
    	idcalcwindow.setVisible(false);
    	idmysqlwindow.setVisible(false);
    	idtopexcel.setVisible(true);
    	idtopmysql.setVisible(false);
    	idtopcalc.setVisible(false);
    	
    	if (impExcel == true) {
    		
    		FileChooser chooser = new FileChooser();
	        chooser.setTitle("Open File");
	        chooser.getExtensionFilters().add(new ExtensionFilter("Excel Files", "*.xlsx"));
	        myExcelFile = chooser.showOpenDialog(iddbm.getScene().getWindow());
        
    	}
    	
    }

    @FXML
    void clickimportcalc1(ActionEvent event) {
    	System.out.println("Calc");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(true);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
        idexcelwindow.setVisible(false);
        iddbmwindow.setVisible(false);
    	idcalcwindow.setVisible(true);
    	idmysqlwindow.setVisible(false);
    	idtopexcel.setVisible(false);
    	idtopmysql.setVisible(false);
    	idtopcalc.setVisible(true);
    	
    	if (impCalc == true) {
    		
    		FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            chooser.getExtensionFilters().add(new ExtensionFilter("Open Document Spreadsheet", "*.ods"));
            File myCalcFile = chooser.showOpenDialog(iddbm.getScene().getWindow());
            
    	}
    }
    
    @FXML
    void clickmyfiles(ActionEvent event) {
    	System.out.println("My Files");
    	idmyfiles.setVisible(true);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
    	idexcelwindow.setVisible(false);
    	iddbmwindow.setVisible(false);
    	idcalcwindow.setVisible(false);
    	idmysqlwindow.setVisible(false);
    	idtopexcel.setVisible(false);
    	idtopmysql.setVisible(false);
    	idtopcalc.setVisible(false);
    }
    
//    calculator
    
    @FXML
    void displaycalculator(ActionEvent event) {
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/calculator.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Calculator");
            stage.setScene(new Scene(root, 400, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

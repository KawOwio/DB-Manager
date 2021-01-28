package dbmanager;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class Controller {
	
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
	
//	Main windows buttons
	
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
	
//	
	
//	@FXML
//	public void initialize() {
//		my_files_main_bar.setVisible(false);
//	}
	
//	Action on click
	
    @FXML
    void clickdbm(ActionEvent event) {
    	System.out.println("Main");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(true);
    }
	
    @FXML
    void clickconnectmysql1(ActionEvent event) {
    	System.out.println("MySQL");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(true);
    	iddbm.setVisible(false);
    }
    
    @FXML
    void clickimportexcel1(ActionEvent event) {
    	System.out.println("Excel");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(false);
    	idexcel.setVisible(true);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.showOpenDialog(iddbm.getScene().getWindow());
    }

    @FXML
    void clickimportcalc1(ActionEvent event) {
    	System.out.println("Calc");
    	idmyfiles.setVisible(false);
    	idcalc.setVisible(true);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
    }
    
    @FXML
    void clickmyfiles(ActionEvent event) {
    	System.out.println("My Files");
    	idmyfiles.setVisible(true);
    	idcalc.setVisible(false);
    	idexcel.setVisible(false);
    	idmysql.setVisible(false);
    	iddbm.setVisible(false);
    }
    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//    	
//    }
}

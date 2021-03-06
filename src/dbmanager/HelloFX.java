package dbmanager;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {
				
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Database Manager");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(825);
        
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/sample.fxml"));
    
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
	
}
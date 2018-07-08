/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;

import javafx.stage.WindowEvent;
import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rahul
 */
public class SCADA extends Application {
    int PCC1FEEDERS = 10;
    int PCC2FEEDERS = 10;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        Application.setUserAgentStylesheet(null);

        scene.getStylesheets().add("hmi.css");
        stage.setTitle("PLC and SCADA Interface");
        stage.setScene(scene);
        stage.show();
        
        //stage.setFullScreen(true);
    }
    @Override
    public void stop(){
        FXMLDocumentController.db.stopDB();
        FXMLDocumentController.db.close();
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FXMLDocumentController.db.stopDB();
        launch(args);
    }
    
}

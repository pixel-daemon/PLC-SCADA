package scada;

import java.awt.Desktop;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class FXMLDocumentController implements Initializable {
    static DataBase db;
    static{
        db = new DataBase();
    }
    
    @FXML
    private ScrollPane rootPane;
    @FXML
    private Pane checkIt;
    
    @FXML
    private Pane group1, group2, group3, apfc1, apfc2, apfc3, mccb, controller1, controller2, controller3;   
    
    ObservableList<Node> ob;
    
    /*Table below of feeders*/
    @FXML
    private javafx.scene.control.TableView<Feeders> ftag1, ftag2, ftag3, ftag4;
    @FXML
    private TableColumn<Feeders, String> tag1,discription1,tag2,discription2,tag3,discription3,tag4,discription4;
      
    @FXML
    private javafx.scene.control.Button showFile;
    
    ObservableList<Feeders> data1=FXCollections.observableArrayList(
        new Feeders("AI","electrical Computer-4"),
        new Feeders("BI","Director Office,Gulabchand Reasearch-3"),
        new Feeders("CI","Applied Old Computer - 2"),
        new Feeders("DI","Hydrawlic Lab - 18")     
    );
    ObservableList<Feeders> data2=FXCollections.observableArrayList(
        new Feeders("EI","CCF - 17"),
        new Feeders("FI","Electrical Dept - 5"),
        new Feeders("GI","Electronics - 1"),
        new Feeders("HI","PG Lab,Bank Of India.")
    );

    ObservableList<Feeders> data3=FXCollections.observableArrayList(
        new Feeders("AII","Stage Applied, Mech, Civil Staff- 11"),
        new Feeders("BII","Stage Applied, IT - 16"),
        new Feeders("CII","Generator Room"),
        new Feeders("DII","Workshop 12")
    );

    ObservableList<Feeders> data4=FXCollections.observableArrayList(
        new Feeders("EII","Cyber Host.,New Liabrary,Director bunglow-10"),
        new Feeders("FII","H.E. Lab - 9"),
        new Feeders("GII","Applied Computer"),
        new Feeders("HII","CSE,Cyber hostel,elc comp,water pump")
    );
    
    private Stage stage;    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
     public static int getFeederNumber(int group, int index){
        return (group-1)*10 + index;
    }
    static int getStatus(int group, int index){
        // Check whether feeder is ON/OFF
        // 1 for on 0 for off
        int feederno = getFeederNumber(group,index);
        String s = CurrentDB.getStatus(feederno);
        System.out.println("Status string = "+s);
        if(s.equals("off"))
            return 1;
        if(s.equals("on"))
            return 2;
        return 2;
    }
    void showDetails(Pane pane, int group, int index){
        try{
            DetailsController.setGroup(group, index);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scada/DetailsDocument.fxml"));
            Parent p = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            System.out.println("A");
            DetailsController controller = loader.<DetailsController>getController();
            System.out.println("B "+controller);
            controller.setData(pane,group,index,db,this);
            System.out.println("C");
            stage.show();
            List<String[]> li;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public ObservableList getOBList(int group){
        switch(group){
            case 1:
                ob = group1.getChildren();
                System.out.println("Group 1");
                break;
                
            case 2:
                ob = group2.getChildren();
                System.out.println("Group 2");
                break;
            case 3:
                ob = group3.getChildren();
                System.out.println("Group 3");
                break;
            case 4:
                ob = apfc1.getChildren();
                break;
            case 5:
                ob = apfc2.getChildren();
                break;
            case 6:
                ob = apfc3.getChildren();
                break;
            case 7:
                ob = mccb.getChildren();
                break;
            case 8:
                ob = controller1.getChildren();
                break;
                
            case 9:
                ob = controller3.getChildren();
                break;
                
            case 10:
                ob = controller2.getChildren();
                break;
                
            default:
                System.out.println("Not found");
        }
        return ob;
    }
    public void updateView(int group, int index){
        Pane pane;
        String image="";
        int flag = getStatus(group, index);
        switch(group){
            case 1:
                if(flag == 1)
                    image = "oncbf.jpg";
                else 
                    image = "offcbf2.jpg";
                ob = group1.getChildren();
                System.out.println("Group 1");
                break;
                
            case 2:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = group2.getChildren();
                System.out.println("Group 2");
                break;
            case 3:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = group3.getChildren();
                System.out.println("Group 3");
                break;
            case 4:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = apfc1.getChildren();
                break;
            case 5:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = apfc2.getChildren();
                break;
            case 6:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = apfc3.getChildren();
                break;
            case 7:
                if(flag == 1)
                    image = "oncbf.jpg";
                else
                    image = "offcbf2.jpg";
                ob = mccb.getChildren();
                break;
            case 8:
                if(flag == 1)
                    image = "onapfc.jpg";
                else
                    image = "offapfcf.jpg";
                ob = controller1.getChildren();
                break;
                
            case 9:
                if(flag == 1)
                    image = "onapfc.jpg";
                else
                    image = "offapfcf.jpg";
                ob = controller3.getChildren();
                break;
                
            case 10:
                if(flag == 1)
                    image = "onapfc.jpg";
                else
                    image = "offapfcf.jpg";
                ob = controller2.getChildren();
                break;
                
            default:
                System.out.println("Not found");
                return;
        }
        
        pane = (Pane)ob.get(index);
        pane.setStyle("-fx-background-image: url('"+image+"');-fx-background-size:cover;");
        
    }
    public final void onBreakerSelected(int group, int index) {
        
        System.out.println("Group "+group+" index "+index);
        //updateView(group, index);
        ob = getOBList(group);
        showDetails((Pane)ob.get(index),group, index);
        
    }
    
    void initializeGroup(){
        ob = group1.getChildren();
        /*for(int i=0;i<ob.size(); i++){
            updateView(1,i);
        }*/
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(1, a);
            });
        }
        
        ob = group2.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(2, a);
            });
        }
        
        ob = group3.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(3, a);
            });
        }
        
        ob = apfc1.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(4, a);
            });
        }
        
        ob = controller1.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(8, a);
            });
        }
        
        
        ob = apfc2.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(5, a);
            });
        }
        
        ob = controller2.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(10, a);
            });
        }
        
        ob = apfc3.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(6, a);
            });
        }
        
        ob = controller3.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(9, a);
            });
        }
        
        ob = mccb.getChildren();
        System.out.println(ob.size());
        for(int i=0; i<ob.size(); i++){
            final int a = i;
            ob.get(i).setOnMouseClicked((e)->{
                onBreakerSelected(7, a);
            });
        }
    }
    
    public void initializeTable(){
        tag1.setCellValueFactory(new PropertyValueFactory<>("tag"));
        discription1.setCellValueFactory(new PropertyValueFactory<>("discription"));
        
        tag2.setCellValueFactory(new PropertyValueFactory<>("tag"));
        discription2.setCellValueFactory(new PropertyValueFactory<>("discription"));
        
        tag3.setCellValueFactory(new PropertyValueFactory<>("tag"));
        discription3.setCellValueFactory(new PropertyValueFactory<>("discription"));
        
        tag4.setCellValueFactory(new PropertyValueFactory<>("tag"));
        discription4.setCellValueFactory(new PropertyValueFactory<>("discription"));
        
        ftag1.setItems(data1);
        ftag2.setItems(data2);
        ftag3.setItems(data3);
        ftag4.setItems(data4);
        
        db.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showFile.setOnAction((ActionEvent e) -> {
            Desktop d = Desktop.getDesktop();
                
                //DataBase b=new DataBase();
                db.fileWrite();
            try {
                d.open(new File("FeederData.csv"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        initializeGroup();
        initializeTable();
    }       
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author mrunal
 */
public class DetailsController implements Initializable {
    
    public Pane pane;
    public static int group, index;
    
    /*@FXML
    private void handleButtonAction(java.awt.event.ActionEvent event) {
        System.out.println("You clicked me!");
    }*/
    @FXML private javafx.scene.control.Button closeButton;
    
    @FXML
    private javafx.scene.control.TableView<Readings> rtable1,ytable1,btable1; 
    @FXML
    private TableColumn<Readings,Integer> ri,yi,bi;     
  
     
    @FXML
    private javafx.scene.control.TableView<Readings> rtable2,ytable2,btable2;    
    @FXML
    private TableColumn<Readings,Integer> rl,yl,bl;     
    @FXML
    private javafx.scene.control.TableView<Readings> rtable3,ytable3,btable3;    
    @FXML
    private TableColumn<Readings,Integer> rph,yph,bph;      
    @FXML
    private javafx.scene.control.TableView<Readings> rtable4,ytable4,btable4;    
    @FXML
    private TableColumn<Readings,Integer> rpo,ypo,bpo;      
    @FXML
    private javafx.scene.control.TableView<Readings> rtable5,ytable5,btable5;    
    @FXML
    private TableColumn<Readings,Integer> rpf,ypf,bpf;     
    @FXML
    private javafx.scene.control.TableView<Readings> rtable6,ytable6,btable6;    
    @FXML
    private TableColumn<Readings,Integer> re,ye,be;     
    @FXML
    private javafx.scene.control.Button graphButton;
    @FXML
    private javafx.scene.control.ToggleButton onoff;
   // @FXML
    //private javafx.scene.control.Button graphButton;
    
    DataBase db;
    FXMLDocumentController t;
    public static int getFeederNumber(int group, int index){
        return (group-1)*10 + index;
    }
    public static void setGroup(int g, int i){
        System.out.println("in setgroup group "+group);
        group = g;
        index = i;
    }
    public void setData(Pane p, int g, int i, DataBase db, FXMLDocumentController fxctl){
        pane = p;
        group = g;
        index = i;
        //System.out.println("SetData "+group);
        this.db = db;
        t = fxctl;
    }
    
    public void addReadings(double[] d){
        //System.out.println(Arrays.toString(d));
        ObservableList<Readings> dt1=FXCollections.observableArrayList(
             new Readings(d[6])                      
        );
        ObservableList<Readings> dt2=FXCollections.observableArrayList(
                  new Readings(d[7])
            );
        ObservableList<Readings> dt3=FXCollections.observableArrayList(
                  new Readings(d[8])
            );
        ObservableList<Readings> dt4=FXCollections.observableArrayList(
                  new Readings(d[0])
            );
        ObservableList<Readings> dt5=FXCollections.observableArrayList(
                  new Readings(d[1])
            );
        ObservableList<Readings> dt6=FXCollections.observableArrayList(
                  new Readings(d[2])
            );
        ObservableList<Readings> dt7=FXCollections.observableArrayList(
                  new Readings(d[3])
            );
        ObservableList<Readings> dt8=FXCollections.observableArrayList(
                  new Readings(d[4])
            );
        ObservableList<Readings> dt9=FXCollections.observableArrayList(
                  new Readings(d[5])
            );
        ObservableList<Readings> dt10=FXCollections.observableArrayList(
                  new Readings(d[9])                      
            );
        ObservableList<Readings> dt11=FXCollections.observableArrayList(
                  new Readings(d[10])
            );

        ObservableList<Readings> dt12=FXCollections.observableArrayList(
                  new Readings(d[11])
            );  
        ObservableList<Readings> dt13=FXCollections.observableArrayList(
                  new Readings(d[12])
            );
        ObservableList<Readings> dt14=FXCollections.observableArrayList(
                  new Readings(d[13])
            );
        ObservableList<Readings> dt15=FXCollections.observableArrayList(
                  new Readings(d[14])
            );
        ObservableList<Readings> dt16=FXCollections.observableArrayList(
                  new Readings(d[15])
            );
        ObservableList<Readings> dt17=FXCollections.observableArrayList(
                  new Readings(d[16])
            );
        ObservableList<Readings> dt18=FXCollections.observableArrayList(
                  new Readings(d[17])
            );
    
    
         ri.setCellValueFactory(new PropertyValueFactory<>("i"));
         yi.setCellValueFactory(new PropertyValueFactory<>("i"));
         bi.setCellValueFactory(new PropertyValueFactory<>("i"));
         
         rl.setCellValueFactory(new PropertyValueFactory<>("i"));
         yl.setCellValueFactory(new PropertyValueFactory<>("i"));
         bl.setCellValueFactory(new PropertyValueFactory<>("i"));
         
         rph.setCellValueFactory(new PropertyValueFactory<>("i"));
         yph.setCellValueFactory(new PropertyValueFactory<>("i"));
         bph.setCellValueFactory(new PropertyValueFactory<>("i"));
         
         rpo.setCellValueFactory(new PropertyValueFactory<>("i"));
         ypo.setCellValueFactory(new PropertyValueFactory<>("i"));
         bpo.setCellValueFactory(new PropertyValueFactory<>("i"));
         
         rpf.setCellValueFactory(new PropertyValueFactory<>("i"));
         ypf.setCellValueFactory(new PropertyValueFactory<>("i"));
         bpf.setCellValueFactory(new PropertyValueFactory<>("i"));
         
         re.setCellValueFactory(new PropertyValueFactory<>("i"));
         ye.setCellValueFactory(new PropertyValueFactory<>("i"));
         be.setCellValueFactory(new PropertyValueFactory<>("i"));
        
       
         rtable1.setItems(dt1);
         ytable1.setItems(dt2);
         btable1.setItems(dt3);
         
         rtable2.setItems(dt4);
         ytable2.setItems(dt5);
         btable2.setItems(dt6);
         
         rtable3.setItems(dt7);
         ytable3.setItems(dt8);
         btable3.setItems(dt9);
         
         rtable4.setItems(dt10);
         ytable4.setItems(dt11);
         btable4.setItems(dt12);
         
         rtable5.setItems(dt13);
         ytable5.setItems(dt14);
         btable5.setItems(dt15);
         
         rtable6.setItems(dt16);
         ytable6.setItems(dt17);
         btable6.setItems(dt18);
    }
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    public void setReadings(){
        //System.out.println("Group = "+group);
        int n = getFeederNumber(group, index);
       // System.out.println("Feeder number "+n);
        double[] readings = CurrentDB.getCurrentReadings(n);
        
        addReadings(readings);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hii");
        //addReadings();
        System.out.println("group in initialize "+group);
        
        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                            setReadings();
                    }
                },0,1000);
                
        int fno = getFeederNumber(group,index);
        
        if(CurrentDB.getStatus(fno).equals("on")){
            onoff.setText("OFF");
            onoff.setSelected(false);
        }
        else{
            onoff.setText("ON");
            onoff.setSelected(true);
        }
        
        onoff.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            //System.out.println(" changed from " + oldValue + " to " + newValue);
            int n = getFeederNumber(group,index);
            System.out.println("Shutting down feeder = "+n);
            if(newValue == false){
                CurrentDB.updateStatus(n, "on");
                onoff.setText("OFF");
            }
            else{
                CurrentDB.updateStatus(n, "off");
                onoff.setText("ON");
            }
            t.updateView(group, index);
        }));
        graphButton.setOnAction((javafx.event.ActionEvent event) -> 
        {
            new Graph(group,index,db);
        });       
    }
}
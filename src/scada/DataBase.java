package scada;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.*;


/**
 *
 * @author Rahul
 */

public class DataBase extends Thread implements Serializable{
    public String file = "data.csv";
    CSVWriter writer;
    public String currentFile="currentReadings.csv";
    
    
    
    public DataBase(){
        super();
        try{
            writer = new CSVWriter(new FileWriter(file,true));
        }
        catch(Exception e){
            System.out.println("Error in DataBase constructor : "+e);
        }
    }
    
    
    public List<String[]> getData(){
        CSVReader reader = null;
        List<String[]> list = null;
        try{
            writer.close();
            reader = new CSVReader(new FileReader(file),',');
            //reader = new CSVReader(new FileReader(file),',');
            list = reader.readAll();
            reader.close();
            writer = new CSVWriter(new FileWriter(file,true));
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public void writeData(String s){
        try{
            //System.out.println(new Timestamp(new Date().getTime()));
            s+=",\""+new Timestamp(new Date().getTime())+"\"";
            String []arr = s.split(",");
            writer.writeNext(arr);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void stopDB(){
        try{
            String s = InputClass.getNullString();
            System.out.println(new Timestamp(new Date().getTime()));
            s+=",\""+new Timestamp(new Date().getTime())+"\"";
            String []arr = s.split(",");
            for(int i=0; i<17; i++)
                writer.writeNext(arr);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void close(){
        try{
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    @Override
    public void run(){
        CurrentDB cdb = new CurrentDB();
        try{
            while(true){
                //System.out.println(InputClass.getInputString());
                String s="",status;
                for(int i=1; i<=17; i++){
                    status = CurrentDB.getStatus(i);
                  
                    if(status.equals("off")){
                        s = InputClass.getNullString();
                    }
                    else
                        s = InputClass.getInputString();
                   
                    writeData(s);
                    cdb.updateTable(i, s);
                }
                //System.out.println(Arrays.toString(InputClass.getInputArray()));
                Thread.sleep(1000);
        }
        }
        catch(InterruptedException e){
            System.out.println("in database run method "+e);
        }
    }
    public void fileWrite()
    {
          try (BufferedReader inputStream = new BufferedReader(new FileReader("data.csv")))
          {
                File newFile = new File("FeederData.csv");
                
                FileWriter filewriter = new FileWriter(newFile.getAbsoluteFile());
                try (BufferedWriter outputStream = new BufferedWriter(filewriter))
                {
                    for(int i=0;i<20;i++)
                    {
                      String count;
                      while ((count = inputStream.readLine()) != null)
                      {
                          outputStream.write(count+"\n");
                      }
                      outputStream.flush();
                    }

                }
              catch(IOException e){
              System.out.println(""+e);
              }
          }
          catch(IOException e){
              System.out.println(""+e);
          }
    }
}
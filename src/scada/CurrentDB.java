package scada;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
/**
 *
 * @author IT Lab
 */
public class CurrentDB {
    
    static Connection con = null;
    
    static{
        try{
            System.out.println("connecting to sqlite");
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:dbname.db");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public CurrentDB(){
        try{
            
    
            Statement stmt = con.createStatement();
            
            String queryString = "CREATE TABLE IF NOT EXISTS currentData(id INT PRIMARY KEY , tag VARCHAR(5), description VARCHAR(50), status VARCHAR(5)";
            queryString +=", vry REAL, vyb REAL,vrb REAL,vrn REAL,vyn REAL,vbn REAL";
            queryString +=", ir REAL,iy REAL,ib REAL";
            queryString += ", pr REAL, py REAL, pb REAL";
            queryString += ", pfr REAL, pfy REAL, pfb REAL";
            queryString += ", er REAL, ey REAL, eb REAL";
            
            queryString += ", time VARCHAR(20));";
            //System.out.print(queryString);
            stmt.executeUpdate(queryString);           
        }
        catch(Exception e){
            
            System.out.println("In currentDB constructor"+e);
        }
    }
    
    public void insertData(){
        try{
            //System.out.println("in insertdata");
            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Timestamp(new java.util.Date().getTime())).toString();
            //PreparedStatement ps = con.prepareStatement("insert into currentData values(?,?,?,\"on\",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,\""+time+"\")");
            PreparedStatement ps = con.prepareStatement("insert into currentData values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(4,"on");
            ps.setString(23,time);
            String[] tag = new String[17];
            String[] description = new String[17];
            tag[0] = "AI";
            tag[1] = "BI";
            tag[2] = "CI";
            tag[3] = "DI";
            tag[4] = "EI";
            tag[5] = "FI";
            tag[6] = "GI";
            tag[7] = "HI";
            
            tag[8] = "AII";
            tag[9] = "BII";
            tag[10] = "CII";
            tag[11] = "DII";
            tag[12] = "EII";
            tag[13] = "FII";
            tag[14] = "GII";
            tag[15] = "HII";
            tag[16] = "III";
            
            description[0] = "electrical Computer-4";
            description[1] = "Director Office,Gulabchand Reasearch-3";
            description[2] = "Applied Old Computer - 2";
            description[3] = "Hydrawlic Lab - 18";
            description[4] = "CCF - 17";
            description[5] = "Electrical Dept - 5";
            description[6] = "Electronics - 1";
            description[7] = "PG Lab,Bank Of India.";
            description[8] = "Stage Applied, Mech, Civil Staff- 11";
            description[9] = "Stage Applied, IT - 16";
            description[10] = "Generator Room";
            description[11] = "Workshop 12";
            description[12] = "Cyber Host.,New Liabrary,Director bunglow-10";
            description[13] = "H.E. Lab - 9";
            description[14] = "Applied Computer";
            description[15] = "CSE,Cyber hostel,elc comp,water pump";            
            description[16] = "Data modified";            
            
            for(int i=5;i<=22;i++)
                ps.setDouble(i,0.0);
            for(int i=0; i<17; i++)
            {
                ps.setInt(1,i+1);
                ps.setString(2,"\""+tag[i]+"\"");
                ps.setString(3,"\""+description[i]+"\"");
               // System.out.println("Before update");
                ps.executeUpdate();
           //     System.out.println("i = "+i);
            }
          //  System.out.println("byee");
        }
        catch(SQLException e){
            System.out.println("in insertData "+e);
        }
    }
    
    /*public void updateTable(int id, double vry,double vyb,double vrb,double vrn,double vyn,double vbn,ir,iy,ib,pr,py,pb){
        String queryStatement = "update table currentData set vry,vyb,vrb,vrn,vyn,vbn,ir,iy,ib,pr,py,pb,pfr,pfy,pfb,er,ey,eb";
    }*/
    
    public static void updateStatus(int index, String status){
        String query = "update currentData set status = \""+status+"\" where id = "+index+";";
        System.out.println(query);
        try{
            Statement st = con.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static String getStatus(int index){
        String query = "select status from currentData where id = "+index+";";
        try{
            Statement str = con.createStatement();
            ResultSet rsr = str.executeQuery(query);
            if(rsr.next())
                return rsr.getString(1);
            else{
                System.out.println("no row in rs");
            }
            return "error";
        }
        catch(SQLException e){
            System.out.println("in getstatus "+e);
        }
        return "";
    }
    
    public void updateTable(int id,String s){
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) as cnt from currentData");
            rs.next();
            int n = rs.getInt("cnt");
            //System.out.println("count "+n);
            if(n == 0)
            {
                insertData();
            }
           // System.out.println("hiiii");
            String queryStatement = "update currentData set vry=?,vyb=?,vrb=?,vrn=?,vyn=?,vbn=?,ir=?,iy=?,ib=?,pr=?,py=?,pb=?,pfr=?,pfy=?,pfb=?,er=?,ey=?,eb=?, time=? where id="+id+";";
            PreparedStatement ps = con.prepareStatement(queryStatement);
          //  System.out.println("hoooo");
            String[] str = s.split(",");
            for(int i=1; i<19; i++){
                ps.setDouble(i, Double.parseDouble(str[i-1]));
            }
            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Timestamp(new java.util.Date().getTime())).toString();
            ps.setString(19,time);
            //System.out.println(ps);
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println("in updatetable "+e);
        }
    }
    public static double[] getCurrentReadings(int index){
        double[] readings = new double[18];
        try{
            int i=0;
            Statement st = con.createStatement();
            //System.out.println("Your index is "+index);
            ResultSet rs = st.executeQuery("select vry,vyb,vrb,vrn,vyn,vbn,ir,iy,ib,pr,py,pb,pfr,pfy,pfb,er,ey,eb,status from currentData where id = "+(index));
            i=0;
            rs.next();
            String status = rs.getString(19);
            //System.out.println(rs.getDouble(1)+""+rs.getDouble(2)+""+rs.getDouble(3)+"");
            if(status.equals("off"))
            {
                for( i=0; i<18; i++)
                    readings[i] = 0.0;
            }
            else{
                for( i=1;i<=18;i++)
                    readings[i-1] = rs.getDouble(i);
            }
        }
        catch(Exception e){
            System.out.println("in currentdb getcurrentreadings "+e);
        }
        //System.out.println(Arrays.toString(readings));
        return readings;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import java.sql.Timestamp;
import java.util.*;


class InputClass{
    static double vry,vyb,vrb,vrn,vyn,vbn;
    static double ir,iy,ib;
    static double pr,py,pb;
    static double pfr,pfy,pfb;
    static double er,ey,eb;
    
    static double stdLineVoltage, stdPhaseVoltage, stdCurrent, stdPower, stdPF;
    
    static{
        vry = vyb = vrb = stdLineVoltage = 440;
        vrn = vyn = vbn = stdPhaseVoltage = 230;
        ir = iy = ib = stdCurrent = 7;
        pr = py = pb = stdPower = 1.5;
        pfr = pfy = pfb = stdPF = 0.8;
        
        er = pr*24;
        ey = py*24;
        eb = pb*24;
    }
    
    static double round(double d){
        long factor = (long) Math.pow(10, 2);
        d = d * 100;
        long tmp = Math.round(d);
        return (double) tmp / 100;
    }
    
    static int getRandomDeviation(){
        if(Math.random()>0.5){
            return ((int)(Math.random()*10))%10;
        }
        return -1*((int)(Math.random()*10))%10;
    }
    
    static double[] getInputArray(){
        int dev = InputClass.getRandomDeviation();
        
        vry = stdLineVoltage + stdLineVoltage*dev/100.0;
        vyb = stdLineVoltage + stdLineVoltage*dev/100.0;
        vrb = stdLineVoltage + stdLineVoltage*dev/100.0;
        
        vrn = stdPhaseVoltage + stdPhaseVoltage*dev/100.0;
        vyn = stdPhaseVoltage + stdPhaseVoltage*dev/100.0;
        vbn = stdPhaseVoltage + stdPhaseVoltage*dev/100.0;
        
        ir = stdCurrent + stdCurrent*getRandomDeviation()/100.0;
        iy = stdCurrent + stdCurrent*getRandomDeviation()/100.0;
        ib = stdCurrent + stdCurrent*getRandomDeviation()/100.0;
        
        pr = stdPower + stdPower*getRandomDeviation()/100.0;
        py = stdPower + stdPower*getRandomDeviation()/100.0;
        pb = stdPower + stdPower*getRandomDeviation()/100.0;
        
        pfr = stdPF + stdPF*dev/100.0;
        pfy = stdPF + stdPF*dev/100.0;
        pfb = stdPF + stdPF*dev/100.0;
        
        er = pr*24;
        ey = py*24;
        eb = pb*24;
        
        double[] arr = new double[18];
        
        arr[0] = round(vry);
        arr[1] = round(vyb);
        arr[2] = round(vrb);
        
        arr[3] = round(vrn);
        arr[4] = round(vyn);
        arr[5] = round(vbn);
        
        arr[6] = round(ir);
        arr[7] = round(iy);
        arr[8] = round(ib);
        
        arr[9] = round(pr);
        arr[10] = round(py);
        arr[11] = round(pb);
        
        arr[12] = round(pfr);
        arr[13] = round(pfy);
        arr[14] = round(pfb);
        
        arr[15] = round(er);
        arr[16] = round(ey);
        arr[17] = round(eb);
        
        //arr[18] = new Timestamp(new Date().getTime());
        return arr;
    }
    
    static String getNullString(){
        String s = "";
        for(int i=0; i<18; i++){
            s+=0;
            if(i!=17)
                s+=',';
        }
        return s;
    }
    
    static String getInputString(){
        double[] d = getInputArray();
        String s = "";
        for(int i=0; i<18; i++){
            s+=d[i];
            if(i!=17)
                s+=',';
        }
        return s;
    }
}
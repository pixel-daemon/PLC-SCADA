/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mrunal
 */
public class addReadings {
    private final SimpleIntegerProperty red1;
    private final SimpleIntegerProperty yellow1;
    private final SimpleIntegerProperty blue1;

    public addReadings(int red,int yellow,int blue){
        this.red1=new SimpleIntegerProperty(red);
        this.yellow1=new SimpleIntegerProperty(yellow);
        this.blue1=new SimpleIntegerProperty(blue);

    }

    public int getRed() {
        return red1.get();
    }

    public int getYellow() {
        return yellow1.get();
    }
    
     public int getBlue() {
        return blue1.get();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;

import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author mrunal
 */
public class Readings {
    private final SimpleDoubleProperty i1;
     
    public Readings(double i){
        this.i1=new SimpleDoubleProperty(i);
       
    }
    public double getI() {
        return i1.get();
    }
}

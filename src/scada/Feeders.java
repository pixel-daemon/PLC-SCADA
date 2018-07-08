/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scada;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mrunal
 */
public class Feeders {
    private final SimpleStringProperty tag;
    private final SimpleStringProperty discription;

    public Feeders(String tag,String discription){
        this.tag=new SimpleStringProperty(tag);
        this.discription=new SimpleStringProperty(discription);
    }

    public String getTag() {
        return tag.get();
    }

    public String getDiscription() {
        return discription.get();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author MLBaiche
 */
public class Plage {
    private Date date;
    private Time timeDebut;
    private int nbPlaceDispo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeDebut() {
        return timeDebut;
    }

    public void setTimeDebut(Time timeDebut) {
        this.timeDebut = timeDebut;
    }

    public int getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }
    
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MohamedBahaaEddine
 */
public class Reservation {
    private int idReservation;
    private int idAnnonce;
    private int nbPersonne;
    private Annonce annonce;
    private Plage plage;
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    
    
    public Plage getPlage() {
        return plage;
    }

    public void setPlage(Plage plage) {
        this.plage = plage;
    }
    

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    
    

    public int getIdReservation() {
        return idReservation;
    }

    public Reservation() {
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Reservation(int ID_RESERVATION, int ID_ANNONCE) {
        this.idReservation = ID_RESERVATION;
        this.idAnnonce = ID_ANNONCE;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID_RESERVATION=" + idReservation + ", ID_ANNONCE=" + idAnnonce + '}';
    }
}

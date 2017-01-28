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
    private int ID_RESERVATION;
    private int ID_ANNONCE;

    public int getID_RESERVATION() {
        return ID_RESERVATION;
    }

    public Reservation() {
    }

    public int getID_ANNONCE() {
        return ID_ANNONCE;
    }

    public void setID_RESERVATION(int ID_RESERVATION) {
        this.ID_RESERVATION = ID_RESERVATION;
    }

    public void setID_ANNONCE(int ID_ANNONCE) {
        this.ID_ANNONCE = ID_ANNONCE;
    }

    public Reservation(int ID_RESERVATION, int ID_ANNONCE) {
        this.ID_RESERVATION = ID_RESERVATION;
        this.ID_ANNONCE = ID_ANNONCE;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID_RESERVATION=" + ID_RESERVATION + ", ID_ANNONCE=" + ID_ANNONCE + '}';
    }
}

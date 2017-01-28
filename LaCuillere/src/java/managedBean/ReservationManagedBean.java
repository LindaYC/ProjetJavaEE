/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Manager.ReadManager;
import Manager.WriteManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.Annonce;
import model.Reservation;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author YC-Linda
 */
@javax.faces.bean.ManagedBean(name="reservationManagedBean")
@SessionScoped
public class ReservationManagedBean extends ParentManagedBean implements Serializable{
    private String user;
    private Reservation reservation;
    private List<Reservation> allReservations;
    private int nbrOfReservationsByUser;
    
    @Autowired
    private WriteManager writeManager;
    
    @Autowired
    private ReadManager readManager;
    
    
    public ReservationManagedBean(){
        reservation=new Reservation();
        Annonce cat=new Annonce();
        HttpSession session = getHttpSession();
        user=(String)session.getAttribute("USER");
    }
   
    public void addReservation(){
        try {            
            int res=writeManager.addReservation(new Reservation(2,2));
            redirect("espaceClient.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RestaurantManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public List<Reservation> getReservationByUser() {
        return allReservations;
    }
    
    public int getNbrOfReservationsByUser(){
        return nbrOfReservationsByUser;
    }
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
}

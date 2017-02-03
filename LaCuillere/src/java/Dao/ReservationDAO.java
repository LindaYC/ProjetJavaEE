/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Annonce;
import model.Categorie;
import model.Plage;
import model.Reservation;
import model.Restaurant;

/**
 *
 * @author MLBaiche
 */
public class ReservationDAO {
    
    private static final String INSERT_RESERVATION="INSERT INTO T_RESERVATION(ID_RESERVATION, ID_ANNONCE,NB_PERSONNE,JOUR,TIME_DEBUT) VALUES (?,?,?,?,?)";
    private static final String NEXT_VAL="SELECT NEXTVAL('SQ_ID_RESERVATION')";
    private static final String SEARCH_RESERVATION = "SELECT r.ID_RESERVATION, r.NB_PERSONNE, r.JOUR, r.TIME_DEBUT, rest.ID_RESTAURANT, rest.NOM, rest.CATEGORIE, rest.ADRESSE, rest.VILLE "
            + "FROM T_RESERVATION r, T_RESTAURANT rest, T_USER_RESERVATION ur, T_ANNONCE a " +
                                        "WHERE ur.MAIL=? AND ur.ID_RES=r.ID_RESERVATION " +
                                        "AND r.ID_ANNONCE = a.ID_ANNONCE " +
                                        "AND a.ID_RESTAURANT = rest.ID_RESTAURANT ";
    private DataSource dataSource;
    private int nbrOfReservations;
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public int createReservation(Reservation reservation) {
        
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_RESERVATION);
            int i=1;
            ps.setInt(i++, reservation.getIdReservation());
            ps.setInt(i++, reservation.getIdAnnonce());
            
            rs = ps.executeUpdate();
            ResultSet resSet = ps.getGeneratedKeys();
            if(resSet.next()){
                rs=resSet.getInt(1);
            }
            
            System.out.println("Reservation effectuée : "+reservation.getIdReservation());    
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return rs;
            }
    
    public List<Reservation> getReservationByUser(String mail) {
        Connection con = null;
        ResultSet rs=null;
        List<Reservation> result=new ArrayList<Reservation>();
         try {
            
          con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SEARCH_RESERVATION);        
            ps.setString(1, mail);
            rs = ps.executeQuery();
            while(rs.next()){
                 // r.ID_RESERVATION, r.NB_PERSONNE,r.JOUR,r.TIME_DEBUT, rest.ID_RESTAURANT,rest.NOM,rest.CATEGORIE,rest.ADRESSE,rest.VILLE
                Reservation rest=new Reservation();
                rest.setIdReservation(rs.getInt("ID_RESERVATION"));
                rest.setNbPersonne(rs.getInt("NB_PERSONNE"));
                Plage p = new Plage();
                p.setDate(rs.getDate("JOUR"));
                p.setTimeDebut(rs.getTime("TIME_DEBUT"));
                rest.setPlage(p);
                Annonce a = new Annonce();
                Restaurant restaurant = new Restaurant();
                a.setRestaurant(restaurant);
                
                restaurant.setIdRestaurant(rs.getInt("ID_RESTAURANT"));
                restaurant.setNom(rs.getString("NOM"));
                Categorie categorie = new Categorie();
                categorie.setNom(rs.getString("CATEGORIE"));
                restaurant.setCategorie(categorie);
                restaurant.setAdresse(rs.getString("ADRESSE"));
                restaurant.setVille(rs.getString("VILLE"));
                rest.setAnnonce(a);
                result.add(rest);
                
                System.out.println("Réservation trouvée : "+rest.getIdReservation());
            }
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return result;
        
    }
    public int getNbrOfReservationsByUser(String email) throws SQLException{
        Connection con = null;
        ResultSet rs = null;
        nbrOfReservations = 0;
        PreparedStatement ps = con.prepareStatement(SEARCH_RESERVATION);
            if(email != null){
                rs = ps.executeQuery();
                nbrOfReservations = rs.getFetchSize();
            } else 
                System.out.println("0 réservations faites!");
            
            return nbrOfReservations;
    }
    public int nextVal() {
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(NEXT_VAL);
            ResultSet resSet = ps.executeQuery();
            
            if(resSet.next()){
                rs=resSet.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(RestaurantDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return rs;
        
    }

    public void createReservation(int idRes, int idAnnonce,int nbPersonne,Date date,Time heure) {
       Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_RESERVATION);
            int i=1;
            ps.setInt(i++, idRes);
            ps.setInt(i++, idAnnonce);
            ps.setInt(i++, nbPersonne);
            ps.setDate(i++, date);
            ps.setTime(i++, heure);
            
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(RestaurantDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
    
        
    }
}

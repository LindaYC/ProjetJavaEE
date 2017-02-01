/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Categorie;
import model.Reservation;

/**
 *
 * @author MLBaiche
 */
public class ReservationDAO {
    
    private static final String INSERT_RESERVATION="INSERT INTO T_RESERVATION(ID_RESERVATION, ID_ANNONCE) VALUES (?,?)";
    private String NEXT_VAL="SELECT NEXTVAL('SQ_ID_RESERVATION')";
    private String SEARCH_RESERVATION = "SELECT * FROM T_RESERVATION, T_USER_RESERVATION, T_ANNONCE_PLAGE, T_ANNONCE " +
                                        "WHERE T_RESERVATION.ID_RESERVATION = T_USER_RESERVATION.ID_RES " +
                                        "AND T_RESERVATION.ID_ANNONCE = T_ANNONCE_PLAGE.ID_ANNONCE " +
                                        "AND T_ANNONCE.ID_ANNONCE = T_ANNONCE_PLAGE.ID_ANNONCE " +
                                        "AND T_USER_RESERVATION.MAIL = ";
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
            ps.setInt(i++, reservation.getID_RESERVATION());
            ps.setInt(i++, reservation.getID_ANNONCE());
            
            rs = ps.executeUpdate();
            ResultSet resSet = ps.getGeneratedKeys();
            if(resSet.next()){
                rs=resSet.getInt(1);
            }
            
            System.out.println("Reservation effectuée : "+reservation.getID_RESERVATION());    
            
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
    
    public List<Reservation> getReservationByUser(/*@RequestParam("id")*/ String mail) {
        Connection con = null;
        ResultSet rs=null;
        List<Reservation> result=new ArrayList<Reservation>();
         try {
            
            int i=1;        
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SEARCH_RESERVATION+mail+";");        
            rs = ps.executeQuery();
            while(rs.next()){
                Reservation rest=new Reservation();
                rest.setID_RESERVATION(rs.getInt("getID_RESERVATION"));
                rest.setID_ANNONCE(rs.getInt("getID_ANNONCE"));;
                result.add(rest);
                System.out.println("Réservation trouvée : "+rest.getID_RESERVATION());
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

    public void createReservation(int idRes, int idAnnonce) {
       Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_RESERVATION);
            ps.setInt(1, idRes);
            ps.setInt(2, idAnnonce);
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

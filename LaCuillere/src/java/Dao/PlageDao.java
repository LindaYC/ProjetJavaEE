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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author MLBaiche
 */
public class PlageDao {
    private DataSource dataSource;
    
    private static final String UPDATE_PLACE_DISPO="UPDATE T_PLAGE SET NB_PLACE_DISPO=? WHERE JOUR=? AND TIME_DEBUT=?";
    private static final String INSERT_LINE="INSERT INTO T_PLAGE (JOUR,TIME_DEBUT,NB_PLACE_DISPO) VALUES(?,?,?)";

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void updatePlaceDispo(Time heure, Date sqlDate, int i) {
       Connection con = null;
          
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_PLACE_DISPO);
            ps.setInt(1, i);
            ps.setDate(2, sqlDate);
            ps.setTime(3, heure);
            
             ps.executeUpdate();
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        
    }

    public void addPlage(Time heure, Date sqlDate, int nbPlace) {
        Connection con = null;
          
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_LINE);
            
            ps.setDate(1, sqlDate);
            ps.setTime(2, heure);
            ps.setInt(3, nbPlace);
             ps.executeUpdate();
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
                
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        
    }
    
    
}

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
import model.Annonce;

/**
 *
 * @author MLBaiche
 */
public class AnnonceDao {
     private DataSource dataSource;
     private final static String INSERT_ANNONCE="INSERT INTO T_ANNONCE(ID_ANNONCE,NUM_PHONE,EMAIL,ID_RESTAURANT) VALUES(?,?,?,?)";
     private final static String NEXT_VAL="SELECT NEXTVAL('SQ_ID_ANNONCE')";
     private final static String GET_ID_ANNONCE_BY_ID_RESTAURANT="SELECT ID_ANNONCE FROM T_ANNONCE WHERE ID_RESTAURANT=?";
     private final static String EXIST_PLAGE="SELECT * FROM T_ANNONCE_PLAGE WHERE ID_ANNONCE = ? AND JOUR = ? AND TIME_DEBUT=?";
     private final static String INSERT_PLAGE="INSERT INTO T_ANNONCE_PLAGE(JOUR,TIME_DEBUT,ID_ANNONCE) VALUES(?,?,?)";
     
     public int createAnnonce(Annonce annonce){
         int res=0;
         Connection con=null;
        
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_ANNONCE);
            int i=1;
            ps.setInt(i++, annonce.getIdAnnonce());
            ps.setString(i++, annonce.getNumPhone());
            ps.setString(i++, annonce.getEmail());
            ps.setInt(i++, annonce.getIdRestaurant());
            
            rs = ps.executeUpdate();
            System.out.println("Resultat insertion : "+rs);
            
            
            
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
            
            System.out.println("Clé primaine généré : "+rs);
            
            
            
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getAnnonceByIdRestaurant(int idRestaurant) {
         Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_ID_ANNONCE_BY_ID_RESTAURANT);
            ps.setInt(1,idRestaurant);
            ResultSet resSet = ps.executeQuery();
            
            if(resSet.next()){
                rs=resSet.getInt("ID_ANNONCE");
            }
            
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

    public boolean existPlage(Date sqlDate, Time heure, int idAnnonce) {
        Connection con = null;
            boolean rs=false;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(EXIST_PLAGE);
            ps.setInt(1, idAnnonce);
            ps.setDate(2, sqlDate);
            ps.setTime(3, heure);
            
            ResultSet resSet = ps.executeQuery();
            
            if(resSet.next()){
                rs=true;
            }
            
           
            
            
            
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

    public void createPlage(int idAnnonce, Time heure, Date sqlDate) {
         
         Connection con=null;
        
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_PLAGE);
            int i=1;
            ps.setDate(i++, sqlDate);
            ps.setTime(i++, heure);
            ps.setInt(i++,idAnnonce);
            
            rs = ps.executeUpdate();
            System.out.println("Resultat insertion : "+rs);
            
            
            
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

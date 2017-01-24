/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
}

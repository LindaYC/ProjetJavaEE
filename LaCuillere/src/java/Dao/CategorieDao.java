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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Categorie;

/**
 *
 * @author MLBaiche
 */
public class CategorieDao {
    private DataSource dataSource;
    
    private static final String SELECT_ALL="SELECT * FROM T_CATEGORIE";
    private static final String INSERT_CATEGORIE="INSERT INTO T_CATEGORIE(CATEGORIE,LIBELLE) VALUES(?,?)";

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Categorie> loadAll() {
        List<Categorie> liste= new ArrayList<Categorie>();
        Connection con = null;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Categorie categorie = new Categorie();
                categorie.setNom(rs.getString("CATEGORIE"));
                categorie.setDescription(rs.getString("LIBELLE"));
               
                //  rajouter les autres....
                liste.add(categorie);
                System.out.println("Categorie : "+categorie.getNom());
            }
			
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
        return liste;
    }

    public boolean existCategorie(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertCategorie(String nom) {
       Connection con = null;
        
 
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_CATEGORIE);
            ps.setString(1, nom);
            // pour le moment le libelle est identique au nom
            ps.setString(2, nom);
            
            ps.executeUpdate();
            
             
             ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
            try {
		con.close();
		} catch (SQLException e) {}
            }      
			
        }
            
            
    }
    
}

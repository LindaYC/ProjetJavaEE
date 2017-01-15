/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Restaurant;

/**
 *
 * @author MLBaiche
 */
public class RestaurantDao {
    
    private static final String INSERT_RESTAURANT="INSERT INTO T_RESTAURANT(ID_RESTAURANT,NOM,ADRESSE,VILLE,NUM_PHONE,EMAIL,NB_PLACE_MAX,TIME_OUVERTURE,TIME_FERMETURE,CATEGORIE,PHOTO) VALUES (NEXTVAL('SQ_ID_RESTAURANT'),?,?,?,?,?,?,?,?,?,?)";
 
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int createRestaurant(Restaurant restaurant) {
        Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_RESTAURANT);
            int i=1;
            ps.setString(i++, restaurant.getNom());
            ps.setString(i++, restaurant.getAdresse());
            ps.setString(i++, restaurant.getVille());
            ps.setString(i++, restaurant.getNumPhone());
            ps.setString(i++, restaurant.getEmail());
            ps.setInt(i++,restaurant.getCapacite());
            ps.setTime(i++, restaurant.getHeureOuverture());
            ps.setTime(i++, restaurant.getHeureFermeture());
            ps.setString(i++, restaurant.getCategorie().getNom());
            
            ps.setBinaryStream(i, restaurant.getPackageBlob(),restaurant.getTaillePhoto());
            
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
    
}

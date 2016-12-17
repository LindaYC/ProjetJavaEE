/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.User;

/**
 *
 * @author MLBaiche
 */
public class UserDao {
    
    private DataSource dataSource;
    
    private final String SELECT_ALL_USER="SELECT * FROM T_USER";
    
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }
    
    public List<User> loadAll(){
        List<User> liste= new ArrayList<User>();
        Connection con = null;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setNom(rs.getString("NOM"));
                user.setPrenom(rs.getString("PRENOM"));
                user.setEmail(rs.getString("MAIL"));
                //  rajouter les autres....
                System.out.println("nom : "+user.getNom());
                liste.add(user);
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
}

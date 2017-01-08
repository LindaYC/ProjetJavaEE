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
    private final String SELECT_USER_BY_EMAIL="SELECT * FROM T_USER WHERE MAIL=?";
    private final String INSERT_USER="INSERT INTO T_USER(NOM,PRENOM,MAIL,PASSWORD,TELEPHONE,PROFIL,BONUS) VALUES(?,?,?,?,?,?,?)";
    
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
                user.setPassword(rs.getString("PASSWORD"));
                user.setTelephone(rs.getString("TELEPHONE"));
                user.setProfil(rs.getInt("PROFIL"));
                user.setBonus(rs.getInt("BONUS"));
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
    
        public int addUser(User user){
            Connection con = null;
            int rs=0;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_USER);
            int i=1;
            ps.setString(i++, user.getNom());
            ps.setString(i++, user.getPrenom());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setString(i++, user.getTelephone());
            ps.setInt(i++,user.getProfil());
            ps.setInt(i, user.getBonus());
            System.out.println("USER : "+user.toString());
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

    public boolean existsUser(String email) {
        Connection con = null;
        boolean result=false;
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            result=rs.next();
            System.out.println("USER EXISTS : "+result);
			
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
        return result;
    }
}

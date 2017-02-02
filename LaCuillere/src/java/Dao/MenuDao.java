/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.ByteArrayOutputStream;
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
import model.Menu;
import model.Restaurant;

/**
 *
 * @author MLBaiche
 */
public class MenuDao {
    private DataSource dataSource;
    private static final String SELECT_MENU_BY_RESTAURANT="SELECT * FROM T_MENU WHERE ID_MENU IN (SELECT m.ID_MENU FROM T_RESTAURANT_MENU m"
            + " WHERE m.ID_RESTAURANT=?)";
    private static final String NEXT_VAL="SELECT NEXTVAL('SQ_ID_MENU')";
    private static final String INSERT_MENU="INSERT INTO T_MENU(ID_MENU,NOM,PRIX,ENTREE,PLAT,DESSERT) VALUES(?,?,?,?,?,?)";

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Menu> loadListMenuByRestaurant(int idRestaurant) {
         Connection con = null;
        ResultSet rs=null;
        List<Menu> result=new ArrayList<Menu>();
        
        
        
         try {
            
           
        con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_MENU_BY_RESTAURANT);
            ps.setInt(1, idRestaurant);
          
            rs = ps.executeQuery();
            
            while(rs.next()){
                Menu menu=new Menu();
                menu.setIdMenu(rs.getInt("ID_MENU"));
                menu.setNom(rs.getString("NOM"));
                menu.setPrix(rs.getInt("PRIX"));
                menu.setReduction(rs.getInt("REDUCTION"));
                menu.setEntree(rs.getString("ENTREE"));
                menu.setPlat(rs.getString("PLAT"));
                menu.setDessert(rs.getString("DESSERT"));
                System.err.println("Menu trouvée :"+menu.getNom());
                result.add(menu);
                
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

    public void createMenu(Menu menu) {
         Connection con = null;
         
        
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_MENU);
            int i=1;
            ps.setInt(i++, menu.getIdMenu());
            ps.setString(i++, menu.getNom());
            ps.setInt(i++, menu.getPrix());
            ps.setString(i++, menu.getEntree());
            ps.setString(i++, menu.getPlat());
            ps.setString(i++, menu.getDessert());
            
            ps.executeUpdate();
            ps.close();
            con.close();
            
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

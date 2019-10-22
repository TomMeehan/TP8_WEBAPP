/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.sql.ResultSet;
import simplejdbc.*;

/**
 *
 * @author pedago
 */
public class DAOExtended extends DAO{
    
    public DAOExtended(DataSource dataSource) {
        super(dataSource);
    }
    
    public List<String> getStates() throws DAOException{
        String sql = "SELECT DISTINCT state FROM customer";
        List<String> states = new ArrayList<String>();
        
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) { // On a trouvé
                            states.add(rs.getString("state"));
                        } // else on n'a pas trouvé, on renverra null
                }
        }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                throw new DAOException(ex.getMessage());
        }

        return states;
    }
    
   
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author onio
 */
public class DataManager {
    
    private static final String JDBC_JNDI = "jdbc/base_medecin_ed_JNDI";
    private DataSource  dataSource = null;
    private static DataManager  instance = null;
    
    public static synchronized DataManager  getInstance()
    {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }
    
    private DataManager()
    {
        try {
            dataSource = (DataSource)new InitialContext().lookup(JDBC_JNDI);
        } catch (NamingException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Standard queries
     * @param inPreQuery
     * @return
     * @throws SQLException 
     */
    public synchronized PreparedStatement   buildStatement(String inPreQuery) throws SQLException
    {
        return dataSource.getConnection().prepareStatement(inPreQuery);
    }
    
    /**
     * 
     * @param inPreQuery
     * @return
     * @throws Exception 
     */
    public synchronized PreparedStatement   buildInsertAutoInc(String inPreQuery) throws Exception
    {
        return dataSource.getConnection().prepareStatement(inPreQuery, 
                Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * 
     * @param inStatement
     * @return
     * @throws SQLException 
     */
    public synchronized int                  doUpdateAutoInc(PreparedStatement inStatement) throws SQLException
    {
        ResultSet   rs = null;
        int         id = -1;
        
        inStatement.executeUpdate();
        rs = inStatement.getGeneratedKeys();

        // particulierement PRATIQUE
        if (rs.next())
            id = rs.getInt(1);

        rs.close();
        
        return id;
    }
    
    /**
     * 
     * @param inStatement
     * @throws Exception 
     */
    public synchronized void                doUpdate(PreparedStatement inStatement) throws Exception
    {
        inStatement.executeUpdate();
    }
    
    /**
     * 
     * @param inStatement
     * @return
     * @throws Exception 
     */
    public synchronized ResultSet           doExecute(PreparedStatement inStatement) throws Exception
    {
        ResultSet   rs = null;

        rs = inStatement.executeQuery();
        
        return rs;
    }
}

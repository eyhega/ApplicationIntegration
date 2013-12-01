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
    
    /**
     * Connection chain
     */
    private static final String JDBC_JNDI = "jdbc/base_medecin_ed_JNDI";
    private DataSource  dataSource = null;
    private static DataManager  instance = null;
    private Connection currentConnection = null;
    
    public static synchronized DataManager  getInstance()
    {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }
    
    private DataManager()
    {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource)initContext.lookup(JDBC_JNDI);
            currentConnection = dataSource.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Standard queries (like select...)
     * @param inPreQuery The query as a String 
     * @return The PrepareStatement associated
     * @throws SQLException 
     */
    public synchronized PreparedStatement   buildStatement(String inPreQuery) throws SQLException
    {
        if(currentConnection == null) {
            currentConnection = dataSource.getConnection();
        }
        return currentConnection.prepareStatement(inPreQuery);
    }
    
    /**
     * Build a preparedSatement for an insert query which required auto incrementation.
     * @param inPreQuery The insert query as String
     * @return The preparedStatement object based on the query
     * @throws Exception 
     */
    public synchronized PreparedStatement   buildInsertAutoInc(String inPreQuery) throws Exception
    {
        if(currentConnection == null) {
            currentConnection = dataSource.getConnection();
        }
        
        return currentConnection.prepareStatement(inPreQuery, 
                Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * Execute an Update query which required auto incrementation
     * @param inStatement The preparedStatement object which contains the query
     * @return The generated ID
     * @throws SQLException 
     */
    public synchronized int                  doUpdateAutoInc(PreparedStatement inStatement) throws SQLException
    {
        ResultSet   rs = null;
        int         id = -1;
        
        inStatement.executeUpdate();
        rs = inStatement.getGeneratedKeys();
        
        if (rs.next()) {
            id = rs.getInt(1);
        }
            
        rs.close();
        
        return id;
    }
    
    /**
     * Execute an UpDate query from a PreparedStatement
     * @param inStatement The object which contains the query
     * @throws Exception 
     */
    public synchronized void                doUpdate(PreparedStatement inStatement) throws Exception
    {
        inStatement.executeUpdate();
    }
    
    /**
     * Execute a query.
     * @param inStatement The preparedStatement which contains the simple query
     * @return The result as a resultSet object.
     * @throws Exception 
     */
    public synchronized ResultSet           doExecute(PreparedStatement inStatement) throws Exception
    {
        ResultSet   rs = null;

        rs = inStatement.executeQuery();
        
        return rs;
    }
    
    public void closeConnection() {
        try {
            currentConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE,"Can not clause the connection", ex);
        }
    }
}

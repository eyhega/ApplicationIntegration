package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author eyheramo
 */
public class DoctorManager {

    /**
     * Connection to the database.
     */
    private Connection _connection = null;
    /**
     * Statement to use the database
     */
    private Statement _statement = null;

    /**
     * Constructor which create the connection.
     */
    public DoctorManager() {
        createConnection();
    }

    public Connection getConnection() {
        return _connection;
    }

    public Statement getStatement() {
        return _statement;
    }

    /**
     * Create the connection to the database.
     */
    public void createConnection() {
        try {
            _connection = getJNDIConnection();
            _statement = _connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Can not load the driver", ex);
        }
    }

    /**
     * Get the JNDI connection. If can not connect to the JNDI database,
     * exceptions will be thrown (detail of exceptions in the logger).
     *
     * @return A Connection object initalized which permit to deal with the
     * database.
     */
    private Connection getJNDIConnection() {
        String DATASOURCE_CONTEXT = "jdbc:jdbc/base_medecin_ed_JNDI";

        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            if (initialContext == null) {
                Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "JNDI problem. Cannot get InitialContext.");
            }
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Failed to lookup datasource.");
            }
        } catch (NamingException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Cannot get connection: " + ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Cannot get connection: " + ex);
        }
        return result;
    }

    /**
     * Get all the doctors stored in the database.
     *
     * @return A ResultSet object which contains the doctors or null if the
     * query failed.
     */
    public ResultSet getAllDoctors() {
        String preparedStatement = "SELECT TITRE, NOM, PRENOM FROM MEDECINS";
        ResultSet result = null;
        try {
            result = _statement.executeQuery(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Can not get all the doctors", ex);
        }

        return result;
    }

    /**
     * Permit to insert a new doctor in the database.
     *
     * @param version Version for the doctor
     * @param title Doctor title
     * @param name Doctor name
     * @param firstname Doctor firstname
     * @return True if the insertion has be done, otherwise false (tuple already
     * existed, ...)
     */
    public boolean insertDoctor(int version, String title, String name, String firstname) {
        String preparedStatement = "INSERT INTO MEDECINS VERSION, TITRE, NOM, PRENOM"
                + " VALUES '" + version + "', '" + title + "', '" + name + "', '" + firstname;
        int nbModify = 0;
        try {
            nbModify = _statement.executeUpdate(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Can not insert the new doctor...", ex);
        }
        return nbModify == 1;
    }

    /**
     * Delete a doctor in the database thank to his id.
     * 
     * @param doctorId The Doctor id.
     * @return True if the doctor has been deleted, false otherwise.
     */
    public boolean deleteDoctor(int doctorId) {
        String preparedStatement = "DELETE FROM MEDECINS WHERE ID = '" + doctorId + "'";

        int nbModify = 0;
        try {
            nbModify = _statement.executeUpdate(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorManager.class.getName()).log(Level.SEVERE, "Can not delete the doctor...", ex);
        }

        return nbModify == 1;
    }
}

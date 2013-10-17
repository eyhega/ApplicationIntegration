/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp1ex1.dal.DataManager;
import tp1ex1.dao.Appointment;

/**
 *
 * @author Gaetan
 */
public class AppointmentManager extends ObjectManager<Appointment>{

    private static AppointmentManager instance = null;
    
    public static synchronized AppointmentManager getInstance() {
        if(instance == null) {
            instance = new AppointmentManager();
        }
        
        return instance;
    }
    
    private AppointmentManager() {
        
    }
    
    @Override
    public List<Appointment> findAll() {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Appointment> appointmentList = null;
        
        try {
            rs = dm.doExecute(dm.buildStatement("SELECT * FROM rv;"));
            appointmentList =buildListFromResultSet(rs);
            rs.close();
        } catch(Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not get all the Appointements.", ex);
        } 
        return appointmentList;
    }

    @Override
    public Appointment save(Appointment objectToSave) {
        try {
            if (exists(objectToSave)) {
                this.update(objectToSave);
            } else {
                this.insert(objectToSave);
            }
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not save the Appointment Object in the DB.", ex);
        }
        return objectToSave;
    }

    @Override
    protected void update(Appointment objectToUpdate) {
         try {
            PreparedStatement ps = DataManager.getInstance().buildStatement(
                    "UPDATE rv SET  jour = ?,id_client = ?,id_creneau = ? WHERE id = ?;");

            ps.setDate(1, objectToUpdate.getJour());
            ps.setInt(2, objectToUpdate.getIdPatient());
            ps.setInt(3, objectToUpdate.getIdCreneau());
            ps.setInt(4, objectToUpdate.getId());
            DataManager.getInstance().doUpdate(ps);
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not update the current Appointment Object.", ex);
        }
    }

    @Override
    protected void insert(Appointment objectToInsert) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildInsertAutoInc(
                    "INSERT INTO rv (jour, id_client, id_creneau)"
                    + " VALUES (?, ?, ?);");

            ps.setDate(1, objectToInsert.getJour());
            ps.setInt(2, objectToInsert.getIdPatient());
            ps.setInt(3, objectToInsert.getIdCreneau());
            objectToInsert.setId(DataManager.getInstance().doUpdateAutoInc(ps));
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not insert the current Appointment.", ex);
        }
    }

    @Override
    public void delete(Appointment objectToDelete) {
        if (exists(objectToDelete)) {
            try {
                DataManager dm = DataManager.getInstance();
                PreparedStatement ps = dm.buildStatement("DELETE from rv WHERE id = ?;");
                ps.setInt(1, objectToDelete.getId());
                dm.doUpdate(ps);
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not delete the current Appointment.", ex);
            }
        }
    }

    @Override
    public boolean exists(Appointment objectToTest) {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Appointment> lst = null;
        try {

            PreparedStatement ps = dm.buildStatement("SELECT * from rv WHERE ID = ?;");

            ps.setInt(1, objectToTest.getId());
            rs = dm.doExecute(ps);
            lst = buildListFromResultSet(rs);
            ps.close();
            rs.close();

            return (!lst.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not test if the current Appointment exists.", ex);
        }

        return (lst != null && !lst.isEmpty());
    }

    @Override
    protected List<Appointment> buildListFromResultSet(ResultSet inResultSet) {
         List<Appointment> lst = new ArrayList<Appointment>();
        try {
            while (inResultSet.next()) {
                Appointment app = new Appointment();

                app.setId(inResultSet.getInt("id"));
                app.setJour(inResultSet.getDate("jour"));
                app.setIdPatient(inResultSet.getInt("id_client"));
                app.setIdCreneau(inResultSet.getInt("id_creneau"));
                
                lst.add(app);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Error while building the result list for Appointment query.", ex);
        }
        return lst;
    }
    
}

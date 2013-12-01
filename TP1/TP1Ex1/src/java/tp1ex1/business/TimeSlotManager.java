/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp1ex1.dal.DataManager;
import tp1ex1.dao.TimeSlot;

/**
 *
 * @author Gaetan
 */
public class TimeSlotManager extends ObjectManager<TimeSlot>{
    
    private static TimeSlotManager instance = null;
    
    public static synchronized  TimeSlotManager getInstance() {
        if(instance == null) {
            instance = new TimeSlotManager();
        }
        
        return instance;
    }
    
    private TimeSlotManager() {
    }

    @Override
    public List<TimeSlot> findAll() {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<TimeSlot> timeSlotList = null;
        
        try {
            PreparedStatement buildStatement = dm.buildStatement("SELECT * FROM creneaux;");
            rs = dm.doExecute(buildStatement);
            timeSlotList = buildListFromResultSet(rs);
            rs.close();
            buildStatement.close();
        } catch(Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not get all the TimeSlots.", ex);
        } 
        return timeSlotList;
    }

    @Override
    protected void update(TimeSlot objectToUpdate) {
         try {
            PreparedStatement ps = DataManager.getInstance().buildStatement(
                    "UPDATE creneaux SET  version = ?,hdebut = ?,mdebut = ?, hfin = ?, mfin = ?"
                    + ", id_medecin = ? WHERE id = ?;");

            ps.setInt(1, objectToUpdate.getVersion());
            ps.setInt(2, objectToUpdate.getHdebut());
            ps.setInt(3, objectToUpdate.getMdebut());
            ps.setInt(4, objectToUpdate.getHfin());
            ps.setInt(5, objectToUpdate.getMfin());
            ps.setInt(6, objectToUpdate.getIdMedecin());
            ps.setInt(7, objectToUpdate.getId());
            DataManager.getInstance().doUpdate(ps);
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not update the current TimeSlot Object.", ex);
        }
    }

    @Override
    protected void insert(TimeSlot objectToInsert) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildInsertAutoInc(
                    "INSERT INTO creneaux (version, hdebut, mdebut, hfin, mfin, id_medecin)"
                    + " VALUES (?, ?, ?, ?, ?, ?);");

            ps.setInt(1, objectToInsert.getVersion());
            ps.setInt(2, objectToInsert.getHdebut());
            ps.setInt(3, objectToInsert.getMdebut());
            ps.setInt(4, objectToInsert.getHfin());
            ps.setInt(5, objectToInsert.getMfin());
            ps.setInt(6, objectToInsert.getIdMedecin());
            objectToInsert.setId(DataManager.getInstance().doUpdateAutoInc(ps));
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not insert the current TimeSlot.", ex);
        }
    }

    @Override
    public void delete(TimeSlot objectToDelete) {
        if (exists(objectToDelete)) {
            try {
                DataManager dm = DataManager.getInstance();
                PreparedStatement ps = dm.buildStatement("DELETE from creneaux WHERE id = ?;");
                ps.setInt(1, objectToDelete.getId());
                dm.doUpdate(ps);
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not delete the current TimeSlot.", ex);
            }
        }
    }

    @Override
    public boolean exists(TimeSlot objectToTest) {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<TimeSlot> lst = null;
        try {

            PreparedStatement ps = dm.buildStatement("SELECT * from creneaux WHERE ID = ?;");

            ps.setInt(1, objectToTest.getId());
            rs = dm.doExecute(ps);
            lst = buildListFromResultSet(rs);
            ps.close();
            rs.close();

            return (!lst.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not test if the current TimeSlot exists.", ex);
        }

        return (lst != null && !lst.isEmpty());
    }

    @Override
    protected List<TimeSlot> buildListFromResultSet(ResultSet inResultSet) {
        List<TimeSlot> lst = new ArrayList<TimeSlot>();
        try {
            while (inResultSet.next()) {
                TimeSlot ts = new TimeSlot();

                ts.setId(inResultSet.getInt("id"));
                ts.setHdebut(inResultSet.getInt("hdebut"));
                ts.setMdebut(inResultSet.getInt("mdebut"));
                ts.setHfin(inResultSet.getInt("hfin"));
                ts.setMfin(inResultSet.getInt("mfin"));
                ts.setIdMedecin(inResultSet.getInt("id_medecin"));
                
                lst.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Error while building the result list for TimeSlot query.", ex);
        }
        return lst;
    }
    
}

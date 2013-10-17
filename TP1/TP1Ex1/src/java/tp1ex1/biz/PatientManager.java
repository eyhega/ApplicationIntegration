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
import org.w3c.dom.ls.LSException;
import tp1ex1.dal.DataManager;
import tp1ex1.dao.Patient;

/**
 *
 * @author Gaetan
 */
public class PatientManager extends ObjectManager<Patient> {

    private static PatientManager instance = null;

    public synchronized PatientManager getInstance() {
        if (instance == null) {
            instance = new PatientManager();
        }

        return instance;
    }

    private PatientManager() {
    }

    @Override
    public List<Patient> findAll() {

        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Patient> patientList = null;

        try {
            rs = dm.doExecute(dm.buildStatement("SELECT * FROM CLIENTS;"));
            patientList = buildListFromResultSet(rs);
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not get all the Clients.", ex);
        }

        return patientList;
    }

    @Override
    public Patient save(Patient objectToSave) {
        try {
            if (exists(objectToSave)) {
                this.update(objectToSave);
            } else {
                this.insert(objectToSave);
            }
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not save the Patient Object int the DB.", ex);
        }

        return objectToSave;
    }

    @Override
    protected void update(Patient objectToUpdate) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildStatement(
                    "UPDATE CLIENS SET  version = ?,titre = ?,nom = ?,prenom = ? WHERE id = ?;");

            ps.setInt(1, objectToUpdate.getVersion());
            ps.setString(2, objectToUpdate.getTitre());
            ps.setString(3, objectToUpdate.getNom());
            ps.setString(4, objectToUpdate.getPrenom());
            ps.setLong(5, objectToUpdate.getId());
            DataManager.getInstance().doUpdate(ps);
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not update the current Client Object.", ex);
        }
    }

    @Override
    protected void insert(Patient objectToInsert) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildInsertAutoInc(
                    "INSERT INTO ClIENTS (version, titre, nom, prenom)"
                    + " VALUES (?, ?, ?, ?);");

            ps.setInt(1, objectToInsert.getVersion());
            ps.setString(2, objectToInsert.getTitre());
            ps.setString(3, objectToInsert.getNom());
            ps.setString(4, objectToInsert.getPrenom());
            objectToInsert.setId(DataManager.getInstance().doUpdateAutoInc(ps));
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not insert the current Patient.", ex);
        }
    }

    @Override
    public void delete(Patient objectToDelete) {
        if (exists(objectToDelete)) {
            try {
                DataManager dm = DataManager.getInstance();
                PreparedStatement ps = dm.buildStatement("DELETE from CLIENTS WHERE id = ?;");
                ps.setInt(1, objectToDelete.getId());
                dm.doUpdate(ps);
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not delete the current Patient.", ex);
            }
        }
    }

    @Override
    public boolean exists(Patient objectToTest) {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Patient> lst = null;
        try {

            PreparedStatement ps = dm.buildStatement("SELECT * from CLIENTS WHERE ID = ?;");

            ps.setInt(1, objectToTest.getId());
            rs = dm.doExecute(ps);
            lst = buildListFromResultSet(rs);
            ps.close();
            rs.close();

            return (!lst.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Can not test if the current Patient exists.", ex);
        }

        return (lst != null && !lst.isEmpty());
    }

    @Override
    protected List<Patient> buildListFromResultSet(ResultSet inResultSet) {
        List<Patient> lst = new ArrayList<Patient>();
        try {
            while (inResultSet.next()) {
                Patient r = new Patient();

                r.setId(inResultSet.getInt("id"));
                r.setVersion(inResultSet.getInt("version"));
                r.setTitre(inResultSet.getString("titre"));
                r.setNom(inResultSet.getString("nom"));
                r.setPrenom(inResultSet.getString("prenom"));

                lst.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, "Error while building the result list for Patient query.", ex);
        }
        return lst;
    }
}

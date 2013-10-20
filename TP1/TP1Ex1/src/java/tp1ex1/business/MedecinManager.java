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
import tp1ex1.dao.Medecin;

/**
 *
 * @author onio
 */
public class MedecinManager extends ObjectManager<Medecin> {

    private static MedecinManager instance = null;

    public static synchronized MedecinManager getInstance() {
        if (instance == null) {
            instance = new MedecinManager();
        }

        return instance;
    }

    private MedecinManager() {
    }

    /**
     * Retrieves every "Medecin"s, from db.
     *
     * @return A list of "medecin", if nothing is found, then null is returned.
     */
    @Override
    public synchronized List<Medecin> findAll() {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Medecin> lst = null;

        try {
            PreparedStatement buildStatement = dm.buildStatement("SELECT * from medecins;");
            rs = dm.doExecute(buildStatement);
            lst = buildListFromResultSet(rs);
            rs.close();
            buildStatement.close();
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not get all the Medecin.", ex);
        }

        return lst;
    }

    @Override
    protected void update(Medecin inMedecin) {
        try {
            String buildStatement = "UPDATE medecins"
                                    + " SET  version = ?"
                                    + ",    titre = ?"
                                    + ",    nom = ?"
                                    + ",    prenom = ?"
                                    + " WHERE id = ?;";
            PreparedStatement ps = DataManager.getInstance().buildStatement(
                    buildStatement);

            ps.setInt(1, inMedecin.getVersion());
            ps.setString(2, inMedecin.getTitre());
            ps.setString(3, inMedecin.getNom());
            ps.setString(4, inMedecin.getPrenom());
            ps.setInt(5, inMedecin.getId());
            DataManager.getInstance().doUpdate(ps);
            ps.close();
            
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not update the current Medecin.", ex);
        }
    }

    @Override
    protected void insert(Medecin inMedecin) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildInsertAutoInc(
                    "INSERT INTO medecins (version, titre, nom, prenom)"
                    + " VALUES (?, ?, ?, ?);");

            ps.setInt(1, inMedecin.getVersion());
            ps.setString(2, inMedecin.getTitre());
            ps.setString(3, inMedecin.getNom());
            ps.setString(4, inMedecin.getPrenom());
            inMedecin.setId(DataManager.getInstance().doUpdateAutoInc(ps));
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not insert the current Medecin.", ex);
        }
    }

    @Override
    public synchronized void delete(Medecin inMedecin) {
        if (exists(inMedecin)) {
            DataManager dm = DataManager.getInstance();
            try {
                PreparedStatement ps = dm.buildStatement("DELETE from medecins WHERE id = ?;");
                ps.setInt(1, inMedecin.getId());
                dm.doUpdate(ps);
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not delete the current Medecin.", ex);
            }
        }
    }

    @Override
    public synchronized boolean exists(Medecin inMedecin) {
        DataManager dm = DataManager.getInstance();
        ResultSet rs = null;
        List<Medecin> lst = null;
        try {

            PreparedStatement ps = dm.buildStatement("SELECT * from medecins WHERE ID = ?;");

            ps.setInt(1, inMedecin.getId());
            rs = dm.doExecute(ps);
            lst = buildListFromResultSet(rs);
            ps.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not test if the current Medecin exists.", ex);
        }

        return (lst != null && !lst.isEmpty());
    }

    @Override
    protected List<Medecin> buildListFromResultSet(ResultSet inResultSet) {
        List<Medecin> lst = new ArrayList<Medecin>();
      
        try {
            while (inResultSet.next()) {
                Medecin m = new Medecin();

                m.setId(inResultSet.getInt("id"));
                m.setVersion(inResultSet.getInt("version"));
                m.setTitre(inResultSet.getString("titre"));
                m.setNom(inResultSet.getString("nom"));
                m.setPrenom(inResultSet.getString("prenom"));

                lst.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Error while building the result list for Medecin query.", ex);
        }
        return (lst);
    }
}

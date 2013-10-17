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
            rs = dm.doExecute(dm.buildStatement("SELECT * from medecins;"));
            lst = buildListFromResultSet(rs);
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not get all the Medecin.", ex);
        }

        return lst;
    }

    /**
     * If inMedecin exists in db, then its status is saved, if it does not exist
     * then it's created into db, in this case, id is overwritten from
     * auto-generated value of ('fucking ?') db.
     *
     * @param inMedecin
     * @return A reference on the inMedecin updated instance (for id set in
     * create case).
     */
    @Override
    public synchronized Medecin save(Medecin inMedecin) {
        try {
            if (exists(inMedecin)) {
                this.update(inMedecin);
            } else {
                this.insert(inMedecin);
            }
        } catch (Exception ex) {
            Logger.getLogger(MedecinManager.class.getName()).log(Level.SEVERE, "Can not save the Medecin Object in the DB.", ex);
        }
        return inMedecin;
    }

    @Override
    protected void update(Medecin inMedecin) {
        try {
            PreparedStatement ps = DataManager.getInstance().buildStatement(
                    "UPDATE medecins"
                    + " SET  version = ?"
                    + ",    titre = ?"
                    + ",    nom = ?"
                    + ",    prenom = ?"
                    + " WHERE id = ?;");

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

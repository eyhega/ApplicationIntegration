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
import tp1ex1.dal.DataManager;
import tp1ex1.dao.Medecin;

/**
 *
 * @author onio
 */
public class MedecinManager {
    
    private static   MedecinManager     instance = null;
    
    public static synchronized MedecinManager  getInstance()
    {
        if (instance == null)
            instance = new MedecinManager();
        
        return instance;
    }
    
    private MedecinManager()
    {}

    /**
     * Retrieves every "Medecin"s, from db.
     * @return A list of "medecin", if nothing is found, then an empty list is returned.
     * @throws SQLException 
     */
    public synchronized List<Medecin>   findAll() throws SQLException, Exception
    {
        DataManager dm = DataManager.getInstance();
        ResultSet   rs = null;
        List<Medecin>   lst = null;
        
        rs = dm.doExecute(dm.buildStatement("SELECT * from medecins;"));
        lst = buildListFromResultSet(rs);
        rs.close();
        return lst;
    }
    
    /**
     * If inMedecin exists in db, then its status is saved, if it does not exist
     * then it's created into db, in this case, id is overwritten from
     * auto-generated value of ('fucking ?') db.
     * @param inMedecin
     * @return A reference on the inMedecin updated instance (for id set in create case).
     * @throws SQLException 
     */
    public synchronized Medecin save(Medecin inMedecin) throws SQLException, Exception
    {
        if (exists(inMedecin))
            this.update(inMedecin);
        else
            this.insert(inMedecin);
        
        return inMedecin;
    }
    
    private void                update(Medecin inMedecin) throws SQLException, Exception
    {
        PreparedStatement   ps = DataManager.getInstance().buildStatement(
                    "UPDATE medecins"
                    + " SET  version = ?"
                    + ",    titre = ?"
                    + ",    nom = ?"
                    + ",    prenom = ?"
                    + " WHERE id = ?;"
                );
        
        ps.setInt(1, inMedecin.getVersion());
        ps.setString(2, inMedecin.getTitre());
        ps.setString(3, inMedecin.getNom());
        ps.setString(4, inMedecin.getPrenom());
        ps.setInt(5, inMedecin.getId());
        DataManager.getInstance().doUpdate(ps);
    }
    
    private void                insert(Medecin inMedecin) throws SQLException, Exception
    {
        PreparedStatement   ps = DataManager.getInstance().buildInsertAutoInc(
                    "INSERT INTO medecins (version, titre, nom, prenom)"
                    + " VALUES (?, ?, ?, ?);");
        
        ps.setInt(1, inMedecin.getVersion());
        ps.setString(2, inMedecin.getTitre());
        ps.setString(3, inMedecin.getNom());
        ps.setString(4, inMedecin.getPrenom());
        inMedecin.setId(DataManager.getInstance().doUpdateAutoInc(ps));
    }
    
    public synchronized void    delete(Medecin inMedecin) throws SQLException, Exception
    {
        if (exists(inMedecin))
        {
            DataManager dm = DataManager.getInstance();
            PreparedStatement ps = dm.buildStatement("DELETE from medecins WHERE id = ?;");
            ps.setInt(1, inMedecin.getId());
            dm.doUpdate(ps);
        }
    }
    
    public synchronized boolean exists(Medecin inMedecin) throws SQLException, Exception
    {
        DataManager dm = DataManager.getInstance();
        ResultSet   rs = null;
        List<Medecin>   lst = null;
        PreparedStatement   ps = dm.buildStatement("SELECT * from medecins WHERE ID = ?;");
        
        ps.setInt(1, inMedecin.getId());
        rs = dm.doExecute(ps);
        lst = buildListFromResultSet(rs);
        rs.close();
        
        
        
        return (!lst.isEmpty());
    }
    
    private List<Medecin>   buildListFromResultSet(ResultSet inResultSet) throws SQLException
    {
        List<Medecin>   lst = new ArrayList<Medecin>();
        
        while (inResultSet.next())
        {
            Medecin m = new Medecin();
            
            m.setId(inResultSet.getInt("id"));
            m.setVersion(inResultSet.getInt("version"));
            m.setTitre(inResultSet.getString("titre"));
            m.setNom(inResultSet.getString("nom"));
            m.setPrenom(inResultSet.getString("prenom"));
            
            lst.add(m);
        }
        
        return (lst);
    }
}

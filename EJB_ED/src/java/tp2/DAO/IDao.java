/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp2.ejb.ClientsFacadeLocal;
import tp2.ejb.CreneauxFacadeLocal;
import tp2.ejb.MedecinsFacade;
import tp2.ejb.MedecinsFacadeLocal;
import tp2.ejb.RvFacade;
import tp2.ejb.RvFacadeLocal;
import tp2.jpa.Clients;
import tp2.jpa.Creneaux;
import tp2.jpa.Medecins;
import tp2.jpa.Rv;

/**
 *
 * @author eyheramo
 */
@Stateless(mappedName="Interface")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IDao implements IDaoLocal {

    
    @PersistenceContext
    private EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method"

    @EJB
    MedecinsFacadeLocal daoMedecin;
    
    @EJB
    RvFacadeLocal daoRv;
    
    @EJB
    CreneauxFacadeLocal daoCreneaux;
    
    @EJB
    ClientsFacadeLocal  daoClients;
    
    @Override
    public List<Medecins> getAllMedecins() {
        return daoMedecin.findAll();
    }
    
    @Override
    public List<Creneaux> getCreneauxForMedecins(Long idMedecin) {
        return (List<Creneaux>)daoMedecin.find(idMedecin).getCreneauxCollection();
    }
    
    @Override
    public List<Rv> getRvForMedecinAndDate(Medecins m,Date date) {
        List<Creneaux>    creneaux = (List<Creneaux>)m.getCreneauxCollection();
        List<Rv>          rdvs = new ArrayList<Rv>();
        
        /*
         * Boucle de rdvs en O n2 moisie honteux pour jee 2013 
         * 
         */
        for (Creneaux c : creneaux)
        {
            List<Rv>        rvs = (List<Rv>)c.getRvCollection();
            
            for (Rv rv : rvs)
            {
                if (rv.getJour().equals(date))
                    rdvs.add(rv);
            }
        }
        
        return rdvs;
    }

    @Override
    public void addRv(Date date, Long idClient, Long idCreneaux) {
        
        daoRv.create(new Rv(date, daoClients.find(idClient), daoCreneaux.find(idCreneaux)));
    }

    @Override
    public void removeRv(Long idRv) {
        
        daoRv.remove(daoRv.find(idRv));
    }
    
    
    
}

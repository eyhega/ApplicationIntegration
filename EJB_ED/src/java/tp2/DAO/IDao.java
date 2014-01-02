/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.DAO;

import java.util.Date;
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

    
    @PersistenceContext(unitName = "EJB_EDPU")
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
        return daoMedecin.find(idMedecin).getCreneauxCollection();
    }
    
   /* @Override
    public List<Rv> getRvForMedecinAndDate(Medecins m,Date date) {
        List<Creneaux>    creneaux = m.getCreneauxCollection();
        List<Rv>          rdvs = new ArrayList<Rv>();
        
        for (Creneaux c : creneaux)
        {
            List<Rv>        rvs = c.getRvCollection();
            
            for (Rv rv : rvs)
            {
                if (rv.getJour().equals(date))
                    rdvs.add(rv);
            }
        }
        
        return rdvs;
    }*/
    
    @Override
    public List<Creneaux> getFreeCreneauxForMedecinsAndDate(Long idMedecin,Date date) {
        
        List<Rv> allRv = daoRv.findAll();
        Medecins currentMed = daoMedecin.find(idMedecin);
        List<Creneaux> creneaux = currentMed.getCreneauxCollection();
        
        for(Rv r : allRv) {
            if(r.getJour().equals(date)) {
                if(r.getIdCreneau().getIdMedecin().equals(currentMed)) {
                    creneaux.remove(r.getIdCreneau());
                }
            }
        }
        
        return creneaux;
    }

    @Override
    public Rv addRv(Date date, Long idClient, Long idCreneaux) {
        Rv toCreate = new Rv(date, daoClients.find(idClient), daoCreneaux.find(idCreneaux));
        daoRv.create(toCreate);
        return toCreate;
    }

    @Override
    public void removeRv(Long idRv) {
        
        daoRv.remove(daoRv.find(idRv));
    }

    @Override
    public List<Clients> getAllClients() {
        return daoClients.findAll();
    }
    
    
    
}

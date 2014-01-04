/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp2.jpa.Creneaux;
import tp2.jpa.Medecins;

/**
 *
 * @author eyheramo
 */
@Stateless
public class CreneauxFacade extends AbstractFacade<Creneaux> implements CreneauxFacadeLocal {
    @PersistenceContext(unitName = "EJB_EDPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CreneauxFacade() {
        super(Creneaux.class);
    }

}

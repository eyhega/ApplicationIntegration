/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp2.jpa.Medecins;

/**
 *
 * @author eyheramo
 */
@Stateless
public class MedecinsFacade extends AbstractFacade<Medecins> implements MedecinsFacadeLocal {
    @PersistenceContext(unitName = "EJB_EDPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MedecinsFacade() {
        super(Medecins.class);
    }
    
}

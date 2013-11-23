/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp2.jpa.Rv;

/**
 *
 * @author eyheramo
 */
@Stateless
public class RvFacade extends AbstractFacade<Rv> implements RvFacadeLocal {
    @PersistenceContext(unitName = "EJB_EDPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RvFacade() {
        super(Rv.class);
    }
}

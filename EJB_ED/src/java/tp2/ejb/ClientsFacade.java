/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp2.jpa.Clients;

/**
 *
 * @author eyheramo
 */
@Stateless
public class ClientsFacade extends AbstractFacade<Clients> implements ClientsFacadeLocal {
    @PersistenceContext(unitName = "EJB_EDPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientsFacade() {
        super(Clients.class);
    }
    
}

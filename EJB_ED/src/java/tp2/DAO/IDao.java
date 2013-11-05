/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.DAO;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    // "Insert Code > Add Business Method")
    
    
    
}

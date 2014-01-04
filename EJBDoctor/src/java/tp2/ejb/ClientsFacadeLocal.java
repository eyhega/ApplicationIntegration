/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Clients;

/**
 *
 * @author eyheramo
 */
@Local
public interface ClientsFacadeLocal extends Serializable {

    void create(Clients clients);

    void edit(Clients clients);

    void remove(Clients clients);

    Clients find(Object id);

    List<Clients> findAll();

    List<Clients> findRange(int[] range);

    int count();
    
}

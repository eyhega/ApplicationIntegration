/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Rv;

/**
 *
 * @author eyheramo
 */
@Local
public interface RvFacadeLocal {

    void create(Rv rv);

    void edit(Rv rv);

    void remove(Rv rv);

    Rv find(Object id);

    List<Rv> findAll();

    List<Rv> findRange(int[] range);

    int count();
    
}

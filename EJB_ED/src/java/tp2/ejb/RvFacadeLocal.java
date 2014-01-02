/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Medecins;
import tp2.jpa.Rv;

/**
 *
 * @author eyheramo
 */
@Local
public interface RvFacadeLocal extends Serializable {

    void create(Rv rv);

    void edit(Rv rv);

    void remove(Rv rv);

    Rv find(Object id);

    List<Rv> findAll();
    
    List<Rv> findRange(int[] range);

    int count();
    
}

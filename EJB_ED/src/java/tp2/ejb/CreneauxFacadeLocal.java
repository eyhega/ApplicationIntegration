/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Creneaux;

/**
 *
 * @author eyheramo
 */
@Local
public interface CreneauxFacadeLocal {

    void create(Creneaux creneaux);

    void edit(Creneaux creneaux);

    void remove(Creneaux creneaux);

    Creneaux find(Object id);

    List<Creneaux> findAll();

    List<Creneaux> findRange(int[] range);

    int count();
    
}

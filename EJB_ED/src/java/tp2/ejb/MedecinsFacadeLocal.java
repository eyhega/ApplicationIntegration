/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ejb;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Medecins;

/**
 *
 * @author eyheramo
 */
@Local
public interface MedecinsFacadeLocal extends Serializable {

    void create(Medecins medecins);

    void edit(Medecins medecins);

    void remove(Medecins medecins);

    Medecins find(Object id);

    List<Medecins> findAll();

    List<Medecins> findRange(int[] range);

    int count();
    
}

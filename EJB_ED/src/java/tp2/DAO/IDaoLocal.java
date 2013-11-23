/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.DAO;

import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Medecins;

/**
 *
 * @author eyheramo
 */
@Local
public interface IDaoLocal {
    
    List<Medecins> getAllMedecins();
}

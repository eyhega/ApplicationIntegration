/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.DAO;

import java.sql.Date;
import java.util.List;
import javax.ejb.Local;
import tp2.jpa.Clients;
import tp2.jpa.Creneaux;
import tp2.jpa.Medecins;
import tp2.jpa.Rv;

/**
 *
 * @author eyheramo
 */
@Local
public interface IDaoLocal {
    
    List<Medecins> getAllMedecins();
    
    List<Clients> getAllClients();
    
    List<Creneaux> getCreneauxForMedecins(Long idMedecin);
    
    List<Rv> getRvForMedecinAndDate(Medecins m,Date date);
    
    void     addRv(Date date, Long  clients, Long creneaux);
    void     removeRv(Long idRv);
    
}

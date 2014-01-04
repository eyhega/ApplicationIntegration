/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.dao;

import java.io.Serializable;
import java.util.Date;
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
public interface IDaoLocal extends Serializable {
    
    List<Medecins> getAllMedecins();
    
    List<Clients> getAllClients();
    
    List<Creneaux> getCreneauxForMedecins(Long idMedecin);
    
    List<Creneaux> getFreeCreneauxForMedecinsAndDate(Long idMedecin,Date date);
    
    //List<Rv> getRvForMedecinAndDate(Medecins m,Date date);
    
    Rv     addRv(Date date, Long  clients, Long creneaux);
    void     removeRv(Long idRv);
    
}

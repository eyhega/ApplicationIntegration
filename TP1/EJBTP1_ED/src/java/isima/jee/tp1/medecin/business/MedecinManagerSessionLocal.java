/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isima.jee.tp1.medecin.business;

import isima.jee.tp1.medecin.DAO.MedecinEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *  NOTE : convention nommage methode perso
 *      getXXX() => resultat attendu => une reference non null,
 *                      si null trouvé => exception
 *      findXXX() => resultat attendu => null si non trouvé,
 *                      l'objet sinon
 * 
 * 
 * @author onio
 */
@Local
public interface MedecinManagerSessionLocal {
    
    /**
     * Creates a "Medecin"
     * @param inVersion
     * @param inTitre
     * @param inNom
     * @param inPrenom
     * @return The MedecinEntity built, or null if it failed.
     * @throws Exception 
     */
    public MedecinEntity        create(Long     inVersion,
                                        String  inTitre,
                                        String  inNom,
                                        String  inPrenom) throws Exception;
    
    
    /**
     * Deletes a "Medecin"
     * @param inId
     * @throws Exception 
     */
    public void                 delete(Long inId) throws Exception;
    
    /**
     * Retrieves every "Medecin" from the database
     * @return A list of "Medecin"s, if nothing is found, then an empty list is returned
     * @throws Exception 
     */
    public List<MedecinEntity>  getAllMedecins() throws Exception;
    
    /**
     * Retrieves a "Medecin" by its Entity ID (db PK)
     * @param inId
     * @return The MedecinEntity, or null if ID given is unknown.
     * @throws Exception 
     */
    public MedecinEntity        findMedecinById(Long inId) throws Exception;
    
    /**
     * Retrieves every "Medecin"s whose name is as given with params
     * @param inNom
     * @param inPrenom
     * @return A list of "Medecin"s, if nothing is found, then an empty list is returned
     * @throws Exception 
     */
    public List<MedecinEntity>  findMedecinsByName(String inNom, 
                                                     String inPrenom) throws Exception;
    
    /**
     * Asks the manager to save the entity, if the entity given is not
     * present to the db, then an exception is thrown.
     * @param inMedecinEntity
     * @throws Exception 
     */
    public void                 save(MedecinEntity inMedecinEntity) throws Exception;
    
    /**
     * Gives the number of entities availables inside DB.
     * @return The number of MedecinEntit(ies) managed.
     * @throws Exception 
     */
    public Long                 count() throws Exception;
}

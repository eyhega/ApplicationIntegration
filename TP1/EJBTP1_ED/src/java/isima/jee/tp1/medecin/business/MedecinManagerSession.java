/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isima.jee.tp1.medecin.business;

import isima.jee.tp1.medecin.dal.MedecinEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author onio
 */
@Stateless
public class MedecinManagerSession implements MedecinManagerSessionLocal {

    @PersistenceContext
    private EntityManager   entityManager;

    
    @Override
    public MedecinEntity        create(Long     inVersion,
                                        String  inTitre,
                                        String  inNom,
                                        String  inPrenom) throws Exception
    {
        MedecinEntity   me = new MedecinEntity();
        
        me.setVersion(inVersion);
        me.setNom(inNom);
        me.setPrenom(inPrenom);
        me.setTitre(inTitre);
        entityManager.persist(me);
        return me;
    }
    
    @Override
    public void                 delete(Long inId) throws Exception
    {
        MedecinEntity   me = this.findMedecinById(inId);
        
        if (me == null)
        {
            throw new Exception("inId given (" + inId 
                            + ") is not mapped to a valid MedecinEntity");
        }
        
        entityManager.remove(me);
    }
    
    @Override
    public List<MedecinEntity> getAllMedecins() throws Exception {
        TypedQuery<MedecinEntity> query = null;
        
        query = entityManager.createNamedQuery( "MedecinEntity.findAll",
                                                MedecinEntity.class);
        
        return (query.getResultList());
    }

    @Override
    public MedecinEntity findMedecinById(Long inId) throws Exception {
        TypedQuery<MedecinEntity> query = null;
        
        query = entityManager.createNamedQuery( "MedecinEntity.findById",
                                                MedecinEntity.class);
        query.setParameter("id", inId);
        return (query.getSingleResult());
    }

    @Override
    public List<MedecinEntity> findMedecinsByName(String inNom,
                                                   String inPrenom) throws Exception {
        TypedQuery<MedecinEntity>   query = null;
        
        query = entityManager.createNamedQuery( "MedecinEntity.findByName", 
                                                MedecinEntity.class);
        query.setParameter("nom", inNom);
        query.setParameter("prenom", inPrenom);
        return (query.getResultList());
    }

    @Override
    public void save(MedecinEntity inMedecinEntity) throws Exception {
        if (this.findMedecinById(inMedecinEntity.getId()) != null)
            entityManager.merge(inMedecinEntity);
        
        throw new Exception("inMedecinEntity given (" + inMedecinEntity.toString() + ") is"
                + " not known from the entityManager");
    }

    @Override
    public Long count() throws Exception {
        TypedQuery<Long>    query = null;
        
        query = entityManager.createNamedQuery("MedecinEntity.getCount", Long.class);
        return query.getSingleResult();
    }
}

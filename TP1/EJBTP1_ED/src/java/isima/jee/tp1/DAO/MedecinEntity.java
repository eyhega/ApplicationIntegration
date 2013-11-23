/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isima.jee.tp1.DAO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Medecin entity from DB
 * @author onio
 */
@Entity
@Table(name = "MEDECIN")
@NamedQueries({
    @NamedQuery(name = "MedecinEntity.findAll",
                query = "SELECT me FROM MedecinEntity me"),
    @NamedQuery(name = "MedecinEntity.findById",
                query = "SELECT me FROM MedecinEntity me WHERE me.id = :id"),
    @NamedQuery(name = "MedecinEntity.findByName",
                query = "SELECT me FROM MedecinEntity me WHERE"
            + "         (me.nom = :nom) AND (me.prenom = :prenom) "),
    @NamedQuery(name = "MedecinEntity.getCount",
                query = "SELECT COUNT(me) FROM MedecinEntity me")
})
public class MedecinEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "VERSION")
    private Long version;
    
    @Column(name = "TITRE")
    private String titre;
    
    @Column(name = "NOM")
    private String nom;
    
    @Column(name = "PRENOM")
    private String prenom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedecinEntity other = (MedecinEntity) obj;
        if (this.id != other.id && 
            (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MedecinEntity{" + "id=" + id + ", version=" + version 
                + ", titre=" + titre + ", nom=" + nom 
                + ", prenom=" + prenom + '}';
    }
}

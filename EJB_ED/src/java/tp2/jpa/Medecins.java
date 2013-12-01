/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eyheramo
 */
@Entity
@Table(name = "medecins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecins.findAll", query = "SELECT m FROM Medecins m"),
    @NamedQuery(name = "Medecins.findById", query = "SELECT m FROM Medecins m WHERE m.id = :id"),
    @NamedQuery(name = "Medecins.findByVersion", query = "SELECT m FROM Medecins m WHERE m.version = :version"),
    @NamedQuery(name = "Medecins.findByTitre", query = "SELECT m FROM Medecins m WHERE m.titre = :titre"),
    @NamedQuery(name = "Medecins.findByNom", query = "SELECT m FROM Medecins m WHERE m.nom = :nom"),
    @NamedQuery(name = "Medecins.findByPrenom", query = "SELECT m FROM Medecins m WHERE m.prenom = :prenom")})
public class Medecins implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION")
    private int version;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TITRE")
    private String titre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOM")
    private String nom;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PRENOM")
    private String prenom;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedecin")
    private Collection<Creneaux> creneauxCollection;

    @OneToMany(cascade= CascadeType.ALL, mappedBy= "idMedecin")
    private transient List<Creneaux> creneauxList;
    
    public Medecins() {
    }

    public Medecins(Long id) {
        this.id = id;
    }

    public Medecins(Long id, int version, String titre, String nom, String prenom) {
        this.id = id;
        this.version = version;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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

    @XmlTransient
    public Collection<Creneaux> getCreneauxCollection() {
        return creneauxCollection;
    }

    public void setCreneauxCollection(Collection<Creneaux> creneauxCollection) {
        this.creneauxCollection = creneauxCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecins)) {
            return false;
        }
        Medecins other = (Medecins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp2.DAO.Medecins[ id=" + id + " ]";
    }
    
}

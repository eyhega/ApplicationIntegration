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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eyheramo
 */
@Entity
@Table(name = "creneaux")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creneaux.findAll", query = "SELECT c FROM Creneaux c"),
    @NamedQuery(name = "Creneaux.findById", query = "SELECT c FROM Creneaux c WHERE c.id = :id"),
    @NamedQuery(name = "Creneaux.findByVersion", query = "SELECT c FROM Creneaux c WHERE c.version = :version"),
    @NamedQuery(name = "Creneaux.findByHdebut", query = "SELECT c FROM Creneaux c WHERE c.hdebut = :hdebut"),
    @NamedQuery(name = "Creneaux.findByMdebut", query = "SELECT c FROM Creneaux c WHERE c.mdebut = :mdebut"),
    @NamedQuery(name = "Creneaux.findByHfin", query = "SELECT c FROM Creneaux c WHERE c.hfin = :hfin"),
    @NamedQuery(name = "Creneaux.findByMfin", query = "SELECT c FROM Creneaux c WHERE c.mfin = :mfin")})
public class Creneaux implements Serializable {
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
    @Column(name = "HDEBUT")
    private int hdebut;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MDEBUT")
    private int mdebut;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "HFIN")
    private int hfin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MFIN")
    private int mfin;
    
    @JoinColumn(name = "ID_MEDECIN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Medecins idMedecin;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCreneau")
    private List<Rv> rvCollection;
    
    public Creneaux() {
    }

    public Creneaux(Long id) {
        this.id = id;
    }

    public Creneaux(Long id, int version, int hdebut, int mdebut, int hfin, int mfin) {
        this.id = id;
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.hfin = hfin;
        this.mfin = mfin;
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

    public int getHdebut() {
        return hdebut;
    }

    public void setHdebut(int hdebut) {
        this.hdebut = hdebut;
    }

    public int getMdebut() {
        return mdebut;
    }

    public void setMdebut(int mdebut) {
        this.mdebut = mdebut;
    }

    public int getHfin() {
        return hfin;
    }

    public void setHfin(int hfin) {
        this.hfin = hfin;
    }

    public int getMfin() {
        return mfin;
    }

    public void setMfin(int mfin) {
        this.mfin = mfin;
    }

    @XmlTransient
    public Medecins getIdMedecin() {
        return idMedecin;
    }

    @XmlTransient
    public void setIdMedecin(Medecins idMedecin) {
        this.idMedecin = idMedecin;
    }

    @XmlTransient
    public List<Rv> getRvCollection() {
        return rvCollection;
    }

    @XmlTransient
    public void setRvCollection(List<Rv> rvCollection) {
        this.rvCollection = rvCollection;
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
        if (!(object instanceof Creneaux)) {
            return false;
        }
        Creneaux other = (Creneaux) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp2.DAO.Creneaux[ id=" + id + " ]";
    }
    
}

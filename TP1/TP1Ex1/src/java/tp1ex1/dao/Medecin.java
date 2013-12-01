/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.dao;

import java.io.Serializable;

/**
 *
 * @author onio
 */
public class Medecin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int         id;
    private int         version;
    private String      titre;
    private String      nom;
    private String      prenom;

    public Medecin() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final Medecin other = (Medecin) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Medecin{" + "id=" + id + ", version=" + version + ", titre=" + titre + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
    
}

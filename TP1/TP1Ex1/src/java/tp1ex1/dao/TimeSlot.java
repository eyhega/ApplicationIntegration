/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.dao;

import java.io.Serializable;

/**
 *
 * @author Gaetan
 */
public class TimeSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private int version;

    private int hdebut;

    private int mdebut;

    private int hfin;

    private int mfin;
    
    private int idMedecin;

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public TimeSlot() {
    }

    public TimeSlot(int id) {
        this.id = id;
    }

    public TimeSlot(int id, int version, int hdebut, int mdebut, int hfin, int mfin, int idMedecin) {
        this.id = id;
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.hfin = hfin;
        this.mfin = mfin;
        this.idMedecin = idMedecin;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += 17 * (id + idMedecin) * hdebut;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean toReturn = false;
        if(object instanceof TimeSlot) {
            if(object != null) {
                TimeSlot tmp = (TimeSlot)object;
                toReturn = tmp.getId() == id;
            }
        }
        
        return toReturn;
    }

    @Override
    public String toString() {
        return "TimeSlot[ id==" + id + " hdebut="+ hdebut + " mdebut=" + mdebut+
                " hfin="+ hfin + " mfin=" + mfin + " idMedecin=" + idMedecin +  "]";
    }
    
}

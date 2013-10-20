/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.dao;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Gaetan
 */
public class Appointment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    
    private Date jour;
    
    private int idTimeSlot;

    private int idPatient;

    public int getIdTimeSlot() {
        return idTimeSlot;
    }

    public void setIdTimeSlot(int idMedecin) {
        this.idTimeSlot = idMedecin;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }
    
    public Appointment() {
    }

    public Appointment(int id) {
        this.id = id;
    }

    public Appointment(int id, Date jour) {
        this.id = id;
        this.jour = jour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    @Override
    public int hashCode() {
        int hash = 89;
        hash += (id) + jour.getTime();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean toReturn = false;
       if(object != null && object instanceof Appointment) {
           Appointment toComp = (Appointment)object;
           if(toComp.getId() == id && toComp.getJour().equals(jour)) {
               toReturn = true;
           }
       }
       return toReturn;
    }

    @Override
    public String toString() {
        return " Appointment[ id=" + id + " jour=" + jour +" idPatient="+idPatient +" idTimeSlot=" + idTimeSlot + "]";
    }
    
}

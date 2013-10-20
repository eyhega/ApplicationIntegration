/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.business;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Specifications for ObjectManager
 * @author Gaetan
 */
public abstract class ObjectManager<T> {
    
    public abstract List<T> findAll();
    protected abstract void update(T objectToUpdate);
    protected abstract void insert(T objectToInsert);
    public abstract void delete(T objectToDelete);
    public abstract boolean exists(T objectToTest);
    protected abstract List<T> buildListFromResultSet(ResultSet inResultSet);
    
    /**
     * If objectToSave exists in db, then its status is saved, if it does not exist
     * then it's created into db, in this case, id is overwritten from
     * auto-generated value of db.
     *
     * @param objectToSave the object to save
     * @return A reference on the objectToSave updated instance (for id set in
     * create case).
     */
    public synchronized T save(T objectToSave) {
        try {
            if (exists(objectToSave)) {
                this.update(objectToSave);
            } else {
                this.insert(objectToSave);
            }
        } catch (Exception ex) {
            Logger.getLogger(ObjectManager.class.getName()).log(Level.SEVERE, "Can not save the " + objectToSave.getClass().getName() + " Object in the DB.", ex);
        }
        return objectToSave;
    }
}

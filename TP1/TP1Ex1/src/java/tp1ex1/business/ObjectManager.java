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
 * @param <T> The Entity type.
 */
public abstract class ObjectManager<T> {
    
    /**
     * Find all the tuples in the table and return a collection of entity filled.
     * @return the collection set.
     */
    public abstract List<T> findAll();
    
    /**
     * Update an existing tuple in the database.
     * @param objectToUpdate The entity object which contains data.
     */
    protected abstract void update(T objectToUpdate);
    
    /**
     * Insert a new tuple in the database.
     * @param objectToInsert The object to insert.
     */
    protected abstract void insert(T objectToInsert);
    
    /**
     * Delete an existing tuple from the database.
     * @param objectToDelete The entity object to delete.
     */
    public abstract void delete(T objectToDelete);
    
    /**
     * Check if a tuple exists in the database.
     * @param objectToTest The entity object to test.
     * @return True if the tuple has been found, False otherwise.
     */
    public abstract boolean exists(T objectToTest);
    
    /**
     * Build an Entity Collection from a ResultSet.
     * @param inResultSet The ResulSet object which contains data (tuples)
     * @return An entity collection filled.
     */
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

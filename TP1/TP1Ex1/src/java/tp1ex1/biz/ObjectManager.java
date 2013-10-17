/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1.biz;

import java.sql.ResultSet;
import java.util.List;

/**
 * Specifications for ObjectManager
 * @author Gaetan
 */
public abstract class ObjectManager<T> {
    
    public abstract List<T> findAll();
    public abstract T save(T objectToSave);
    protected abstract void update(T objectToUpdate);
    protected abstract void insert(T objectToInsert);
    public abstract void delete(T objectToDelete);
    public abstract boolean exists(T objectToTest);
    protected abstract List<T> buildListFromResultSet(ResultSet inResultSet);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.interfaces.DSAKey;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp1ex1.business.AppointmentManager;
import tp1ex1.business.MedecinManager;
import tp1ex1.business.PatientManager;
import tp1ex1.business.ObjectManager;
import tp1ex1.business.TimeSlotManager;
import tp1ex1.dao.Appointment;
import tp1ex1.dao.Medecin;
import tp1ex1.dao.Patient;
import tp1ex1.dao.TimeSlot;

/**
 *
 * @author onio
 */
public class Main {
    
    public static String STRING_TEST = "TST";
    public static String STRING_CHANGED_TEST = "ST";
    public static int INT_TEST = 1;
    public static int INT_CHANGED_TEST = 2;
    public static Date DATE_TEST = new Date(0);
    public static Date DATE_CHANGED_TEST = new Date(1);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testAll();
    }
    
    
    private static void testAll() {
        Medecin med = new Medecin();
        testGeneric(MedecinManager.getInstance(),med);
        
        Patient pat =  new Patient();
        testGeneric(PatientManager.getInstance(), pat);
        
        Appointment app = new Appointment();
        testGeneric(AppointmentManager.getInstance(), app);
        
        TimeSlot ts = new TimeSlot();
        testGeneric(TimeSlotManager.getInstance(), ts);
    }
    
    private static <T extends ObjectManager,U> void testGeneric(T managerInstance,U entity) {
        
        System.out.println("*------DEMO FOR "+ entity.getClass().getName() + " Entity class------*");
        
        System.out.println("---TEST FIND ALL---");
        displayObjects(managerInstance);
        
        System.out.println("---TEST ADD (SAVE METHOD)---");
        fillEntity(entity);
        managerInstance.save(entity);
        displayObjects(managerInstance);
        
        System.out.println("---TEST CHANGE (SAVE METHOD)---");
        setRandomField(entity);
        managerInstance.save(entity);
        displayObjects(managerInstance);
        
        System.out.println("---TEST DELETE---");
        managerInstance.delete(entity);
        displayObjects(managerInstance);
        
        System.out.println("*------END DEMO FOR "+ entity.getClass().getName() + " Entity class------*");
    }
    
    
    private static <U> void setRandomField(U entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        int randomField = (int) (Math.random()*fields.length);
        
        while(fields[randomField].getName().equals("id") || fields[randomField].getName().equals("serialVersionUID")) {
            randomField = (int) (Math.random()*fields.length);
        }
        String name = fields[randomField].getName();
        System.out.println("Changing " + name);
        Class<?> type = fields[randomField].getType();
        Object changedData = getGenericChangedData(type);
        String methodName = "set" + name.toUpperCase().charAt(0) + name.substring(1);
        try {
            Method set = entity.getClass().getMethod(methodName,type);
            set.invoke(entity,changedData);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static <T> void fillEntity(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        
        String name = null;
        Class<?> type = null;
        String setMethod = null;
        Object param = null;
        Method set = null;
        for(Field f : fields) {
            name = f.getName();
            type = f.getType();
            if(!name.equals("id") && !name.equals("serialVersionUID")) {
                setMethod = "set" + name.toUpperCase().charAt(0) + name.substring(1);
                param = getGenericData(type);
                try {
                    set = entity.getClass().getMethod(setMethod,type);
                    set.invoke(entity, param);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    private static Object getGenericData(Class<?> type) {
        Object toReturn = null;
        
        if(type.equals(String.class)) {
            toReturn = STRING_TEST;
        } else if(type.equals(int.class)) {
            toReturn = INT_TEST;
        } else {
            toReturn = DATE_TEST;
        }
        
        return toReturn;
    }
    
    private static Object getGenericChangedData(Class<?> type) {
        Object toReturn = null;
        
        if(type.equals(String.class)) {
            toReturn = STRING_CHANGED_TEST;
        } else if(type.equals(int.class)) {
            toReturn = INT_CHANGED_TEST;
        } else {
            toReturn = DATE_CHANGED_TEST;
        }
        
        return toReturn;
    }

    private static <T extends ObjectManager, U> void displayObjects(T managerInstance) {
        List<U> list = null;
        list = managerInstance.findAll();
        for(U obj : list) {
            System.out.println(obj);
        }
    }





}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java;

import DAL.DoctorManager;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eyheramo
 */
public class DoctorManagerTest {
    
    
    public DoctorManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createConnection method, of class DoctorManager.
     */
    @Test
    public void testCreateConnection() {
        System.out.println("createConnection");
        DoctorManager instance = new DoctorManager();
        
        assertNotNull(instance.getConnection());
        assertNotNull(instance.getStatement());
    }

    /**
     * Test of getAllDoctors method, of class DoctorManager.
     */
    @Test
    public void testGetAllDoctors() {
        System.out.println("getAllDoctors");
        DoctorManager instance = new DoctorManager();
        ResultSet expResult = null;
        ResultSet result = instance.getAllDoctors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertDoctor method, of class DoctorManager.
     */
    @Test
    public void testInsertDoctor() {
        System.out.println("insertDoctor");
        int version = 0;
        String title = "";
        String name = "";
        String firstname = "";
        DoctorManager instance = new DoctorManager();
        boolean expResult = false;
        boolean result = instance.insertDoctor(version, title, name, firstname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDoctor method, of class DoctorManager.
     */
    @Test
    public void testDeleteDoctor() {
        System.out.println("deleteDoctor");
        int doctorId = 0;
        DoctorManager instance = new DoctorManager();
        boolean expResult = false;
        boolean result = instance.deleteDoctor(doctorId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

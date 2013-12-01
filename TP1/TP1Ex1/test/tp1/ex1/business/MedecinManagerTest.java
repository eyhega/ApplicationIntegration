/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.ex1.business;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tp1ex1.business.MedecinManager;
import tp1ex1.dao.Medecin;

/**
 *
 * @author Gaetan
 */
public class MedecinManagerTest {
    private final MedecinManager instance;
    
    public MedecinManagerTest() {
        instance = MedecinManager.getInstance();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindAll() {
        List<Medecin> medList = instance.findAll();
        assertNotNull(medList);
        assertEquals(4,medList.size());
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isima.jee.tp1.BIZ;

import isima.jee.tp1.medecin.DAO.MedecinEntity;
import isima.jee.tp1.Business.MedecinManagerSessionLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author onio
 */
public class MedecinManagerSessionLocalTest {
    
    private static EJBContainer                container;
    private MedecinManagerSessionLocal         medecinManager;
    
    public MedecinManagerSessionLocalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
    
    @Before
    public void setUp() {
        try {
            medecinManager = (MedecinManagerSessionLocal)container.getContext().
                        lookup("java:/global/classes/MedecinManagerSessionLocal");
        } catch (NamingException ex) {
            Logger.getLogger(MedecinManagerSessionLocalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testCreate() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testDelete() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMedecins method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testGetAllMedecins() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMedecinById method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testFindMedecinById() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMedecinsByName method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testFindMedecinsByName() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testSave() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class MedecinManagerSessionLocal.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        Long expResult = new Long(4);
        Long result = medecinManager.count();
        assertEquals(expResult, result);
    }
}
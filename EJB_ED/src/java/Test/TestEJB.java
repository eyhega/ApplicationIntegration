/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.List;
import tp2.DAO.IDao;
import tp2.jpa.Medecins;

/**
 *
 * @author eyheramo
 */
public class TestEJB {

    public static void main(String[] args) {
        IDao dao = new IDao();
        List<Medecins> list = dao.getAllMedecins();
        for (Medecins m : list) {
            System.out.println(m);
        }
    }
}

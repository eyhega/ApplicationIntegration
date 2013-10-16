/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1ex1;

import java.util.List;
import tp1ex1.biz.MedecinManager;
import tp1ex1.dao.Medecin;

/**
 *
 * @author onio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            List<Medecin>   meds;
            
            meds = MedecinManager.getInstance().findAll();
            for (Medecin m : meds)
            {
                System.out.println(m.toString());
            }
            
            
            
            Medecin mi = new Medecin();
            
            mi.setNom("Li");
            mi.setPrenom("JJ");
            mi.setTitre("Mme");
            mi.setVersion(1);
            System.out.println("ADD LI");
            MedecinManager.getInstance().save(mi);
            
            meds = MedecinManager.getInstance().findAll();
            for (Medecin m : meds)
            {
                System.out.println(m.toString());
            }
            
            System.out.println("CHANGE LI => LIT");
            
            mi.setNom("Lit");
            MedecinManager.getInstance().save(mi);
            
            meds = MedecinManager.getInstance().findAll();
            for (Medecin m : meds)
            {
                System.out.println(m.toString());
            }
            
            System.out.println("DEL LIT");
            
            MedecinManager.getInstance().delete(mi);
            
            meds = MedecinManager.getInstance().findAll();
            for (Medecin m : meds)
            {
                System.out.println(m.toString());
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        
    }
}

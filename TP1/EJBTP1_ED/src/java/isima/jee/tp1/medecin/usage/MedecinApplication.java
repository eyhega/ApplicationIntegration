/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isima.jee.tp1.medecin.usage;

import isima.jee.tp1.Business.MedecinManagerSessionLocal;
import javax.ejb.EJB;

/**
 *
 * @author onio
 */
public class MedecinApplication {
    
    @EJB
    private MedecinManagerSessionLocal   medecinManager;
    
    
}

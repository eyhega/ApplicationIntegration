/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.exceptions;

import javax.ejb.ApplicationException;


@ApplicationException(rollback=true)
/**
 *
 * @author eyheramo
 */
public class MedecinException extends RuntimeException {
    
    //champ privé de type entier (32bits) nommé "code" initialisé à 0
    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    //Constructeur public appelant celui de la classe mère (super)
    public MedecinException() {
        super();
    }
    
    //Constructeur public appelant celui de la classe mère (super) et lui passant un message pour l'erreur
    public MedecinException(String message) {
        super(message);
    }
    
    /**
     * Constructeur public appelant celui de la classe mère (super) et lui passant un message pour l'erreur
     * ainsi que la cause de l'erreur sous forme d'un objet de type "Throwable"
    */
    public MedecinException(String message, Throwable cause) {
        super(message,cause);
    }
    
    /**
     * Constructeur public appelant celui de la classe mère (super) et lui passant un objet "Throwable"
     * qui permet notamment d'identifier la cause de l'erreur mais aussi de récupérer tout un tas d'informations très pertinentes.
     */
    public MedecinException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructeur public appelant celui de la classe mère (super) et lui passant un message pour l'erreur
     * L'entier identifié par le label "code" permet d'identifier l'erreur
     */
    public MedecinException(String message, int code) {
        super(message);
        setCode(code);
    }
    
    /**
     * Constructeur public appelant celui de la classe mère (super) et lui passant un objet "Throwable"
     * qui permet notamment d'identifier la cause de l'erreur mais aussi de récupérer tout un tas d'informations très pertinentes.
     * L'entier identifié par le label "code" permet d'identifier l'erreur
     */
    public MedecinException(Throwable cause, int code) {
        super(cause);
        setCode(code);
    }
    
     /**
     * Constructeur public appelant celui de la classe mère (super) et lui passant un message pour l'erreur
     * ainsi que la cause de l'erreur sous forme d'un objet de type "Throwable"
     * L'entier identifié par le label "code" permet d'identifier l'erreur
     */
    public MedecinException(String message, Throwable cause, int code) {
        super(message,cause);
        setCode(code);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tp2.DAO.IDaoLocal;
import tp2.jpa.Creneaux;
import tp2.jpa.Medecins;

/**
 *
 * @author Gaetan
 */
@WebServlet(urlPatterns = {"/InsertRvServlet"})
public class InsertRvServlet extends HttpServlet {

    @EJB
    protected IDaoLocal dao;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String view="/formRv.jsp";
            String actionServ = (String)request.getParameter("action");
            if(actionServ == null || actionServ.equals("")) {
                prepareMedecinsClientsForm(request, response);
            }
            else if(actionServ.equals("getCreneaux")) {
                handleDataAndPrepareCreneauForm(request,response);
            }
            else if(actionServ.equals("saveRv")) {
                saveRV(request,response);
            }
            getServletContext().getRequestDispatcher(view).forward(request, response);
        } finally {            
            out.close();
        }
    }
    
    protected void prepareMedecinsClientsForm(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("medecins", dao.getAllMedecins());
        request.setAttribute("action","choisirMed");
    }
    
    
    protected void handleDataAndPrepareCreneauForm(HttpServletRequest request, HttpServletResponse response) {
        Long idMedecin = Long.getLong(request.getParameter("medecin"));
        request.setAttribute("idMedecin",idMedecin);
        request.setAttribute("creneaux",dao.getCreneauxForMedecins(idMedecin));
        request.setAttribute("clients", dao.getAllClients());
        request.setAttribute("action","choisirCreneau");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void saveRV(HttpServletRequest request, HttpServletResponse response) {
        Long idClient = Long.getLong(request.getParameter("client"));
        Integer jour = Integer.getInteger(request.getParameter("jour"));
        Integer mois = Integer.getInteger(request.getParameter("mois"));
        Integer annee = Integer.getInteger(request.getParameter("annee"));
    }

}

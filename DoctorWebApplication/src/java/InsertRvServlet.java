/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
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
import javax.servlet.http.HttpSession;
import tp2.DAO.IDaoLocal;
import tp2.jpa.Creneaux;
import tp2.jpa.Medecins;
import tp2.jpa.Rv;

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
        Long idMedecin = Long.parseLong(request.getParameter("medecin"));
        Integer jour = Integer.parseInt(request.getParameter("jour"));
        Integer mois = Integer.parseInt(request.getParameter("mois"));
        Integer annee = Integer.parseInt(request.getParameter("annee"));
        
        //create session and set different attributes
        HttpSession session = request.getSession(true);
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(annee, (mois - 1), jour);
        Date date = new Date(c.getTimeInMillis());
        session.setAttribute("date", date);
        
        //set attributes for JSP page
        request.setAttribute("creneaux",dao.getFreeCreneauxForMedecinsAndDate(idMedecin,date));
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
        Long idClient = Long.parseLong(request.getParameter("client"));
        Long idCreneau  = Long.parseLong(request.getParameter("creneau"));
        
        //get back the data from Session
        HttpSession session = request.getSession();
        Date date = (Date) session.getAttribute("date");
        session.invalidate();
        
        Rv newRv = dao.addRv(date, idClient, idCreneau);
        request.setAttribute("rv",newRv);
        request.setAttribute("action","finalize");
    }

}
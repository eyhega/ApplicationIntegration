<%-- 
    Document   : formRv
    Created on : 26 nov. 2013, 16:43:24
    Author     : eyheramo
--%>

<%@page import="tp2.jpa.Creneaux"%>
<%@page import="tp2.jpa.Clients"%>
<%@page import="tp2.jpa.Medecins"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a RV</title>
    </head>
    <body>
        <%
            String action = (String)request.getAttribute("action");
            if(action.equals("choisirMed")) {
            %>
            <form method="POST" name="formRv">
            <input type="hidden" name="action" value="getCreneaux" />
            <table>
                <tr>
                    <td>Choisissez le nom du medecin</td>
                    <td>
                        <select name="medecin">
                        <%
                           List<Medecins> medecins = (List<Medecins>)request.getAttribute("medecins");
                           for(Medecins m : medecins) {
                               out.print("<option value=\"" + m.getId() + "\">" + m.getNom() + " " + m.getPrenom() + "</option>");
                           }
                        %>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" value="Chercher les creneaux"/></td></tr>
            </table>
        </form>
        <%
            }
            else if(action.equals("choisirCreneau")){
        %>
        
        <form method="POST" name="formRv">
            <% 
                Long idMedecin = (Long)request.getAttribute("idMedecin");
                out.print("<input type=\"hidden\" name=\"medecin\" value=\"" + idMedecin + "\" />");
            %>
            <input type="hidden" name="action" value="saveRv" />
            <table>
                <tr>
                    <td>Date du rendez-vous</td>
                    <td>Jour/Mois/Annee</td>
                    <td>
                        <select name="jour">
                            <option value="1" selected>1</option>
                            <%
                                for(int i = 2 ; i <= 31 ; ++i) {
                                    out.println("<option value=\""+i+"\">" + i + "</option>");
                                }
                            %>
                        </select>
                        <select name="mois">
                            <option value="1" selected>1</option>
                            <%
                                for(int i = 2 ; i <= 31 ; ++i) {
                                    out.println("<option value=\""+i+"\">" + i + "</option>");
                                }
                                %>
                        </select>
                        <select name="annee">
                            <option value="2013" selected>2013</option>
                            <%
                                for(int i = 2014 ; i <= 2020 ; ++i) {
                                    out.println("<option value=\""+i+"\">" + i + "</option>");
                                }
                                %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Choisissez le nom du client:</td>
                    <td>
                        <select name="client">
                            <%
                           List<Clients> clients = (List<Clients>)request.getAttribute("clients");
                           for(Clients c : clients) {
                               out.print("<option value=\"" + c.getId() + "\">" + c.getNom() + " " + c.getPrenom() + "</option>");
                           }
                           %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Choisir le creneau selon vos disponibilies:</td>
                    <td>
                        <select name="creneau">
                            <%
                           List<Creneaux> creneaux = (List<Creneaux>)request.getAttribute("creneaux");
                           for(Creneaux c : creneaux) {
                               out.print("<option value=\"" + c.getId() + "\">" + c.getHdebut()+ "h" + c.getMdebut()+ " à " + c.getHfin() + "h" +  c.getMfin() + "</option>");
                           }
                           %>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" value="Enregistrer RV" /></td></tr>
            </table>
        </form>
        
                
        <%
            }
        %>
    </body>
</html>

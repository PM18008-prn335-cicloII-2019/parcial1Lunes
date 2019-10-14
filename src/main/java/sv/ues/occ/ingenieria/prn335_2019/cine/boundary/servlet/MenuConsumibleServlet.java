/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.occ.ingenieria.prn335_2019.cine.boundary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.ues.occ.ingenieria.prn335_2019.cine.DataAccessException;
import sv.ues.occ.ingenieria.prn335_2019.cine.control.MenuConsumibleBean;
import sv.ues.occ.ingenieria.prn335_2019.cine.entity.MenuConsumible;

/**
 *
 * @author jcpenya
 */
@WebServlet(name = "MenuConsumibleServlet", urlPatterns = {"/menuconsumible"})
public class MenuConsumibleServlet extends HttpServlet {

    final static String NOMBRE_PARAMETRO = "nombre";
    @Inject
    MenuConsumibleBean menuConsumibleBean;
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int total = 0;
        List<MenuConsumible> registros = null;
        if (request.getParameterMap().containsKey(NOMBRE_PARAMETRO)) {
            response.setContentType("application/json");
        }
        try ( PrintWriter out = response.getWriter()) {
            StringBuilder sb = new StringBuilder("[");

            if (request.getParameterMap().containsKey(NOMBRE_PARAMETRO)) {
                try {
                    total = menuConsumibleBean.countByNombreLike(request.getParameter(NOMBRE_PARAMETRO));
                    if (total > 0) {
                        System.out.println("Par dentro "+request.getParameter(NOMBRE_PARAMETRO));
                        registros = menuConsumibleBean.findByNombreLike(request.getParameter(NOMBRE_PARAMETRO), 0, 1000);
                        for (int i = 0; i < registros.size(); i++) {

                            MenuConsumible registro = registros.get(i);
                            sb.append("{");
                            sb.append("idconsumible:").append(registro.getIdConsumible());
                            sb.append(",nombre:").append(registro.getNombre());
                            sb.append(",precio:").append(registro.getPrecio());
                            sb.append("}");
                            if (i < total) {
                                sb.append(",");
                            }
                        }
                        sb.append("]");
                        out.println(sb.toString());
                        return;
                    }   
                    throw new DataAccessException("no hay registros", null);
                } catch (DataAccessException ex) {
                    Logger.getLogger(MenuConsumibleServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("[]");
                }
                return;
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MenuConsumibleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Nada que mostrar!</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

}

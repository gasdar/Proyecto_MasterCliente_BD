/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import root.model.dao.MasterClienteDAO;
import root.model.dao.exceptions.RollbackFailureException;
import root.model.entities.MasterCliente;

/**
 *
 * @author Eliulson
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

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
        
        /* Acceder a valores obtenidos del JSP hacia variables locales del controlador */
        
        String identificacion = request.getParameter("identificacion");
        String nombres = request.getParameter("nombres");
        String apellido1 = request.getParameter("apellido1");
        
        
        /* Creación de un objeto entity que representa un registro de la tabla clientes
        Se le asignan valores y luego se los enviamos a la BD */
        
        MasterCliente cliente = new MasterCliente();
        cliente.setCliIdentificacion(identificacion);
        cliente.setCliNombres(nombres);
        cliente.setCliApellido1(apellido1);
        
        /* Creación del Objeto DAO, que sería parte del modelo de persistencia de la app,
        y posteriormente entregaría los datos a la BD, a la tabla correspondiente */
        
        MasterClienteDAO dao = new MasterClienteDAO();
        try {
            dao.create(cliente);
            /* El {0} indica que recibe un parámetro que está luego de la coma */
            Logger.getLogger("log").log(Level.INFO, "Valor identificaci\u00f3n cliente: {0}", cliente.getCliIdentificacion());
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger("log").log(Level.SEVERE, "Se present\u00f3 un error al intentar ingresar un cliente. {0}", ex.getMessage());
        }
        
        request.getRequestDispatcher("salida.jsp").forward(request, response);
        
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

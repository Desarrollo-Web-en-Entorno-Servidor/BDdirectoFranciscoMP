/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import es.albarregas.beans.Ave;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author FranciscoAntonio
 */
@WebServlet(name = "AccesoBD", urlPatterns = {"/AccesoBD"})
public class AccesoBD extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccesoBD</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccesoBD at " + request.getContextPath() + "</h1>");
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
        Connection conexion = null;
        Statement sentencia = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        Ave ave = null;
        List<Ave> listado = null;

        String anilla = request.getParameter("anilla");
        String sql = null;
        String url = null;
        

        
        
        
        try {
            //Carga el controlador para acceder a la BD.
            Class.forName("com.mysql.jdbc.Driver");
            //Realiza la conexión.
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebasjava","java2019","2019");
            sentencia = conexion.createStatement();
            
            if (request.getParameter("unaAnilla") != null) {

                sql = "select * from aves where anilla = ?";
                preparada = conexion.prepareStatement(sql);
                preparada.setString(1, anilla);
                try {
                    resultado = preparada.executeQuery();

                    resultado.next();
                    url = "unResultado.jsp";
                    request.setAttribute("anilla", resultado.getString("anilla"));
                    request.setAttribute("especie", resultado.getString("especie"));
                    request.setAttribute("lugar", resultado.getString("lugar"));
                    request.setAttribute("fecha", resultado.getString("fecha"));
                } catch (SQLException e) {

                    url = "error.jsp";
                    request.setAttribute("error", "La anilla " + anilla
                            + " no existe en la base de datos");
                }
            } else {
                sql = "select * from aves";
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(sql);
                listado = new ArrayList();
                url = "listaResultado.jsp";

                while (resultado.next()) {

                    ave = new Ave();
                    ave.setAnilla(resultado.getString("anilla"));
                    ave.setEspecie(resultado.getString("especie"));
                    ave.setLugar(resultado.getString("lugar"));
                    ave.setFecha(resultado.getString("fecha"));
                    listado.add(ave);
                }
                request.setAttribute("lista", listado);
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            System.out.println("Error al crear la conexión");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (preparada != null) {
                    preparada.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    }
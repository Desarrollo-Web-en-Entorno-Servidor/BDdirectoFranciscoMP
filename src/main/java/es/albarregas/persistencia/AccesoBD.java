/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    Connection conexion = null;
    Statement stm = null; // instrucción de consulta
    ResultSet rset = null; // conjunto de resultados
    Ave ave=null;
    List<Ave> aves=null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String BaseDeDatos = "jdbc:mysql://localhost/pruebasjava\",\"root\", \"albarregas\"";
			conexion = DriverManager.getConnection(BaseDeDatos,"java2019","2019");
			stm=conexion.createStatement();
			rset=stm.executeQuery("select * from aves where anilla=?");
			ResultSetMetaData metaDatos = rset.getMetaData();
			
		// Creamos un array unidimensional con los títulos de las columnas
			Object[] columnas = {metaDatos.getColumnName(1),metaDatos.getColumnName(2),metaDatos.getColumnName(3)};
			
			while ( rset.next() ) {
			// Se añade un elemento <Persona> a la colección por cada fila del resultado
				personas.add(new Persona(rset.getObject(1).toString(),rset.getObject(2).toString(),rset.getObject(3).toString()));			
			} // fin de while
			
		// Creamos un array bidimensional con los datos de la colección.		
			Object[][] filas = new Object[personas.size()][3];
			for ( int x = 0;x < personas.size(); x++ ){
				filas[x][0]=personas.get(x).getNumero();	
				filas[x][1]=personas.get(x).getNombre();		
				filas[x][2]=personas.get(x).getCategoria();					
			}
// Se crea objeto de tipo Jtable
			JTable tabla = new JTable(filas,columnas);
			
// Se crea objeto de tipo Vista
			@SuppressWarnings("unused")
			Vista miVista = new Vista(tabla);
		}catch ( SQLException excepcionSql ){
				System.out.println("Excepción:"+excepcionSql);
		} // fin de catch
		catch ( ClassNotFoundException noEncontroClase ){
			System.out.println("Excepción:"+noEncontroClase);
		} // fin de catch
		catch ( Exception excepcion ){
			System.out.println("Excepción:"+excepcion);
		} // fin de catch
		finally{
			try {
				rset.close();
				stm.close();
				conexion.close();
			} // fin de try
			catch ( Exception e ) {
				System.out.println("Excepción:"+e);
			} // fin de catch
		} // fin de finally	
	}
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

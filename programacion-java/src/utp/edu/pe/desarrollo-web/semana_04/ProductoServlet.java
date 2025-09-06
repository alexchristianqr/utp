/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import utils.HtmlGenerator;

/**
 *
 * @author LAB-USR-LCENTRO
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            String name = "";
//            HtmlGenerator html = new HtmlGenerator(out);
//            html.startHtml("Productos");
//            html.form("/MyServlet", "post", () -> {
//                html.p("Este es un formulario de ejemplo:");
//                html.input("text", "nombre", "");
//                html.button("submit", "Enviar");
//            });
//            html.endHtml();
//        }
//    }
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
        // processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HtmlGenerator html = new HtmlGenerator(out);
            html.startHtml("Productos");
            html.form("/WebApplication1/ProductoServlet", "post", () -> {
                html.p("Formulario de Contacto:");
                html.input("text", "Nombre", "inputName", "");
                html.input("text", "Email", "inputEmail", "");
                html.button("submit", "Enviar");
            });
            html.endHtml();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    class Usuario{
        String nombre = "";
        String email = "";

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        
        
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        
        List<Usuario> usuarios = new ArrayList<>();
        String nombre = request.getParameter("inputName");
        String email = request.getParameter("inputEmail");
        
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuarios.add(0,usuario.getNombre());

        response.setContentType("text/html;charset=UTF-8");

        HtmlGenerator html = new HtmlGenerator(response.getWriter());
        html.startHtml("Resultado del Formulario");
        html.h1("Hola, " + nombre + "!");
        html.p("Se ha recibido lo siguiente:");
        html.p("Nombre: " + nombre);
        html.p("Email: " + email);
        html.p("Gracias por enviar el formulario.");
        
        html.table(() -> {
            // Fila de encabezados
            html.tr(() -> {
                html.th("ID");
                html.th("Nombre");
                html.th("Em,ail");
            });

            // Fila 1 de datos
            html.tr(() -> {
                html.td("1");
                html.td("Laptop");
                html.td("$1200");
            });
        });
        
        html.endHtml();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}

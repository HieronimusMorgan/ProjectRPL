/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.pengguna;
import operation.operation;

/**
 *
 * @author Asus
 */
@WebServlet(urlPatterns = {"/KelolaUser"})
public class KelolaUser extends HttpServlet {
    operation op = new operation();
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
            out.println("<title>Servlet KelolaUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KelolaUser at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession(false);
//        String coba = (String) session.getAttribute("username");
//        session.getAttribute("password");
        ArrayList<pengguna> data = op.tampilPenggunaWP();

        out.print("<html>\n"
                + "<head>\n"
                + "<title>FaceIT</title>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style_kelola.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"sidebar\">\n"
                + "<h1>FaceIT </h1>                         "
                + "<a href=\"homeAdmin\">Home</a>\n"
                + "<a href=\"Akun\">Akun</a>\n"
                + "<a class=\"active\" href=\"KelolaUser\">Kelola User</a>\n"
                + "<a href=\"Logout\">Logout</a>\n"
                + "</div>\n"
                + "</form>\n"
                + "</div>"
                + "<div class=\"content\">\n"
                + "<div class=\"row\">\n"
                + "<div class=\"leftcolumn\">\n"
        );

        out.print("<html>\n"
                + "<head>\n"
                + "<title>FaceIT</title>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style_kelola.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"login-box\">\n"
                + "<h1>Kelola User</h1>\n"
                + "<form>\n"
                + "<p>Nama Lengkap</p>\n"
                + "<input type=\"text\" name=\"nama\" placeholder=\"Masukkan Nama Lengkap\">\n"
                + "<p>NIM</p>\n"
                + "<input type=\"text\" name=\"NIM\" placeholder=\"Masukkan NIM\">\n"
                + "<p>*NIM akan menajdi Username dan Password User</p>\n"
                + "<br>"
                + "<input type=\"submit\" name=\"Tambah\" value=\"Tambah\" onclick=\"form.action='TambahUser';\">\n"
                + "<input type=\"submit\" name=\"Hapus\" value=\"Hapus\" onclick=\"form.action='HapusUser';\">\n"
                + "</form>\n"
                + "</div>\n"
                + "</body>");
        
            if (data.isEmpty()) {
                out.println("<center>");
                out.println("<h1>Data User</h1>");
                out.println("<table border=3>");
                out.println("<tr>");
                out.println("<td>ID User</td>");
                out.println("<td>Nama</td>");
                out.println("<td>Username</td>");
                out.println("<td>Password Date</td>");
                out.println("</tr>");

                for (int i = 0; i < data.size(); i++) {
                    out.println("<tr>");
                    out.println("<td>" + data.get(i).getIdUser() + "</td>");
                    out.println("<td>" + data.get(i).getName() + "</td>");
                    out.println("<td>" + data.get(i).getUsername() + "</td>");
                    out.println("<td>" + data.get(i).getPassword() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</center>");
            } 
        out.print("</div>\n");
        out.print("</div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(urlPatterns = {"/AkunUser"})
public class AkunUser extends HttpServlet {

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
            out.println("<title>Servlet Akun</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Akun at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        String nama = (String) session.getAttribute("username");
        String pass = (String) session.getAttribute("password");
        String id = (String) session.getAttribute("iduser");
        String nameuser = (String) session.getAttribute("nameuser");
        session.getAttribute("password");
        out.print("<html>\n"
                + "<head>\n"
                + "<title>FaceIT</title>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n"
                + "\n"
                + "    <meta name=\"description\" content=\"\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "\n"
                + "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n"
                + "    <link rel=\"stylesheet\" href=\"css/bootstrap-theme.min.css\">\n"
                + "    <link rel=\"stylesheet\" href=\"css/bootstrap-grid.min.css\">\n"
                + "    <link rel=\"stylesheet\" href=\"css/templatemo-style.css\">\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n"
                + "<link rel=\"stylesheet\" href=\"css/styleSidebar_1_1.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"sidebar bg-transparent\">\n"
                + "<h1 class=\"text-white\">FaceIT </h1>                         "
                + "<a class=\"text-white\" href=\"home\">Home</a>\n"
                + "<a class=\"text-white\" href=\"AkunUser\">Akun</a>\n"
                + "<a class=\"text-white\" href=\"kontakInfo\">Kontak & Informasi</a>\n"
                + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
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
                + "<h1>Informasi Akun</h1>\n"
                + "<form>\n"
                + "<p>Nama User</p>\n"
                + "<input type=\"text\" name=\"nama\" value=\"" + nameuser + "\" disabled>\n"
                + "<p>Username</p>\n"
                + "<input type=\"text\" name=\"NIM\" value=\"" + nama + "\" disabled>\n"
                + "<p>Password</p>\n"
                + "<input type=\"text\" name=\"NIM\" value=\"" + pass + "\" disabled>\n"
                + "<p>*NIM adalah Username dan Password User default</p>\n"
                + "<br>"
                + "<input class=\"btn btn-primary\" type=\"submit\" name=\"ubah\" value=\"Ubah Password\" onclick=\"form.action='ubahPass';\">\n"
                + "<input class=\"btn btn-primary\" type=\"submit\" name=\"kembali\" value=\"Kembali\" onclick=\"form.action='home';\">\n"
                + "</form>\n"
                + "</div>\n"
                + "</body>\n"
                + "\n"
                + "</html>");
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

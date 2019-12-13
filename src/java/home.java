/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.pengguna;
import object.postingan;
import operation.*;

/**
 *
 * @author asus
 */
@WebServlet(urlPatterns = {"/home"})
public class home extends HttpServlet {

    operation a = new operation();

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
            out.println("<title>Servlet home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet home at " + request.getContextPath() + "</h1>");
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
        out.println("<html>\n"
                + "\n"
                + "<head>\n"
                + "<meta charset=\"utf-8\">\n"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n"
                + "\n"
                + "<meta name=\"description\" content=\"\">\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "\n"
                + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n"
                + "<link rel=\"stylesheet\" href=\"css/bootstrap-theme.min.css\">\n"
                + "<link rel=\"stylesheet\" href=\"css/bootstrap-grid.min.css\">\n"
                + "<link rel=\"stylesheet\" href=\"css/templatemo-style.css\">\n"
                + "<link rel=\"stylesheet\" href=\"css/styleSidebar.css\">\n"
                + "    <link rel=\"stylesheet\" href=\"styleLogin.css\">\n"
                + "\n"
                + "\n"
                + "    <script src=\"js/bootstrap.js\"></script>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "<div class=\"sidebar bg-transparent\">\n"
                + "<center>\n"
                + "<h1 class=\"text-white\">FaceIT</h1>\n"
                + "</center>\n"
                + "<a class=\"text-white\" class=\"active\" href=\"home\">Home</a>\n"
                + "<a class=\"text-white\" href=\"AkunUser\">Akun</a>\n"
                + "<a class=\"text-white\" href=\"kontakInfo\">Kontak & Informasi</a>\n"
                + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                + "</div>\n"
                + "<div class=\"carda\">\n"
                + "<div class=\"card bg-dark rounded-sm\">\n"
                + "<form method=\"GET\" action=\"tambah\" id=\"usrform\">\n"
                + "<h5 class=\"card-header  text-white\">Tambah Postingan</h5>\n"
                + "<div class=\"card-body\">\n"
                + "<div class=\"form-group\">\n"
                + "<textarea class=\"form-control rounded-0\" form=\"usrform\"  rows=\"3\" name=\"posting\" placeholder=\"Tulis disini ...\"></textarea>\n"
                + "</div>\n"
                + "<input class=\"btn btn-primary\" type=\"submit\" value=\"Tambah Postingan\">\n"
                + "</div>\n"
                + "</form>\n"
                + "</div>\n"
                + "</div>\n"
                + "<div class=\"content\">\n"
                + "<div class=\"row\">\n"
                + "<div class=\"leftcolumn\">");
        java.util.List<postingan> posting;

        posting = a.tampilPostingan();
        for (int i = 0; i < posting.size(); i++) {
            out.print("<div class=\"cardi bg-transparent border border-white rounded-lg\">\n"
                    + "<h5 class=\"card-header text-white\">" + a.cariPengirim(posting.get(i).getIdUser(), posting.get(i).getIdAdmin()) + "</h5>\n"
                    + "<div class=\"card-body text-white\">\n"
                    + "<h6 class=\"card-title\">" + posting.get(i).getWaktu().toGMTString() + "</h6>\n"
                    + "<p class=\"card-text\">" + posting.get(i).getIsi() + "</p>\n"
                    + "<a href=tampilKomentar?idPostingan=" + posting.get(i).getIdPostingan() + " class=\"btn btn-primary\">Komentar</a>\n"
                    + "</div></div>");
        }

        out.print(
                "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</body>\n"
                + "\n"
                + "</html>");
        session.removeAttribute("idPostingan");
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
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        pengguna pengguna = a.operation(username, password);
        if (pengguna != null) {
            if (a.cekUser(pengguna)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", pengguna.getUsername());
                session.setAttribute("password", pengguna.getPassword());
                session.setAttribute("iduser", pengguna.getIdUser());
                session.setAttribute("nameuser", pengguna.getName());
                response.sendRedirect("home");
            } else if (!a.cekUser(pengguna)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", pengguna.getUsername());
                session.setAttribute("password", pengguna.getPassword());
                session.setAttribute("iduser", pengguna.getIdUser());
                session.setAttribute("nameuser", pengguna.getName());
                response.sendRedirect("homeAdmin");
            }
        } else {
            response.sendRedirect("index_1.html");
        }

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

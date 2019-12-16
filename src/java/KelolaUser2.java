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
@WebServlet(urlPatterns = {"/KelolaUser2"})
public class KelolaUser2 extends HttpServlet {

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
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\">"
                + "<script type=\"text/javascript\" src=\"js/jquery.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/bootstrap.js\"></script>	"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"sidebar bg-transparent\">\n"
                + "<center>\n"
                + "<h1 class=\"text-white\">FaceIT</h1>\n"
                + "</center>\n"
                + "<a class=\"text-white\" href=\"homeAdmin\">Home</a>\n"
                + "<a class=\"text-white\" href=\"Akun\">Akun</a>\n"
                + "<a class=\"active\" class=\"text-white\" href=\"KelolaUser\">Kelola User</a>\n"
                + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                + "</div>\n"
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
                + "<p>*NIM akan menjadi Username dan Password User</p>\n"
                + "<br>"
                + "<input type=\"submit\" name=\"Tambah\" value=\"Tambah\" onclick=\"form.action='TambahUser';\">\n"
                + "<input type=\"submit\" name=\"Hapus\" value=\"Hapus\" onclick=\"form.action='HapusUser';\">\n"
                + "<p style=\"color: red;\">*NIM kurang/lebih dari 9</p>"
                + "</form>\n"
                + "</div>\n"
                + "</body>"
                + "\n"
                + "</html>"
        );

        if (!data.isEmpty()) {
            out.print("<table class=\"table table-bordered table-striped\">\n"
                    + "<thead>\n"
                    + "<tr>\n"
                    + "<th scope=\"col\">No</th>\n"
                    + "<th scope=\"col\">Id User</th>\n"
                    + "<th scope=\"col\">Nama</th>\n"
                    + "<th scope=\"col\">NIM</th>\n"
                    + "</tr>\n"
                    + "</thead>"
                    + "<tbody>");

            for (int i = 0; i < data.size(); i++) {
                out.print("<tr>\n"
                        + "<th scope=\"row\">" + (i + 1) + "</th>\n"
                        + "<td>" + data.get(i).getIdUser() + "</td>\n"
                        + "<td>" + data.get(i).getName() + "</td>\n"
                        + "<td>" + data.get(i).getUsername() + "</td>\n"
                        + "</tr>\n"
                        + "<tr>");
            }
            out.print("</tbody>");
            out.print("</table>");
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

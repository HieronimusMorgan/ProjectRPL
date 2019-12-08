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
import object.komentar;
import object.pengguna;
import object.postingan;

/**
 *
 * @author Asus
 */
@WebServlet(urlPatterns = {"/kontakInfo"})
public class kontakInfo extends HttpServlet {

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
            out.println("<title>Servlet kontakInfo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet kontakInfo at " + request.getContextPath() + "</h1>");
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

        out.print("<html>\n"
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
                + "<link rel=\"stylesheet\" href=\"css/styleSidebar_1_1.css\">\n"
                + "\n"
                + "\n"
                + "<script src=\"js/bootstrap.js\"></script>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "<div class=\"sidebar bg-transparent \">\n"
                + "<center>\n"
                + " <h1 class=\"text-white\">FaceIT</h1>\n"
                + "</center>\n"
                + "<a class=\"text-white\" href=\"home\">Home</a>\n"
                + "<a class=\"text-white\" href=\"Akun\">Akun</a>\n"
                + "<a class=\"text-white\" href=\"kontakInfo\">Kontak & Informasi</a>\n"
                + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                + "</div>\n"
                + "</form>\n"
                + "</div>\n"
                + "</div>\n"
                + "<div class=\"coba \">\n"
                + "     \n"
                + "            <div class=\"leftcolumn\">\n"
                + "                <div class=\"cardi bg-transparent rounded-lg\">\n"
                + "                    <div class=\"container-md bg-transparent\">\n"
                + "                        <div class=\"row justify-content-md-center bg-transparent \">\n"
                + "                            <div class=\"card text-center row-4 bg-transparent\">\n"
                + "                                <div class=\"card-body text-white\">\n"
                + "<h2>Kontak & Informasi FaceIT</h2>"
                + "<br><br>"
                + "<h3>Informasi :</h3>"
                + "<p>Ini merupakan forum diskusi bagi Program Studi Informatika Universitas Sanata Dharma Yogyakarta</p>"
                + "<br><br>"
                + "<h3>Apabila Terjadi Kesalahan :</h3>"
                + "<p>Hubungi Sekretariat Informatika</p>"
                + "<p>Tlp : (0274) 883037</p>"
                + "<br><br>"
                + "<h3>Alamat :</h3>"
                + "<p>Jl. Paingan, Maguwoharjo, Depok, Krodan, Maguwoharjo, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281</p>"
                + "<br><br>"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "   \n"
                + "</body>\n"
                + "\n"
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

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
import object.postingan;
import operation.operation;

/**
 *
 * @author acer
 */
@WebServlet(urlPatterns = {"/komentar"})
public class komentar extends HttpServlet {

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
            out.println("<title>Servlet komen</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet komen at " + request.getContextPath() + "</h1>");
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
        String pengirim = request.getParameter("pengirim");
        String isi = request.getParameter("isi");
        out.print("<html>\n"
                + "    <head>\n"
                + "        <title>FaceIT</title>\n"
                + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"cssKomen.css\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"sidebar\">\n"
                + "            <h1>FaceIT </h1>                         \n"
                + "            <a class=\"active\" href=\"home\">Home</a>\n"
                + "            <a href=\"AkunUser\">Akun</a>\n"
                + "            <a href=\"Logout\">Logout</a>\n"
                + "        </div>\n"
                + "        <div class=\"carda\">\n"
                + "            <form>\n"
                + "                <h2>Postingan</h2>\n"
                + "                <h5>\n"
                + "                    <textarea rows=\"4\" cols=\"50\" name=\"posting\" form=\"usrform\" readonly>"+isi+"</textarea>\n"
                + "                </h5>\n"
                + "                <textarea rows=\"2\" cols=\"50\" name=\"komentar\" placeholder=\"Tulis Komentar\"></textarea>\n"
                + "                <br>\n"
                + "                <div class=\"kirim-komentar\"><input type=\"submit\" name=\"kirim\" value=\"Kirim\"></div>\n"
                + "            </form>\n"
                + "\n"
                + "        </div>\n"
                + "\n"
                + "    </body>\n"
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

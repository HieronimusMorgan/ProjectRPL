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
import javax.xml.ws.WebServiceRef;
import org.me.service.WebServiceRPL_Service;

/**
 *
 * @author asus
 */
@WebServlet(urlPatterns = {"/home"})
public class home extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebServiceRPL/WebServiceRPL.wsdl")
    private WebServiceRPL_Service service;

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
        String coba = (String) session.getAttribute("username");
        session.getAttribute("password");
        out.print("<html>\n"
                + "<head>\n"
                + "    <title>FaceIT</title>\n"
                + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"sidebar\">\n"
                + "        <h1>FaceIT </h1>                         "
                + "        <a class=\"active\" href=\"#home\">Home</a>\n"
                + "        <a href=\"#news\">Akun</a>\n"
                + "        <a href=\"#contact\">Logout</a>\n"
                + "    </div>\n"
                + "<div class=\"carda\">\n"
                + "        <form method=\"GET\" action=\"tambah\" id=\"usrform\">\n"
                + "\n"
                + "                <h2>Tambah Postingan</h2>\n"
                + "                <h5><textarea rows=\"4\" cols=\"50\" name=\"posting\" form=\"usrform\" placeholder=\"Tulis disini ...\">\n"
                + "</textarea></h5>\n"
                + "                <div class=\"tambah\"><input type=\"submit\" name=\"tambah\" value=\"Tambah\"></div>\n"
                + "\n"
                + "        </form>\n"
                + "    </div>"
                + "    <div class=\"content\">\n"
                + "        <div class=\"row\">\n"
                + "            <div class=\"leftcolumn\">\n"
        );
        java.util.List<org.me.service.Postingan> posting;
        posting = tampilPostingan();

        for (int i = 0; i < posting.size(); i++) {

            out.print("<div class=\"card\">\n");
            out.print(" <h1>" + posting.get(i).getNamaPengirim() + "</h1>\n");
            out.print("<h5>" + posting.get(i).getWaktu().getEonAndYear() + "-" + posting.get(i).getWaktu().getMonth() + "-"
                    + posting.get(i).getWaktu().getDay() + " WIB " + posting.get(i).getWaktu().getHour() + ":"
                    + posting.get(i).getWaktu().getMinute() + ":" + posting.get(i).getWaktu().getSecond() + "</h5>");
            out.print(" <p>" + posting.get(i).getIsi() + "</p>\n");
            out.print("                </div>\n");
        }
        out.print("            </div>\n"
                + "        </div>\n"
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
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (operation(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);

            response.sendRedirect("home");
        } else {
            request.setAttribute("errorMessage", "Invalid user or password");
            response.sendRedirect("index.html");
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

    private Boolean operation(java.lang.String userDB, java.lang.String passDB) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.service.WebServiceRPL port = service.getWebServiceRPLPort();
        return port.operation(userDB, passDB);
    }

    private java.util.List<org.me.service.Postingan> tampilPostingan() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.service.WebServiceRPL port = service.getWebServiceRPLPort();
        return port.tampilPostingan();
    }

}

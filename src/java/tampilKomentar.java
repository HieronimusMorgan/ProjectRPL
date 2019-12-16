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
import operation.operation;

/**
 *
 * @author acer
 */
@WebServlet(urlPatterns = {"/tampilKomentar"})
public class tampilKomentar extends HttpServlet {

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

        if (session.getAttribute("idPostingan") == null) {
            String nama = (String) session.getAttribute("username");
            String pass = (String) session.getAttribute("password");
            String id = (String) session.getAttribute("iduser");
            String nameuser = (String) session.getAttribute("nameuser");
            String pengirim = request.getParameter("pengirim");
            String idPostingan = request.getParameter("idPostingan");
            pengguna pengguna = a.operation(nama, pass);
            session.setAttribute("idPostingan", idPostingan);
            if (a.cekUser(pengguna)) {
                String idPos = (String) session.getAttribute("idPostingan");
                String postNama = a.namaPostingan(idPos);
                postingan pos = a.cariPostingan(idPos);
                System.out.println(postNama + " idUser");
                System.out.println(id);
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
                        + "<link rel=\"stylesheet\" href=\"css/styleSidebar_1.css\">\n"
                        + "<link rel=\"stylesheet\" href=\"styleLogin.css\">\n"
                        + "\n"
                        + "\n"
                        + "<script src=\"js/bootstrap.js\"></script>\n"
                        + "<script src=\"js/jquery-3.4.1.slim.min.js\"></script>\n"
                        + "<script src=\"js/popper.min.js\"></script>\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "<div class=\"modal fade\" id=\"modalPostingan\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                        + "            <div class=\"modal-dialog\" role=\"document\">\n"
                        + "                <div class=\"modal-content\">\n"
                        + "                    <div class=\"modal-header\">\n"
                        + "                        <h5 class=\"modal-title\">HAPUS POSTINGAN</h5>\n"
                        + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                        + "                            <span aria-hidden=\"true\">&times;</span>\n"
                        + "                        </button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-body\">\n"
                        + "                        <p>Apakah anda yakin menghapus postingan ini?</p>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-footer\">\n"
                        + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusPostingan'\">Hapus</button>\n"
                        + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>"
                        + "<div class=\"sidebar bg-transparent \">\n"
                        + "<center>\n"
                        + " <h1 class=\"text-white\">FaceIT</h1>\n"
                        + "</center>\n"
                        + "<a class=\"text-white\" href=\"home\">Home</a>\n"
                        + "<a class=\"text-white\" href=\"AkunUser\">Akun</a>\n"
                        + "<a class=\"text-white\" href=\"kontakInfo\">Kontak & Informasi</a>\n"
                        + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                        + "</div>\n"
                        + "<div class=\"carda\">\n"
                        + "<div class=\"card bg-dark rounded-sm\">\n"
                        + "<form method=\"GET\" action=\"tambahKomentar\" id=\"usrform\">\n"
                        + "<center>"
                        + "<h5 class=\"card-header text-white\">Tambah Komentar - "+nameuser+" - "+nama+"</h5>\n"
                        + "</center>\n"
                        + "<div class=\"card-body\">\n"
                        + "<div class=\"form-group\">\n"
                        + "<h5 style='color:white;'>Postingan : " + postNama + " "+pos.getWaktu().toString()+"</h5>\n");
                if (pos.getIdUser() != null) {
                    if (pos.getIdUser().equalsIgnoreCase(id)) {
                        out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                                + "<br>"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalPostingan\">Hapus Postingan</button>"
                                + "<br><br>"
                                + "</div>\n ");
                    } else {
                        out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                                + "</div>\n ");
                    }
                } else if (pos.getIdUser() == null) {
                    out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                            + "</div>\n ");
                } else {
                    out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                            + "</div>\n ");
                }

                out.print("<div class=\"form-group\">\n"
                        + "<textarea class=\"form-control rounded-0\" form=\"usrform\"  rows=\"3\" name=\"komentar\" placeholder=\"Tulis komen disini ...\"></textarea>\n"
                        + "</div>\n"
                        + "<input class=\"btn btn-primary\" type=\"submit\" value=\"Tambah Komentar\">\n"
                        + "</div>\n"
                        + "</form>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "<div class=\"content\">\n"
                        + "<div class=\"row\">\n"
                        + "<div class=\"leftcolumn\">");
                List<komentar> komen;
                postingan p = new postingan();
                p.setIdPostingan(idPostingan);
                komen = a.tampilKomentar(p);
                for (int i = 0; i < komen.size(); i++) {
                    if (i == 0) {
                        out.print("<div class=\"cardi  bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString() + "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n");
                    } else {
                        out.print("<div class=\"cardu  bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString() + "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n");
                    }
                    if (komen.get(i).getIdUser() != null) {
                        if (komen.get(i).getIdUser().equalsIgnoreCase(id)) {
                            out.print("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                    + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                    + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                    + "                <div class=\"modal-content\">\n"
                                    + "                    <div class=\"modal-header\">\n"
                                    + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                    + "                        </button>\n"
                                    + "                    </div>\n"
                                    + "                    <div class=\"modal-body\">\n"
                                    + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                    + "                    </div>\n"
                                    + "                    <div class=\"modal-footer\">\n"
                                    + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                    + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                    + "                    </div>\n"
                                    + "                </div>\n"
                                    + "            </div>\n"
                                    + "        </div>"
                                    + "</div></div>");
                        } else {
                            out.print("</div></div>");
                        }
                    } else if (komen.get(i).getIdUser() == null) {
                        out.print("</div></div>");
                    } else {
                        out.print("</div></div>");
                    }
                }
                out.print(
                        "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + " <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n"
                        + " <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
                        + " <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            } else if (!a.cekUser(pengguna)) {
                String idPos = (String) session.getAttribute("idPostingan");
                postingan pos = a.cariPostingan(idPos);
                String postNama = a.namaPostingan(idPos);
                System.out.println(idPos);
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
                        + "<link rel=\"stylesheet\" href=\"css/styleSidebar_1.css\">\n"
                        + "<link rel=\"stylesheet\" href=\"styleLogin.css\">\n"
                        + "\n"
                        + "\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "<div class=\"modal fade\" id=\"modalPostingan\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                        + "            <div class=\"modal-dialog\" role=\"document\">\n"
                        + "                <div class=\"modal-content\">\n"
                        + "                    <div class=\"modal-header\">\n"
                        + "                        <h5 class=\"modal-title\">HAPUS POSTINGAN</h5>\n"
                        + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                        + "                            <span aria-hidden=\"true\">&times;</span>\n"
                        + "                        </button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-body\">\n"
                        + "                        <p>Apakah anda yakin menghapus postingan ini?</p>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-footer\">\n"
                        + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusPostingan'\">Hapus</button>\n"
                        + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>"
                        + "<div class=\"sidebar bg-transparent\">\n"
                        + "<center>\n"
                        + "<h1 class=\"text-white\">FaceIT</h1>\n"
                        + "</center>\n"
                        + "<a class=\"text-white\" href=\"homeAdmin\">Home</a>\n"
                        + "<a class=\"text-white\" href=\"KelolaUser\">Kelola User</a>\n"
                        + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                        + "</div>\n"
                        + "<div class=\"carda\">\n"
                        + "<div class=\"card bg-dark rounded-sm\">\n"
                        + "<form method=\"GET\" action=\"tambahKomentar\" id=\"usrform\">\n"
                        + "<center>"
                        + "<h5 class=\"card-header text-white\">Tambah Komentar - "+nameuser+" - "+nama+"</h5>\n"
                        + "</center>\n"
                        + "<div class=\"card-body\">\n" + "                <div class=\"form-group\">\n"
                        + "<h5 style='color:white;'>Postingan : " + postNama + " "+pos.getWaktu().toString()+"</h5>\n"
                        + "<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                        + "</div>\n "
                        + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalPostingan\">Hapus Postingan</button>"
                        + "<br><br>"
                        + "<div class=\"form-group\">\n"
                        + "<textarea class=\"form-control rounded-0\" form=\"usrform\"  rows=\"3\" name=\"komentar\" placeholder=\"Tulis komen disini ...\"></textarea>\n"
                        + "</div>\n"
                        + "<input class=\"btn btn-primary\" type=\"submit\" value=\"Tambah Komentar\">\n"
                        + "</div>\n"
                        + "</form>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "<div class=\"content\">\n"
                        + "<div class=\"row\">\n"
                        + "<div class=\"leftcolumn\">");
                List<komentar> komen;
                postingan p = new postingan();
                p.setIdPostingan(idPostingan);
                komen = a.tampilKomentar(p);
                for (int i = 0; i < komen.size(); i++) {
                    if (i == 0) {
                        out.print("<div class=\"cardi bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                + "                <div class=\"modal-content\">\n"
                                + "                    <div class=\"modal-header\">\n"
                                + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                + "                        </button>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-body\">\n"
                                + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-footer\">\n"
                                + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>"
                                + "</div></div>");
                    } else {
                        out.print("<div class=\"cardu bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                + "                <div class=\"modal-content\">\n"
                                + "                    <div class=\"modal-header\">\n"
                                + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                + "                        </button>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-body\">\n"
                                + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-footer\">\n"
                                + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>"
                                + "</div></div>");
                    }
                }
                out.print(
                        "</div>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + " <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            }
        } else {
            String nama = (String) session.getAttribute("username");
            String pass = (String) session.getAttribute("password");
            String id = (String) session.getAttribute("iduser");
            String nameuser = (String) session.getAttribute("nameuser");
            String pengirim = request.getParameter("pengirim");
            String idPostingan = request.getParameter("idPostingan");
            pengguna pengguna = a.operation(nama, pass);
            if (a.cekUser(pengguna)) {
                String idPos = (String) session.getAttribute("idPostingan");
                postingan pos = a.cariPostingan(idPos);
                String postNama = a.namaPostingan(idPos);
                out.print("<html>\n"
                        + "\n"
                        + "<head>\n"
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
                        + "    <link rel=\"stylesheet\" href=\"css/styleSidebar_1.css\">\n"
                        + "<link rel=\"stylesheet\" href=\"styleLogin.css\">\n"
                        + "\n"
                        + "\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "<div class=\"modal fade\" id=\"modalPostingan\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                        + "            <div class=\"modal-dialog\" role=\"document\">\n"
                        + "                <div class=\"modal-content\">\n"
                        + "                    <div class=\"modal-header\">\n"
                        + "                        <h5 class=\"modal-title\">HAPUS POSTINGAN</h5>\n"
                        + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                        + "                            <span aria-hidden=\"true\">&times;</span>\n"
                        + "                        </button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-body\">\n"
                        + "                        <p>Apakah anda yakin menghapus postingan ini?</p>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-footer\">\n"
                        + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusPostingan'\">Hapus</button>\n"
                        + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>"
                        + "<div class=\"sidebar bg-transparent \">\n"
                        + "<center>\n"
                        + " <h1 class=\"text-white\">FaceIT</h1>\n"
                        + "</center>\n"
                        + "<a class=\"text-white\" href=\"home\">Home</a>\n"
                        + "<a class=\"text-white\" href=\"AkunUser\">Akun</a>\n"
                        + "<a class=\"text-white\" href=\"kontakInfo\">Kontak & Informasi</a>\n"
                        + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                        + "</div>\n"
                        + "<div class=\"carda\">\n"
                        + "<div class=\"card bg-dark rounded-sm\">\n"
                        + "<form method=\"GET\" action=\"tambahKomentar\" id=\"usrform\">\n"
                        + "<center>"
                        + "<h5 class=\"card-header text-white\">Tambah Komentar - "+nameuser+" - "+nama+"</h5>\n"
                        + "</center>\n"
                        + "            <div class=\"card-body\">\n"
                        + "                <div class=\"form-group\">\n"
                        + "<h5 style='color:white;'>Postingan : " + postNama + " "+pos.getWaktu().toString()+"</h5>\n");
                if (pos.getIdUser() != null) {
                    if (pos.getIdUser().equalsIgnoreCase(id)) {
                        out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                                + "<br>"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalPostingan\">Hapus Postingan</button>"
                                + "<br><br>"
                                + "</div>\n ");
                    } else {
                        out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                                + "</div>\n ");
                    }
                } else if (pos.getIdUser() == null) {
                    out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                            + "</div>\n ");
                } else {
                    out.print("<text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                            + "</div>\n ");
                }

                out.print("<div class=\"form-group\">\n"
                        + "<textarea class=\"form-control rounded-0\" form=\"usrform\"  rows=\"3\" name=\"komentar\" placeholder=\"Tulis komen disini ...\"></textarea>\n"
                        + "</div>\n"
                        + "<input class=\"btn btn-primary\" type=\"submit\" value=\"Tambah Komentar\">\n"
                        + "</div>\n"
                        + "</form>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "<div class=\"content\">\n"
                        + "<div class=\"row\">\n"
                        + "<div class=\"leftcolumn\">"
                );
                List<komentar> komen;
                postingan p = new postingan();
                p.setIdPostingan(idPostingan);
                komen = a.tampilKomentar(p);
                for (int i = 0; i < komen.size(); i++) {
                    if (i == 0) {
                        out.print("<div class=\"cardi  bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n");
                    } else {
                        out.print("<div class=\"cardu bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n");
                    }
                    if (komen.get(i).getIdUser() != null) {
                        if (komen.get(i).getIdUser().equalsIgnoreCase(id)) {
                            out.print("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                    + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                    + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                    + "                <div class=\"modal-content\">\n"
                                    + "                    <div class=\"modal-header\">\n"
                                    + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                    + "                        </button>\n"
                                    + "                    </div>\n"
                                    + "                    <div class=\"modal-body\">\n"
                                    + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                    + "                    </div>\n"
                                    + "                    <div class=\"modal-footer\">\n"
                                    + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                    + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                    + "                    </div>\n"
                                    + "                </div>\n"
                                    + "            </div>\n"
                                    + "        </div>"
                                    + "</div></div>");
                        } else {
                            out.print("</div></div>");
                        }
                    } else if (komen.get(i).getIdUser() == null) {
                        out.print("</div></div>");
                    } else {
                        out.print("</div></div>");
                    }
                }
                out.print(
                        "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + " <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            } else if (!a.cekUser(pengguna)) {
                String idPos = (String) session.getAttribute("idPostingan");
                postingan pos = a.cariPostingan(idPos);
                String postNama = a.namaPostingan(idPos);
                out.print("<html>\n"
                        + "\n"
                        + "<head>\n"
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
                        + "    <link rel=\"stylesheet\" href=\"css/styleSidebar_1.css\">\n"
                        + "<link rel=\"stylesheet\" href=\"styleLogin.css\">\n"
                        + "\n"
                        + "\n"
                        + "<script src=\"js/bootstrap.js\"></script>\n"
                        + "<script src=\"js/jquery-3.4.1.slim.min.js\"></script>\n"
                        + "<script src=\"js/popper.min.js\"></script>\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "<div class=\"modal fade\" id=\"modalPostingan\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                        + "            <div class=\"modal-dialog\" role=\"document\">\n"
                        + "                <div class=\"modal-content\">\n"
                        + "                    <div class=\"modal-header\">\n"
                        + "                        <h5 class=\"modal-title\">HAPUS POSTINGAN</h5>\n"
                        + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                        + "                            <span aria-hidden=\"true\">&times;</span>\n"
                        + "                        </button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-body\">\n"
                        + "                        <p>Apakah anda yakin menghapus postingan ini?</p>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-footer\">\n"
                        + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusPostingan'\">Hapus</button>\n"
                        + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>"
                        + "<div class=\"sidebar bg-transparent\">\n"
                        + "<center>\n"
                        + "<h1 class=\"text-white\">FaceIT</h1>\n"
                        + "</center>\n"
                        + "<a class=\"text-white\" href=\"homeAdmin\">Home</a>\n"
                        + "<a class=\"text-white\" href=\"KelolaUser\">Kelola User</a>\n"
                        + "<a class=\"text-white\" href=\"Logout\">Logout</a>\n"
                        + "</div>\n"
                        + "<div class=\"carda\">\n"
                        + "<div class=\"card bg-dark rounded-sm\">\n"
                        + "<form method=\"GET\" action=\"tambahKomentar\" id=\"usrform\">\n"
                        + "<center>"
                        + "<h5 class=\"card-header text-white\">Tambah Komentar - "+nameuser+" - "+nama+"</h5>\n"
                        + "</center>\n"
                        + "            <div class=\"card-body\">\n"
                        + "                <div class=\"form-group\">\n"
                        + "<h5 style='color:white;'>Postingan : " + postNama + " "+pos.getWaktu().toString()+"</h5>\n"
                        + "                    <text readonly class=\"form-control\" id=\"exampleFormControlTextarea2\" rows=\"2\">" + pos.getIsi() + "</text>\n"
                        + "                </div>\n "
                        + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalPostingan\">Hapus Postingan</button>"
                        + "<br><br>"
                        + "<div class=\"form-group\">\n"
                        + "                    <textarea class=\"form-control rounded-0\" form=\"usrform\"  rows=\"3\" name=\"komentar\" placeholder=\"Tulis komen disini ...\"></textarea>\n"
                        + "                </div>\n"
                        + "                <input class=\"btn btn-primary\" type=\"submit\" value=\"Tambah Komentar\">\n"
                        + "            </div>\n"
                        + "</form>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div class=\"content\">\n"
                        + "        <div class=\"row\">\n"
                        + "            <div class=\"leftcolumn\">");
                List<komentar> komen;
                postingan p = new postingan();
                p.setIdPostingan(idPostingan);
                komen = a.tampilKomentar(p);
                for (int i = 0; i < komen.size(); i++) {
                    if (i == 0) {
                        out.print("<div class=\"cardi bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                + "                <div class=\"modal-content\">\n"
                                + "                    <div class=\"modal-header\">\n"
                                + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                + "                        </button>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-body\">\n"
                                + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-footer\">\n"
                                + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>"
                                + "</div></div>");
                    } else {
                        out.print("<div class=\"cardu bg-transparent border border-white rounded-lg\">\n"
                                + "<h5 class=\"card-header text-white\">" + a.cariPengirim(komen.get(i).getIdUser(), komen.get(i).getIdAdmin()) + "</h5>\n"
                                + "<div class=\"card-body text-white\">\n"
                                + "<h6 class=\"card-title\">" + komen.get(i).getWaktuKomentar().toString()+ "</h6>\n"
                                + "<p class=\"card-text\">" + komen.get(i).getIsiKomentar() + "</p>\n"
                                + "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modalKomentar\">Hapus Komentar</button>\n"
                                + "<div class=\"modal fade\" id=\"modalKomentar\" role=\"dioalog\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n"
                                + "            <div class=\"modal-dialog\" role=\"document\">\n"
                                + "                <div class=\"modal-content\">\n"
                                + "                    <div class=\"modal-header\">\n"
                                + "                        <h5 class=\"modal-title\" style=\"color:black;\">HAPUS KOMENTAR</h5>\n"
                                + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                                + "                            <span aria-hidden=\"true\">&times;</span>\n"
                                + "                        </button>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-body\">\n"
                                + "                        <p style=\"color:black;\">Apakah anda yakin menghapus komentar ini?</p>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"modal-footer\">\n"
                                + "                        <button type=\"submit\" class=\"btn btn-primary\" onclick=\"window.location.href='hapusKomentar?idKomentar=" + komen.get(i).getIdKomentar() + "'\">Hapus</button>\n"
                                + "                        <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Batal</button>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>"
                                + "</div></div>");
                    }
                }
                out.print(
                        "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + " <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
                        + "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>"
                        + "</body>\n"
                        + "\n"
                        + "</html>");
            }
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

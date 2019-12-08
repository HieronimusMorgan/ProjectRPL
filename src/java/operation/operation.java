/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import Tools.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import object.*;

/**
 *
 * @author asus
 */
public class operation {

    private DatabaseConnection conn;

    public pengguna operation(String userDB, String passDB) {
        try {
            conn = new DatabaseConnection();
            String query = "SELECT * FROM `user` WHERE usernameUser like '" + userDB + "' AND passwordUser like '" + passDB + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            pengguna a = new pengguna();
            if (result.isFirst()) {
                a.setIdUser(result.getString("idUser"));
                a.setUsername(result.getString("usernameUser"));
                a.setName(result.getString("nameUser"));
                a.setPassword(result.getString("passwordUser"));
                return a;
            }
            String query1 = "SELECT * FROM `admin` WHERE usernameAdmin like '" + userDB + "' AND passwordAdmin like '" + passDB + "'";
            java.sql.Statement statement1 = conn.getConnection().createStatement();
            java.sql.ResultSet result1 = statement1.executeQuery(query1);
            result1.next();
            if (result1.isFirst()) {
                a.setIdUser(result1.getString("idAdmin"));
                a.setUsername(result1.getString("usernameAdmin"));
                a.setName(result1.getString("nameAdmin"));
                a.setPassword(result1.getString("passwordAdmin"));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean cekUser(pengguna a) {
        try {
            conn = new DatabaseConnection();
            String query = "SELECT usernameUser, passwordUser FROM `user` WHERE usernameUser like '" + a.getUsername() + "' AND passwordUser like '" + a.getPassword() + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            return result.isFirst();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public pengguna cariUser(String username, String password) {
        try {
            String query = "SELECT * FROM user WHERE usernameUser LIKE '" + username + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.first()) {
                pengguna p = new pengguna();
                p.setIdUser(result.getString("idUser"));
                p.setUsername(result.getString("usernameUser"));
                p.setPassword(result.getString("passwordUser"));
                p.setName(result.getString("nameUser"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String cariPengirim(String idUser, String idAdmin) {
        try {
            String query = "SELECT a.nameUser FROM user a , postingan b WHERE b.idUser LIKE '" + idUser + "' AND a.idUser LIKE '" + idUser + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.first()) {
                return result.getString("nameUser");
            }
            String query1 = "SELECT a.nameAdmin FROM admin a , postingan b WHERE b.idAdmin LIKE '" + idAdmin + "' AND a.idAdmin LIKE '" + idAdmin + "'";
            java.sql.Statement statement1 = conn.getConnection().createStatement();
            java.sql.ResultSet result1 = statement1.executeQuery(query1);
            result1.next();
            if (result1.first()) {
                return result1.getString("nameAdmin");
            }

        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private String caripengirim(String user, String admin) {
        conn = new DatabaseConnection();
        String query = "SELECT nameUser FROM `user` WHERE '" + user + "' = usernameUser";
        String query1 = "SELECT nameAdmin FROM `admin` WHERE '" + admin + "' = usernameAdmin";
        java.sql.Statement statement;
        try {
            statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.isFirst()) {
                return result.getString("nameUser");
            }
            java.sql.ResultSet result1 = statement.executeQuery(query1);
            result1.next();
            if (result1.isFirst()) {
                return result1.getString("nameAdmin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    public ArrayList<postingan> tampilPostingan() {
        //TODO write your implementation code here:
        conn = new DatabaseConnection();
        ArrayList<postingan> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM postingan ORDER BY waktuPostingan DESC";
            try (java.sql.Statement statement = conn.getConnection().createStatement()) {
                java.sql.ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    postingan p = new postingan();
                    p.setIdPostingan(result.getString("idPostingan"));
                    p.setIsi(result.getString("isiPostingan"));
                    p.setIdUser(result.getString("idUser"));
                    p.setIdAdmin(result.getString("idAdmin"));
                    java.sql.Timestamp s = result.getTimestamp("waktuPostingan");
                    p.setWaktu(s);
                    data.add(p);
                }
                statement.close();
            }

        } catch (SQLException ex) {
            System.out.println("Gagal");
        }
        return data;
    }

    public void tambahPostingan(String username, String password, String iduser, String postingan) {
        conn = new DatabaseConnection();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        System.out.println(caripengirim(username, username));
        try {
            if (caripengirim(username, username).equalsIgnoreCase("Sekre Informatika")) {
                String idposting = date.getTime() + "" + iduser;
                String query = "INSERT INTO POSTINGAN (idPostingan, isiPostingan, idAdmin, waktuPostingan) "
                        + "VALUES ('" + idposting + "', '" + postingan + "', '" + iduser
                        + "', '" + date + "')";
                java.sql.Statement statement = conn.getConnection().createStatement();
                statement.executeUpdate(query);
                statement.close();
            } else {
                String idposting = date.getTime() + "" + iduser;
                String query = "INSERT INTO POSTINGAN (idPostingan, isiPostingan, idUser, waktuPostingan) "
                        + "VALUES ('" + idposting + "', '" + postingan + "', '" + iduser
                        + "', '" + date + "')";
                java.sql.Statement statement = conn.getConnection().createStatement();
                statement.executeUpdate(query);
                statement.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hapusUser(pengguna p) {
        try {
            conn = new DatabaseConnection();
            String sql = "DELETE FROM USER WHERE USERNAMEUSER = '" + p.getUsername() + "'";
            java.sql.Statement stat = conn.getConnection().createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tambahUser(pengguna p) {
        try {
            conn = new DatabaseConnection();
            String query = "INSERT INTO USER VALUES ('"
                    + p.getIdUser() + "','" + p.getUsername()
                    + "','" + p.getName()
                    + "','" + p.getPassword() + "')";

            java.sql.Statement statement = conn.getConnection().createStatement();

            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<pengguna> tampilPenggunaWP() {
        //TODO write your implementation code here:
        conn = new DatabaseConnection();
        ArrayList<pengguna> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM USER";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                pengguna p = new pengguna();
                p.setUsername(result.getString("usernameUser"));
                p.setPassword(result.getString("passwordUser"));
                p.setName(result.getString("nameUser"));
                p.setIdUser(result.getString("idUser"));
                data.add(p);
            }
            statement.close();
        } catch (Exception ex) {
            System.out.println("Gagal");
        }
        return data;
    }

    public ArrayList<komentar> tampilKomentar(postingan p) {
        conn = new DatabaseConnection();
        ArrayList<komentar> data = new ArrayList<>();
        try {
            String query = "SELECT * FROM komentar WHERE IDPOSTINGAN LIKE '" + p.getIdPostingan() + "' ORDER BY waktuKomentar ASC";
            try (java.sql.Statement statement = conn.getConnection().createStatement()) {
                java.sql.ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    komentar k = new komentar();
                    k.setIdKomentar(result.getString("idKomentar"));
                    k.setIsiKomentar(result.getString("isiKomentar"));
                    k.setIdPostingan(result.getString("idPostingan"));
                    k.setIdAdmin(result.getString("idAdmin"));
                    k.setIdUser(result.getString("idUser"));
                    java.sql.Timestamp s = result.getTimestamp("waktuKomentar");
                    k.setWaktuKomentar(s);
                    k.setNamaPengirim(caripengirim(k.getIdUser(), k.getIdAdmin()));
                    data.add(k);
                }
                statement.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void tambahKomentar(String username, String password, String iduser, String komentar, String postingan) {
        conn = new DatabaseConnection();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        System.out.println(caripengirim(username, username));
        try {
            pengguna p = operation(username, password);
            String idKomentar = date.getTime() + "" + iduser;
            System.out.println("Tambah");
            if (cekUser(p)) {
                System.out.println("User dongg");
                String query = "INSERT INTO KOMENTAR (idKomentar, isiKomentar, idPostingan, waktuKomentar, idUser) "
                        + "VALUES ('" + idKomentar + "', '" + komentar + "', '" + postingan + "', '" + date
                        + "', '" + iduser + "')";
                java.sql.Statement statement = conn.getConnection().createStatement();
                System.out.println("sukses");
                statement.executeUpdate(query);
                statement.close();
            } else if (!cekUser(p)) {
                System.out.println("Admin dongg");
                String query = "INSERT INTO KOMENTAR (idKomentar, isiKomentar, idPostingan, waktuKomentar, idAdmin) "
                        + "VALUES ('" + idKomentar + "', '" + komentar + "', '" + postingan + "', '" + date
                        + "', '" + iduser + "')";
                java.sql.Statement statement = conn.getConnection().createStatement();
                System.out.println("sukses");
                statement.executeUpdate(query);
                statement.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editPostingan(postingan p, String isi) {
        try {
            conn = new DatabaseConnection();
            pengguna a = new pengguna();
            a.setIdUser(p.getIdUser());

            String sql = "UPDATE `postingan` SET `idPostingan`= '" + p.getIdPostingan()
                    + "', `isiPostingan`= '" + isi
                    + "' WHERE idPostingan = " + p.getIdPostingan();
            java.sql.Statement stat = conn.getConnection().createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hapusKomentar(komentar k) {
        try {
            conn = new DatabaseConnection();
            String sql = "DELETE FROM KOMENTAR WHERE idkomentar = '" + k.getIdKomentar() + "'";
            java.sql.Statement stat = conn.getConnection().createStatement();
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hapusPostingan(postingan p) {
        try {
            conn = new DatabaseConnection();
            System.out.println(p.getIdPostingan() + " hapus postingan");
            String sql = "DELETE FROM POSTINGAN WHERE idPostingan = '" + p.getIdPostingan() + "'";
            java.sql.Statement stat = conn.getConnection().createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public postingan cariPostingan(String id) {
        postingan post = new postingan();
        try {
            conn = new DatabaseConnection();
            String query = "SELECT * FROM POSTINGAN WHERE idPostingan = '" + id + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.isFirst()) {
                post.setIdPostingan(result.getString("idPostingan"));
                System.out.println(post.getIdPostingan());
                post.setIdAdmin(result.getString("idAdmin"));
                post.setIdUser(result.getString("idUser"));
                post.setIsi(result.getString("isiPostingan"));
                post.setWaktu(result.getTime("waktuPostingan"));
                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    public komentar cariKomentar(String id) {
        komentar kom = new komentar();
        try {
            conn = new DatabaseConnection();
            String query = "SELECT * FROM KOMENTAR WHERE idKomentar = '" + id + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.isFirst()) {
                kom.setIdKomentar(result.getString("idKomentar"));
                kom.setIsiKomentar(result.getString("isiKomentar"));
                kom.setIdPostingan(result.getString("idPostingan"));
                kom.setWaktuKomentar(result.getTime("waktuKomentar"));
                kom.setIdUser(result.getString("idUser"));
                kom.setIdAdmin(result.getString("idAdmin"));
                return kom;
            }
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kom;
    }

}

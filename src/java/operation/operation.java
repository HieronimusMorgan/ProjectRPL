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

    public String cariPengirim(String idUser, String idAdmin) {
        try {
            String query = "SELECT a.nameUser FROM user a , postingan b WHERE b.idUser OR a.idUser LIKE '" + idUser + "'";
            java.sql.Statement statement = conn.getConnection().createStatement();
            java.sql.ResultSet result = statement.executeQuery(query);
            result.next();
            if (result.first()) {
                System.out.println(result.getString("nameUser") + " user");
                return result.getString("nameUser");
            } else {
                String query1 = "SELECT a.nameAdmin FROM admin a , postingan b WHERE b.idAdmin OR a.idAdmin LIKE '" + idAdmin + "'";
                java.sql.Statement statement1 = conn.getConnection().createStatement();
                java.sql.ResultSet result1 = statement1.executeQuery(query1);
                result1.next();
                if (result1.first()) {
                    System.out.println(result.getString("nameAdmin") + " user");
                    return result.getString("nameAdmin");
                }
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
                    System.out.println(p.getIdUser());
                    p.setIdAdmin(result.getString("idAdmin"));
                    java.sql.Timestamp s = result.getTimestamp("waktuPostingan");
                    p.setWaktu(s);
                    data.add(p);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Gagal");
        }
        return data;
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
            String sql = "DELETE FROM PENGGUNA WHERE IDUSER = '" + p.getIdUser() + "'";
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

}

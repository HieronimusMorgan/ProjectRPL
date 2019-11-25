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
                System.out.println(a.getIdUser());
                System.out.println(a.getUsername());
                System.out.println(a.getName());
                System.out.println(a.getPassword());
                return a;
            }
            String query1 = "SELECT usernameAdmin, passwordAdmin FROM `admin` WHERE usernameAdmin like '" + userDB + "' AND passwordAdmin like '" + passDB + "'";
            java.sql.Statement statement1 = conn.getConnection().createStatement();
            java.sql.ResultSet result1 = statement1.executeQuery(query1);
            result1.next();
            if (result1.isFirst()) {
                a.setUsername(result1.getString("usernameAdmin"));
                a.setPassword(result1.getString("passwordAdmin"));
                a.setIdUser(result1.getString("IdUser"));
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
                    p.setNamaPengirim(caripengirim(p.getIdUser(), p.getIdAdmin()));
                    System.out.println(caripengirim(p.getIdUser(), p.getIdAdmin()));
                    data.add(p);
                }
            statement.close();
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
                System.out.println(result.getString(1) + "a");
                return result.getString(1);
            }
            java.sql.ResultSet result1 = statement.executeQuery(query1);
            result1.next();
            if (result1.isFirst()) {
                System.out.println(result.getString(1) + "b");
                return result1.getString(1);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    public void tambahPostingan(String username, String password, String iduser, String postingan) {
        conn = new DatabaseConnection();
        String id;
        Timestamp date = new Timestamp(System.currentTimeMillis());
        System.out.println(caripengirim(username, username));
        try {
            id = "17080";
            String idposting = date.getTime() + "" + id;
            String query = "INSERT INTO POSTINGAN (idPostingan, isiPostingan, idUser, waktuPostingan) "
                    + "VALUES ('" + idposting + "', '" + postingan + "', '" + iduser
                    + "', '" + date + "')";
            java.sql.Statement statement = conn.getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(operation.class.getName()).log(Level.SEVERE, null, ex);
        }
//        if (caripengirim(username, username).equalsIgnoreCase(username)) {
//            try {
//                id = "234";
//                String idposting = date.getTime() + "" + id;
//                String query = "INSERT INTO POSTINGAN (idPostingan, isiPostingan, idUser, waktuPostingan) "
//                        + "VALUES ('" + idposting + "', '" + postingan + "', '" + id
//                        + "', '" + date + "')";
//                java.sql.Statement statement = conn.getConnection().createStatement();
//                statement.executeUpdate(query);
//            } catch (SQLException ex) {
//                System.out.println("Gagal 1");
//            }
//        } else {
//            try {
//                id = "345345";
//                System.out.println(id);
//                String idposting = date.getTime() + "" + id;
//                String query = "INSERT INTO POSTINGAN (idPostingan, isiPostingan, idAdmin, waktuPostingan) "
//                        + "VALUES ('" + idposting + "', '" + postingan + "', '" + id
//                        + "', '" + date + "')";
//                java.sql.Statement statement = conn.getConnection().createStatement();
//                statement.executeUpdate(query);
//            } catch (SQLException ex) {
//                System.out.println("Gagal 2");
//            }
//        }
    }

}

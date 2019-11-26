/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author asus
 */
public class komentar {
    
    private String isiKomentar;
    private String idKomentar;
    private Date waktuKomentar;
    private String idPostingan;
    private String isiPostingan;
    private String idUser;
    private String idAdmin;
    private String namaPengirim;

    public String getIsiKomentar() {
        return isiKomentar;
    }

    public void setIsiKomentar(String isiKomentar) {
        this.isiKomentar = isiKomentar;
    }

    public Date getWaktuKomentar() {
        return waktuKomentar;
    }

    public void setWaktuKomentar(Date waktuKomentar) {
        this.waktuKomentar = waktuKomentar;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getIsiPostingan() {
        return isiPostingan;
    }

    public void setIsiPostingan(String isiPostingan) {
        this.isiPostingan = isiPostingan;
    }

  

    public String getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(String idKomentar) {
        this.idKomentar = idKomentar;
    }


    public String getIdPostingan() {
        return idPostingan;
    }

    public void setIdPostingan(String idPostingan) {
        this.idPostingan = idPostingan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    
    
}

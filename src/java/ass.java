
import java.sql.Timestamp;
import java.sql.Date;
import object.komentar;
import object.postingan;
import operation.operation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asus
 */
public class ass {
    
    public static void main(String[] args) {
        operation a = new operation();
        komentar k = new komentar();
        postingan p = new postingan();
        p.setIdPostingan("157475111165515562");
        p.setIsi("hjgj");
        p.setIdAdmin("15562");
        a.editPostingan(p, "halo");
        
        postingan c = a.cariPostingan("157526182622815562");
        System.out.println(c.getIsi());
    }
    
    
    
}

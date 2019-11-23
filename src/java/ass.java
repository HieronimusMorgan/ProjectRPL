

import java.sql.Timestamp;
import java.sql.Date;

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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String s = String.valueOf(timestamp);
        System.out.println(s);
        
        tambahPostingan("175314080", "admin", "dsdssdacxzczxczcrtr");
    }

    private static String caripengirim(java.lang.String arg0, java.lang.String arg1) {
        org.me.service.WebServiceRPL_Service service = new org.me.service.WebServiceRPL_Service();
        org.me.service.WebServiceRPL port = service.getWebServiceRPLPort();
        return port.caripengirim(arg0, arg1);
    }

    private static void tambahPostingan(java.lang.String username, java.lang.String password, java.lang.String postingan) {
        org.me.service.WebServiceRPL_Service service = new org.me.service.WebServiceRPL_Service();
        org.me.service.WebServiceRPL port = service.getWebServiceRPLPort();
        port.tambahPostingan(username, password, postingan);
    }
}

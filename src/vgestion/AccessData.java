/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;
import java.sql.*;

/**
 *
 * @author Juan Luis
 */
public class AccessData {

    
    public AccessData() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            Connection con = DriverManager.getConnection("jdbc:odbc:VGESTION");
            Statement stat = con.createStatement();
//            System.out.println(con.getCatalog());
//            stat.execute("INSERT INTO MATERIAL (ID_MATERIAL,ID_PROVEEDOR,DESCRIPCION,MARCA) VALUES ('tt',12,'TT','FF')");
            ResultSet result; //= stat.executeQuery("SELECT * FROM MATERIAL");
            result = stat.executeQuery("SELECT * FROM MATERIAL");
//            System.out.println(result.getRow());
//            System.out.println(result.get);
//            System.out.println(result.isFirst());
//            while (result.next())
//                System.out.println("DESCRIPCION: "+result.getString("DESCRIPCION"));
            
        }catch(Exception e){ 
            e.printStackTrace();
//                System.out.println(e); 
        }
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Juan Luis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        acortarIdMaterialDielectro();
        // TODO code application logic here
//        AccessData accessData = new AccessData();
//        updateAlbaran(2007);
//        updateAlbaran(2008);
//        updateFactura(2007);
//        updateFactura(2008);
//        quitarRDicusel();
    }
    
    public static void quitarRDicusel() {
        Connection connection;
        String sql="";
        Statement statement;
        ResultSet rs;
        String id_albaranOld;
        String id_albaranNew;
        
        Vector vAlbaran;
        vAlbaran = new Vector();
        
        
        
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            connection = DriverManager.getConnection("jdbc:odbc:VGESTION");
            sql = "SELECT * FROM MATERIAL_ALBARAN WHERE ID_PROVEEDOR='00791' AND ID_ALBARAN LIKE 'R-%';";
            statement=connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                id_albaranOld = rs.getString("ID_ALBARAN");
                id_albaranNew = id_albaranOld.substring(2);
                vAlbaran.addElement(id_albaranOld);
                System.out.println(id_albaranOld);
            }
        }catch(Exception e){ 
            e.printStackTrace();
            System.out.println(sql);
        }
        
        try {            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            connection = DriverManager.getConnection("jdbc:odbc:VGESTION");
            statement=connection.createStatement();
            for(int i=0;i<vAlbaran.size();i++) {
                id_albaranOld = (String)vAlbaran.elementAt(i);
                id_albaranNew = id_albaranOld.substring(2);
                sql = "UPDATE MATERIAL_ALBARAN SET ID_ALBARAN='"+
                      id_albaranNew+
                      "' WHERE ID_ALBARAN='"+
                      id_albaranOld+
                      "' AND ID_PROVEEDOR='00791'";
                statement.execute(sql);
                System.out.println(id_albaranOld+" -> "+id_albaranNew);
            }
            connection.close();
        }catch(Exception e){ 
            e.printStackTrace();
            System.out.println(sql);
        }
    }
    
    
    private static void updateFactura(int anio) {
        Connection connection;
        String sql="";
        Statement statement;
        ResultSet rs;
        Vector vFactura;
        vFactura = new Vector();
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            connection = DriverManager.getConnection("jdbc:odbc:VGESTION");
            sql = "SELECT * FROM FACTURA WHERE ID_PROVEEDOR='9155' AND ANIO="+anio+";";
            statement=connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                vFactura.addElement(rs.getString("ID_FACTURA"));
            }
            for(int i=0;i<vFactura.size();i++) {
                String idFactura;
                idFactura = (String)vFactura.elementAt(i);
                sql = "UPDATE FACTURA SET ID_FACTURA='"+
                      anio+
                      "-"+
                      idFactura+
                      "' WHERE ID_FACTURA='"+
                      idFactura+
                      "' AND ID_PROVEEDOR='9155'";
                statement.execute(sql);
                 sql = "UPDATE ALBARAN_FACTURA SET ID_FACTURA='"+
                      anio+
                      "-"+
                      idFactura+
                      "' WHERE ID_FACTURA='"+
                      idFactura+
                      "' AND ID_PROVEEDOR='9155'";
                statement.execute(sql);
            }
        }catch(Exception e){ 
            e.printStackTrace();
            System.out.println(sql);
//            System.out.println(e); 
//            this.dispose();
        }
    }
    
    
    
    
    
    
    
    
    
    
    private static void updateAlbaran(int anio) {
        Connection connection;
        String sql="";
        Statement statement;
        ResultSet rs;
        Vector vAlbaran;
        vAlbaran = new Vector();
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            connection = DriverManager.getConnection("jdbc:odbc:VGESTION");
            sql = "SELECT * FROM ALBARAN WHERE ID_PROVEEDOR='9155' AND ANIO="+anio+";";
            statement=connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                vAlbaran.addElement(rs.getString("ID_ALBARAN"));
            }
            for(int i=0;i<vAlbaran.size();i++) {
                String idAlbaran;
                idAlbaran = (String)vAlbaran.elementAt(i);
                sql = "UPDATE MATERIAL_ALBARAN SET ID_ALBARAN='"+
                      anio+
                      "-"+
                      idAlbaran+
                      "' WHERE ID_ALBARAN='"+
                      idAlbaran+
                      "' AND ID_PROVEEDOR='9155'";
                statement.execute(sql);
                sql = "UPDATE ALBARAN SET ID_ALBARAN='"+
                      anio+
                      "-"+
                      idAlbaran+
                      "' WHERE ID_ALBARAN='"+
                      idAlbaran+
                      "' AND ID_PROVEEDOR='9155'";
                statement.execute(sql);
                 sql = "UPDATE ALBARAN_FACTURA SET ID_ALBARAN='"+
                      anio+
                      "-"+
                      idAlbaran+
                      "' WHERE ID_ALBARAN='"+
                      idAlbaran+
                      "' AND ID_PROVEEDOR='9155'";
                statement.execute(sql);
            }
        }catch(Exception e){ 
            e.printStackTrace();
            System.out.println(sql);
//            System.out.println(e); 
//            this.dispose();
        }
    }
    
    
    
    private static void acortarIdMaterialDielectro() {                                          
        Connection connection;
        String sql="";
        Statement statement;
        ResultSet resultSet;
        Vector vMaterial;
        String idMaterial, temp;
        int contador, paramodificar;
        
        
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            connection = DriverManager.getConnection("jdbc:odbc:VGESTION");
        }catch(Exception ex){
            System.out.println("Imposible establecer comunicación con la Base de Datos");
            ex.printStackTrace();
            return;
        }
            
        sql = "SELECT * FROM MATERIAL WHERE ID_PROVEEDOR = '9155';";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            vMaterial = new Vector();
            
            contador = 0;
            paramodificar = 0;
            while(resultSet.next()) {
                idMaterial = resultSet.getString("ID_MATERIAL");
//                System.out.println(idMaterial.length());
                contador++;
                if(idMaterial.length()>6) {
                    vMaterial.addElement(idMaterial);
                    System.out.println("Revisados: "+contador+ "   "+idMaterial);
                    paramodificar++;
                } else {
                    System.out.println("Revisados: "+contador);
                }
            }
        } catch (Exception e) {
            System.out.println("Error consiguiendo idMateriales");
            e.printStackTrace();
            return;
        }
        
        
        System.out.println();
        System.out.println();
        System.out.println("Hay "+paramodificar+" entradas que modificar");
        System.out.println();
        System.out.println();
        
        for(int i=0;i<vMaterial.size();i++) {
            try {
                idMaterial = (String)vMaterial.elementAt(i);
                temp = idMaterial.substring(0,6);
                System.out.println("Modificando "+i+" de "+paramodificar+" : "+idMaterial+" ..."+temp);
                
                sql = "SELECT * FROM MATERIAL WHERE ID_PROVEEDOR = '9155' AND ID_MATERIAL='" +
                      temp +
                      "';";
                
                resultSet = statement.executeQuery(sql);
                if(resultSet.next()) {
                    sustituirMaterial(connection, idMaterial,temp,"9155");
                    borrarMaterial(connection, idMaterial,"9155");
                } else {
                    sustituirMaterial(connection, idMaterial,temp,"9155");
                    cambiarIdMaterial(connection, idMaterial,temp,"9155");
                }
                
            } catch (Exception e) {
                System.out.println("Error acortando id");
                e.printStackTrace();
                return;
            }
        }
        
        
        
        System.out.println("fin: "+contador);
        
        System.exit(0);
        
        
    }
    
    private static void cambiarIdMaterial(Connection connection, String idMaterialAntiguo, String idMaterialNuevo, String idProveedor) {
        String sql;
        Statement statement ;
        
        
        sql = "UPDATE MATERIAL SET ID_MATERIAL= '" +
              idMaterialNuevo +
              "' WHERE ID_PROVEEDOR = '" +
              idProveedor +
              "' AND ID_MATERIAL = '"+
              idMaterialAntiguo +
              "';";
        
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error borrando material");
            return;
        }
        
    }
    
    private static void sustituirMaterial(Connection connection, String idMaterialAntiguo, String idMaterialNuevo, String idProveedor) {
        String sql;
        Statement statement ;
        
        
        sql = "UPDATE MATERIAL_ALBARAN SET ID_MATERIAL= '" +
              idMaterialNuevo +
              "' WHERE ID_PROVEEDOR = '" +
              idProveedor +
              "' AND ID_MATERIAL = '"+
              idMaterialAntiguo +
              "';";
        
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error sustituyendo material");
            return;
        }
        
    }
    
    private static void borrarMaterial(Connection connection, String idMaterial, String idProveedor) {
        String sql;
        Statement statement ;
        
        
        sql = "DELETE FROM MATERIAL WHERE ID_PROVEEDOR = '" +
              idProveedor +
              "' AND ID_MATERIAL = '" +
              idMaterial +
              "';";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error borrando material");
            e.printStackTrace();
            return;
        }
        
    }
    

}

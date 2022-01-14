/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Juan Luis
 */
public class Albaran {

    String id_albaran;
    String id_proveedor;
    Fecha fecha;
//    int dia, mes, anio;
    double descuento;
    String obra;
    String receptor;
    String comentario;
    Connection connection;
    int fila;

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getId_albaran() {
        return id_albaran;
    }

    public void setId_albaran(String id_albaran) {
        this.id_albaran = id_albaran;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
    
    public boolean isInFecha(int diaDesde, int mesDesde, int anioDesde, int diaHasta, int mesHasta, int anioHasta) {
        return UtilGestion.isInFecha(fecha.dia, fecha.mes, fecha.anio, diaDesde, mesDesde, anioDesde, diaHasta, mesHasta, anioHasta);
    }
    
    public double getTotalNeto() {
        return getTotalNeto(connection, id_proveedor, id_albaran);
    }
    
    public static double getTotalNeto(Connection connection, String id_proveedor, String id_albaran) {
        Statement statement;
        ResultSet resultSet;
        String sql;
        MaterialAlbaran materialAlbaran;
        double totalNeto;
        
        if(connection==null)
            return 0;
        
        sql = "SELECT * FROM MATERIAL_ALBARAN WHERE ID_PROVEEDOR='" +
              id_proveedor +
              "' AND ID_ALBARAN='" +
              id_albaran +
              "';";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            totalNeto = 0;
            while(resultSet.next()) {
                materialAlbaran = new MaterialAlbaran();
                materialAlbaran.setCantidad(resultSet.getDouble("CANTIDAD"));
                materialAlbaran.setDescuento(resultSet.getDouble("DESCUENTO"));
                materialAlbaran.setPrecio(resultSet.getDouble("PRECIO"));
                materialAlbaran.setRaee(resultSet.getDouble("RAEE"));
                totalNeto += materialAlbaran.getNeto() * materialAlbaran.getCantidad();
            }
            return UtilGestion.round(totalNeto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }
    
    public void updateFechaFromDB() {
        Statement statement;
        ResultSet resultSet;
        String sql;
        int dia,mes,anio;
        
        if(connection==null)
            return;
        
        sql = "SELECT * FROM ALBARAN WHERE ID_PROVEEDOR='" +
              id_proveedor +
              "' AND ID_ALBARAN='" +
              id_albaran +
              "';";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                dia = resultSet.getInt("DIA");
                mes = resultSet.getInt("MES");
                anio = resultSet.getInt("ANIO");
                fecha = new Fecha(dia,mes,anio);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String toString() {
        return id_albaran;
    }
    
}

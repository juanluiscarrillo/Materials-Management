/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.sql.*;
/**
 *
 * @author USUARIO
 */
public class Factura {
    String id_factura;
    String id_proveedor;
    int dia, mes, anio;
    String comentario;
    int tipo;
    double totalNeto;

    public double getTotalNeto() {
        return UtilGestion.round(totalNeto);
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }
    
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Fecha getFecha() {
        return new Fecha(dia,mes,anio);
        //return ""+dia+"/"+mes+"/"+anio;
    }
    
    public String getId_factura() {
        return id_factura;
    }

    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String getTipoString() {
        switch(tipo) {
            case 1: return "A";
            case 2: return "B";
        }
        return null;
    }
    
    public boolean isInFecha(int diaDesde, int mesDesde, int anioDesde, int diaHasta, int mesHasta, int anioHasta) {
        return UtilGestion.isInFecha(dia, mes, anio, diaDesde, mesDesde, anioDesde, diaHasta, mesHasta, anioHasta);
    }
    
    public static double getTotalNeto(Connection connection, String id_proveedor, String id_factura) {
        Statement statement;
        ResultSet resultSet;
        String sql;
        String id_albaran;
        double totalNeto;
        
        if(connection==null)
            return 0;
        
        sql = "SELECT * FROM ALBARAN_FACTURA WHERE ID_PROVEEDOR='" +
              id_proveedor +
              "' AND ID_FACTURA='" +
              id_factura +
              "';";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            totalNeto = 0;
            while(resultSet.next()) {
                id_albaran = resultSet.getString("ID_ALBARAN");
                totalNeto += Albaran.getTotalNeto(connection, id_proveedor, id_albaran);
            }
            return totalNeto;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }
    

    
}

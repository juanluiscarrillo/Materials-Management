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
public class HistorialMaterial {
    Material material;
    String id_albaran;
    String id_proveedor;
    Fecha fecha;
//    int dia, mes, anio;
    double cantidad, precio, descuento, raee;
    private Connection connection;

    public double getRaee() {
        return raee;
    }

    public void setRaee(double raee) {
        this.raee = raee;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
//    public int getAnio() {
//        return anio;
//    }
//
//    public void setAnio(int anio) {
//        this.anio = anio;
//    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

//    public int getDia() {
//        return dia;
//    }
//
//    public void setDia(int dia) {
//        this.dia = dia;
//    }

    public Fecha getFecha() {
//        return ""+dia+"/"+mes+"/"+anio;
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
 
    public String getId_albaran() {
        return id_albaran;
    }

    public void setId_albaran(String id_albaran) {
        this.id_albaran = id_albaran;
    }

    public double getImporte() {
        return getNeto()*cantidad;
    }

//    public int getMes() {
//        return mes;
//    }
//
//    public void setMes(int mes) {
//        this.mes = mes;
//    }

    public double getNeto() {
        return (precio*(1-descuento/100.0))+raee;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public boolean isInFecha(int diaDesde, int mesDesde, int anioDesde, int diaHasta, int mesHasta, int anioHasta) {
//        return UtilGestion.isInFecha(dia, mes, anio, diaDesde, mesDesde, anioDesde, diaHasta, mesHasta, anioHasta);
        return isInFecha(new Fecha(diaDesde,mesDesde,anioDesde),new Fecha(diaHasta,mesHasta,anioHasta));
    }
    
    public boolean isInFecha(Fecha fechaDesde, Fecha fechaHasta) {
        return UtilGestion.isInFecha(fecha, fechaDesde, fechaHasta);
    }
    
    public void updateFecha() {
        String sql;
        Statement statement;
        ResultSet rs;
        int dia, mes, anio;
        
        sql = "SELECT * FROM ALBARAN WHERE ID_ALBARAN='"+
              id_albaran+
              "' AND ID_PROVEEDOR='"+
              id_proveedor+
              "';";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
//            System.out.println(sql);
            if(rs.next()){
                dia = rs.getInt("DIA");
                mes = rs.getInt("MES");
                anio = rs.getInt("ANIO");
                fecha = new Fecha(dia,mes,anio);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
  
}

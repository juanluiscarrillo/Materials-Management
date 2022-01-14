/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

/**
 *
 * @author Juan Luis
 */
public class MaterialAlbaran {
    String id_albaran;
    Material material;
    int fila;
    double cantidad;
    double precio;
    double descuento;
    String observacion;
    double raee;
    String cantidadString, precioString, descuentoString, raeeString;
    
    public MaterialAlbaran() {
        cantidadString = new String();
        precioString = new String();
        descuentoString = new String();
        raeeString = new String();
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getId_albaran() {
        return id_albaran;
    }

    public void setId_albaran(String id_albaran) {
        this.id_albaran = id_albaran;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getCantidad() {
        return cantidad;
    }
    
    public String getCantidadString() {
        if(cantidad!=0)
            return ""+cantidadString;
        else
            return "";
                
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
        this.cantidadString = String.valueOf(cantidad);
    }

    public double getDescuento() {
        return descuento;
    }
    
    public String getDescuentoString() {
        if(descuento!=0)
            return ""+descuentoString;
        else
            return "";
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        this.descuentoString = String.valueOf(descuento);
    }

    public double getPrecio() {
        return precio;
    }
    
    public String getPrecioString() {
        if(precio!=0)
            return ""+precioString;
        else
            return "";
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        this.precioString = String.valueOf(precio);
    }

    public double getImporte() {
        return getNeto() * cantidad;
    }
    
    public double getNeto() {
        return precio * (1-descuento/100) + raee;
    }

    public double getRaee() {
        return raee;
    }
    
    public String getRaeeString() {
        if(raee!=0)
            return ""+raeeString;
        else
            return "";
        
    }

    public void setRaee(double raee) {
        this.raee = raee;
        raeeString = String.valueOf(raee);
    }
    
    
    


    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

/**
 *
 * @author Juan Luis
 */


public class Material {
    String id_material;
    String id_proveedor;
    String categoria;
    String marca;
    String ref_marca;
    String descripcion;
    double precio;
    double descuento;
    double raee;

    public double getNeto() {
        return precio * (1-descuento/100.0) + raee ;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getRaee() {
        return raee;
    }

    public void setRaee(double raee) {
        this.raee = raee;
    }
    
    

    
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId_material() {
        return id_material;
    }

    public void setId_material(String id_material) {
        this.id_material = id_material;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRef_marca() {
        return ref_marca;
    }

    public void setRef_marca(String ref_marca) {
        this.ref_marca = ref_marca;
    }
    
    public boolean isEmpty() {
        if(categoria!=null && !this.categoria.equals(""))
            return false;
        if(descripcion!=null && !this.descripcion.equals(""))
            return false;
        if(id_material!=null && !this.id_material.equals(""))
            return false;
        if(id_proveedor!=null && !this.id_proveedor.equals(""))
            return false;
        if(marca!=null && !this.marca.equals(""))
            return false;
        if(ref_marca!=null && !this.ref_marca.equals(""))
            return false;
        return true;
    }
    
        
}

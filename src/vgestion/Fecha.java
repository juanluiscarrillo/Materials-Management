/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

/**
 *
 * @author USUARIO
 */
public final class Fecha implements Comparable<Fecha>{
    int dia, mes, anio;
    
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    
    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public String toString() {
        return ""+dia+"/"+mes+"/"+anio;
    }
    
    public int compareTo(Fecha anotherFecha) {
        if(anio<anotherFecha.getAnio())
            return -1;
        else if(anio>anotherFecha.getAnio())
            return 1;
        else if(mes<anotherFecha.getMes()) 
            return -1;
        else if(mes>anotherFecha.getMes())
            return 1;
        else if(dia<anotherFecha.getDia())
            return -1;
        else if(dia>anotherFecha.getDia())
            return 1;
        else
            return 0;
                    
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.util.Vector;

/**
 *
 * @author Juan Luis
 */
public class FacturaTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vFactura;
     
    public FacturaTableModel(Vector vFactura) {
        this.vFactura = vFactura;   
    }
    
    public FacturaTableModel() {
        this.vFactura = new Vector();   
    }
    
    public void addFactura(Factura factura) {
        this.vFactura.addElement(factura);
    }
    
    public Factura getFactura(int row) {
        try {
            return (Factura)vFactura.elementAt(row);
            
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
    }
    
    public int getRowCount() {
//        if (this.vFactura.size()<10)
//            return 10;
        return this.vFactura.size();
    }
    
    public int getColumnCount() {
        return 5;
    }
    public Object getValueAt(int row, int column) {
        Factura factura;
        try {
            factura = (Factura)this.vFactura.elementAt(row);
            if(factura == null)
                return null;
            switch(column) {
                case 0: return factura.getId_factura();
                case 1: return factura.getId_proveedor();
                case 2: return factura.getFecha();
                case 3: return factura.getTotalNeto();
                case 4: return factura.getTipoString();
            }
        } catch(Exception e) {
            
        }
                
        
        return null;
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_FACTURA";
            case 1: return "ID_PROVEEDOR";
            case 2: return "FECHA";
            case 3: return "TOTAL NETO";
            case 4: return "TIPO";
        }
        return null;
    }  
    
    public Class getColumnClass(int column) {
        switch(column) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Fecha.class;
            case 3: return Double.class;
            case 4: return String.class;
        }
        return null;
    }
    
    public void clearTable() {
        this.vFactura = new Vector();
    }


}

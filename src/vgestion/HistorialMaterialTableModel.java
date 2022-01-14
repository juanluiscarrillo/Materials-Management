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
public class HistorialMaterialTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vHistorialMaterial;
    
    public HistorialMaterialTableModel(Vector vHistorialMaterial) {
        this.vHistorialMaterial = vHistorialMaterial;   
    }
    
    public HistorialMaterialTableModel() {
        this.vHistorialMaterial = new Vector();   
    }
    
    public void addHistorialMaterial(HistorialMaterial historialMaterial) {
        this.vHistorialMaterial.addElement(historialMaterial);
    }
    
    public int getRowCount() {
//        if (this.vHistorialMaterial.size()<10)
//            return 10;
        return this.vHistorialMaterial.size();
    }
    
    public int getColumnCount() {
        return 8;
    }
    public Object getValueAt(int row, int column) {
        HistorialMaterial historialMaterial;
        try {
            historialMaterial = (HistorialMaterial)this.vHistorialMaterial.elementAt(row);
            if(historialMaterial == null)
                return null;
            switch(column) {
                case 0: return historialMaterial.getId_albaran();
                case 1: return historialMaterial.getFecha();
                case 2: return historialMaterial.getCantidad();
                case 3: return historialMaterial.getPrecio();
                case 4: return historialMaterial.getDescuento();
                case 5: return historialMaterial.getRaee();
                case 6: return historialMaterial.getNeto();
                case 7: return historialMaterial.getImporte();
            }
        } catch(Exception e) {
            
        }
                
        
        return null;
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_ALBARAN";
            case 1: return "FECHA";
            case 2: return "CANTIDAD";
            case 3: return "PRECIO";
            case 4: return "DESCUENTO";
            case 5: return "RAEE";
            case 6: return "NETO";
            case 7: return "IMPORTE";
        }
        return null;
    }  
    
    public Class getColumnClass(int column) {
        switch(column) {
            case 0: return String.class;
            case 1: return Fecha.class;
            case 2: return Double.class;
            case 3: return Double.class;
            case 4: return Double.class;
            case 5: return Double.class;
            case 6: return Double.class;
            case 7: return Double.class;
        }
        return null;
    }
    
    public void clearTable() {
        this.vHistorialMaterial = new Vector();
    }


}

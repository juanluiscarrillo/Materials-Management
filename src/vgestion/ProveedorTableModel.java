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
public class ProveedorTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vProveedor;
    
    public ProveedorTableModel(Vector vMaterial) {
        this.vProveedor = vProveedor;   
    }
    
    public ProveedorTableModel() {
        this.vProveedor = new Vector();   
    }
    
    public void clearAll(){
        vProveedor.clear();
    }
    
    public void addProveedor(Proveedor proveedor) {
        this.vProveedor.addElement(proveedor);
    }
    
    public int getRowCount() {
        return this.vProveedor.size();
    }
    
    public int getColumnCount() {
        return 7;
    }
    
    public Object getValueAt(int row, int column) {
        Proveedor proveedor;
        try {
            proveedor = (Proveedor)this.vProveedor.elementAt(row);
            if(proveedor == null)
                return null;
            switch(column) {
                case 0: return proveedor.getId_proveedor();
                case 1: return proveedor.getCif();
                case 2: return proveedor.getNombre();
                case 3: return proveedor.getDireccion();
                case 4: return proveedor.getPoblacion();
                case 5: return proveedor.getCp();
                case 6: return proveedor.getTelefono();
            }
        } catch(Exception e) {
            
        }
        return null;
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_PROVEEDOR";
            case 1: return "CIF";
            case 2: return "NOMBRE";
            case 3: return "DIRECCION";
            case 4: return "POBLACION";
            case 5: return "CP";
            case 6: return "TELEFONO";
        }
        return null;
    }  
    
    public Class getColumnClass(int c) {
        String s;
        
        s = new String();
        return s.getClass();
    }


}

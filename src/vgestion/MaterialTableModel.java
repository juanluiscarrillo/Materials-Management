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
public class MaterialTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vMaterial;
    
    public MaterialTableModel(Vector vMaterial) {
        this.vMaterial = vMaterial;   
    }
    
    public MaterialTableModel() {
        this.vMaterial = new Vector();   
    }
    
    public void addMaterial(Material material) {
        this.vMaterial.addElement(material);
    }
    
    public int getRowCount() {
        if (this.vMaterial.size()<10)
            return 10;
        return this.vMaterial.size();
    }
    
    public int getColumnCount() {
        return 6;
    }
    public Object getValueAt(int row, int column) {
        Material material;
        try {
            material = (Material)this.vMaterial.elementAt(row);
            if(material == null)
                return null;
            switch(column) {
                case 0: return material.getId_material();
                case 1: return material.getId_proveedor();
                case 2: return material.getCategoria();
                case 3: return material.getMarca();
                case 4: return material.getRef_marca();
                case 5: return material.getDescripcion();
            }
        } catch(Exception e) {
            
        }
                
        
        return null;
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_MATERIAL";
            case 1: return "ID_PROVEEDOR";
            case 2: return "CATEGORIA";
            case 3: return "MARCA";
            case 4: return "REF_MARCA";
            case 5: return "DESCRIPCION";
        }
        return null;
    }  
    
    public void clearTable() {
        this.vMaterial = new Vector();
    }
    
    public Material getMaterial(int row) {
        Material material;
        
        material = new Material();
        material.setId_material((String)getValueAt(row,0));
        material.setId_proveedor((String)getValueAt(row,1));
        material.setCategoria((String)getValueAt(row,2));
        material.setMarca((String)getValueAt(row,3));
        material.setRef_marca((String)getValueAt(row,4));
        material.setDescripcion((String)getValueAt(row,5));
        
        return material;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.util.Vector;
import java.sql.*;

    
/**
 *
 * @author Juan Luis
 */
public class MaterialAlbaranTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vMaterialAlbaran;
    String id_proveedor;
    private Connection connection;
    String bagIdMaterial;
    
    public MaterialAlbaranTableModel(Vector vMaterialAlbaran) {
        this.vMaterialAlbaran = vMaterialAlbaran;   
    }
    
    public MaterialAlbaranTableModel() {
        this.vMaterialAlbaran = new Vector();   
    }

    public String getBagIdMaterial() {
        return bagIdMaterial;
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

    public void addMaterialAlbaran(MaterialAlbaran materialAlbaran) {
        this.vMaterialAlbaran.addElement(materialAlbaran);
    }
    
    public void deleteMaterialAlbaran(int row) {
        this.vMaterialAlbaran.remove(row);
    }
    
    public int getRowCount() {
      return this.vMaterialAlbaran.size()+1;
    }
    
    public int getColumnCount() {
        return 8;
    }
    public Object getValueAt(int row, int column) {
        MaterialAlbaran materialAlbaran;
        try {
            materialAlbaran = (MaterialAlbaran)this.vMaterialAlbaran.elementAt(row);
            if(materialAlbaran == null)
                return null;
            switch(column) {
                case 0: return materialAlbaran.getMaterial().getId_material();
                case 1: return materialAlbaran.getMaterial().getDescripcion();
                case 2: return materialAlbaran.getCantidadString();
                case 3: return materialAlbaran.getPrecioString();
                case 4: return materialAlbaran.getDescuentoString();
                case 5: return materialAlbaran.getRaeeString();
                case 6: return materialAlbaran.getNeto();
                case 7: return UtilGestion.round(materialAlbaran.getImporte());
            }
        } catch(Exception e) {
            
        }
                
        
        return "";
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_MATERIAL";
            case 1: return "DESCRIPCION";
            case 2: return "CANTIDAD";
            case 3: return "PRECIO";
            case 4: return "DESCUENTO";
            case 5: return "RAEE";
            case 6: return "NETO";
            case 7: return "IMPORTE";
            
        }
        return null;
    }  
    
    public void setValueAt(Object value, int row, int column) {
        switch(column) {
            case 0: 
                idMaterialModified(value,row,column);
                return;
            case 2:
                cantidadMaterialModified(value,row,column);
                return;
            case 3:
                precioMaterialModified(value,row,column);
                return;
            case 4:
                descuentoMaterialModified(value,row,column);
                return;
            case 5:
                raeeMaterialModified(value,row,column);
                return;
               
                
                
        }
        
        
        
    }
    
    private void cantidadMaterialModified(Object value, int row, int column) {
        MaterialAlbaran materialAlbaran;
        if(vMaterialAlbaran.size()==row)
            return;
//        System.out.println(value);
        
        materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        try {
            materialAlbaran.setCantidad(Double.parseDouble((String)(value)));
            fireTableCellUpdated(row, column);
        } catch (Exception ex) {
            
//            ex.printStackTrace();
        }
        
    }
    
    private void precioMaterialModified(Object value, int row, int column) {
        MaterialAlbaran materialAlbaran;
        if(vMaterialAlbaran.size()==row)
            return;
//        System.out.println(value);
        
        materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        try {
            materialAlbaran.setPrecio(Double.parseDouble((String)(value)));
            fireTableCellUpdated(row, column);
        } catch (Exception ex) {
            
//            ex.printStackTrace();
        }
        
    }
    
    private void descuentoMaterialModified(Object value, int row, int column) {
        MaterialAlbaran materialAlbaran;
        if(vMaterialAlbaran.size()==row)
            return;
//        System.out.println(value);
        
        materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        try {
            materialAlbaran.setDescuento(Double.parseDouble((String)(value)));
            fireTableCellUpdated(row, column);
        } catch (Exception ex) {
            
//            ex.printStackTrace();
        }
        
    }
    
    private void raeeMaterialModified(Object value, int row, int column) {
        MaterialAlbaran materialAlbaran;
        if(vMaterialAlbaran.size()==row)
            return;
//        System.out.println(value);
        
        materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        try {
            materialAlbaran.setRaee(Double.parseDouble((String)(value)));
            fireTableCellUpdated(row, column);
        } catch (Exception ex) {
            
//            ex.printStackTrace();
        }
        
    }
        
    private void idMaterialModified(Object value, int row, int column) {
        MaterialAlbaran materialAlbaran;
     
        if(this.vMaterialAlbaran.size()==row){
            materialAlbaran = new MaterialAlbaran();
            materialAlbaran.setFila(row);
        } else {
            materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        }
        
        if(column == 0) {
            Material material;
            String id_material;
            String sql;
            
            id_material = (String)(value);
            sql = "SELECT * FROM MATERIAL WHERE ID_MATERIAL='" +
                  id_material +
                  "' AND ID_PROVEEDOR='" +
                  id_proveedor +
                  "';" ;
            
            try { 
                Statement statement;
                ResultSet resultSet;
                statement = this.connection.createStatement();
                resultSet = statement.executeQuery(sql);
//                System.out.println(sql);
                if(!resultSet.next()) {
                    bagIdMaterial = id_material;
                    fireTableCellUpdated(row, 0);
                    return;
                }
                
                material = new Material();
                material.setId_material(id_material);
                material.setId_proveedor(id_proveedor);
                material.setCategoria((String)resultSet.getString("CATEGORIA"));
                material.setDescripcion((String)resultSet.getString("DESCRIPCION"));
                material.setMarca((String)resultSet.getString("MARCA"));
                material.setRef_marca((String)resultSet.getString("REF_MARCA"));
                material.setPrecio(resultSet.getDouble("PRECIO"));
                material.setDescuento(resultSet.getDouble("DESCUENTO"));
                material.setRaee(resultSet.getDouble("RAEE"));
                materialAlbaran.setMaterial(material);
//                setBestPrecio(materialAlbaran, id_material, id_proveedor);
                
                materialAlbaran.setPrecio(material.getPrecio());
                materialAlbaran.setDescuento(material.getDescuento());
                materialAlbaran.setRaee(material.getRaee());
                if(this.vMaterialAlbaran.size()==row)
                    this.vMaterialAlbaran.addElement(materialAlbaran);
                
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void setBestPrecio(MaterialAlbaran materialAlbaran, String id_material, String id_proveedor) {
        String sql;
        double precio, descuento, neto, raee;
        double precioOld, descuentoOld, netoOld, raeeOld;
        boolean next;
        
        sql = "SELECT * FROM MATERIAL_ALBARAN WHERE ID_MATERIAL = '" +
              id_material +
              "' AND ID_PROVEEDOR = '" +
              id_proveedor;
        
        try { 
                Statement statement;
                ResultSet resultSet;
                statement = this.connection.createStatement();
                resultSet = statement.executeQuery(sql);
                next = true;
                netoOld = 0;
                precioOld = 0;
                descuentoOld = 0;
                raeeOld = 0;
                while(resultSet.next()) {
                    precio = resultSet.getDouble("PRECIO");
                    descuento = resultSet.getDouble("DESCUENTO");
                    raee = resultSet.getDouble("RAEE");
                    neto = (precio * (1-descuento/100.0)) + raee;
                    if(next || neto < netoOld) {
                        next = false;
                        precioOld = precio;
                        descuentoOld = descuento;
                        raeeOld = raee;
                        netoOld = neto;
                    }
                }
                materialAlbaran.setPrecio(precioOld);
                materialAlbaran.setDescuento(descuentoOld);
                materialAlbaran.setRaee(raeeOld);
        } catch (Exception ex) {
            
        }
        
        
    }
    
        
    public void clearTable() {
        this.vMaterialAlbaran = new Vector();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return true;
            case 2: return true;
            case 3: return true;
            case 4: return true;
            case 5: return true;
            
        }
        return false;
    }
    
    public double calculateSuma() {
        MaterialAlbaran materialAlbaran;
        double suma =0;
        for(int i=0;i<vMaterialAlbaran.size();i++) {
            materialAlbaran = (MaterialAlbaran)vMaterialAlbaran.elementAt(i);
            suma += UtilGestion.round(materialAlbaran.getNeto()* materialAlbaran.getCantidad());
        }
        return UtilGestion.round(suma);
    }
    
    public void subir(int row) {
        MaterialAlbaran materialAlbaran1, materialAlbaran2;
        if(row<1 || row>=vMaterialAlbaran.size())
            return;
        materialAlbaran1 = (MaterialAlbaran)vMaterialAlbaran.elementAt(row-1);
        materialAlbaran2 = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        materialAlbaran1.setFila(row);
        materialAlbaran2.setFila(row-1);
        vMaterialAlbaran.remove(row-1);
        vMaterialAlbaran.insertElementAt(materialAlbaran1, row);
    }
    
    public void bajar(int row) {
        MaterialAlbaran materialAlbaran1, materialAlbaran2;
        if(row<0 || row>=vMaterialAlbaran.size()-1)
            return;
        materialAlbaran1 = (MaterialAlbaran)vMaterialAlbaran.elementAt(row+1);
        materialAlbaran2 = (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
        materialAlbaran1.setFila(row);
        materialAlbaran2.setFila(row+1);
        vMaterialAlbaran.remove(row+1);
        vMaterialAlbaran.insertElementAt(materialAlbaran1, row);
    }
    
    public MaterialAlbaran getMaterialAlbaran(int row) {
        return (MaterialAlbaran)vMaterialAlbaran.elementAt(row);
    }
    
//    public void newDataAvailable(javax.swing.event.TableModelEvent event) {
//        System.out.println(event.getSource().getClass());
//    }

}

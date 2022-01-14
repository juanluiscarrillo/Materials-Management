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
public class AlbaranTableModel extends javax.swing.table.AbstractTableModel{
    Vector columnNames = new Vector();
    Vector vAlbaran;
    private boolean toAlbaran;
    Connection connection;
    String id_proveedor;
    
    public AlbaranTableModel(Vector vAlbaran) {
        this.vAlbaran = vAlbaran;  
        toAlbaran = true;
    }
    
    public AlbaranTableModel() {
        this.vAlbaran = new Vector();   
        toAlbaran = true;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    public boolean isToAlbaran() {
        return toAlbaran;
    }

    public void setToAlbaran(boolean toAlbaran) {
        this.toAlbaran = toAlbaran;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void addAlbaran(Albaran albaran) {
        this.vAlbaran.addElement(albaran);
    }
    
    public void deleteAlbaran(int row) {
        this.vAlbaran.remove(row);
    }
    
    public Albaran getAlbaran(int row) {
        try {
            return (Albaran)vAlbaran.elementAt(row);
            
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
    }
    
    public int getRowCount() {
        if (toAlbaran)
            return this.vAlbaran.size();
        else
            return this.vAlbaran.size()+1;
                
    }
    
    public int getColumnCount() {
        return 6;
    }
    public Object getValueAt(int row, int column) {
        Albaran albaran;
        try {
            albaran = (Albaran)this.vAlbaran.elementAt(row);
            albaran.setConnection(connection);
            if(albaran == null)
                return null;
            switch(column) {
                case 0: return albaran.getId_albaran();
                case 1: return albaran.getId_proveedor();
                case 2: return albaran.getFecha(); 
                case 3: return albaran.getTotalNeto();
                case 4: return albaran.getReceptor();
                case 5: return albaran.getObra();
            }
        } catch(Exception e) {
            
        }
                
        
        return null;
    }
    
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "ID_ALBARAN";
            case 1: return "ID_PROVEEDOR";
            case 2: return "FECHA";
            case 3: return "TOTAL NETO";
            case 4: return "RECEPTOR";
            case 5: return "OBRA";
        }
        return null;
    }  
    
    public void setValueAt(Object value, int row, int column) {
        switch(column) {
            case 0: 
                isAlbaranModified(value,row,column);
                return;
            
        }
    }
    
    private void isAlbaranModified(Object value, int row, int column) {
        Albaran albaran;
     
        if(this.vAlbaran.size()==row){
            albaran = new Albaran();
            albaran.setFila(row);
        } else {
            albaran = (Albaran)vAlbaran.elementAt(row);
        }
        
        if(column == 0) {
            Material material;
            String id_albaran;
            String sql;
            
            id_albaran = (String)(value);
            sql = "SELECT * FROM ALBARAN WHERE ID_ALBARAN='" +
                  id_albaran +
                  "' AND ID_PROVEEDOR='" +
                  id_proveedor +
                  "';" ;
            
           try { 
                Statement statement;
                ResultSet resultSet;
                statement = this.connection.createStatement();
                resultSet = statement.executeQuery(sql);
                if(!resultSet.next()) 
                    return;
                
                albaran = new Albaran();
                albaran.setId_albaran(id_albaran);
                albaran.setId_proveedor(id_proveedor);
                albaran.setFecha(new Fecha(resultSet.getInt("DIA"),
                                 resultSet.getInt("MES"),
                                 resultSet.getInt("ANIO")));
                albaran.setObra(resultSet.getString("OBRA"));
                albaran.setReceptor(resultSet.getString("RECEPTOR"));
                albaran.setDescuento(resultSet.getDouble("DESCUENTO"));
                
                if(this.vAlbaran.size()==row)
                    this.vAlbaran.addElement(albaran);
                
                fireTableCellUpdated(row, column);
                
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void clearTable() {
        this.vAlbaran = new Vector();
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return !toAlbaran;
        }
        return false;
    }
    
    public double calculateSuma() {
        Albaran albaran;
        double suma =0;
        for(int i=0;i<vAlbaran.size();i++) {
            albaran = (Albaran)vAlbaran.elementAt(i);
            albaran.setConnection(connection);
            suma += UtilGestion.round(albaran.getTotalNeto());
        }
        return UtilGestion.round(suma);
    }
    
    public void subir(int row) {
        Albaran albaran1, albaran2;
        if(row<1 || row>=vAlbaran.size())
            return;
        albaran1 = (Albaran)vAlbaran.elementAt(row-1);
        albaran2 = (Albaran)vAlbaran.elementAt(row);
        albaran1.setFila(row);
        albaran2.setFila(row-1);
        vAlbaran.remove(row-1);
        vAlbaran.insertElementAt(albaran1, row);
    } 
    
    public void bajar(int row) {
        Albaran albaran1, albaran2;
        if(row<0 || row>=vAlbaran.size()-1)
            return;
        albaran1 = (Albaran)vAlbaran.elementAt(row+1);
        albaran2 = (Albaran)vAlbaran.elementAt(row);
        albaran1.setFila(row);
        albaran2.setFila(row+1);
        vAlbaran.remove(row+1);
        vAlbaran.insertElementAt(albaran1, row);
    }
    
    public boolean isAlbaran(Albaran albaran) {
        Albaran temp;
        
        for(int i=0;i<vAlbaran.size();i++) {
            temp = (Albaran)vAlbaran.elementAt(i);
            if(temp.getId_albaran()==albaran.getId_albaran())
                return true;
        }
        return false;
    }
    
    public Class getColumnClass(int column) {
        switch(column) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Fecha.class;
            case 3: return Double.class;
            case 4: return String.class;
            case 5: return String.class;
        }
        return null;
    }


}

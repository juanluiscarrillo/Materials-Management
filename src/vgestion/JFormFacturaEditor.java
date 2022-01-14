/*
 * JFormMaterial.java
 *
 * Created on 11 de enero de 2008, 0:00
 */

package vgestion;

import javax.swing.event.TableModelEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.*;
import java.util.*;

/**
 *
 * @author  Juan Luis
 */
public class JFormFacturaEditor extends javax.swing.JDialog {
    private java.awt.Frame parent;
    private AlbaranTableModel albaranTableModel;
    private Connection connection;
    private JTextField jtfTable;
    private boolean newFactura;
    
    
    MaterialAlbaranCellRenderer materialAlbaranCellRenderer;
    

    public Connection getConnection() {
        return connection;
    }
    
    public void setFactura(Factura factura) {
        jtfIdproveedor.setText(factura.getId_proveedor());
        jtfIdproveedor.setEnabled(false);
        jtfIdfactura.setText(factura.getId_factura());
        jtfFechaDia.setText(""+factura.getDia());
        jtfFechaMes.setText(""+factura.getMes());
        jtfFechaAnio.setText(""+factura.getAnio());
        jtfIdfactura.setEnabled(false);
        updateAlbaran();
        proveedorChanged();
        updateTotal(); 
    }
    
    private void updateAlbaran() {
        String sql, sql2;
        Statement statement;
        ResultSet resultSet, rs;
        MaterialAlbaran materialAlbaran;
        Material material;
        Albaran albaran;
        
        
        sql = "SELECT * FROM ALBARAN_FACTURA WHERE ID_FACTURA='" +
              jtfIdfactura.getText().trim() +
              "' AND ID_PROVEEDOR='" +
              jtfIdproveedor.getText().trim() +
              "' ORDER BY FILA;";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String id_albaran;
                albaran = new Albaran();
                
                id_albaran = resultSet.getString("ID_ALBARAN");
                sql2 = "SELECT * FROM ALBARAN WHERE ID_ALBARAN";
                
                albaran.setId_albaran(id_albaran);
                albaran.setId_proveedor(jtfIdproveedor.getText().trim());
                albaran.setFila(resultSet.getInt("FILA"));
                albaran.setConnection(connection);
                albaran.updateFechaFromDB();
                albaranTableModel.addAlbaran(albaran);
            }
            jTMaterial.updateUI();
                
        } catch (Exception ex) {
            ex.printStackTrace();
        }
          
    }
    
//    private Material getMaterial(String id_material, String id_proveedor) {
//        String sql;
//        Statement statement;
//        ResultSet resultSet;
//        Material material;
//        
//        sql = "SELECT * FROM MATERIAL WHERE ID_MATERIAL='" +
//              id_material +
//              "' AND ID_PROVEEDOR='" +
//              id_proveedor +
//              "';";
//        try {
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            if(resultSet.next()) {
//                material = new Material();
//                material.setId_material(id_material);;
//                material.setId_proveedor(id_proveedor);
//                material.setCategoria(resultSet.getString("CATEGORIA"));
//                material.setDescripcion(resultSet.getString("DESCRIPCION"));
//                material.setMarca(resultSet.getString("MARCA"));
//                material.setRef_marca(resultSet.getString("REF_MARCA"));
//                return material;
//            } else
//                return null;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        this.albaranTableModel.setConnection(connection);
    }

    public boolean isNewFactura() {
        return newFactura;
    }

    public void setNewFactura(boolean newFactura) {
        this.newFactura = newFactura;
    }
    
    /** Creates new form JFormMaterial */
    public JFormFacturaEditor(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        this.parent = parent;
        
        newFactura = true;
        
        albaranTableModel = new AlbaranTableModel();
        albaranTableModel.setToAlbaran(false);
        initComponents();
        
        albaranTableModel.addTableModelListener(new javax.swing.event.TableModelListener() {
            

            public void tableChanged(TableModelEvent arg0) {
                albaranTableChanged(arg0);
            }
        });
    }
    
    public void albaranTableChanged(TableModelEvent arg0) {
        
        updateTotal();
        
    }
    
    private void updateTotal() {
        double suma, iva, total;
        
        suma = albaranTableModel.calculateSuma();
        iva = 0;
        
        jTMaterial.updateUI();
        
        try {
            iva = Double.parseDouble(jtfIva.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.jtfSuma.setText(Double.toString(suma));
        total = UtilGestion.round(suma * (1+iva/100.0));
        this.jtfTotal.setText(Double.toString(total));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlIdfactura = new javax.swing.JLabel();
        jtfIdfactura = new javax.swing.JTextField();
        jlIdproveedor = new javax.swing.JLabel();
        jtfIdproveedor = new javax.swing.JTextField();
        jlProveedor = new javax.swing.JLabel();
        jtfProveedor = new javax.swing.JTextField();
        jlFecha = new javax.swing.JLabel();
        jtfFechaDia = new javax.swing.JTextField();
        jtfFechaMes = new javax.swing.JTextField();
        jtfFechaAnio = new javax.swing.JTextField();
        jbBorrarFila = new javax.swing.JButton();
        jbSubir = new javax.swing.JButton();
        jbBajar = new javax.swing.JButton();
        jbCompararPrecios = new javax.swing.JButton();
        jbAñadirAlbaranes = new javax.swing.JButton();
        jSPMaterial = new javax.swing.JScrollPane();
        jTMaterial = new javax.swing.JTable();
        jlSuma = new javax.swing.JLabel();
        jtfSuma = new javax.swing.JTextField();
        jlIva = new javax.swing.JLabel();
        jtfIva = new javax.swing.JTextField();
        jlTotal = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Factura Editor");
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlIdfactura.setText("Id Factura");
        getContentPane().add(jlIdfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdfactura.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdfactura.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfIdfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlIdproveedor.setText("Id Proveedor");
        getContentPane().add(jlIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdproveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdproveedor.setPreferredSize(new java.awt.Dimension(400, 20));
        jtfIdproveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfIdproveedorFocusLost(evt);
            }
        });
        jtfIdproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfIdproveedorKeyTyped(evt);
            }
        });
        getContentPane().add(jtfIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlProveedor.setText("Proveedor");
        getContentPane().add(jlProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfProveedor.setEnabled(false);
        jtfProveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfProveedor.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlFecha.setText("Fecha");
        getContentPane().add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaDia.setColumns(2);
        getContentPane().add(jtfFechaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaMes.setColumns(2);
        getContentPane().add(jtfFechaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaAnio.setColumns(4);
        getContentPane().add(jtfFechaAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbBorrarFila.setText("Borrar fila");
        jbBorrarFila.setEnabled(false);
        jbBorrarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarFilaActionPerformed(evt);
            }
        });
        getContentPane().add(jbBorrarFila, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbSubir.setText("Subir");
        jbSubir.setEnabled(false);
        jbSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSubirActionPerformed(evt);
            }
        });
        getContentPane().add(jbSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbBajar.setText("Bajar");
        jbBajar.setEnabled(false);
        jbBajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBajarActionPerformed(evt);
            }
        });
        getContentPane().add(jbBajar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbCompararPrecios.setText("Comparar precios");
        jbCompararPrecios.setEnabled(false);
        jbCompararPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCompararPreciosActionPerformed(evt);
            }
        });
        getContentPane().add(jbCompararPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbAñadirAlbaranes.setText("Añadir Albaranes");
        jbAñadirAlbaranes.setEnabled(false);
        jbAñadirAlbaranes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAñadirAlbaranesActionPerformed(evt);
            }
        });
        getContentPane().add(jbAñadirAlbaranes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTMaterial.setAutoCreateRowSorter(true);
        jTMaterial.setCellSelectionEnabled(true);
        jTMaterial.setEnabled(false);
        jTMaterial.setModel(albaranTableModel);
        jSPMaterial.setViewportView(jTMaterial);

        getContentPane().add(jSPMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlSuma.setText("SUMA");
        getContentPane().add(jlSuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfSuma.setColumns(10);
        jtfSuma.setEditable(false);
        jtfSuma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(jtfSuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlIva.setText("IVA (%)");
        getContentPane().add(jlIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIva.setColumns(3);
        jtfIva.setEditable(false);
        jtfIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfIva.setText("16");
        getContentPane().add(jtfIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlTotal.setText("TOTAL");
        getContentPane().add(jlTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfTotal.setColumns(10);
        jtfTotal.setEditable(false);
        jtfTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(jtfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbAceptar.setText("Aceptar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(jbAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jbCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int locX, locY, sizeX, sizeY;
        sizeX = 300;
        sizeY = 20;
        this.jlIdfactura.setSize(sizeX, sizeY);
        this.jlIdproveedor.setSize(sizeX, sizeY);
        this.jlProveedor.setSize(sizeX, sizeY);
        this.jlFecha.setSize(sizeX, sizeY);
        
        this.jtfIdfactura.setSize(sizeX, sizeY);
        this.jtfIdproveedor.setSize(sizeX, sizeY);
        this.jtfProveedor.setSize(sizeX, sizeY);
        
        locX = 10;
        locY = 10;
        this.jlIdfactura.setLocation(locX, locY);
        locY+=this.jlIdfactura.getHeight();
        this.jlIdproveedor.setLocation(locX, locY);
        locY+=this.jlIdproveedor.getHeight();
        this.jlProveedor.setLocation(locX, locY);
        locY+=this.jlProveedor.getHeight();
        this.jlFecha.setLocation(locX, locY);
        locY+=this.jlFecha.getHeight();
        locY+=10;
        
        locX = jlIdproveedor.getLocation().x+jlIdproveedor.getPreferredSize().width+10;
        locY = 10;
        this.jtfIdfactura.setLocation(locX, locY);
        locY+=this.jtfIdfactura.getHeight();
        this.jtfIdproveedor.setLocation(locX, locY);
        locY+=this.jtfIdproveedor.getHeight();
        this.jtfProveedor.setLocation(locX, locY);
        locY+=this.jtfProveedor.getHeight();
        this.jtfFechaDia.setLocation(locX, locY);
        locX = jtfFechaDia.getLocation().x + jtfFechaDia.getWidth() + 5;
        this.jtfFechaMes.setLocation(locX, locY);
        locX = jtfFechaMes.getLocation().x + jtfFechaMes.getWidth() + 5;
        this.jtfFechaAnio.setLocation(locX, locY);
        locY+=this.jtfFechaDia.getHeight();
        locY+=10;
        locX = 10;
        this.jbBorrarFila.setLocation(locX, locY);
        this.jbSubir.setLocation(jbBorrarFila.getLocation().x+jbBorrarFila.getWidth() + 10, locY);
        this.jbBajar.setLocation(jbSubir.getLocation().x+jbSubir.getWidth() + 10, locY);
        this.jbCompararPrecios.setLocation(jbBajar.getLocation().x+jbBajar.getWidth() + 10, locY);
        this.jbAñadirAlbaranes.setLocation(jbCompararPrecios.getLocation().x+jbCompararPrecios.getWidth() + 10, locY);
        locY += jbBorrarFila.getHeight();
        
        
        locX=10;
        locY+=10;
        this.jSPMaterial.setLocation(locX, locY);
        this.jSPMaterial.setSize(this.getWidth()-2*locX,this.getHeight()-locY-40-4*this.jbAceptar.getHeight());
        this.jTMaterial.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(3).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(4).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(5).setPreferredWidth(this.getWidth()/6);
        this.jTMaterial.getColumnModel().getColumn(0).sizeWidthToFit();

        locX = getWidth() - jtfTotal.getWidth() - 20;
        locY = (int)(this.jSPMaterial.getHeight()+ this.jSPMaterial.getLocation().getY() + this.jbAceptar.getHeight()/2);
        this.jtfTotal.setLocation(locX , locY);
        locX = (int)(jtfTotal.getLocation().getX()) - jlTotal.getWidth() - 5;
        this.jlTotal.setLocation(locX, locY);
        
        locX = (int)(jlTotal.getLocation().getX()) - jtfIva.getWidth() - 20;
        this.jtfIva.setLocation(locX, locY);
        locX = (int)(jtfIva.getLocation().getX()) - jlIva.getWidth() - 5;
        this.jlIva.setLocation(locX, locY);
        
        locX = (int)(jlIva.getLocation().getX()) - jtfSuma.getWidth() - 20;
        this.jtfSuma.setLocation(locX, locY);
        locX = (int)(jtfSuma.getLocation().getX()) - jlSuma.getWidth() - 5;
        this.jlSuma.setLocation(locX, locY);

        locY = (int)(this.jSPMaterial.getHeight()+ this.jSPMaterial.getLocation().getY() + 2*this.jbAceptar.getHeight());
        locX = this.getWidth()/2 - this.jbAceptar.getWidth() -10;
        this.jbAceptar.setLocation(locX, locY);
        locX = this.getWidth()/2 + this.jbCancelar.getWidth() +10;
        this.jbCancelar.setLocation(locX , locY);
    }//GEN-LAST:event_formComponentResized

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        
        if(javax.swing.JOptionPane.YES_OPTION==javax.swing.JOptionPane.showConfirmDialog(this, "Está seguro que desea salir sin salvar los cambios"))
            dispose();
}//GEN-LAST:event_jbCancelarActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        String sql;
        Statement statement;
        ResultSet resultSet;
        Albaran albaran;
        
        if(jtfIdfactura.getText().trim().equals("")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Id de factura incorrecto");
            return;
        }

        try {
            statement = this.connection.createStatement();
            
            sql = "SELECT * FROM PROVEEDOR WHERE ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            
            resultSet = statement.executeQuery(sql);
            if(!resultSet.next()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El proveedor no es correcto");
                return;
            }
            
            if(UtilGestion.getStringFecha(jtfFechaDia.getText().trim(), jtfFechaMes.getText().trim(), jtfFechaAnio.getText().trim())==null) {
                javax.swing.JOptionPane.showMessageDialog(this, "La fecha es incorrecta");
                return;
            }
            
            sql = "SELECT * FROM FACTURA WHERE ID_FACTURA='" +
                  this.jtfIdfactura.getText().trim() +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                if(newFactura) {
                    javax.swing.JOptionPane.showMessageDialog(this, "La factura ya existe");
                    return;
                } else {
                    sql = "DELETE FROM FACTURA WHERE ID_FACTURA='"+
                          jtfIdfactura.getText().trim() +
                          "' AND ID_PROVEEDOR='" +
                          jtfIdproveedor.getText().trim() +
                          "';";
//                    System.out.println(sql);
                    statement.executeUpdate(sql);
                }
            } else {
                if(!newFactura) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se encuentra el albaran");
                    return;
                }
            }
            
            sql = "INSERT INTO FACTURA (ID_FACTURA, ID_PROVEEDOR, DIA, MES, ANIO, COMENTARIO, TIPO) VALUES ('" +
                  jtfIdfactura.getText().trim() +
                  "', '" +
                  jtfIdproveedor.getText().trim() +
                  "', " +
                  jtfFechaDia.getText().trim() +
                  ", " +
                  jtfFechaMes.getText().trim() +
                  ", " +
                  jtfFechaAnio.getText().trim() +
                  ", '',1);";
            statement.executeUpdate(sql);
            
            sql = "DELETE * FROM ALBARAN_FACTURA WHERE ID_PROVEEDOR='" +
                  jtfIdproveedor.getText().trim() +
                  "' AND ID_FACTURA='" +
                  jtfIdfactura.getText().trim() +
                  "';";
            statement.executeUpdate(sql);
            
            for(int i=0;i<albaranTableModel.getRowCount()-1;i++) {
                albaran = (Albaran)albaranTableModel.getAlbaran(i);
                sql = "SELECT * FROM ALBARAN WHERE ID_ALBARAN='" +
                      albaran.getId_albaran() +
                      "' AND ID_PROVEEDOR='" +
                      jtfIdproveedor.getText().trim() +
                      "';";
                resultSet = statement.executeQuery(sql);
                if(!resultSet.next()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Algun albaran no es correcto");
//                    System.out.println(sql);
                    return;    
                } else {
                    sql = "INSERT INTO ALBARAN_FACTURA (ID_FACTURA, ID_ALBARAN, ID_PROVEEDOR, FILA) VALUES ('" +
                          jtfIdfactura.getText().trim() +
                          "', '" +
                          albaran.getId_albaran() +
                          "', '" +
                          jtfIdproveedor.getText().trim() +
                          "', "+
                          i +
                          ");";
                    statement.executeUpdate(sql);
                }
            }
            this.dispose();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
            
}//GEN-LAST:event_jbAceptarActionPerformed

    private void jtfIdproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdproveedorKeyTyped
        if(evt.getKeyChar()=='\n')
            proveedorChanged();
    }//GEN-LAST:event_jtfIdproveedorKeyTyped

    private void jtfIdproveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdproveedorFocusLost
        proveedorChanged();
    }//GEN-LAST:event_jtfIdproveedorFocusLost

    private void jbBorrarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFilaActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        if(row>0) {
            albaranTableModel.deleteAlbaran(row);
            jTMaterial.updateUI();
        }
        
}//GEN-LAST:event_jbBorrarFilaActionPerformed

    private void jbSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSubirActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        
        if(row>0) {
            albaranTableModel.subir(row);
            jTMaterial.updateUI();
        }
             
                
}//GEN-LAST:event_jbSubirActionPerformed

    private void jbBajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBajarActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        
        if(row>-1) {
            albaranTableModel.bajar(row);
            jTMaterial.updateUI();
        }
}//GEN-LAST:event_jbBajarActionPerformed

    private void jbCompararPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCompararPreciosActionPerformed
//        JFormHistorialMaterial jFormHistorialMaterial;
//        int row;
//        String id_material;
//        
//        row = jTMaterial.getSelectedRow();
//        if(row<0)
//            return;
//           
//        id_material = (String)materialAlbaranTableModel.getValueAt(row, 0); 
//        id_material = id_material.trim();
//        if(id_material.equals(""))
//            return;
//        
//        jFormHistorialMaterial = new JFormHistorialMaterial(null,true);
//        jFormHistorialMaterial.setConnection(connection);
//        jFormHistorialMaterial.setMaterial(id_material, jtfIdproveedor.getText().trim());
//        jFormHistorialMaterial.setVisible(true);
//        
}//GEN-LAST:event_jbCompararPreciosActionPerformed

    private void jbAñadirAlbaranesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAñadirAlbaranesActionPerformed
        JFormAñadirAlbaranes jFormAñadirAlbaranes;
        Vector vAlbaranes;
        Albaran albaran;
        
        jFormAñadirAlbaranes = new JFormAñadirAlbaranes(null,true);
        jFormAñadirAlbaranes.setIdProveedor(jtfIdproveedor.getText().trim());
        jFormAñadirAlbaranes.setProveedor(jtfProveedor.getText().trim());
        jFormAñadirAlbaranes.setIdFactura(jtfIdfactura.getText().trim());
        
        jFormAñadirAlbaranes.setConnection(connection);
        jFormAñadirAlbaranes.setVisible(true);
        
        vAlbaranes = jFormAñadirAlbaranes.getVAlbaranes();
        
        for(int i=0;i<vAlbaranes.size();i++){
            albaran = (Albaran)vAlbaranes.elementAt(i);
            if(!albaranTableModel.isAlbaran(albaran)) {
                albaranTableModel.addAlbaran(albaran);
            }
        }
        
        updateTotal();
        jTMaterial.updateUI();
        
}//GEN-LAST:event_jbAñadirAlbaranesActionPerformed
    
    
    public void añadirAlbaran(Albaran albaran) {
        if(!albaranTableModel.isAlbaran(albaran)) {
            albaranTableModel.addAlbaran(albaran);
        }
        updateTotal();
    }
    
    private void proveedorChanged() {
        String sql;
        Statement statement;
        ResultSet resultSet;
        
        jtfProveedor.setText("");
        if(jtfIdproveedor.getText().trim().equals("")) 
            return;
            
        sql = "SELECT * FROM PROVEEDOR WHERE ID_PROVEEDOR='" + 
              jtfIdproveedor.getText().trim() +
              "';";
        
        albaranTableModel.setId_proveedor(jtfIdproveedor.getText().trim());
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            if(resultSet.next()) {
                jtfProveedor.setText(resultSet.getString("NOMBRE"));  
                jTMaterial.setEnabled(true);
                jTMaterial.setEnabled(true);
                jbBorrarFila.setEnabled(true);
                jbBajar.setEnabled(true);
                jbCompararPrecios.setEnabled(true);
                jbAñadirAlbaranes.setEnabled(true);
                jbSubir.setEnabled(true);
            } else {
                jTMaterial.setEnabled(false);
                jbBorrarFila.setEnabled(false);
                jbBajar.setEnabled(false);
                jbCompararPrecios.setEnabled(false);
                jbAñadirAlbaranes.setEnabled(false);
                jbSubir.setEnabled(false);
            }
        } catch (Exception ex) {
            jTMaterial.setEnabled(false);
            ex.printStackTrace();
        }
        
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFormFacturaEditor(null,true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jSPMaterial;
    private javax.swing.JTable jTMaterial;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbAñadirAlbaranes;
    private javax.swing.JButton jbBajar;
    private javax.swing.JButton jbBorrarFila;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbCompararPrecios;
    private javax.swing.JButton jbSubir;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlIdfactura;
    private javax.swing.JLabel jlIdproveedor;
    private javax.swing.JLabel jlIva;
    private javax.swing.JLabel jlProveedor;
    private javax.swing.JLabel jlSuma;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JTextField jtfFechaAnio;
    private javax.swing.JTextField jtfFechaDia;
    private javax.swing.JTextField jtfFechaMes;
    private javax.swing.JTextField jtfIdfactura;
    private javax.swing.JTextField jtfIdproveedor;
    private javax.swing.JTextField jtfIva;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JTextField jtfSuma;
    private javax.swing.JTextField jtfTotal;
    // End of variables declaration//GEN-END:variables
    
}

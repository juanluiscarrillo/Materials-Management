/*
 * JFormMaterial.java
 *
 * Created on 11 de enero de 2008, 0:00
 */

package vgestion;

import vgestion.*;
import javax.swing.table.TableColumnModel;
import java.sql.*;

/**
 *
 * @author  Juan Luis
 */
public class JFormHistorialMaterial extends javax.swing.JDialog {
    private java.awt.Frame parent;
    private HistorialMaterialTableModel historialMaterialTableModel;
    private Connection connection;
    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void setMaterial(String id_material, String id_proveedor) {
        jtfIdmaterial.setText(id_material);
        jtfIdproveedor.setText(id_proveedor);
        jtfIdmaterial.setEnabled(false);
        jtfIdproveedor.setEnabled(false);
        buscar();
        
    }
    
    
    /** Creates new form JFormMaterial */
    public JFormHistorialMaterial(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        this.parent = parent;
        historialMaterialTableModel = new HistorialMaterialTableModel();
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlIdmaterial = new javax.swing.JLabel();
        jtfIdmaterial = new javax.swing.JTextField();
        jlIdproveedor = new javax.swing.JLabel();
        jtfIdproveedor = new javax.swing.JTextField();
        jlFechaDesde = new javax.swing.JLabel();
        jtfFechaDesdeDia = new javax.swing.JTextField();
        jtfFechaDesdeMes = new javax.swing.JTextField();
        jtfFechaDesdeAnio = new javax.swing.JTextField();
        jlFechaHasta = new javax.swing.JLabel();
        jtfFechaHastaDia = new javax.swing.JTextField();
        jtfFechaHastaMes = new javax.swing.JTextField();
        jtfFechaHastaAnio = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jbActualizarPrecio = new javax.swing.JButton();
        jSPMaterial = new javax.swing.JScrollPane();
        jTMaterial = new javax.swing.JTable();

        setTitle("Historial material");
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jlIdmaterial.setText("Id Material");

        jtfIdmaterial.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdmaterial.setPreferredSize(new java.awt.Dimension(400, 20));

        jlIdproveedor.setText("Id Proveedor");

        jtfIdproveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdproveedor.setPreferredSize(new java.awt.Dimension(400, 20));

        jlFechaDesde.setText("Desde");

        jtfFechaDesdeDia.setColumns(2);

        jtfFechaDesdeMes.setColumns(2);

        jtfFechaDesdeAnio.setColumns(4);

        jlFechaHasta.setText("Hasta");

        jtfFechaHastaDia.setColumns(2);

        jtfFechaHastaMes.setColumns(2);

        jtfFechaHastaAnio.setColumns(4);

        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jbActualizarPrecio.setText("Actualizar Precio");
        jbActualizarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarPrecioActionPerformed(evt);
            }
        });

        jTMaterial.setAutoCreateRowSorter(true);
        jTMaterial.setModel(historialMaterialTableModel);
        jTMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTMaterialMouseClicked(evt);
            }
        });
        jTMaterial.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTMaterialInputMethodTextChanged(evt);
            }
        });
        jSPMaterial.setViewportView(jTMaterial);
        //jTMaterial.getColumnModel().getColumn(0).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(1).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(2).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(3).setWidth(1000);
        //jTMaterial.updateUI();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jSPMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlIdmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jtfIdmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jtfFechaDesdeDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(196, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbActualizarPrecio, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(265, 265, 265)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(32, Short.MAX_VALUE)
                    .addComponent(jtfIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jtfFechaDesdeMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(368, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jtfFechaDesdeAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(358, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jlFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jtfFechaHastaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(332, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jtfFechaHastaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(442, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(jtfFechaHastaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(416, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jSPMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(348, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlIdmaterial))
                        .addComponent(jtfIdmaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(2, 2, 2)
                    .addComponent(jlIdproveedor)
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlFechaDesde))
                        .addComponent(jtfFechaDesdeDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(201, 201, 201)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jbBuscar)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jbActualizarPrecio)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(jtfIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(229, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(189, 189, 189)
                    .addComponent(jtfFechaDesdeMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(191, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(199, Short.MAX_VALUE)
                    .addComponent(jtfFechaDesdeAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(181, 181, 181)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(189, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlFechaHasta))
                        .addComponent(jtfFechaHastaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(171, 171, 171)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addComponent(jtfFechaHastaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(238, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(266, Short.MAX_VALUE)
                    .addComponent(jtfFechaHastaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(171, 171, 171)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int locX, locY, sizeX, sizeY;
        sizeX = 300;
        sizeY = 20;
        this.jlIdmaterial.setSize(sizeX, sizeY);
        this.jlIdproveedor.setSize(sizeX, sizeY);
        this.jlFechaDesde.setSize(sizeX, sizeY);
        this.jlFechaHasta.setSize(sizeX, sizeY);
        
        this.jtfIdmaterial.setSize(sizeX, sizeY);
        this.jtfIdproveedor.setSize(sizeX, sizeY);
        
        locX = 10;
        locY = 10;
        this.jlIdmaterial.setLocation(locX, locY);
        locY+=this.jlIdmaterial.getHeight();
        this.jlIdproveedor.setLocation(locX, locY);
        locY+=this.jlIdproveedor.getHeight();
        this.jlFechaDesde.setLocation(locX, locY);
        locY+=this.jlFechaDesde.getHeight();
        locY+=10;
        this.jbBuscar.setLocation(locX, locY);
        locX+=10 + jbBuscar.getWidth();
        this.jbActualizarPrecio.setLocation(locX, locY);
        locY+=this.jbBuscar.getHeight();
        
        
        locX = jbBuscar.getWidth()+20;
        locY = 10;
        this.jtfIdmaterial.setLocation(locX, locY);
        locY+=this.jtfIdmaterial.getHeight();
        this.jtfIdproveedor.setLocation(locX, locY);
        locY+=this.jtfIdproveedor.getHeight();
        this.jtfFechaDesdeDia.setLocation(locX, locY);
        locX += jtfFechaDesdeDia.getWidth() + 5;
        this.jtfFechaDesdeMes.setLocation(locX, locY);
        locX += jtfFechaDesdeMes.getWidth() + 5;
        this.jtfFechaDesdeAnio.setLocation(locX, locY);
        
        
        
        locX = jtfIdproveedor.getLocation().x + jtfIdproveedor.getWidth() - jtfFechaHastaAnio.getWidth();
        this.jtfFechaHastaAnio.setLocation(locX, locY);
        locX -= jtfFechaHastaMes.getWidth() + 5;
        this.jtfFechaHastaMes.setLocation(locX, locY);        
        locX -= jtfFechaHastaDia.getWidth() + 5;
        this.jtfFechaHastaDia.setLocation(locX, locY);
        locX -= jlFechaHasta.getPreferredSize().width + 10;
        this.jlFechaHasta.setLocation(locX, locY);
        
        locX = 10;
                
        locY+=this.jtfFechaDesdeDia.getHeight();
        locY+=10;
        this.jbBuscar.setLocation(locX, locY);
        locY+=this.jbBuscar.getHeight();
        
        locX=10;
        locY+=10;
        this.jSPMaterial.setLocation(locX, locY);
        this.jSPMaterial.setSize(this.getWidth()-2*locX,this.getHeight()-locY-40);
        this.jTMaterial.getColumnModel().getColumn(0).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(2).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(3).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(4).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(5).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(6).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(7).setPreferredWidth(this.getWidth()/8);
        this.jTMaterial.getColumnModel().getColumn(0).sizeWidthToFit();
    }//GEN-LAST:event_formComponentResized

    private void jTMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTMaterialMouseClicked
//        this.jTMaterial.getCellEditor(WIDTH, WIDTH);
//        System.out.println(this.jTMaterial.columnAtPoint(evt.getPoint()));
    }//GEN-LAST:event_jTMaterialMouseClicked

    private void jTMaterialInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTMaterialInputMethodTextChanged
//        System.out.println("");
    }//GEN-LAST:event_jTMaterialInputMethodTextChanged

    public void buscar() {
        String sql;
        Statement statement;
        ResultSet resultSet;
        HistorialMaterial historialMaterial;
        int diaHasta, mesHasta, anioHasta;
        int diaDesde, mesDesde, anioDesde;
        boolean noFecha;
        
        try {
            sql = "SELECT * FROM MATERIAL WHERE ID_MATERIAL='" +
                  this.jtfIdmaterial.getText().trim() +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(!resultSet.next()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El material no existe");
                return; 
            }
            
            noFecha = jtfFechaDesdeDia.getText().trim().equals("") &&
                      jtfFechaDesdeMes.getText().trim().equals("") &&
                      jtfFechaDesdeAnio.getText().trim().equals("") &&
                      jtfFechaHastaDia.getText().trim().equals("") &&
                      jtfFechaHastaMes.getText().trim().equals("") &&
                      jtfFechaHastaAnio.getText().trim().equals("");
            
            diaHasta = 0;
            mesHasta = 0;
            anioHasta = 0;
            
            diaDesde = 0;
            mesDesde = 0;
            anioDesde = 0;
            
            if(!noFecha) {
                if(UtilGestion.getStringFecha(jtfFechaDesdeDia.getText().trim(), jtfFechaDesdeMes.getText().trim(), jtfFechaDesdeAnio.getText().trim())==null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Alguna de las fechas son incorrectas");
                    return;
                }
                if(UtilGestion.getStringFecha(jtfFechaHastaDia.getText().trim(), jtfFechaHastaMes.getText().trim(), jtfFechaHastaAnio.getText().trim())==null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Alguna de las fechas son incorrectas");
                    return;
                }
                diaHasta = Integer.parseInt(jtfFechaHastaDia.getText().trim());
                mesHasta = Integer.parseInt(jtfFechaHastaMes.getText().trim());
                anioHasta = Integer.parseInt(jtfFechaHastaAnio.getText().trim());

                diaDesde = Integer.parseInt(jtfFechaDesdeDia.getText().trim());
                mesDesde = Integer.parseInt(jtfFechaDesdeMes.getText().trim());
                anioDesde = Integer.parseInt(jtfFechaDesdeAnio.getText().trim());
            }
           
            sql = "SELECT ALBARAN.ID_ALBARAN, ALBARAN.DIA, ALBARAN.MES, ALBARAN.ANIO, MATERIAL_ALBARAN.CANTIDAD, MATERIAL_ALBARAN.PRECIO, MATERIAL_ALBARAN.DESCUENTO, MATERIAL_ALBARAN.RAEE FROM ALBARAN, MATERIAL_ALBARAN WHERE MATERIAL_ALBARAN.ID_PROVEEDOR='" +
                  jtfIdproveedor.getText().trim() +
                  "' AND MATERIAL_ALBARAN.ID_MATERIAL='" +
                  jtfIdmaterial.getText().trim() +
                  "' AND ALBARAN.ID_ALBARAN=MATERIAL_ALBARAN.ID_ALBARAN AND ALBARAN.ID_PROVEEDOR=MATERIAL_ALBARAN.ID_PROVEEDOR;";
//            System.out.println(sql);
            
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            historialMaterialTableModel.clearTable();
            while(resultSet.next()) {
                historialMaterial = new HistorialMaterial();
                historialMaterial.setId_albaran(resultSet.getString("ID_ALBARAN"));
                historialMaterial.setCantidad(resultSet.getDouble("CANTIDAD"));
                historialMaterial.setPrecio(resultSet.getDouble("PRECIO"));
                historialMaterial.setDescuento(resultSet.getDouble("DESCUENTO"));
                historialMaterial.setRaee(resultSet.getDouble("RAEE"));
                historialMaterial.setFecha(new Fecha(resultSet.getInt("DIA"),resultSet.getInt("MES"),resultSet.getInt("ANIO")));
                if(noFecha || historialMaterial.isInFecha(diaDesde, mesDesde, anioDesde, diaHasta, mesHasta, anioHasta)) {
                    historialMaterialTableModel.addHistorialMaterial(historialMaterial);
                }
            }
            jTMaterial.updateUI();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbActualizarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarPrecioActionPerformed
        int row;
        String sql;
        String id_material;
        Statement statement;
        
        
        if(jTMaterial.getSelectedRows().length > 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Solo puede haber una fila seleccionada");
            return;
        }
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        
        
        try {
            statement = this.connection.createStatement();
            
            sql = "UPDATE MATERIAL SET PRECIO = " +
                  historialMaterialTableModel.getValueAt(row, 3) +
                  ", DESCUENTO = " +
                  historialMaterialTableModel.getValueAt(row, 4) +
                  ", RAEE = " +
                  historialMaterialTableModel.getValueAt(row, 5) +
                  " WHERE ID_MATERIAL = '"+
                  jtfIdmaterial.getText().trim() +
                  "' AND ID_PROVEEDOR = '" +
                  jtfIdproveedor.getText().trim() +
                  "';";
            System.out.println(sql);
            
            statement.executeUpdate(sql);
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
}//GEN-LAST:event_jbActualizarPrecioActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFormHistorialMaterial(null,true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jSPMaterial;
    private javax.swing.JTable jTMaterial;
    private javax.swing.JButton jbActualizarPrecio;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JLabel jlFechaDesde;
    private javax.swing.JLabel jlFechaHasta;
    private javax.swing.JLabel jlIdmaterial;
    private javax.swing.JLabel jlIdproveedor;
    private javax.swing.JTextField jtfFechaDesdeAnio;
    private javax.swing.JTextField jtfFechaDesdeDia;
    private javax.swing.JTextField jtfFechaDesdeMes;
    private javax.swing.JTextField jtfFechaHastaAnio;
    private javax.swing.JTextField jtfFechaHastaDia;
    private javax.swing.JTextField jtfFechaHastaMes;
    private javax.swing.JTextField jtfIdmaterial;
    private javax.swing.JTextField jtfIdproveedor;
    // End of variables declaration//GEN-END:variables
    
}

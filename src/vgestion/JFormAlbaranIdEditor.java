/*
 * JFormMaterial.java
 *
 * Created on 11 de enero de 2008, 0:00
 */

package vgestion;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.*;
import java.util.*;

/**
 *
 * @author  Juan Luis
 */
public class JFormAlbaranIdEditor extends javax.swing.JDialog {
    private java.awt.Frame parent;
    private Connection connection;
    private String idAlbaranOld;
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setAlbaran(Albaran albaran) {
        idAlbaranOld = albaran.getId_albaran();
        jtfIdproveedor.setText(albaran.getId_proveedor());
        jtfIdalbaran.setText(albaran.getId_albaran());
        jtfObra.setText(albaran.getObra());
        jtfReceptor.setText(albaran.getReceptor());
        jtfFechaDia.setText(""+albaran.getFecha().getDia());
        jtfFechaMes.setText(""+albaran.getFecha().getMes());
        jtfFechaAnio.setText(""+albaran.getFecha().getAnio());
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    
    /** Creates new form JFormMaterial */
    public JFormAlbaranIdEditor(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        this.parent = parent;
        
        initComponents();
    }
   

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlIdalbaran = new javax.swing.JLabel();
        jtfIdalbaran = new javax.swing.JTextField();
        jlIdproveedor = new javax.swing.JLabel();
        jtfIdproveedor = new javax.swing.JTextField();
        jlProveedor = new javax.swing.JLabel();
        jtfProveedor = new javax.swing.JTextField();
        jlReceptor = new javax.swing.JLabel();
        jtfReceptor = new javax.swing.JTextField();
        jlObra = new javax.swing.JLabel();
        jtfObra = new javax.swing.JTextField();
        jlFecha = new javax.swing.JLabel();
        jtfFechaDia = new javax.swing.JTextField();
        jtfFechaMes = new javax.swing.JTextField();
        jtfFechaAnio = new javax.swing.JTextField();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Albaran Editor");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlIdalbaran.setText("Id Albaran");
        getContentPane().add(jlIdalbaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdalbaran.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdalbaran.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfIdalbaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlIdproveedor.setText("Id Proveedor");
        getContentPane().add(jlIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdproveedor.setEditable(false);
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

        jtfProveedor.setEditable(false);
        jtfProveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfProveedor.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlReceptor.setText("Receptor");
        getContentPane().add(jlReceptor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfReceptor.setEditable(false);
        jtfReceptor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfReceptor.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfReceptor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlObra.setText("Obra");
        getContentPane().add(jlObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfObra.setEditable(false);
        jtfObra.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfObra.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlFecha.setText("Fecha");
        getContentPane().add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaDia.setColumns(2);
        jtfFechaDia.setEditable(false);
        getContentPane().add(jtfFechaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaMes.setColumns(2);
        jtfFechaMes.setEditable(false);
        getContentPane().add(jtfFechaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaAnio.setColumns(4);
        jtfFechaAnio.setEditable(false);
        getContentPane().add(jtfFechaAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
        
        this.jlIdalbaran.setSize(sizeX, sizeY);
        this.jlIdproveedor.setSize(sizeX, sizeY);
        this.jlProveedor.setSize(sizeX, sizeY);
        this.jlFecha.setSize(sizeX, sizeY);
        this.jlReceptor.setSize(sizeX, sizeY);
        this.jlObra.setSize(sizeX, sizeY);
        
        this.jtfIdalbaran.setSize(sizeX, sizeY);
        this.jtfIdproveedor.setSize(sizeX, sizeY);
        this.jtfProveedor.setSize(sizeX, sizeY);
        this.jtfReceptor.setSize(sizeX, sizeY);
        this.jtfObra.setSize(sizeX, sizeY);
        
        locX = 10;
        locY = 10;
        this.jlIdalbaran.setLocation(locX, locY);
        locY+=this.jlIdalbaran.getHeight();
        this.jlIdproveedor.setLocation(locX, locY);
        locY+=this.jlIdproveedor.getHeight();
        this.jlProveedor.setLocation(locX, locY);
        locY+=this.jlProveedor.getHeight();
        this.jlReceptor.setLocation(locX, locY);
        locY+=this.jlReceptor.getHeight();
        this.jlObra.setLocation(locX, locY);
        locY+=this.jlObra.getHeight();
        this.jlFecha.setLocation(locX, locY);
        locY+=this.jlFecha.getHeight();
        locY+=10;
        
        
        
        locX = jlIdproveedor.getLocation().x+jlIdproveedor.getPreferredSize().width+10;
        locY = 10;
        this.jtfIdalbaran.setLocation(locX, locY);
        locY+=this.jtfIdalbaran.getHeight();
        this.jtfIdproveedor.setLocation(locX, locY);
        locY+=this.jtfIdproveedor.getHeight();
        this.jtfProveedor.setLocation(locX, locY);
        locY+=this.jtfProveedor.getHeight();
        this.jtfReceptor.setLocation(locX, locY);
        locY+=this.jtfReceptor.getHeight();
        this.jtfObra.setLocation(locX, locY);
        locY+=this.jtfObra.getHeight();
        this.jtfFechaDia.setLocation(locX, locY);
        locX = jtfFechaDia.getLocation().x + jtfFechaDia.getWidth() + 5;
        this.jtfFechaMes.setLocation(locX, locY);
        locX = jtfFechaMes.getLocation().x + jtfFechaMes.getWidth() + 5;
        this.jtfFechaAnio.setLocation(locX, locY);
        locY+=this.jtfFechaDia.getHeight();
        locY+=10;
        locX = 10;
       

        locY = (int)(this.jtfFechaDia.getHeight()+ this.jtfFechaDia.getLocation().getY() + 2*this.jbAceptar.getHeight());
        locX = jtfProveedor.getLocation().x + jtfProveedor.getWidth() + 20;
        locX = locX/2 - this.jbAceptar.getWidth() - 10;
        this.jbAceptar.setLocation(locX, locY);
        locX = jtfProveedor.getLocation().x + jtfProveedor.getWidth() + 20;
        locX = locX/2 + 10;
        this.jbCancelar.setLocation(locX , locY);
        
        locX = jtfProveedor.getLocation().x + jtfProveedor.getWidth() + 20;
        locY = jbCancelar.getLocation().y + 2*jbCancelar.getHeight() + 20;
        
        setSize(locX,locY);
        
        
        
//        this.jBNuevo.setLocation(this.getWidth()/2-this.jBNuevo.getWidth(), 0);
    }//GEN-LAST:event_formComponentResized

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        
        if(javax.swing.JOptionPane.YES_OPTION==javax.swing.JOptionPane.showConfirmDialog(this, "Est?? seguro que desea salir sin salvar los cambios"))
            dispose();
}//GEN-LAST:event_jbCancelarActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        String sql;
        Statement statement;
        ResultSet resultSet;

        try {
            statement = this.connection.createStatement();
            
            if(jtfIdalbaran.getText().trim().equals("")){
                javax.swing.JOptionPane.showMessageDialog(this, "Id de Albaran incorrecto.");
                return;
            }
            
            sql = "SELECT * FROM ALBARAN WHERE ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "' AND ID_ALBARAN='" +
                  jtfIdalbaran.getText().trim() +
                  "';";
            
            
            
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El albaran ya existe.");
                return;
            }
          
            sql = "UPDATE ALBARAN_FACTURA SET ID_ALBARAN = '"+
                  jtfIdalbaran.getText().trim() +
                  "' WHERE ID_ALBARAN='" +
                  idAlbaranOld +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            statement.execute(sql);
            
            sql = "UPDATE MATERIAL_ALBARAN SET ID_ALBARAN = '"+
                   jtfIdalbaran.getText().trim() +
                   "' WHERE ID_ALBARAN='" +
                  idAlbaranOld +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            statement.execute(sql);
            
            sql = "UPDATE ALBARAN SET ID_ALBARAN = '"+
                   jtfIdalbaran.getText().trim() +
                   "' WHERE ID_ALBARAN='" +
                  idAlbaranOld +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            statement.execute(sql);
            
            
            this.dispose();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
            
}//GEN-LAST:event_jbAceptarActionPerformed

    private void jtfIdproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdproveedorKeyTyped

    }//GEN-LAST:event_jtfIdproveedorKeyTyped

    private void jtfIdproveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdproveedorFocusLost

    }//GEN-LAST:event_jtfIdproveedorFocusLost
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFormAlbaranIdEditor(null,true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlIdalbaran;
    private javax.swing.JLabel jlIdproveedor;
    private javax.swing.JLabel jlObra;
    private javax.swing.JLabel jlProveedor;
    private javax.swing.JLabel jlReceptor;
    private javax.swing.JTextField jtfFechaAnio;
    private javax.swing.JTextField jtfFechaDia;
    private javax.swing.JTextField jtfFechaMes;
    private javax.swing.JTextField jtfIdalbaran;
    private javax.swing.JTextField jtfIdproveedor;
    private javax.swing.JTextField jtfObra;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JTextField jtfReceptor;
    // End of variables declaration//GEN-END:variables
    
}

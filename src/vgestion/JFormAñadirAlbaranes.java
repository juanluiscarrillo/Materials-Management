/*
 * JFormAñadirAlbaranes.java
 *
 * Created on 31 de agosto de 2008, 19:06
 */

package vgestion;
import java.util.Vector;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.List;
/**
 *
 * @author  Juan Luis
 */
public class JFormAñadirAlbaranes extends javax.swing.JDialog {
    private Vector vAlbaranes;

    
    private Connection connection;

    public Vector getVAlbaranes() {
        Vector vTemp;
        List selected;
        
        vTemp = new Vector();
        selected = jlistAlbaranes.getSelectedValuesList();
        
        for(int i=0;i<selected.size();i++) 
            vTemp.add(selected.get(i));
        
        
        return vTemp;
    }
    
    /** Creates new form JFormAñadirAlbaranes */
    public JFormAñadirAlbaranes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        vAlbaranes = new Vector();
        
        jlistAlbaranes.setModel(new javax.swing.AbstractListModel() {
            public int getSize() { return vAlbaranes.size(); }
            public Object getElementAt(int i) { return vAlbaranes.elementAt(i); }
        });
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
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
        jlFechaDesde = new javax.swing.JLabel();
        jtfFechaDesdeDia = new javax.swing.JTextField();
        jtfFechaDesdeMes = new javax.swing.JTextField();
        jtfFechaDesdeAnio = new javax.swing.JTextField();
        jlFechaHasta = new javax.swing.JLabel();
        jtfFechaHastaDia = new javax.swing.JTextField();
        jtfFechaHastaMes = new javax.swing.JTextField();
        jtfFechaHastaAnio = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jspAlbaranes = new javax.swing.JScrollPane();
        jlistAlbaranes = new javax.swing.JList();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insertar Albaranes");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlIdfactura.setText("Id Factura");
        getContentPane().add(jlIdfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdfactura.setEnabled(false);
        getContentPane().add(jtfIdfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlIdproveedor.setText("Id Proveedor");
        getContentPane().add(jlIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfIdproveedor.setEnabled(false);
        jtfIdproveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdproveedor.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlProveedor.setText("Proveedor");
        getContentPane().add(jlProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfProveedor.setEnabled(false);
        jtfProveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfProveedor.setPreferredSize(new java.awt.Dimension(400, 20));
        getContentPane().add(jtfProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlFechaDesde.setText("Desde");
        getContentPane().add(jlFechaDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaDesdeDia.setColumns(2);
        getContentPane().add(jtfFechaDesdeDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaDesdeMes.setColumns(2);
        getContentPane().add(jtfFechaDesdeMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaDesdeAnio.setColumns(4);
        getContentPane().add(jtfFechaDesdeAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jlFechaHasta.setText("Hasta");
        getContentPane().add(jlFechaHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaHastaDia.setColumns(2);
        getContentPane().add(jtfFechaHastaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaHastaMes.setColumns(2);
        getContentPane().add(jtfFechaHastaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtfFechaHastaAnio.setColumns(4);
        getContentPane().add(jtfFechaHastaAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jbBuscar.setText("Buscar");
        jbBuscar.setActionCommand("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jbBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jspAlbaranes.setViewportView(jlistAlbaranes);

        getContentPane().add(jspAlbaranes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        if(javax.swing.JOptionPane.YES_OPTION==javax.swing.JOptionPane.showConfirmDialog(this, "Está seguro que desea salir sin salvar los cambios")) {
            vAlbaranes = null;
            dispose();
        }
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int locX, locY, sizeX, sizeY;
        
        
        sizeX = 200;
        sizeY = 20;
        this.jlIdfactura.setSize(sizeX, sizeY);
        this.jlIdproveedor.setSize(sizeX, sizeY);
        this.jlProveedor.setSize(sizeX, sizeY);
        this.jlFechaDesde.setSize(sizeX, sizeY);
        this.jlFechaHasta.setSize(sizeX, sizeY);
        
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
        this.jlFechaDesde.setLocation(locX, locY);
        locY+=this.jlFechaDesde.getHeight();
        this.jlFechaHasta.setLocation(locX, locY);
        locY+=this.jlFechaHasta.getHeight();
        locY+=10;
        
        locX = jlIdproveedor.getLocation().x+jlIdproveedor.getPreferredSize().width+10;
        locY = 10;
        this.jtfIdfactura.setLocation(locX, locY);
        locY+=this.jtfIdfactura.getHeight();
        this.jtfIdproveedor.setLocation(locX, locY);
        locY+=this.jtfIdproveedor.getHeight();
        this.jtfProveedor.setLocation(locX, locY);
        locY+=this.jtfProveedor.getHeight();
        this.jtfFechaDesdeDia.setLocation(locX, locY);
        locX = jtfFechaDesdeDia.getLocation().x + jtfFechaDesdeDia.getWidth() + 5;
        this.jtfFechaDesdeMes.setLocation(locX, locY);
        locX = jtfFechaDesdeMes.getLocation().x + jtfFechaDesdeMes.getWidth() + 5;
        this.jtfFechaDesdeAnio.setLocation(locX, locY);
        locY+=this.jtfFechaDesdeDia.getHeight();
        jbBuscar.setLocation(jtfFechaDesdeAnio.getLocation().x+jtfFechaDesdeAnio.getWidth() + 10, 
                             jtfFechaDesdeAnio.getLocation().y+10);
        
        locX = jlIdproveedor.getLocation().x+jlIdproveedor.getPreferredSize().width+10;
        this.jtfFechaHastaDia.setLocation(locX, locY);
        locX = jtfFechaHastaDia.getLocation().x + jtfFechaDesdeDia.getWidth() + 5;
        this.jtfFechaHastaMes.setLocation(locX, locY);
        locX = jtfFechaHastaMes.getLocation().x + jtfFechaDesdeMes.getWidth() + 5;
        this.jtfFechaHastaAnio.setLocation(locX, locY);
        locY+=this.jtfFechaHastaDia.getHeight();
        
        jspAlbaranes.setSize(jtfIdfactura.getLocation().x + jtfIdfactura.getWidth()-10, 100);
        jspAlbaranes.setLocation(10, jtfFechaHastaDia.getLocation().y+jtfFechaHastaDia.getHeight()+10);
        
        sizeX = jtfIdfactura.getLocation().x + jtfIdfactura.getWidth() + 20;
        
        locY = (int)(this.jspAlbaranes.getHeight()+ this.jspAlbaranes.getLocation().getY() + this.jbAceptar.getHeight());
        locX = sizeX/2 - this.jbAceptar.getWidth() -10;
        this.jbAceptar.setLocation(locX, locY);
        locX = sizeX/2 + 10;
        this.jbCancelar.setLocation(locX , locY);
        
        sizeY = jbCancelar.getLocation().y + jbCancelar.getHeight()*3;
        
        setSize(new java.awt.Dimension(sizeX,sizeY));
        invalidate();
    }//GEN-LAST:event_formComponentResized

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        dispose();
    }//GEN-LAST:event_jbAceptarActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String sql;
        Statement statement;
        ResultSet resultSet;
        
        Fecha fechaDesde;
        Fecha fechaHasta;
        Fecha fechaAlbaran;
        
        Albaran albaran;
        
        fechaDesde = UtilGestion.getFecha(jtfFechaDesdeDia.getText(),
                                          jtfFechaDesdeMes.getText(), 
                                          jtfFechaDesdeAnio.getText());
        
        if (fechaDesde == null) {
            JOptionPane.showMessageDialog(this, "La fecha Desde es incorrecta");
            return;
        }
        
        fechaHasta = UtilGestion.getFecha(jtfFechaHastaDia.getText(),
                                          jtfFechaHastaMes.getText(), 
                                          jtfFechaHastaAnio.getText());
        
        if (fechaHasta == null) {
            JOptionPane.showMessageDialog(this, "La fecha Hasta es incorrecta");
            return;
        }
        
        vAlbaranes.removeAllElements();
        
        try {
            statement = this.connection.createStatement();
            
            sql = "SELECT * FROM ALBARAN WHERE ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                fechaAlbaran = UtilGestion.getFecha(resultSet.getInt("DIA"), 
                                                    resultSet.getInt("MES"), 
                                                    resultSet.getInt("ANIO"));
                if(UtilGestion.isInFecha(fechaAlbaran,fechaDesde,fechaHasta)) {
                    albaran = new Albaran();
                    albaran.setComentario(resultSet.getString("COMENTARIO"));
                    albaran.setDescuento(resultSet.getDouble("DESCUENTO"));
                    albaran.setFecha(fechaAlbaran);
                    albaran.setId_albaran(resultSet.getString("ID_ALBARAN"));
                    albaran.setId_proveedor(resultSet.getString("ID_PROVEEDOR"));
                    albaran.setObra(resultSet.getString("OBRA"));
                    albaran.setReceptor(resultSet.getString("RECEPTOR"));
                    
                    vAlbaranes.addElement(albaran);
                }
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        jlistAlbaranes.updateUI();
}//GEN-LAST:event_jbBuscarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFormAñadirAlbaranes dialog = new JFormAñadirAlbaranes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    public void setIdProveedor(String idProveedor) {
        jtfIdproveedor.setText(idProveedor);        
    }
    public void setProveedor(String proveedor) {
        jtfProveedor.setText(proveedor);        
    }
    public void setIdFactura(String idFactura) {
        jtfIdfactura.setText(idFactura);        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JLabel jlFechaDesde;
    private javax.swing.JLabel jlFechaHasta;
    private javax.swing.JLabel jlIdfactura;
    private javax.swing.JLabel jlIdproveedor;
    private javax.swing.JLabel jlProveedor;
    private javax.swing.JList jlistAlbaranes;
    private javax.swing.JScrollPane jspAlbaranes;
    private javax.swing.JTextField jtfFechaDesdeAnio;
    private javax.swing.JTextField jtfFechaDesdeDia;
    private javax.swing.JTextField jtfFechaDesdeMes;
    private javax.swing.JTextField jtfFechaHastaAnio;
    private javax.swing.JTextField jtfFechaHastaDia;
    private javax.swing.JTextField jtfFechaHastaMes;
    private javax.swing.JTextField jtfIdfactura;
    private javax.swing.JTextField jtfIdproveedor;
    private javax.swing.JTextField jtfProveedor;
    // End of variables declaration//GEN-END:variables
    
}

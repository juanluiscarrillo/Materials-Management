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
public class JFormAlbaranEditor extends javax.swing.JDialog {
    private java.awt.Frame parent;
    private MaterialAlbaranTableModel materialAlbaranTableModel;
    private Connection connection;
    private JTextField jtfTable;
    private boolean newAlbaran;
    
    
    MaterialAlbaranCellRenderer materialAlbaranCellRenderer;
    

    public Connection getConnection() {
        return connection;
    }
    
    public void setAlbaran(Albaran albaran) {
        jtfIdproveedor.setText(albaran.getId_proveedor());
        jtfIdproveedor.setEnabled(false);
        jtfIdalbaran.setText(albaran.getId_albaran());
        jtfObra.setText(albaran.getObra());
        jtfReceptor.setText(albaran.getReceptor());
        jtfFechaDia.setText(""+albaran.getFecha().getDia());
        jtfFechaMes.setText(""+albaran.getFecha().getMes());
        jtfFechaAnio.setText(""+albaran.getFecha().getAnio());
        jtfIdalbaran.setEnabled(false);
        updateMaterialAlbaran();
        proveedorChanged();
        updateTotal();
    }
    
    private void updateMaterialAlbaran() {
        String sql;
        Statement statement;
        ResultSet resultSet;
        MaterialAlbaran materialAlbaran;
        Material material;
        
        
        sql = "SELECT * FROM MATERIAL_ALBARAN WHERE ID_ALBARAN='" +
              jtfIdalbaran.getText().trim() +
              "' AND ID_PROVEEDOR='" +
              jtfIdproveedor.getText().trim() +
              "' ORDER BY FILA;";
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                materialAlbaran = new MaterialAlbaran();
                material = getMaterial(resultSet.getString("ID_MATERIAL"), resultSet.getString("ID_PROVEEDOR"));
                if(material==null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El material no se encuentra");
                    return;
                }
                materialAlbaran.setMaterial(material);
                materialAlbaran.setId_albaran(jtfIdalbaran.getText().trim());
                materialAlbaran.setCantidad(resultSet.getInt("CANTIDAD"));
                materialAlbaran.setDescuento(resultSet.getInt("DESCUENTO"));
                materialAlbaran.setFila(resultSet.getInt("FILA"));
                materialAlbaran.setObservacion(resultSet.getString("OBSERVACION"));
                materialAlbaran.setPrecio(resultSet.getDouble("PRECIO"));
                materialAlbaran.setRaee(resultSet.getDouble("RAEE"));
                materialAlbaranTableModel.addMaterialAlbaran(materialAlbaran);
            }
            jTMaterial.updateUI();
                
        } catch (Exception ex) {
            ex.printStackTrace();
        }
          
    }
    
    private Material getMaterial(String id_material, String id_proveedor) {
        String sql;
        Statement statement;
        ResultSet resultSet;
        Material material;
        
        sql = "SELECT * FROM MATERIAL WHERE ID_MATERIAL='" +
              id_material +
              "' AND ID_PROVEEDOR='" +
              id_proveedor +
              "';";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                material = new Material();
                material.setId_material(id_material);;
                material.setId_proveedor(id_proveedor);
                material.setCategoria(resultSet.getString("CATEGORIA"));
                material.setDescripcion(resultSet.getString("DESCRIPCION"));
                material.setMarca(resultSet.getString("MARCA"));
                material.setRef_marca(resultSet.getString("REF_MARCA"));
                material.setPrecio(resultSet.getDouble("PRECIO"));
                material.setDescuento(resultSet.getDouble("DESCUENTO"));
                material.setRaee(resultSet.getDouble("RAEE"));
                return material;
            } else
                return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        this.materialAlbaranTableModel.setConnection(connection);
    }

    public boolean isNewAlbaran() {
        return newAlbaran;
    }

    public void setNewAlbaran(boolean newAlbaran) {
        this.newAlbaran = newAlbaran;
    }
    
    
    
    /** Creates new form JFormMaterial */
    public JFormAlbaranEditor(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        this.parent = parent;
        
        newAlbaran = true;
        
        materialAlbaranTableModel = new MaterialAlbaranTableModel();
        
        initComponents();
        
        materialAlbaranTableModel.addTableModelListener(new javax.swing.event.TableModelListener() {

            public void tableChanged(TableModelEvent arg0) {
                materialAlbaranTableChanged(arg0);
            }
        });
    }
    
    public void materialAlbaranTableChanged(TableModelEvent arg0) {
        if(arg0.getColumn()==0)
            createNewMaterial(arg0.getFirstRow());
        updateTotal();
        
    }
    
    private void createNewMaterial(int row) {
        JFormMaterialLista jFormMaterialLista;
        jFormMaterialLista = new JFormMaterialLista(parent, true);
        jFormMaterialLista.setConnection(connection);
        jFormMaterialLista.setIdProveedor(jtfIdproveedor.getText().trim());
        jFormMaterialLista.setIdMaterial(materialAlbaranTableModel.getBagIdMaterial());
        jFormMaterialLista.setVisible(true);
        
        if(jFormMaterialLista.isCanceled()) {
            JFormMaterialEditor jFormMaterialEditor = new JFormMaterialEditor(parent,true);
            jFormMaterialEditor.setConnection(connection);
            jFormMaterialEditor.setId_proveedor(jtfIdproveedor.getText().trim());
            jFormMaterialEditor.setId_material(materialAlbaranTableModel.getBagIdMaterial());
            jFormMaterialEditor.setVisible(true);

            if(!jFormMaterialEditor.isCanceled()) {
                materialAlbaranTableModel.setValueAt(jFormMaterialEditor.getMaterial().getId_material(), row, 0);
            }
        } else {
            Material materialTemp;
            materialTemp = jFormMaterialLista.getMaterial();
            if(materialTemp!=null) {
                materialAlbaranTableModel.setValueAt(materialTemp.getId_material(), row, 0);
            }
        }
    }
    
    private void updateTotal() {
        double suma, iva, total;
        
        suma = materialAlbaranTableModel.calculateSuma();
        iva = 0;
        
        jTMaterial.updateUI();
        
        try {
            iva = Double.parseDouble(jtfIva.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.jtfSuma.setText(Double.toString(suma));
        total = suma * (1+iva/100.0);
//        System.out.println(""+total);
        this.jtfTotal.setText(Double.toString(UtilGestion.round(total)));
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
        jbEditarMaterial = new javax.swing.JButton();
        jbBorrarFila = new javax.swing.JButton();
        jbSubir = new javax.swing.JButton();
        jbBajar = new javax.swing.JButton();
        jbCompararPrecios = new javax.swing.JButton();
        jbActualizarPrecios = new javax.swing.JButton();
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
        setTitle("Albaran Editor");
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jlIdalbaran.setText("Id Albaran");

        jtfIdalbaran.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfIdalbaran.setPreferredSize(new java.awt.Dimension(400, 20));

        jlIdproveedor.setText("Id Proveedor");

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

        jlProveedor.setText("Proveedor");

        jtfProveedor.setEnabled(false);
        jtfProveedor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfProveedor.setPreferredSize(new java.awt.Dimension(400, 20));

        jlReceptor.setText("Receptor");

        jtfReceptor.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfReceptor.setPreferredSize(new java.awt.Dimension(400, 20));

        jlObra.setText("Obra");

        jtfObra.setMinimumSize(new java.awt.Dimension(400, 20));
        jtfObra.setPreferredSize(new java.awt.Dimension(400, 20));

        jlFecha.setText("Fecha");

        jtfFechaDia.setColumns(2);

        jtfFechaMes.setColumns(2);

        jtfFechaAnio.setColumns(4);

        jbEditarMaterial.setText("Editar material");
        jbEditarMaterial.setEnabled(false);
        jbEditarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarMaterialActionPerformed(evt);
            }
        });

        jbBorrarFila.setText("Borrar fila");
        jbBorrarFila.setEnabled(false);
        jbBorrarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarFilaActionPerformed(evt);
            }
        });

        jbSubir.setText("Subir");
        jbSubir.setEnabled(false);
        jbSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSubirActionPerformed(evt);
            }
        });

        jbBajar.setText("Bajar");
        jbBajar.setEnabled(false);
        jbBajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBajarActionPerformed(evt);
            }
        });

        jbCompararPrecios.setText("Comparar precios");
        jbCompararPrecios.setEnabled(false);
        jbCompararPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCompararPreciosActionPerformed(evt);
            }
        });

        jbActualizarPrecios.setText("Actualizar precios");
        jbActualizarPrecios.setEnabled(false);
        jbActualizarPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarPreciosActionPerformed(evt);
            }
        });

        jTMaterial.setCellSelectionEnabled(true);
        jTMaterial.setEnabled(false);
        jTMaterial.setModel(materialAlbaranTableModel);
        jSPMaterial.setViewportView(jTMaterial);
        //jTMaterial.getColumnModel().getColumn(0).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(1).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(2).setWidth(1);
        //jTMaterial.getColumnModel().getColumn(3).setWidth(1000);
        //jTMaterial.updateUI();

        jlSuma.setText("SUMA");

        jtfSuma.setColumns(10);
        jtfSuma.setEditable(false);
        jtfSuma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jlIva.setText("IVA (%)");

        jtfIva.setColumns(3);
        jtfIva.setEditable(false);
        jtfIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfIva.setText("16");

        jlTotal.setText("TOTAL");

        jtfTotal.setColumns(10);
        jtfTotal.setEditable(false);
        jtfTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jbAceptar.setText("Aceptar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jSPMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlSuma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSuma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlIva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlTotal)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(342, Short.MAX_VALUE)
                .addComponent(jbEditarMaterial)
                .addGap(139, 139, 139))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlIdalbaran, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jtfIdalbaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jtfFechaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jbAceptar)
                    .addGap(5, 5, 5)
                    .addComponent(jbCancelar)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(32, Short.MAX_VALUE)
                    .addComponent(jtfIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(76, Short.MAX_VALUE)
                    .addComponent(jtfFechaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(86, Short.MAX_VALUE)
                    .addComponent(jtfFechaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addComponent(jlReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jtfReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(44, Short.MAX_VALUE)
                    .addComponent(jlObra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jtfObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(288, Short.MAX_VALUE)
                    .addComponent(jbBorrarFila)
                    .addGap(129, 129, 129)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(324, Short.MAX_VALUE)
                    .addComponent(jbSubir)
                    .addGap(119, 119, 119)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(334, Short.MAX_VALUE)
                    .addComponent(jbBajar)
                    .addGap(109, 109, 109)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(366, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbCompararPrecios, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbActualizarPrecios, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(99, 99, 99)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jSPMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jbEditarMaterial)
                .addGap(168, 168, 168)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSuma)
                    .addComponent(jtfSuma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlIva)
                    .addComponent(jtfIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTotal)
                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlIdalbaran))
                        .addComponent(jtfIdalbaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(2, 2, 2)
                    .addComponent(jlIdproveedor)
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlProveedor))
                        .addComponent(jtfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlFecha))
                        .addComponent(jtfFechaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(181, 181, 181)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbAceptar)
                        .addComponent(jbCancelar))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(jtfIdproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(229, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(215, Short.MAX_VALUE)
                    .addComponent(jtfFechaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(171, 171, 171)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(225, Short.MAX_VALUE)
                    .addComponent(jtfFechaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(161, 161, 161)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(195, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlReceptor))
                        .addComponent(jtfReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(191, 191, 191)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(205, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jlObra))
                        .addComponent(jtfObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(181, 181, 181)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(148, 148, 148)
                    .addComponent(jbBorrarFila)
                    .addContainerGap(235, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(158, 158, 158)
                    .addComponent(jbSubir)
                    .addContainerGap(225, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(168, 168, 168)
                    .addComponent(jbBajar)
                    .addContainerGap(215, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(178, 178, 178)
                    .addComponent(jbCompararPrecios)
                    .addGap(178, 178, 178)
                    .addComponent(jbActualizarPrecios)
                    .addContainerGap(212, Short.MAX_VALUE)))
        );

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
        this.jbEditarMaterial.setLocation(locX, locY);
        this.jbBorrarFila.setLocation(jbEditarMaterial.getLocation().x+jbEditarMaterial.getWidth() + 10, locY);
        this.jbSubir.setLocation(jbBorrarFila.getLocation().x+jbBorrarFila.getWidth() + 10, locY);
        this.jbBajar.setLocation(jbSubir.getLocation().x+jbSubir.getWidth() + 10, locY);
        this.jbCompararPrecios.setLocation(jbBajar.getLocation().x+jbBajar.getWidth() + 10, locY);
        this.jbActualizarPrecios.setLocation(jbCompararPrecios.getLocation().x+jbCompararPrecios.getWidth() + 10, locY);
        locY += jbEditarMaterial.getHeight();
        
        
        locX=10;
        locY+=10;
        this.jSPMaterial.setLocation(locX, locY);
        this.jSPMaterial.setSize(this.getWidth()-2*locX,this.getHeight()-locY-40-4*this.jbAceptar.getHeight());
        this.jTMaterial.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(1).setPreferredWidth(this.getWidth()-700);
        this.jTMaterial.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(5).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(6).setPreferredWidth(100);
        this.jTMaterial.getColumnModel().getColumn(7).setPreferredWidth(100);
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
        
        
//        this.jBNuevo.setLocation(this.getWidth()/2-this.jBNuevo.getWidth(), 0);
    }//GEN-LAST:event_formComponentResized

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        
        if(javax.swing.JOptionPane.YES_OPTION==javax.swing.JOptionPane.showConfirmDialog(this, "Está seguro que desea salir sin salvar los cambios"))
            dispose();
}//GEN-LAST:event_jbCancelarActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        String sql;
        Statement statement;
        ResultSet resultSet;
        MaterialAlbaran materialAlbaran;

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
//                System.out.println(jtfFechaDia.getText().trim()+"/"+jtfFechaMes.getText().trim()+"/"+jtfFechaAnio.getText().trim());
                return;
            }
            
            sql = "SELECT * FROM ALBARAN WHERE ID_ALBARAN='" +
                  this.jtfIdalbaran.getText().trim() +
                  "' AND ID_PROVEEDOR='" +
                  this.jtfIdproveedor.getText().trim() +
                  "';";
            
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                if(newAlbaran) {
                    javax.swing.JOptionPane.showMessageDialog(this, "El albaran ya existe");
                    return;
                } else {
                    sql = "DELETE FROM ALBARAN WHERE ID_ALBARAN='"+
                          jtfIdalbaran.getText().trim() +
                          "' AND ID_PROVEEDOR='" +
                          jtfIdproveedor.getText().trim() +
                          "';";
//                    System.out.println(sql);
                    statement.executeUpdate(sql);
                }
            } else {
                if(!newAlbaran) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se encuentra el albaran");
                    return;
                }
            }
            
            sql = "INSERT INTO ALBARAN (ID_ALBARAN, ID_PROVEEDOR, DIA, MES, ANIO, DESCUENTO, RECEPTOR, OBRA) VALUES ('" +
                  jtfIdalbaran.getText().trim() +
                  "', '" +
                  jtfIdproveedor.getText().trim() +
                  "', " +
                  jtfFechaDia.getText().trim() +
                  ", " +
                  jtfFechaMes.getText().trim() +
                  ", " +
                  jtfFechaAnio.getText().trim() +
                  ", " +
                  0 +
                  ", '" +
                  jtfReceptor.getText().trim() +
                  "', '" +
                  jtfObra.getText().trim() +
                  "');";
            statement.executeUpdate(sql);
            
            sql = "DELETE * FROM MATERIAL_ALBARAN WHERE ID_PROVEEDOR='" +
                  jtfIdproveedor.getText().trim() +
                  "' AND ID_ALBARAN='" +
                  jtfIdalbaran.getText().trim() +
                  "';";
            statement.executeUpdate(sql);
            
            for(int i=0;i<materialAlbaranTableModel.getRowCount()-1;i++) {
                String id_material;
                double cantidad,precio,descuento,raee;
                
                try {
//                    id_material = (String)materialAlbaranTableModel.getValueAt(i,0);
//                    cantidad = ((Double)materialAlbaranTableModel.getValueAt(i,2)).doubleValue();
//                    precio = ((Double)materialAlbaranTableModel.getValueAt(i,3)).doubleValue();
//                    descuento = ((Double)materialAlbaranTableModel.getValueAt(i,4)).doubleValue();     
//                    raee = ((Double)materialAlbaranTableModel.getValueAt(i,5)).doubleValue();  
                    materialAlbaran = (MaterialAlbaran)materialAlbaranTableModel.getMaterialAlbaran(i);
                    id_material = materialAlbaran.getMaterial().getId_material();
                    cantidad = materialAlbaran.getCantidad();
                    precio = materialAlbaran.getPrecio();
                    descuento = materialAlbaran.getDescuento();     
                    raee = materialAlbaran.getRaee();  
                }catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Algun dato es incorrecto: "+i);
                    ex.printStackTrace();
                    return;
                }
                
                sql = "SELECT * FROM MATERIAL WHERE ID_MATERIAL='" +
                      id_material +
                      "' AND ID_PROVEEDOR='" +
                      jtfIdproveedor.getText().trim() +
                      "';";
                resultSet = statement.executeQuery(sql);
                if(!resultSet.next()) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Algun material no es correcto");
//                    System.out.println(sql);
                    return;    
                } else {
                    sql = "INSERT INTO MATERIAL_ALBARAN (ID_ALBARAN, ID_PROVEEDOR, FILA, ID_MATERIAL, CANTIDAD, PRECIO, RAEE, DESCUENTO) VALUES ('" +
                          jtfIdalbaran.getText().trim() +
                          "', '" +
                          jtfIdproveedor.getText().trim() +
                          "', " +
                          i +
                          ", '" +
                          id_material +
                          "', " +
                          cantidad +
                          ", " +
                          precio +
                          ", " +
                          raee +
                          ", " +
                          descuento +
                          ");";
//                    System.out.println(sql);
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

    private void jbEditarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarMaterialActionPerformed
        int row;
        Material material;
        JFormMaterialEditor jFormMaterialEditor;
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        if(row<-1)
            return;
        
        material = materialAlbaranTableModel.getMaterialAlbaran(row).getMaterial();
        if(material==null || material.isEmpty()) {
            return;
        }
        
        material = getMaterial(material.getId_material(),material.getId_proveedor());
        
        jFormMaterialEditor = new JFormMaterialEditor(null,true);
        jFormMaterialEditor.setConnection(connection);
        jFormMaterialEditor.setMaterial(material);
        jFormMaterialEditor.setVisible(true);
        
        
}//GEN-LAST:event_jbEditarMaterialActionPerformed

    private void jbBorrarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarFilaActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        if(row>0) {
            materialAlbaranTableModel.deleteMaterialAlbaran(row);
            jTMaterial.updateUI();
        }
        
}//GEN-LAST:event_jbBorrarFilaActionPerformed

    private void jbSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSubirActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        
        if(row>0) {
            materialAlbaranTableModel.subir(row);
            jTMaterial.updateUI();
        }
             
                
}//GEN-LAST:event_jbSubirActionPerformed

    private void jbBajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBajarActionPerformed
        int row;
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        
        if(row>-1) {
            materialAlbaranTableModel.bajar(row);
            jTMaterial.updateUI();
        }
}//GEN-LAST:event_jbBajarActionPerformed

    private void jbCompararPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCompararPreciosActionPerformed
        JFormHistorialMaterial jFormHistorialMaterial;
        int row;
        String id_material;
        
        row = jTMaterial.getSelectedRow();
        row = jTMaterial.convertRowIndexToModel(row);
        
        if(row<0)
            return;
           
        id_material = (String)materialAlbaranTableModel.getValueAt(row, 0); 
        id_material = id_material.trim();
        if(id_material.equals(""))
            return;
        
        jFormHistorialMaterial = new JFormHistorialMaterial(null,true);
        jFormHistorialMaterial.setConnection(connection);
        jFormHistorialMaterial.setMaterial(id_material, jtfIdproveedor.getText().trim());
        jFormHistorialMaterial.setVisible(true);
        
}//GEN-LAST:event_jbCompararPreciosActionPerformed

    private void jbActualizarPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarPreciosActionPerformed
        int[] rows;
        int row;
        Material material;
        String sql;
        Statement statement;
        ResultSet resultSet;
        String sPrecio, sDescuento, sRaee;
        
        rows = jTMaterial.getSelectedRows();
        if(rows.length<1)
            return;
        
        
        
        try {
            statement = this.connection.createStatement();
            for(int i=0;i<rows.length;i++) {
            
                row = jTMaterial.convertRowIndexToModel(rows[i]);


                material = materialAlbaranTableModel.getMaterialAlbaran(row).getMaterial();
                if(material!=null && !material.isEmpty()) {
       
                    sPrecio = (String)materialAlbaranTableModel.getValueAt(row, 3);
                    if(sPrecio.trim().equals(""))
                        sPrecio = "0";

                    sDescuento = (String)materialAlbaranTableModel.getValueAt(row, 4);
                    if(sDescuento.trim().equals(""))
                        sDescuento = "0";

                    sRaee = (String)materialAlbaranTableModel.getValueAt(row, 5);
                    if(sRaee.trim().equals(""))
                        sRaee = "0";

                    sql = "UPDATE MATERIAL SET PRECIO = " +
                          sPrecio +
                          ", DESCUENTO = " +
                          sDescuento +
                          ", RAEE = " +
                          sRaee +
                          " WHERE ID_MATERIAL = '"+
                          material.getId_material() +
                          "' AND ID_PROVEEDOR = '" +
                          material.getId_proveedor() +
                          "';";
                    System.out.println(sql);

                    statement.executeUpdate(sql);
                }
            }
            
            statement.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
}//GEN-LAST:event_jbActualizarPreciosActionPerformed
    
    
    private void proveedorChanged() {
        String sql;
        Statement statement;
        ResultSet resultSet;
        this.materialAlbaranTableModel.setId_proveedor(this.jtfIdproveedor.getText().trim());
//        System.out.println("Proveedor: " + this.materialAlbaranTableModel.getId_proveedor());
        
        jtfProveedor.setText("");
        if(this.materialAlbaranTableModel.getId_proveedor().trim().equals("")) 
            return;
            
        sql = "SELECT * FROM PROVEEDOR WHERE ID_PROVEEDOR='" + 
              materialAlbaranTableModel.getId_proveedor() +
              "';";
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
                jbActualizarPrecios.setEnabled(true);
                jbEditarMaterial.setEnabled(true);
                jbSubir.setEnabled(true);
            } else {
                jTMaterial.setEnabled(false);
                jbBorrarFila.setEnabled(false);
                jbBajar.setEnabled(false);
                jbCompararPrecios.setEnabled(false);
                jbActualizarPrecios.setEnabled(false);
                jbEditarMaterial.setEnabled(false);
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
                new JFormAlbaranEditor(null,true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jSPMaterial;
    private javax.swing.JTable jTMaterial;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbActualizarPrecios;
    private javax.swing.JButton jbBajar;
    private javax.swing.JButton jbBorrarFila;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbCompararPrecios;
    private javax.swing.JButton jbEditarMaterial;
    private javax.swing.JButton jbSubir;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlIdalbaran;
    private javax.swing.JLabel jlIdproveedor;
    private javax.swing.JLabel jlIva;
    private javax.swing.JLabel jlObra;
    private javax.swing.JLabel jlProveedor;
    private javax.swing.JLabel jlReceptor;
    private javax.swing.JLabel jlSuma;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JTextField jtfFechaAnio;
    private javax.swing.JTextField jtfFechaDia;
    private javax.swing.JTextField jtfFechaMes;
    private javax.swing.JTextField jtfIdalbaran;
    private javax.swing.JTextField jtfIdproveedor;
    private javax.swing.JTextField jtfIva;
    private javax.swing.JTextField jtfObra;
    private javax.swing.JTextField jtfProveedor;
    private javax.swing.JTextField jtfReceptor;
    private javax.swing.JTextField jtfSuma;
    private javax.swing.JTextField jtfTotal;
    // End of variables declaration//GEN-END:variables
    
}

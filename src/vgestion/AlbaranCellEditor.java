/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import javax.swing.*;

/**
 *
 * @author Juan Luis
 */
public class AlbaranCellEditor extends javax.swing.DefaultCellEditor {
    
    public boolean isCellEditable(java.util.EventObject anEvent) {
        return true;
    }

    public AlbaranCellEditor() {
        super (new JTextField());
    }
    
}

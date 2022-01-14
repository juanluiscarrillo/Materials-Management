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
public class AlbaranFacturaListModel extends javax.swing.AbstractListModel{
    Vector vFactura;
    
    public AlbaranFacturaListModel(Vector vFactura) {
        this.vFactura = vFactura;
    }
    
    public int getSize() { 
        return vFactura.size(); 
    }
    
    public Object getElementAt(int i) { 
        return vFactura.elementAt(i); 
    }

}

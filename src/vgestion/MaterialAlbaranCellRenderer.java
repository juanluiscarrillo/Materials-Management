/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vgestion;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author Juan Luis
 */
public class MaterialAlbaranCellRenderer extends JTextField implements TableCellRenderer  {

    public MaterialAlbaranCellRenderer() {
        setBorder(null);
        setSelectedTextColor(java.awt.Color.BLUE);
        setFocusable(true);
    }

    
    
    public Component getTableCellRendererComponent(JTable table, 
                                                   Object value, 
                                                   boolean isSelected, 
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {

        
        if(value!=null)
            setText(value.toString());
        
           
         return this;
      } 
    

}

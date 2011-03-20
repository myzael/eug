/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WarsDialog.java
 *
 * Created on Feb 11, 2011, 11:55:29 PM
 */

package editor;

import eug.shared.GenericObject;
import eug.shared.Style;
import eug.specific.clausewitz.ClausewitzDataSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michael
 */
public class WarsDialog extends javax.swing.JDialog {

    private static final double javaVersion =
            Double.parseDouble(System.getProperty("java.version").substring(0, 3));
    private static final boolean supportsRowSorter =
            (javaVersion >= 1.6);

    private List<GenericObject> wars;

    /** Creates new form WarsDialog */
    public WarsDialog(final java.awt.Frame parent, ClausewitzDataSource dataSource) {
        super(parent, false);
        initComponents();

        wars = dataSource.getWars();
        int index = 1;
        for (final GenericObject war : wars) {
            Vector<Object> row = new Vector<Object>();
            row.add(index++);
            if (war.name.equals("active_war"))
                row.add("(Active) " + war.getString("name"));
            else
                row.add(war.getString("name"));
            if (war.getChild("history") != null && !war.getChild("history").isEmpty()) {
                row.add(war.getChild("history").getChild(0).name);
            } else if (Character.isDigit(war.children.get(0).name.charAt(0))) { // assume a numerical child is a date
                row.add(war.children.get(0).name);
            } else {
                row.add("Unknown");
            }
            row.add(war.getString("action"));
            row.add(getAttackers(war));
            row.add(getDefenders(war));

            ((DefaultTableModel)warTable.getModel()).addRow(row);
        }

        if (supportsRowSorter) {
            warTable.setAutoCreateRowSorter(true);
        }
        
        warTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {
                    int row = warTable.rowAtPoint(e.getPoint());
                    Integer index = (Integer) warTable.getModel().getValueAt(row, 0); // the table might have been sorted and names aren't necessarily unique, so how to find which war to edit?
                    GenericObject war = wars.get(index-1);
                    EditorDialog ed = new EditorDialog(parent, war.getString("name"), war.toString(Style.EU3_SAVE_GAME));
                    ed.setVisible(true);
                }
            }
        });
        
        setLocationRelativeTo(null);
    }
    
    private String getAttackers(GenericObject war) {
        StringBuilder ret = new StringBuilder();
        for (String attacker : war.getStrings("attacker"))
            ret.append(Text.getText(attacker)).append(", ");

        if (ret.length() > 2)
            ret.delete(ret.length() - 2, ret.length());
        return ret.toString();
    }
    
    private String getDefenders(GenericObject war) {
        StringBuilder ret = new StringBuilder();
        for (String defender : war.getStrings("defender"))
            ret.append(Text.getText(defender)).append(", ");

        if (ret.length() > 2)
            ret.delete(ret.length() - 2, ret.length());
        return ret.toString();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        warTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Wars");

        jLabel1.setText("Double-click to view war details. Wars are currently read-only.");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        scrollPane.setPreferredSize(new java.awt.Dimension(642, 442));

        warTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "War Name", "Start Year", "End Year", "Attackers", "Defenders"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(warTable);
        warTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        warTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        warTable.getColumnModel().getColumn(3).setPreferredWidth(40);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable warTable;
    // End of variables declaration//GEN-END:variables

    private class War {
        private GenericObject data;
        
        War(GenericObject data) {
            this.data = data;
        }
    }
}

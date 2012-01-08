/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GroupListCellRenderer.java
 *
 * Created on 21.12.2011, 08:01:06
 */
package de.tor.tribes.ui.renderer;

import de.tor.tribes.ui.components.GroupSelectionList;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author jejkal
 */
public class GroupListCellRenderer extends javax.swing.JPanel implements ListCellRenderer {

    /** Creates new form GroupListCellRenderer */
    public GroupListCellRenderer() {
        initComponents();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object pValue, int pIndex, boolean pSelected, boolean pHasFocus) {
        if (pSelected) {
            setBackground(list.getSelectionForeground());
            jLabel1.setBackground(list.getSelectionBackground());
            jLabel2.setBackground(list.getSelectionBackground());
            jLabel1.setForeground(list.getSelectionForeground());
            jLabel2.setForeground(list.getSelectionForeground());
        } else {
            // setForeground(list.getBackground());
            jLabel1.setBackground(Color.WHITE);
            jLabel2.setBackground(Color.WHITE);
            setOpaque(false);
            jLabel2.setForeground(new java.awt.Color(0, 153, 255));
            jLabel1.setForeground(list.getForeground());
        }

        GroupSelectionList.ListItem item = (GroupSelectionList.ListItem) pValue;
        jLabel1.setText(item.getTag().toString());
        if (item.isSpecial()) {
            if (item.getState() == GroupSelectionList.ListItem.RELATION_TYPE.DISABLED) {
                jLabel2.setText("OHNE");
            } else {
                jLabel2.setText("->");
            }
        } else {
            switch (item.getState()) {
                case NOT:
                    jLabel2.setText("NICHT");
                    break;
                case AND:
                    jLabel2.setText("UND");
                    break;
                case OR:
                    jLabel2.setText("ODER");
                    break;
                default:
                    jLabel2.setText("OHNE");
            }
        }
        return this;
    }

    public void view() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane p = new JScrollPane();
        p.setViewportView(new GroupSelectionList(""));
        f.getContentPane().add(p);
        f.pack();
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
            }
        });

        f.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel1, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("oder");
        jLabel2.setMaximumSize(new java.awt.Dimension(30, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(30, 16));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(30, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jLabel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

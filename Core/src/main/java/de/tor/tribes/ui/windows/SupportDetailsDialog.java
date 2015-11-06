/* 
 * Copyright 2015 Torridity.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tor.tribes.ui.windows;

import de.tor.tribes.types.SupportType;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.ui.models.SupportTableModel;
import de.tor.tribes.ui.renderer.NumberFormatCellRenderer;
import de.tor.tribes.ui.renderer.SupportDirectionRenderer;
import de.tor.tribes.ui.renderer.SupportTableHeaderRenderer;
import de.tor.tribes.ui.renderer.TroopTableHeaderRenderer;
import de.tor.tribes.util.BrowserCommandSender;
import de.tor.tribes.util.Constants;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author Torridity
 */
public class SupportDetailsDialog extends javax.swing.JDialog {

    private static Logger logger = Logger.getLogger("SupportDetailsDialog");

    /**
     * Creates new form SupportDetailsDialog
     */
    public SupportDetailsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setupAndShow(List<Village> pVillages) {
        jxSupportsTable.setHighlighters(HighlighterFactory.createAlternateStriping(Constants.DS_ROW_A, Constants.DS_ROW_B));
        jxSupportsTable.setColumnControlVisible(true);
        jxSupportsTable.setDefaultRenderer(Number.class, new NumberFormatCellRenderer());
        jxSupportsTable.getTableHeader().setDefaultRenderer(new SupportTableHeaderRenderer());
        jxSupportsTable.setDefaultRenderer(SupportType.DIRECTION.class, new SupportDirectionRenderer());
        SupportTableModel supportModel = new SupportTableModel(pVillages);
        jxSupportsTable.setModel(supportModel);
        setVisible(true);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jxSupportsTable = new org.jdesktop.swingx.JXTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unterstützungsdetails");

        jxSupportsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jxSupportsTable);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/att_remove.png"))); // NOI18N
        jButton1.setText("Schließen");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fireHideDetailsEvent(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/center_ingame.png"))); // NOI18N
        jButton2.setToolTipText("Das gewählte Dorf im Spiel zentrieren");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fireCenterInGameEvent(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/center_24x24.png"))); // NOI18N
        jButton3.setToolTipText("Das gewählte Dorf auf der Hauptkarte zentrieren");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fireCenterOnMapEvent(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fireHideDetailsEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireHideDetailsEvent
        dispose();
    }//GEN-LAST:event_fireHideDetailsEvent

    private void fireCenterInGameEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCenterInGameEvent
        int row = jxSupportsTable.getSelectedRow();
        if (row == -1) {
            return;
        }
        Village v = null;
        try {
            v = (Village) ((SupportTableModel) jxSupportsTable.getModel()).getValueAt(jxSupportsTable.convertRowIndexToModel(row), 2);
        } catch (Exception e) {
            logger.error("Failed to center village in game", e);
            return;
        }
        BrowserCommandSender.centerVillage(v);
    }//GEN-LAST:event_fireCenterInGameEvent

    private void fireCenterOnMapEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireCenterOnMapEvent
        int row = jxSupportsTable.getSelectedRow();
        if (row == -1) {
            return;
        }
        Village v = null;
        try {
            v = (Village) ((SupportTableModel) jxSupportsTable.getModel()).getValueAt(jxSupportsTable.convertRowIndexToModel(row), 2);
        } catch (Exception e) {
            logger.error("Failed to center village on map", e);
            return;
        }
        DSWorkbenchMainFrame.getSingleton().centerVillage(v);

    }//GEN-LAST:event_fireCenterOnMapEvent

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SupportDetailsDialog dialog = new SupportDetailsDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable jxSupportsTable;
    // End of variables declaration//GEN-END:variables
}

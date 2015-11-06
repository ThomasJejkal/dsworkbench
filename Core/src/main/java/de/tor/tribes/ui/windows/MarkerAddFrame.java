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

import de.tor.tribes.types.Marker;
import java.awt.Color;
import net.java.dev.colorchooser.ColorChooser;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.util.Constants;
import de.tor.tribes.util.mark.MarkerManager;

/**
 * @author  Torridity
 */
public class MarkerAddFrame extends javax.swing.JFrame {

    private Village mVillage = null;
    private ColorChooser mTribeColorChooser = new ColorChooser();
    private ColorChooser mAllyColorChooser = new ColorChooser();

    /** Creates new form MarkerAddFrame */
    public MarkerAddFrame() {
        initComponents();
        mTribeColorChooser.setColor(Color.WHITE);
        mAllyColorChooser.setColor(Color.WHITE);
        jTribeColorPanel.add(mTribeColorChooser);
        jAllyColorPanel.add(mAllyColorChooser);
        setAlwaysOnTop(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOKButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jTribeColorPanel = new javax.swing.JPanel();
        jMarkAlly = new javax.swing.JCheckBox();
        jMarkTribe = new javax.swing.JCheckBox();
        jAllyColorPanel = new javax.swing.JPanel();
        jTribeName = new javax.swing.JLabel();
        jAllyName = new javax.swing.JLabel();
        jNapButton = new javax.swing.JButton();
        jBndButton = new javax.swing.JButton();
        jEnemyButton = new javax.swing.JButton();

        setTitle("Markierung hinzufügen");
        setResizable(false);

        jOKButton.setBackground(new java.awt.Color(239, 235, 223));
        jOKButton.setText("OK");
        jOKButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireAddMarkEvent(evt);
            }
        });

        jCancelButton.setBackground(new java.awt.Color(239, 235, 223));
        jCancelButton.setText("Abbrechen");
        jCancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCancelButtonfireAbortMarkEvent(evt);
            }
        });

        jTribeColorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTribeColorPanel.setMaximumSize(new java.awt.Dimension(238, 31));
        jTribeColorPanel.setMinimumSize(new java.awt.Dimension(238, 31));
        jTribeColorPanel.setPreferredSize(new java.awt.Dimension(238, 31));
        jTribeColorPanel.setLayout(new java.awt.BorderLayout());

        jMarkAlly.setText("Stamm markieren");
        jMarkAlly.setOpaque(false);

        jMarkTribe.setSelected(true);
        jMarkTribe.setText("Spieler markieren");
        jMarkTribe.setOpaque(false);

        jAllyColorPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jAllyColorPanel.setMaximumSize(new java.awt.Dimension(164, 31));
        jAllyColorPanel.setMinimumSize(new java.awt.Dimension(164, 31));
        jAllyColorPanel.setPreferredSize(new java.awt.Dimension(164, 31));
        jAllyColorPanel.setLayout(new java.awt.BorderLayout());

        jTribeName.setText("jLabel1");

        jAllyName.setText("jLabel1");

        jNapButton.setBackground(new java.awt.Color(127, 0, 127));
        jNapButton.setForeground(new java.awt.Color(255, 255, 255));
        jNapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/nap.png"))); // NOI18N
        jNapButton.setToolTipText("Stamm als NAP markieren");
        jNapButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jNapButton.setMaximumSize(new java.awt.Dimension(31, 31));
        jNapButton.setMinimumSize(new java.awt.Dimension(31, 31));
        jNapButton.setPreferredSize(new java.awt.Dimension(31, 31));
        jNapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireSetDiplomacyColorEvent(evt);
            }
        });

        jBndButton.setBackground(new java.awt.Color(0, 160, 244));
        jBndButton.setForeground(new java.awt.Color(255, 255, 255));
        jBndButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/handshake.png"))); // NOI18N
        jBndButton.setToolTipText("Stamm als BND markieren");
        jBndButton.setIconTextGap(0);
        jBndButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jBndButton.setMaximumSize(new java.awt.Dimension(31, 31));
        jBndButton.setMinimumSize(new java.awt.Dimension(31, 31));
        jBndButton.setPreferredSize(new java.awt.Dimension(31, 31));
        jBndButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireSetDiplomacyColorEvent(evt);
            }
        });

        jEnemyButton.setBackground(new java.awt.Color(255, 0, 0));
        jEnemyButton.setForeground(new java.awt.Color(255, 255, 255));
        jEnemyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/enemy.png"))); // NOI18N
        jEnemyButton.setToolTipText("Stamm als Feind markieren");
        jEnemyButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jEnemyButton.setMaximumSize(new java.awt.Dimension(31, 31));
        jEnemyButton.setMinimumSize(new java.awt.Dimension(31, 31));
        jEnemyButton.setPreferredSize(new java.awt.Dimension(31, 31));
        jEnemyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireSetDiplomacyColorEvent(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTribeColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jMarkTribe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTribeName, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jMarkAlly)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jAllyName, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addComponent(jOKButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jAllyColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEnemyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jNapButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBndButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMarkTribe)
                    .addComponent(jTribeName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTribeColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMarkAlly)
                    .addComponent(jAllyName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jBndButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jNapButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jAllyColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCancelButton)
                            .addComponent(jOKButton)))
                    .addComponent(jEnemyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setVillage(Village pVillage) {
        mVillage = pVillage;

        Marker m = MarkerManager.getSingleton().getMarker(mVillage.getTribe());
        if (m != null) {
            mTribeColorChooser.setColor(m.getMarkerColor());
        } else {
            mTribeColorChooser.setColor(Color.WHITE);
        }

        try {
            jTribeName.setText("(" + mVillage.getTribe() + ")");
            jAllyName.setText("(" + mVillage.getTribe().getAlly().getTag() + ")");
            m = MarkerManager.getSingleton().getMarker(mVillage.getTribe().getAlly());
            if (m != null) {
                mAllyColorChooser.setColor(m.getMarkerColor());
            } else {
                mAllyColorChooser.setColor(Color.WHITE);
            }
            jMarkAlly.setEnabled(true);
        } catch (Exception e) {
            jAllyName.setText("kein Stamm");
            jMarkAlly.setSelected(false);
            jMarkAlly.setEnabled(false);
        }
    }

    public void setAllyOnly() {
        jMarkTribe.setSelected(false);
        jMarkTribe.setEnabled(false);
        jTribeColorPanel.setEnabled(false);
        jTribeColorPanel.removeAll();
        jAllyColorPanel.add(mAllyColorChooser);
        jTribeName.setText("------");
        jMarkAlly.setSelected(true);
    }

    public void setTribeOnly() {
        jMarkAlly.setSelected(false);
        jMarkAlly.setEnabled(false);
        jAllyColorPanel.setEnabled(false);
        jAllyColorPanel.removeAll();
        jTribeColorPanel.add(mTribeColorChooser);
        jAllyName.setText("------");
        jMarkTribe.setSelected(true);
    }

private void fireAddMarkEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireAddMarkEvent
    setVisible(false);
    try {
        if (jMarkTribe.isSelected() && !jMarkAlly.isSelected()) {
            MarkerManager.getSingleton().addMarker(mVillage.getTribe(), mTribeColorChooser.getColor());
        } else if (!jMarkTribe.isSelected() && jMarkAlly.isSelected()) {
            MarkerManager.getSingleton().addMarker(mVillage.getTribe().getAlly(), mAllyColorChooser.getColor());
        } else {
            MarkerManager.getSingleton().addMarker(mVillage.getTribe(), mTribeColorChooser.getColor(), mVillage.getTribe().getAlly(), mAllyColorChooser.getColor());
        }
    } catch (NullPointerException npe) {
        //ignore
    }
    MarkerManager.getSingleton().revalidate(true);

}//GEN-LAST:event_fireAddMarkEvent

private void jCancelButtonfireAbortMarkEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCancelButtonfireAbortMarkEvent
    setVisible(false);
}//GEN-LAST:event_jCancelButtonfireAbortMarkEvent

private void fireSetDiplomacyColorEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireSetDiplomacyColorEvent
    if (evt.getSource() == jNapButton) {
        mAllyColorChooser.setColor(Constants.NAP_MARKER);
    } else if (evt.getSource() == jBndButton) {
        mAllyColorChooser.setColor(Constants.ALLY_MARKER);
    } else {
        mAllyColorChooser.setColor(Constants.ENEMY_MARKER);
    }
}//GEN-LAST:event_fireSetDiplomacyColorEvent
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jAllyColorPanel;
    private javax.swing.JLabel jAllyName;
    private javax.swing.JButton jBndButton;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JButton jEnemyButton;
    private javax.swing.JCheckBox jMarkAlly;
    private javax.swing.JCheckBox jMarkTribe;
    private javax.swing.JButton jNapButton;
    private javax.swing.JButton jOKButton;
    private javax.swing.JPanel jTribeColorPanel;
    private javax.swing.JLabel jTribeName;
    // End of variables declaration//GEN-END:variables
}

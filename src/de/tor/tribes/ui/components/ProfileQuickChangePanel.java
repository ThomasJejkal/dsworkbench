/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProfileQuickChangePanel.java
 *
 * Created on Jan 4, 2012, 11:46:38 PM
 */
package de.tor.tribes.ui.components;

import de.tor.tribes.types.UserProfile;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.ProfileManager;
import de.tor.tribes.util.interfaces.ProfileManagerListener;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Torridity
 */
public class ProfileQuickChangePanel extends javax.swing.JPanel implements ProfileManagerListener {

    /** Creates new form ProfileQuickChangePanel */
    public ProfileQuickChangePanel() {
        initComponents();
        ProfileManager.getSingleton().addProfileManagerListener(this);
        fireProfilesLoadedEvent();
    }

    @Override
    public void fireProfilesLoadedEvent() {
        UserProfile[] profiles = ProfileManager.getSingleton().getProfiles(GlobalOptions.getSelectedServer());

        DefaultComboBoxModel model = new DefaultComboBoxModel(new Object[]{"Standard"});
        if (profiles != null && profiles.length > 0) {
            for (UserProfile profile : profiles) {
                model.addElement(profile);
            }
        }
        jProfileBox.setModel(model);
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

        jLabel11 = new javax.swing.JLabel();
        jProfileBox = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Profil-Schnellauswahl");
        jLabel11.setMaximumSize(new java.awt.Dimension(120, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(120, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(120, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        add(jLabel11, gridBagConstraints);

        jProfileBox.setToolTipText("Erlaubt die Schnellauswahl des Benutzerprofils mit dem Transporte in den Browser übertragen werden");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 5);
        add(jProfileBox, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public UserProfile getSelectedProfile() {
        Object o = jProfileBox.getSelectedItem();
        if (o instanceof UserProfile) {
            return (UserProfile) o;
        }
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JComboBox jProfileBox;
    // End of variables declaration//GEN-END:variables
}

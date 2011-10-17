/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SourcePanel.java
 *
 * Created on Oct 14, 2011, 5:47:34 PM
 */
package de.tor.tribes.ui.wiz.wap;

import java.awt.BorderLayout;
import java.util.Map;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPanel;
import org.netbeans.spi.wizard.WizardPanelNavResult;

/**
 *
 * @author Torridity
 */
public class SourcePanel extends javax.swing.JPanel implements WizardPanel {

    private static SourcePanel singleton = null;

    public static synchronized SourcePanel getSingleton() {
        if (singleton == null) {
            singleton = new SourcePanel();
        }
        return singleton;
    }

    /** Creates new form SourcePanel */
    SourcePanel() {
        initComponents();
    }

    protected void setupForAttack(boolean pValue) {
        if (pValue) {
            removeAll();
            add(AttackSourcePanel.getSingleton(), BorderLayout.CENTER);
        } else {
            removeAll();
            add(DefenseSourcePanel.getSingleton(), BorderLayout.CENTER);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public WizardPanelNavResult allowNext(String string, Map map, Wizard wizard) {
        return WizardPanelNavResult.REMAIN_ON_PAGE;
    }

    @Override
    public WizardPanelNavResult allowBack(String string, Map map, Wizard wizard) {
        return WizardPanelNavResult.PROCEED;

    }

    @Override
    public WizardPanelNavResult allowFinish(String string, Map map, Wizard wizard) {
        return WizardPanelNavResult.PROCEED;
    }
}

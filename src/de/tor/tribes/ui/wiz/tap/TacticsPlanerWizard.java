/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.wiz.tap;

import de.tor.tribes.io.DataHolder;
import de.tor.tribes.ui.wiz.dep.DefensePlanerWizard;
import de.tor.tribes.util.GlobalOptions;
import de.tor.tribes.util.ProfileManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;

/**
 *
 * @author Torridity
 */
public class TacticsPlanerWizard extends WizardPanelProvider {

    private static final String ID_WELCOME = "welcome-id";
    private static final String ID_SOURCE = "source-id";
    private static final String ID_FILTER = "filter-id";
    private static final String ID_TARGET = "target-id";
    private static final String ID_TIME = "time-id";
    private static final String ID_VALIDATE = "validate-id";
    private static final String ID_CALCULATION = "calculation-id";
    private static final String ID_FINISH = "finish-id";
    private static JFrame parent = null;

    public TacticsPlanerWizard() {
        super("DS Workbench - Taktikplaner",
                new String[]{ID_WELCOME, ID_SOURCE, ID_FILTER, ID_TARGET, ID_TIME, ID_VALIDATE, ID_CALCULATION, ID_FINISH},
                new String[]{"Willkommen", "Herkunft", "Herkunft filtern", "Ziel", "Zeiteinstellungen", "Überprüfung", "Berechnung", "Fertigstellung"});
    }

    @Override
    protected JComponent createPanel(WizardController wc, String string, Map map) {
        if (string.equals(ID_WELCOME)) {
            return WelcomePanel.getSingleton();
        } else if (string.equals(ID_SOURCE)) {
            AttackSourcePanel.getSingleton().setController(wc);
            return AttackSourcePanel.getSingleton();
        } else if (string.equals(ID_FILTER)) {
            AttackSourceFilterPanel.getSingleton().setController(wc);
            return AttackSourceFilterPanel.getSingleton();
        } else if (string.equals(ID_TARGET)) {
            AttackTargetPanel.getSingleton().setController(wc);
            return AttackTargetPanel.getSingleton();
        } else if (string.equals(ID_TIME)) {
            TimeSettingsPanel.getSingleton().setController(wc);
            return TimeSettingsPanel.getSingleton();
        } else if (string.equals(ID_VALIDATE)) {
            ValidationPanel.getSingleton().setController(wc);
            return ValidationPanel.getSingleton();
        } else if (string.equals(ID_CALCULATION)) {
            AttackCalculationPanel.getSingleton().setController(wc);
            return AttackCalculationPanel.getSingleton();
        } else if (string.equals(ID_FINISH)) {
            return AttackFinishPanel.getSingleton();
        }
        return null;
    }

    public static JFrame getFrame() {
        return parent;
    }

    public static void show() {
        if (parent != null) {
            parent.toFront();
            return;
        }
        parent = new JFrame();
        parent.setTitle("Angriffsplaner");
        WizardPanelProvider provider = new TacticsPlanerWizard();
        Wizard wizard = provider.createWizard();
        parent.getContentPane().setLayout(new BorderLayout());
        WizardDisplayer.installInContainer(parent, BorderLayout.CENTER, wizard, null, null, null);
        parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        parent.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                parent = null;
            }
        });
        parent.pack();
        parent.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }


        Logger.getRootLogger().addAppender(new ConsoleAppender(new org.apache.log4j.PatternLayout("%d - %-5p - %-20c (%C [%L]) - %m%n")));
        GlobalOptions.setSelectedServer("de43");
        ProfileManager.getSingleton().loadProfiles();
        GlobalOptions.setSelectedProfile(ProfileManager.getSingleton().getProfiles("de43")[0]);
        DataHolder.getSingleton().loadData(false);
        GlobalOptions.loadUserData();

        TacticsPlanerWizard provider = new TacticsPlanerWizard();
        Wizard wizard = provider.createWizard();
        System.out.println(WizardDisplayer.showWizard(wizard));
    }
}
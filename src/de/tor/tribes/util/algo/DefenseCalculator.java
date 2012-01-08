/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.util.algo;

import de.tor.tribes.io.UnitHolder;
import de.tor.tribes.types.Defense;
import de.tor.tribes.types.DefenseInformation;
import de.tor.tribes.ui.views.DSWorkbenchSOSRequestAnalyzer;
import de.tor.tribes.ui.wiz.dep.DefenseAnalysePanel;
import de.tor.tribes.ui.wiz.dep.DefenseFilterPanel;
import de.tor.tribes.ui.wiz.dep.DefenseCalculationSettingsPanel;
import de.tor.tribes.ui.wiz.dep.DefenseSourcePanel.SupportSourceElement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Torridity
 */
public class DefenseCalculator extends Thread {

    private boolean isRunning = false;
    private boolean aborted = false;
    private boolean multiSupport = false;

    public DefenseCalculator() {
        setDaemon(true);
    }

    public void setAllowMultiSupport(boolean pValue) {
        multiSupport = pValue;
    }

    @Override
    public void run() {
        isRunning = true;
        DefenseInformation[] defenses = DefenseAnalysePanel.getSingleton().getAllElements();
        Hashtable<de.tor.tribes.types.ext.Village, Integer> splits = new Hashtable<de.tor.tribes.types.ext.Village, Integer>();
        List<de.tor.tribes.types.ext.Village> usedVillages = new LinkedList<de.tor.tribes.types.ext.Village>();

        for (SupportSourceElement element : DefenseFilterPanel.getSingleton().getFilteredElements()) {
            splits.put(element.getVillage(), element.getSupports());
            usedVillages.add(element.getVillage());
        }

        UnitHolder unit = DSWorkbenchSOSRequestAnalyzer.getSingleton().getSlowestUnit();
        Enumeration<de.tor.tribes.types.ext.Village> villageKeys = splits.keys();
        while (villageKeys.hasMoreElements()) {
            de.tor.tribes.types.ext.Village v = villageKeys.nextElement();
            if (!usedVillages.contains(v)) {
                splits.remove(v);
            }
        }

        DefenseBruteForce algo = new DefenseBruteForce(multiSupport);
        algo.calculateDefenses(splits, defenses, unit, this);
        isRunning = false;
        DefenseCalculationSettingsPanel.getSingleton().notifyCalculationFinished();
    }

    public void abort() {
        aborted = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void logMessage(String pMessage) {
        DefenseCalculationSettingsPanel.getSingleton().notifyStatusUpdate(pMessage);
    }
}

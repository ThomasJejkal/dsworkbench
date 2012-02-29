/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResourceDataReadPanel.java
 *
 * Created on Jan 2, 2012, 1:42:34 PM
 */
package de.tor.tribes.ui.wiz.red;

import com.jidesoft.swing.JideBoxLayout;
import com.jidesoft.swing.JideSplitPane;
import de.tor.tribes.types.StorageStatus;
import de.tor.tribes.types.Tag;
import de.tor.tribes.types.UserProfile;
import de.tor.tribes.types.VillageMerchantInfo;
import de.tor.tribes.types.ext.Village;
import de.tor.tribes.ui.components.GroupSelectionList;
import de.tor.tribes.ui.models.REDExtendedMerchantTableModel;
import de.tor.tribes.ui.renderer.DefaultTableHeaderRenderer;
import de.tor.tribes.ui.renderer.NumberFormatCellRenderer;
import de.tor.tribes.ui.renderer.StorageCellRenderer;
import de.tor.tribes.ui.renderer.TradeDirectionCellRenderer;
import de.tor.tribes.util.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.sort.TableSortController;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelNavResult;

/**
 *@TODO move selection settings to village panel
 * @author Torridity
 */
public class ResourceDistributorSettingsPanel extends WizardPage {

    private static final String GENERAL_INFO = "<html>In diesem Schritt kannst du einstellen, welche der eingelesenen D&ouml;rfer Empf&auml;nger und "
            + "welche D&ouml;rfer Lieferanten von Rohstoffen sind. Die Entscheidung kann entweder basierend auf dem F&uuml;llstand des Bauernhofes, "
            + "der Zugeh&ouml;rigkeit zu bestimmten Gruppen oder durch die Auswahl einzelner D&ouml;rfer geschehen. In jedem Fall musst du nach der "
            + "Einstellung der Auswahlkriterien den Button 'Anwenden' klicken, um die &Auml;nderungen durchzuf&uuml;hren. Es wird empfohlen, immer "
            + "nur ein Kriterium zu verweden, da sich verschiedene Kriterien gegenseitig ausschlie&szlig;en k&ouml;nnen und das Endergebnis dadurch "
            + "schwer nachzuvollziehen ist.</html>";
    private static ResourceDistributorSettingsPanel singleton = null;
    private GroupSelectionList groupList = null;

    public static synchronized ResourceDistributorSettingsPanel getSingleton() {
        if (singleton == null) {
            singleton = new ResourceDistributorSettingsPanel();
        }

        return singleton;
    }

    /**
     * Creates new form ResourceDataReadPanel
     */
    ResourceDistributorSettingsPanel() {
        initComponents();
        jXCollapsiblePane1.setLayout(new BorderLayout());
        jXCollapsiblePane1.add(jInfoScrollPane, BorderLayout.CENTER);
        jInfoTextPane.setText(GENERAL_INFO);

        jDataTable.setModel(new REDExtendedMerchantTableModel());
        jDataTable.getTableHeader().setDefaultRenderer(new DefaultTableHeaderRenderer());
        jDataTable.setHighlighters(HighlighterFactory.createAlternateStriping(Constants.DS_ROW_A, Constants.DS_ROW_B));
        jDataTable.setDefaultRenderer(StorageStatus.class, new StorageCellRenderer());
        jDataTable.setDefaultRenderer(VillageMerchantInfo.Direction.class, new TradeDirectionCellRenderer());
        jDataTable.setDefaultRenderer(Integer.class, new NumberFormatCellRenderer());
        TableSortController sorter = (TableSortController) jDataTable.getRowSorter();
        SlashComparator splitComparator = new SlashComparator();
        sorter.setComparator(3, splitComparator);
        sorter.setComparator(4, splitComparator);

        jideSplitPane1.setOrientation(JideSplitPane.VERTICAL_SPLIT);
        jideSplitPane1.setProportionalLayout(true);
        jideSplitPane1.setDividerSize(5);
        jideSplitPane1.setShowGripper(true);
        jideSplitPane1.setOneTouchExpandable(true);
        jideSplitPane1.setDividerStepSize(10);
        jideSplitPane1.setInitiallyEven(true);
        jideSplitPane1.add(jFilterPanel, JideBoxLayout.FLEXIBLE);
        jideSplitPane1.add(jVillagePanel, JideBoxLayout.VARY);
        jideSplitPane1.getDividerAt(0).addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    jideSplitPane1.setProportions(new double[]{0.5});
                }
            }
        });

        groupList = new GroupSelectionList("/res/awards/group.png");
        jGroupScrollPane.setViewportView(groupList);

        List<GroupSelectionList.ListItem> tags = new LinkedList<GroupSelectionList.ListItem>();
        for (Tag t : TagUtils.getTags(Tag.CASE_INSENSITIVE_ORDER)) {
            tags.add(new GroupSelectionList.ListItem(t));
        }
        groupList.setListData(tags.toArray(new GroupSelectionList.ListItem[tags.size()]));
        groupList.setEnabled(false);
    }

    public static String getDescription() {
        return "Transporte anpassen";
    }

    public static String getStep() {
        return "id-settings";
    }

    public void storeProperties() {
        UserProfile profile = GlobalOptions.getSelectedProfile();
        profile.addProperty("red.send.farm", UIHelper.parseIntFromField(jSenderFarmSpace, 23000));
        profile.addProperty("red.receive.farm", UIHelper.parseIntFromField(jReceiverFarmSpace, 20000));
        profile.addProperty("red.delete.farm", UIHelper.parseIntFromField(jDeleteFarmSpace, 0));

    }

    public void restoreProperties() {
        UserProfile profile = GlobalOptions.getSelectedProfile();
        String val = profile.getProperty("red.send.farm");
        if (val != null) {
            jSenderFarmSpace.setText(val);
        }
        val = profile.getProperty("red.receive.farm");
        if (val != null) {
            jReceiverFarmSpace.setText(val);
        }
        val = profile.getProperty("red.delete.farm");
        if (val != null) {
            jDeleteFarmSpace.setText(val);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jInfoScrollPane = new javax.swing.JScrollPane();
        jInfoTextPane = new javax.swing.JTextPane();
        jFilterPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jEnableFarmSettingsBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSenderFarmSpace = new javax.swing.JTextField();
        jDeleteFarmSpace = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jReceiverFarmSpace = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jPanel3 = new javax.swing.JPanel();
        jFullStashAsSender = new javax.swing.JCheckBox();
        jSelectionAsReceiver = new javax.swing.JCheckBox();
        jSelectionAsSender = new javax.swing.JCheckBox();
        jSelectionAsBoth = new javax.swing.JCheckBox();
        jSelectionRemove = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jEnableGroupSettingsBox = new javax.swing.JCheckBox();
        jGroupScrollPane = new javax.swing.JScrollPane();
        jGroupDirectionBox = new javax.swing.JComboBox();
        jVillagePanel = new javax.swing.JPanel();
        jVillageTableScrollPane = new javax.swing.JScrollPane();
        jDataTable = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jXCollapsiblePane1 = new org.jdesktop.swingx.JXCollapsiblePane();
        jPanel1 = new javax.swing.JPanel();
        jideSplitPane1 = new com.jidesoft.swing.JideSplitPane();

        jInfoScrollPane.setMinimumSize(new java.awt.Dimension(19, 180));
        jInfoScrollPane.setPreferredSize(new java.awt.Dimension(19, 180));

        jInfoTextPane.setContentType("text/html");
        jInfoTextPane.setEditable(false);
        jInfoTextPane.setText("<html>Du befindest dich im <b>Angriffsmodus</b>. Hier kannst du die Herkunftsd&ouml;rfer ausw&auml;hlen, die f&uuml;r Angriffe verwendet werden d&uuml;rfen. Hierf&uuml;r hast die folgenden M&ouml;glichkeiten:\n<ul>\n<li>Einf&uuml;gen von Dorfkoordinaten aus der Zwischenablage per STRG+V</li>\n<li>Einf&uuml;gen der Herkunftsd&ouml;rfer aus der Gruppen&uuml;bersicht</li>\n<li>Einf&uuml;gen der Herkunftsd&ouml;rfer aus dem SOS-Analyzer</li>\n<li>Einf&uuml;gen der Herkunftsd&ouml;rfer aus Berichten</li>\n<li>Einf&uuml;gen aus der Auswahlübersicht</li>\n<li>Manuelle Eingabe</li>\n</ul>\n</html>\n");
        jInfoScrollPane.setViewportView(jInfoTextPane);

        jFilterPanel.setMinimumSize(new java.awt.Dimension(520, 320));
        jFilterPanel.setPreferredSize(new java.awt.Dimension(529, 320));
        jFilterPanel.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bauernhof"));
        jPanel2.setMinimumSize(new java.awt.Dimension(108, 190));
        jPanel2.setPreferredSize(new java.awt.Dimension(571, 190));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jEnableFarmSettingsBox.setText("Aktiviert");
        jEnableFarmSettingsBox.setOpaque(false);
        jEnableFarmSettingsBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEnableFarmSettingsEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jEnableFarmSettingsBox, gridBagConstraints);

        jLabel2.setText("Lieferant ab");
        jLabel2.setEnabled(false);
        jLabel2.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Entfernen ab");
        jLabel3.setEnabled(false);
        jLabel3.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 2, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jSenderFarmSpace.setText("23000");
        jSenderFarmSpace.setEnabled(false);
        jSenderFarmSpace.setMinimumSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jSenderFarmSpace, gridBagConstraints);

        jDeleteFarmSpace.setEnabled(false);
        jDeleteFarmSpace.setMinimumSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jDeleteFarmSpace, gridBagConstraints);

        jLabel4.setText("Plätzen");
        jLabel4.setEnabled(false);
        jLabel4.setMaximumSize(new java.awt.Dimension(40, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(40, 14));
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Plätzen");
        jLabel5.setEnabled(false);
        jLabel5.setMaximumSize(new java.awt.Dimension(40, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(40, 14));
        jLabel5.setPreferredSize(new java.awt.Dimension(40, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Empfänger ab");
        jLabel6.setEnabled(false);
        jLabel6.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 2, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jReceiverFarmSpace.setText("20000");
        jReceiverFarmSpace.setEnabled(false);
        jReceiverFarmSpace.setMinimumSize(new java.awt.Dimension(36, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jReceiverFarmSpace, gridBagConstraints);

        jLabel7.setText("Plätzen");
        jLabel7.setEnabled(false);
        jLabel7.setMaximumSize(new java.awt.Dimension(40, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(40, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(40, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jXLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jXLabel1.setText("Die Entfernung wird bevorzugt verwendet. Möchtest du keine Dörfer entfernen, trage in dieses Feld eine '0' ein.");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jXLabel1.setLineWrap(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 5);
        jPanel2.add(jXLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jFilterPanel.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sonstiges"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jFullStashAsSender.setText("Volle Speicher als Lieferanten");
        jFullStashAsSender.setMaximumSize(new java.awt.Dimension(140, 23));
        jFullStashAsSender.setMinimumSize(new java.awt.Dimension(140, 23));
        jFullStashAsSender.setPreferredSize(new java.awt.Dimension(140, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jFullStashAsSender, gridBagConstraints);

        jSelectionAsReceiver.setText("Gewählte Dörfer als 'Empfänger'");
        jSelectionAsReceiver.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireChangeClipboardHandlingEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jSelectionAsReceiver, gridBagConstraints);

        jSelectionAsSender.setText("Gewählte Dörfer als 'Lieferant'");
        jSelectionAsSender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireChangeClipboardHandlingEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jSelectionAsSender, gridBagConstraints);

        jSelectionAsBoth.setText("Gewählte Dörfer als 'Beides'");
        jSelectionAsBoth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireChangeClipboardHandlingEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jSelectionAsBoth, gridBagConstraints);

        jSelectionRemove.setText("Gewählte Dörfer entfernen");
        jSelectionRemove.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireChangeClipboardHandlingEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jSelectionRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jFilterPanel.add(jPanel3, gridBagConstraints);

        jButton1.setText("Anwenden");
        jButton1.setMaximumSize(new java.awt.Dimension(120, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(120, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(120, 23));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                firePerformSettingsEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jFilterPanel.add(jButton1, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Gruppen"));
        jPanel5.setMinimumSize(new java.awt.Dimension(150, 50));
        jPanel5.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jEnableGroupSettingsBox.setText("Aktiviert");
        jEnableGroupSettingsBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fireEnableGroupSettingsEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jEnableGroupSettingsBox, gridBagConstraints);

        jGroupScrollPane.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jGroupScrollPane, gridBagConstraints);

        jGroupDirectionBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empfänger", "Lieferanten", "Beides", "Entfernen" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jGroupDirectionBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jFilterPanel.add(jPanel5, gridBagConstraints);

        jVillagePanel.setLayout(new java.awt.GridBagLayout());

        jVillageTableScrollPane.setPreferredSize(new java.awt.Dimension(312, 387));

        jDataTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jVillageTableScrollPane.setViewportView(jDataTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jVillagePanel.add(jVillageTableScrollPane, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Auswahl"));
        jPanel4.setMinimumSize(new java.awt.Dimension(80, 37));
        jPanel4.setPreferredSize(new java.awt.Dimension(80, 37));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("jButton2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jButton2, gridBagConstraints);

        jButton3.setText("jButton2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jButton3, gridBagConstraints);

        jButton4.setText("jButton2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jButton4, gridBagConstraints);

        jButton5.setText("jButton2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jButton5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jVillagePanel.add(jPanel4, gridBagConstraints);

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informationen einblenden");
        jLabel1.setToolTipText("Blendet Informationen zu dieser Ansicht und zu den Datenquellen ein/aus");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireShowHideInfoEvent(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel1, gridBagConstraints);

        jXCollapsiblePane1.setCollapsed(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jXCollapsiblePane1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jideSplitPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void fireShowHideInfoEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireShowHideInfoEvent
        if (jXCollapsiblePane1.isCollapsed()) {
            jXCollapsiblePane1.setCollapsed(false);
            jLabel1.setText("Informationen ausblenden");
        } else {
            jXCollapsiblePane1.setCollapsed(true);
            jLabel1.setText("Informationen einblenden");
        }
    }//GEN-LAST:event_fireShowHideInfoEvent

    private void fireEnableFarmSettingsEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireEnableFarmSettingsEvent
        jLabel2.setEnabled(jEnableFarmSettingsBox.isSelected());
        jLabel3.setEnabled(jEnableFarmSettingsBox.isSelected());
        jLabel4.setEnabled(jEnableFarmSettingsBox.isSelected());
        jLabel5.setEnabled(jEnableFarmSettingsBox.isSelected());
        jLabel6.setEnabled(jEnableFarmSettingsBox.isSelected());
        jSenderFarmSpace.setEnabled(jEnableFarmSettingsBox.isSelected());
        jReceiverFarmSpace.setEnabled(jEnableFarmSettingsBox.isSelected());
        jDeleteFarmSpace.setEnabled(jEnableFarmSettingsBox.isSelected());
    }//GEN-LAST:event_fireEnableFarmSettingsEvent

    private void fireEnableGroupSettingsEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireEnableGroupSettingsEvent
        jGroupScrollPane.setEnabled(jEnableGroupSettingsBox.isSelected());
        groupList.setEnabled(jEnableGroupSettingsBox.isSelected());
    }//GEN-LAST:event_fireEnableGroupSettingsEvent

    private void fireChangeClipboardHandlingEvent(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fireChangeClipboardHandlingEvent
        if (evt.getSource().equals(jSelectionAsReceiver)) {
            if (jSelectionAsReceiver.isSelected()) {
                jSelectionAsBoth.setSelected(false);
                jSelectionAsSender.setSelected(false);
                jSelectionRemove.setSelected(false);
            }
        } else if (evt.getSource().equals(jSelectionAsSender)) {
            if (jSelectionAsSender.isSelected()) {
                jSelectionAsBoth.setSelected(false);
                jSelectionAsReceiver.setSelected(false);
                jSelectionRemove.setSelected(false);
            }
        } else if (evt.getSource().equals(jSelectionAsBoth)) {
            if (jSelectionAsBoth.isSelected()) {
                jSelectionAsReceiver.setSelected(false);
                jSelectionAsSender.setSelected(false);
                jSelectionRemove.setSelected(false);
            }
        } else if (evt.getSource().equals(jSelectionRemove)) {
            if (jSelectionRemove.isSelected()) {
                jSelectionAsReceiver.setSelected(false);
                jSelectionAsSender.setSelected(false);
                jSelectionAsBoth.setSelected(false);
            }
        }
    }//GEN-LAST:event_fireChangeClipboardHandlingEvent

    private void firePerformSettingsEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firePerformSettingsEvent
        applySettings();
    }//GEN-LAST:event_firePerformSettingsEvent

    private void applySettings() {
        VillageMerchantInfo[] allElements = getAllElementsInternal();
        filterMisc(allElements);
        filterByFarm(allElements);
        filterByGroup(allElements);
        getModel().fireTableDataChanged();
    }

    private void filterMisc(VillageMerchantInfo[] pAllElements) {
        if (jFullStashAsSender.isSelected()) {
            for (VillageMerchantInfo element : pAllElements) {
                int capacity = element.getStashCapacity();
                if (element.getWoodStock() == capacity || element.getClayStock() == capacity || element.getIronStock() == capacity) {
                    element.setDirection(VillageMerchantInfo.Direction.OUTGOING);
                }
            }
        }

        //do selection handling
        VillageMerchantInfo.Direction newDir = null;
        if (jSelectionAsSender.isSelected()) {
            newDir = VillageMerchantInfo.Direction.OUTGOING;
        } else if (jSelectionAsReceiver.isSelected()) {
            newDir = VillageMerchantInfo.Direction.INCOMING;
        } else if (jSelectionAsBoth.isSelected()) {
            newDir = VillageMerchantInfo.Direction.BOTH;
        } else if (jSelectionRemove.isSelected()) {
            newDir = null;
        }

        for (VillageMerchantInfo element : getSelection()) {
            if (newDir != null) {
                element.setDirection(newDir);
            } else {
                getModel().removeRow(element);
            }
        }
    }

    private void filterByFarm(VillageMerchantInfo[] pAllElements) {
        if (jEnableFarmSettingsBox.isSelected()) {
            int senderFarmSpace = UIHelper.parseIntFromField(jSenderFarmSpace, 23000);
            int receiverFarmSpace = UIHelper.parseIntFromField(jReceiverFarmSpace, 20000);
            int removeFarmSpace = UIHelper.parseIntFromField(jDeleteFarmSpace, 0);
            for (VillageMerchantInfo element : pAllElements) {
                if (removeFarmSpace != 0 && element.getAvailableFarm() >= removeFarmSpace) {
                    getModel().removeRow(element);
                } else {
                    if (element.getAvailableFarm() >= senderFarmSpace) {
                        element.setDirection(VillageMerchantInfo.Direction.OUTGOING);
                    } else if (element.getAvailableFarm() <= receiverFarmSpace) {
                        element.setDirection(VillageMerchantInfo.Direction.INCOMING);
                    }
                }
            }
        }
    }

    private void filterByGroup(VillageMerchantInfo[] pAllElements) {
        if (jEnableGroupSettingsBox.isSelected()) {
            VillageMerchantInfo.Direction newDir = null;
            switch (jGroupDirectionBox.getSelectedIndex()) {
                case 0:
                    newDir = VillageMerchantInfo.Direction.INCOMING;
                    break;
                case 1:
                    newDir = VillageMerchantInfo.Direction.OUTGOING;
                    break;
                case 2:
                    newDir = VillageMerchantInfo.Direction.BOTH;
                    break;
                default:
                    newDir = null;
                    break;
            }

            List<VillageMerchantInfo> toRemove = new LinkedList<VillageMerchantInfo>();
            for (VillageMerchantInfo element : pAllElements) {
                if (!element.getDirection().equals(newDir)) {
                    if (groupList.isVillageValid(element.getVillage())) {
                        if (newDir != null) {
                            element.setDirection(newDir);
                        } else {
                            toRemove.add(element);
                        }
                    }
                }
            }

            for (VillageMerchantInfo remove : toRemove) {
                getModel().removeRow(remove);
            }
        }
    }

    public void setup() {
        REDExtendedMerchantTableModel model = getModel();
        model.clear();
        for (VillageMerchantInfo newInfo : ResourceDistributorDataReadPanel.getSingleton().getAllElements()) {
            model.addRow(newInfo.getVillage(),
                    newInfo.getStashCapacity(),
                    newInfo.getWoodStock(),
                    newInfo.getClayStock(),
                    newInfo.getIronStock(),
                    newInfo.getAvailableMerchants(),
                    newInfo.getOverallMerchants(),
                    newInfo.getAvailableFarm(),
                    newInfo.getOverallFarm(), false);
        }
        model.fireTableDataChanged();
    }

    public List<VillageMerchantInfo> getSelection() {
        int[] selection = jDataTable.getSelectedRows();
        List<VillageMerchantInfo> result = new LinkedList<VillageMerchantInfo>();
        if (selection.length > 0) {
            for (int i : selection) {
                result.add(getModel().getRow(jDataTable.convertRowIndexToModel(i)));
            }
        }
        return result;
    }

    private REDExtendedMerchantTableModel getModel() {
        return (REDExtendedMerchantTableModel) jDataTable.getModel();
    }

    private VillageMerchantInfo[] getAllElementsInternal() {
        List<VillageMerchantInfo> elements = new LinkedList<VillageMerchantInfo>();
        REDExtendedMerchantTableModel model = getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            elements.add(model.getRow(i));
        }

        return elements.toArray(new VillageMerchantInfo[elements.size()]);
    }

    public VillageMerchantInfo[] getAllElements() {
        List<VillageMerchantInfo> elements = new LinkedList<VillageMerchantInfo>();
        REDExtendedMerchantTableModel model = getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            elements.add(model.getRow(i).clone());
        }
        return elements.toArray(new VillageMerchantInfo[elements.size()]);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private org.jdesktop.swingx.JXTable jDataTable;
    private javax.swing.JTextField jDeleteFarmSpace;
    private javax.swing.JCheckBox jEnableFarmSettingsBox;
    private javax.swing.JCheckBox jEnableGroupSettingsBox;
    private javax.swing.JPanel jFilterPanel;
    private javax.swing.JCheckBox jFullStashAsSender;
    private javax.swing.JComboBox jGroupDirectionBox;
    private javax.swing.JScrollPane jGroupScrollPane;
    private javax.swing.JScrollPane jInfoScrollPane;
    private javax.swing.JTextPane jInfoTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jReceiverFarmSpace;
    private javax.swing.JCheckBox jSelectionAsBoth;
    private javax.swing.JCheckBox jSelectionAsReceiver;
    private javax.swing.JCheckBox jSelectionAsSender;
    private javax.swing.JCheckBox jSelectionRemove;
    private javax.swing.JTextField jSenderFarmSpace;
    private javax.swing.JPanel jVillagePanel;
    private javax.swing.JScrollPane jVillageTableScrollPane;
    private org.jdesktop.swingx.JXCollapsiblePane jXCollapsiblePane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private com.jidesoft.swing.JideSplitPane jideSplitPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public WizardPanelNavResult allowNext(String string, Map map, Wizard wizard) {
        ResourceDistributorCalculationPanel.getSingleton().setup(ResourceDistributorWelcomePanel.FILL_DISTRIBUTION.equals(getWizardDataMap().get(ResourceDistributorWelcomePanel.TYPE)));
        return WizardPanelNavResult.PROCEED;
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

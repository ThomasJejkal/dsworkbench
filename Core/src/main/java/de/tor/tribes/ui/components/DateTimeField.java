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
package de.tor.tribes.ui.components;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Torridity
 */
public class DateTimeField extends javax.swing.JPanel {

    private DatePicker dp;
    private TimePicker tp;
    private JDialog dlg;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private boolean timeEnabled = true;
    private boolean dateEnabled = true;
    private ActionListener mListener = null;

    /**
     * Creates new form DateTimeField
     */
    public DateTimeField() {
        initComponents();
        jDateField.setText(dateFormat.format(Calendar.getInstance().getTime()));
        jTimeField.setText(timeFormat.format(Calendar.getInstance().getTime()));
        //jDateField.setEditable(false);
        //jTimeField.setEditable(false);
        jChangeTime.setEnabled(timeEnabled);
        jTimeField.setEnabled(timeEnabled);
        jChangeDate.setEnabled(dateEnabled);
        jDateField.setEnabled(dateEnabled);
    }

    public void setActionListener(ActionListener pListener) {
        mListener = pListener;
    }

    final class Listener extends ComponentAdapter {

        public void componentHidden(ComponentEvent componentevent) {
            if (componentevent.getSource() == dp) {
                Date date = ((DatePicker) componentevent.getSource()).getDate();
                if (null != date) {
                    jDateField.setText(dateFormat.format(date));
                }
            } else if (componentevent.getSource() == tp) {
                Date date = ((TimePicker) componentevent.getSource()).getTime();
                if (null != date) {
                    jTimeField.setText(timeFormat.format(date));
                }
            }
            dlg.dispose();
            if (mListener != null) {
                mListener.actionPerformed(new ActionEvent(this, 0, "changed"));
            }
        }

        Listener() {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateField = new javax.swing.JTextField();
        jTimeField = new javax.swing.JTextField();
        jChangeDate = new javax.swing.JButton();
        jChangeTime = new javax.swing.JButton();

        jDateField.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jDateField.setMinimumSize(new java.awt.Dimension(10, 20));
        jDateField.setPreferredSize(new java.awt.Dimension(12, 22));

        jTimeField.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        jTimeField.setMinimumSize(new java.awt.Dimension(10, 20));
        jTimeField.setPreferredSize(new java.awt.Dimension(80, 22));

        jChangeDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/calendar_31.png"))); // NOI18N
        jChangeDate.setMaximumSize(new java.awt.Dimension(20, 20));
        jChangeDate.setMinimumSize(new java.awt.Dimension(20, 20));
        jChangeDate.setPreferredSize(new java.awt.Dimension(22, 22));
        jChangeDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeDateTimeEvent(evt);
            }
        });

        jChangeTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ui/clock.png"))); // NOI18N
        jChangeTime.setMaximumSize(new java.awt.Dimension(20, 20));
        jChangeTime.setMinimumSize(new java.awt.Dimension(20, 20));
        jChangeTime.setPreferredSize(new java.awt.Dimension(22, 22));
        jChangeTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fireChangeDateTimeEvent(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jTimeField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jChangeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jChangeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jChangeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jChangeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setEnabled(boolean pValue) {
        super.setEnabled(pValue);
        /*
         * jDateField.setEnabled(pValue); jChangeDate.setEnabled(pValue);
         */
        if (timeEnabled) {
            jChangeTime.setEnabled(pValue);
            jTimeField.setEnabled(pValue);
        } else {
            jChangeTime.setEnabled(false);
            jTimeField.setEnabled(false);
        }

        if (dateEnabled) {
            jChangeDate.setEnabled(pValue);
            jDateField.setEnabled(pValue);
        } else {
            jChangeDate.setEnabled(false);
            jDateField.setEnabled(false);
        }
    }

    public void setTimeEnabled(boolean pValue) {
        timeEnabled = pValue;
        jChangeTime.setVisible(timeEnabled);
        jTimeField.setVisible(timeEnabled);
        jChangeTime.setEnabled(timeEnabled);
        jTimeField.setEnabled(timeEnabled);
    }

    public void setDateEnabled(boolean pValue) {
        dateEnabled = pValue;
        jChangeDate.setVisible(dateEnabled);
        jDateField.setVisible(dateEnabled);
        jChangeDate.setEnabled(dateEnabled);
        jDateField.setEnabled(dateEnabled);
    }

    public boolean isTimeEnabled() {
        return timeEnabled;
    }

    public boolean isDateEnabled() {
        return dateEnabled;
    }

    public Date getSelectedDate() {
        try {
            Date date = dateFormat.parse(jDateField.getText());

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            Calendar result = Calendar.getInstance();
            result.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
            result.set(Calendar.MONTH, c.get(Calendar.MONTH));
            result.set(Calendar.YEAR, c.get(Calendar.YEAR));
            if (isTimeEnabled()) {
                Date time = timeFormat.parse(jTimeField.getText());
                c.setTime(time);
                result.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
                result.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
                result.set(Calendar.SECOND, c.get(Calendar.SECOND));
                result.set(Calendar.MILLISECOND, 0);
            } else {
                result.set(Calendar.HOUR_OF_DAY, 0);
                result.set(Calendar.MINUTE, 0);
                result.set(Calendar.SECOND, 0);
                result.set(Calendar.MILLISECOND, 0);
            }

            if (!isDateEnabled()) {
                result.set(Calendar.DAY_OF_MONTH, 0);
                result.set(Calendar.MONTH, 0);
                result.set(Calendar.YEAR, 0);
            }

            return result.getTime();
        } catch (Exception e) {
            Date now = Calendar.getInstance().getTime();
            setDate(now);
            return now;
        }
    }

    public void setDate(Date pDate) {
        jDateField.setText(dateFormat.format(pDate));
        jTimeField.setText(timeFormat.format(pDate));
    }

    private void fireChangeDateTimeEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fireChangeDateTimeEvent
        if (!isEnabled()) {
            return;
        }
        if (evt.getSource() == jChangeDate) {
            if (!dateEnabled) {
                return;
            }
            try {
                dp = new DatePicker(dateFormat.parse(jDateField.getText()));
            } catch (Exception e) {
                dp = new DatePicker();
            }
            dp.addComponentListener(new Listener());
            Point point = jDateField.getLocationOnScreen();
            point.setLocation(point.getX(), (point.getY() - 1.0D) + jDateField.getSize().getHeight());
            dlg = new JDialog(new JFrame(), true);
            dlg.setTitle("Datum auswählen");
            dlg.setLocation(point);
            //dlg.setResizable(false);
            //dlg.setUndecorated(true);
            JPanel p = new JPanel();
            p.add(dp);
            dlg.getContentPane().add(p);
        } else {
            if (!timeEnabled) {
                return;
            }
            try {
                tp = new TimePicker(timeFormat.parse(jTimeField.getText()));
            } catch (Exception e) {
                tp = new TimePicker();
            }
            tp.addComponentListener(new Listener());

            Point point = jTimeField.getLocationOnScreen();
            point.setLocation(point.getX(), (point.getY() - 1.0D) + jTimeField.getSize().getHeight());
            dlg = new JDialog(new JFrame(), true);
            tp.setParent(dlg);
            dlg.setLocation(point);
            dlg.setTitle("Zeit auswählen");
            // dlg.setResizable(false);
            dlg.setUndecorated(false);
            JPanel p = new JPanel();
            p.add(tp);
            dlg.getContentPane().add(p);
        }

        dlg.pack();
        //check for outer bounds
        double deltax = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (dlg.getBounds().getX() + dlg.getBounds().getWidth());
        double deltay = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (dlg.getBounds().getY() + dlg.getBounds().getHeight());
        if (deltax < 0) {
            dlg.setLocation((int) (dlg.getX() + deltax), dlg.getY());
        }
        if (deltay < 0) {
            dlg.setLocation(dlg.getX(), (int) (dlg.getY() + deltay));
        }
        dlg.setVisible(true);
    }//GEN-LAST:event_fireChangeDateTimeEvent
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jChangeDate;
    private javax.swing.JButton jChangeTime;
    private javax.swing.JTextField jDateField;
    private javax.swing.JTextField jTimeField;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new DateTimeField());
        f.pack();
        f.setVisible(true);
    }
}

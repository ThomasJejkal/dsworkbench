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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

/**
 *
 * @author Charon
 */
public class MinimapZoomFrame extends javax.swing.JFrame {

    private static Logger logger = Logger.getLogger("Minimap-Zoomframe");
    protected BufferedImage mMap = null;
    private DrawThread mDrawThread;
    private BufferedImage mBorder = null;

    /**
     * Creates new form MinimapZoomFrame
     */
    public MinimapZoomFrame(BufferedImage pMap) {
        initComponents();
        setMinimap(pMap);
        mDrawThread = new DrawThread(this);
        mDrawThread.start();
        try {
            mBorder = ImageIO.read(new File("graphics/zoom_border.png"));
        } catch (Exception e) {
        }
    }

    public void setMinimap(BufferedImage pMap) {
        mMap = pMap;
    }

    @Override
    public void paint(Graphics g) {
        if (pImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(35, 125, 0));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.drawImage(pImage, xp, yp, null);
            /*
             * if (mBorder != null) { g2d.drawImage(mBorder, null, 0, 0);
            }
             */
            g2d.dispose();
        }

    }
    private int xp = 0;
    private int yp = 0;
    private Image pImage = null;

    public void update(Image bImage, int dx, int dy) {
        /*
         * if (isVisible()) { BufferStrategy bs = getBufferStrategy(); if (bs != null && bImage != null) { Graphics2D g2d = (Graphics2D)
         * bs.getDrawGraphics(); g2d.setColor(new Color(35, 125, 0)); g2d.fillRect(0, 0, getWidth(), getHeight()); g2d.drawImage(bImage, dx,
         * dy, null); if (mBorder != null) { g2d.drawImage(mBorder, null, 0, 0); } g2d.dispose(); bs.show(); }
        }
         */
        pImage = bImage;
        xp = dx;
        yp = dy;
        repaint();
    }

    public void updatePosition(int pXPos, int pYPos) {
        mDrawThread.updatePosition(pXPos, pYPos);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setResizable(false);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

class DrawThread extends Thread {

    private static Logger logger = Logger.getLogger("Minimap-Zoomframe(PaintThread)");
    private MinimapZoomFrame mParent;
    private int centerX = 0;
    private int centerY = 0;

    public DrawThread(MinimapZoomFrame pParent) {
        mParent = pParent;
        setName("MinimapZoomThread");
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (mParent.isVisible()) {
                    int pWidth = mParent.getWidth() / 2;
                    int pHeight = mParent.getHeight() / 2;
                    int dx = 0;
                    int dy = 0;
                    int pXStart = centerX - pWidth / 2;
                    int pYStart = centerY - pHeight / 2;

                    if (pXStart < 0) {
                        dx = -1 * pXStart;
                        pXStart = 0;
                    }
                    if (pYStart < 0) {
                        dy = -1 * pYStart;
                        pYStart = 0;
                    }

                    if (pXStart + pWidth > mParent.mMap.getWidth()) {
                        dx = mParent.mMap.getWidth() - (pXStart + pWidth);
                        pXStart = mParent.mMap.getWidth() - pWidth;
                    }
                    if (pYStart + pHeight > mParent.mMap.getHeight()) {
                        dy = mParent.mMap.getHeight() - (pYStart + pHeight);
                        pYStart = mParent.mMap.getHeight() - pHeight;
                    }
                    BufferedImage part = mParent.mMap.getSubimage(pXStart, pYStart, pWidth, pHeight);
                    mParent.update(part.getScaledInstance(mParent.getWidth(), mParent.getHeight(), BufferedImage.SCALE_DEFAULT), dx, dy);
                }
            } catch (Exception e) {
                //redraw failed, ignore it
                logger.error("Failed to draw zoomed map", e);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
        }
    }

    public void updatePosition(int pXPos, int pYPos) {
        centerX = pXPos;
        centerY = pYPos;
    }
}

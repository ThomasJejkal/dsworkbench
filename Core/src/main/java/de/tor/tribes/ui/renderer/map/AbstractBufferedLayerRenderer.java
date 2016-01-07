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
package de.tor.tribes.ui.renderer.map;

import java.awt.Graphics2D;

/**
 *
 * @author Torridity
 */
public abstract class AbstractBufferedLayerRenderer {

    private boolean fullRenderRequired = true;

    public abstract void performRendering(RenderSettings pSettings, Graphics2D pG2d);

    /**
     * @return the fullRenderRequired
     */
    public boolean isFullRenderRequired() {
        return fullRenderRequired;
    }

    /**
     * @param fullRenderRequired the fullRenderRequired to set
     */
    public void setFullRenderRequired(boolean fullRenderRequired) {
        this.fullRenderRequired = fullRenderRequired;
    }
   
}
/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.builder.macwidgets.factory

import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JToggleButton

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.com>
 */
class MacButtonFactory extends AbstractFactory {
   final Icon icon
   final String buttonType
   protected Icon selectedIcon
   final boolean focusable

   MacButtonFactory( Icon icon, String buttonType, boolean focusable = true, Icon selectedIcon = null ) {
      this.icon = icon
      this.buttonType = buttonType
      this.focusable = focusable
      this.selectedIcon = selectedIcon
   }

   public Object newInstance( FactoryBuilderSupport builder, Object name, Object value, Map attributes )
            throws InstantiationException, IllegalAccessException {
      def button = selectedIcon ? new JToggleButton(icon) : new JButton(icon)
      if(selectedIcon) button.setSelectedIcon(selectedIcon)
      button.putClientProperty("JButton.buttonType", buttonType )
      if( attributes.containsKey("segmentedPosition") ) {
         String segmentedPosition = attributes.remove("segmentedPosition").toLowerCase()
         button.putClientProperty("JButton.segmentedPosition", segmentedPosition )
      }
      button.focusable = focusable
      return button
   }
}
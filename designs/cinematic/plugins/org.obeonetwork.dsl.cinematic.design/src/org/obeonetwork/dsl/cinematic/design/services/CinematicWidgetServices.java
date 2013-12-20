/**
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.obeonetwork.dsl.cinematic.design.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.cinematic.toolkits.Toolkit;
import org.obeonetwork.dsl.cinematic.toolkits.Widget;

/**
 * Services to use the widgets
 * 
 * @author jdupont
 * 
 */
public class CinematicWidgetServices {

	/**
	 * Retrieves widgets that have the property isContainer set to true and
	 * contained in the semantic element.
	 * 
	 * @param context
	 *            VSM context
	 * @param semanticElement
	 *            Semantic element
	 */
	public List<Widget> getWidgetsIsContainer(EObject context,
			EObject semanticElement) {		
		List<Widget> widgetsIsContainer = new ArrayList<Widget>();		
		CinematicRoot cinematicRoot = null;
		if (semanticElement instanceof CinematicRoot) {
			cinematicRoot = (CinematicRoot) semanticElement;
		} else {
			EObject element = semanticElement;
			while (!(element.eContainer() instanceof CinematicRoot)) {
				element = semanticElement.eContainer();
			}
			cinematicRoot = (CinematicRoot) element.eContainer();
		}
		List<Toolkit> toolkits = cinematicRoot.getToolkits();
		for (Toolkit toolkit : toolkits) {
			EList<Widget> widgets = toolkit.getWidgets();
			for (Widget widget : widgets) {
				if (widget.isIsContainer()) {
					widgetsIsContainer.add(widget);
				}
			}
		}
		return widgetsIsContainer;
	}
	
	/**
	 * Retrieves the selected widget. Return null if neither widget was selected.
	 * @param context the VSM context
	 * @param selectedElement The selected Element 
	 * @return the selected widget, null otherwise
	 */
	public Widget getSelectedWidget (EObject context, EObject selectedElement){
		Widget selectedWidget = null;
		if (selectedElement instanceof Widget){
			selectedWidget = (Widget)selectedElement;		
		}
		return selectedWidget;			
	}

}

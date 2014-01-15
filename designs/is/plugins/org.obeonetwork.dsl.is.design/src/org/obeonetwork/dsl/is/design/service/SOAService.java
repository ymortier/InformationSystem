/*******************************************************************************
 * Copyright (c) 2009-2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.is.design.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.obeonetwork.dsl.soa.Component;
import org.obeonetwork.dsl.soa.InterfaceKind;
import org.obeonetwork.dsl.soa.Service;
import org.obeonetwork.dsl.soa.System;
import org.obeonetwork.dsl.soa.Wire;

public class SOAService {

	public List<Component> allNonReferencedExternalComponents(System context) {
		List<Component> allNonReferencedExternalComponents = allExternalComponents(context);
		// Remove all referenced external components
		allNonReferencedExternalComponents
				.removeAll(allReferencedExternalComponents(context));
		return allNonReferencedExternalComponents;

	}

	public List<Component> allExternalComponents(System context) {
		List<Component> allExternalComponents = allComponents(context);
		// Remove The component contained in the System.
		allExternalComponents.removeAll(context.getOwnedComponents());
		return allExternalComponents;
	}

	public List<Component> allReferencedExternalComponents(System context) {
		List<Component> allReferencedExternelComponents = allReferencedComponents(context);
		// Remove The component contained in the System.
		allReferencedExternelComponents.removeAll(context.getOwnedComponents());
		return allReferencedExternelComponents;
	}

	public List<Component> allReferencedComponents(System context) {
		List<Component> allReferencedComponents = new ArrayList<Component>();
		List<Wire> ownedWires = context.getOwnedWires();
		// Add Components parent of source and dest of westWire
		for (Wire wire : ownedWires) {
			Component componentSource = (Component) wire.getSource()
					.eContainer();
			allReferencedComponents.add(componentSource);
			Component componentDest = (Component) wire.getDest().eContainer();
			allReferencedComponents.add(componentDest);
		}
		Set<Component> componentsSet = new HashSet<Component>();
		// Remove duplicates
		componentsSet.addAll(allReferencedComponents);
		return new ArrayList<Component>(componentsSet);
	}

	public List<Component> allComponents(System context) {
		// <%allRoots.eAllContents().filter("soa.Component")%>
		EcoreService ecoreService = new EcoreService();
		// Retrieve allRoots
		Collection<EObject> allRoots = ecoreService.allRoots(context);
		List<Component> components = new ArrayList<Component>();
		// For all roots retrieve all elements of type Component
		for (EObject object : allRoots) {
			List<Component> allContainedEntities = new ArrayList<Component>();
			for (EObject obj : EcoreService.eAllContents(object,
					Component.class)) {
				allContainedEntities.add((Component) obj);
			}
			components.addAll(allContainedEntities);
		}
		return components;
	}

	public List<Component> allSelectableExternalComponents(System context,
			DSemanticDiagram semanticDiagram) {
		List<Component> allNonReferencedExternalComponents = allNonReferencedExternalComponents(context);
		List<Component> allComponentToRemove = new ArrayList<Component>();
		// For all semanticDiagram retrieve all contents elements of type
		// "DNodeContainer"
		for (EObject obj : EcoreService.eContents(semanticDiagram,
				DNodeContainer.class)) {
			// Retrieve and add the target of DNodeContainer type of Component
			if (((DNodeContainer) obj).getTarget() instanceof Component) {
				allComponentToRemove.add((Component) ((DNodeContainer) obj)
						.getTarget());
			}
		}
		allNonReferencedExternalComponents.removeAll(allComponentToRemove);
		return allNonReferencedExternalComponents;
	}
	
	public boolean isRequiredService (Service context){
		return context.getKind().equals (getRequiredLiteral(context));
	}

	public InterfaceKind getProvidedLiteral(EObject object) {
		return InterfaceKind.PROVIDED_LITERAL;
	}

	public InterfaceKind getRequiredLiteral(EObject object) {
		return InterfaceKind.REQUIRED_LITERAL;
	}

}

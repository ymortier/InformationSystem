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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.dsl.cinematic.AbstractPackage;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.cinematic.NamedElement;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

public class CinematicEcoreServices {
	
	public static List<EObject> getAllRootsForCinematic(EObject any) {
		List<EObject> roots = new ArrayList<EObject>();
		
		Session session = SessionManager.INSTANCE.getSession(any);
		
		if (session != null) {
			for (Resource childRes : session.getSemanticResources()) {
				roots.addAll(childRes.getContents());
			}
		}
		
		return roots;
	}
	
	@SuppressWarnings("unchecked")
	public static EObject duplicateCinematicElement(EObject context) {
		EObject clone = EcoreUtil.copy(context);
		if (clone instanceof NamedElement) {
			NamedElement namedElement = (NamedElement)clone;
			namedElement.setName(namedElement.getName() + " copy");
		}
		EStructuralFeature containmentFeature = context.eContainingFeature();
		Object feature = context.eContainer().eGet(containmentFeature, true);
		if (feature instanceof Collection) {
			((Collection<EObject>)feature).add(clone);
		}
		
		return clone;
	}
	
	/**
	 * Retrieve the cinematic root.
	 * @param context the context
	 * @param semanticElement the semantic element 
	 * @return cinematic root
	 */
	public CinematicRoot getCinematicRoot(EObject context, AbstractPackage semanticElement){
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
		return cinematicRoot;
	}
		
}

package org.obeonetwork.dsl.is.design.service;

import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.description.Layer;

public class DiagramService {

	
	public static boolean isLayerActivated(DDiagram diagram, String layerName) {
		for (Layer activatedLayer : diagram.getActivatedLayers()) {
			if (layerName.equals(activatedLayer.getName())) {
				return true;
			}
		}
		return false;
	}
}

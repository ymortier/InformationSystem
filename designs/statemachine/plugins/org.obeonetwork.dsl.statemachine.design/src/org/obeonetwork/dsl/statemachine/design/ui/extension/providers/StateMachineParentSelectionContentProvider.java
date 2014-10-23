package org.obeonetwork.dsl.statemachine.design.ui.extension.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.obeonetwork.dsl.environment.Behaviour;
import org.obeonetwork.dsl.environment.ObeoDSMObject;

public class StateMachineParentSelectionContentProvider extends AdapterFactoryContentProvider {

	public StateMachineParentSelectionContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object[] getElements(Object object) {
		if (object instanceof Object[]) {
			List<Object> elements = new ArrayList<Object>();
			for (Object input : (Object[])object) {
				elements.addAll(Arrays.asList(getElements(input)));
			}
			return elements.toArray();
		} else {
			List<Object> result = new ArrayList<Object>();
			Object[] elements = super.getElements(object);
			for (Object element : elements) {
				if (element instanceof ObeoDSMObject && !(element instanceof Behaviour)) {
					result.add(element);
				}
			}
			return result.toArray();
		}
	}
	
	@Override
	public boolean hasChildren(Object object) {
		return getChildren(object).length > 0;
	}
	
	@Override
	public Object[] getChildren(Object object) {
		List<Object> result = new ArrayList<Object>();
		Object[] children = super.getChildren(object);
		for (Object child : children) {
			if (child instanceof ObeoDSMObject && !(child instanceof Behaviour)) {
				result.add(child);
			}
		}
		return result.toArray();
	}
}
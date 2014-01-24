/**
 * 
 */
package org.obeonetwork.graal.design.services.requirements;

import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.requirement.Requirement;
import org.obeonetwork.graal.Task;
import org.obeonetwork.graal.TasksGroup;
import org.obeonetwork.graal.UseCase;

/**
 * Utilities services concerning Requirements
 * 
 * @author jdupont
 * 
 */
public class RequirementsUtils {

	/**
	 * Return the linked Task if exist using the "asTask" service
	 * 
	 * @param context
	 *            the context on which is applied the service
	 * @return the related task, null otherwise
	 */
	public Task relatedTask(Requirement context) {
		for (EObject object : context.getReferencedObject()) {
			if (asTask(object) != null) {
				return asTask(object);
			}
		}
		return null;
	}

	/**
	 * Try to interpret an object as a Task - return this if the object is a
	 * Task - Up the containing hierarchy to find a Task - null otherwise
	 * 
	 * @param context
	 *            the context on which is applied the service
	 * @return a task if task is find null otherwise
	 */
	public Task asTask(EObject context) {
		if (context instanceof Task) {
			return (Task) context;
		}
		if (context.eContainer() != null) {
			EObject container = context.eContainer();
			while (container != null) {
				if (container instanceof Task) {
					return (Task) container;
				}
				container = container.eContainer();
			}
		}
		return null;
	}

	/**
	 * Return the related Use case
	 * 
	 * @param context
	 *            the context on which is applied the service
	 * @return useCase if useCase is find null otherwise
	 */
	public UseCase relatedUseCase(Requirement context) {
		for (EObject object : context.getReferencedObject()) {
			if (asUseCase(object) != null) {
				return asUseCase(object);
			}
		}
		return null;
	}

	/**
	 * Try to interpret an object as an UseCase // - return this if the object
	 * is a UseCase // - return the UseCase associated to the group if the
	 * referencedObject // can be interpreted as a group // - return the UseCase
	 * associated to the task if the referencedObject // can be interpreted as a
	 * task // - null otherwise
	 * 
	 * @param context
	 *            he context on which is applied the service
	 * @return the useCase, if a useCase is find, null otherwise
	 */
	public UseCase asUseCase(EObject context) {
		if (context instanceof UseCase) {
			return (UseCase) context;
		} else if (context instanceof TasksGroup) {
			return ((TasksGroup) context).getUseCase();
		} else if (asTask(context) != null) {
			return asTask(context).getUseCase();
		}
		return null;
	}
}

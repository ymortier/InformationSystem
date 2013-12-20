package org.obeonetwork.dsl.environment.binding.dialect.ui.treemapper.commands;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.obeonetwork.dsl.environment.BindingElement;
import org.obeonetwork.dsl.environment.BindingReference;
import org.obeonetwork.dsl.environment.bindingdialect.DBindingEdge;

public class DeleteMappingCommand extends RecordingCommand {

	private DBindingEdge bindingEdge = null;
	private ModelAccessor accessor;
	
	public DeleteMappingCommand(TransactionalEditingDomain domain, ModelAccessor accessor, DBindingEdge bindingEdge) {
		super(domain, "Delete mapping");
		this.bindingEdge = bindingEdge;
		this.accessor = accessor;
	}

	@Override
	protected void doExecute() {
		if (bindingEdge.getTarget() != null && bindingEdge.getTarget() instanceof BindingReference) {
			
			BindingReference bindingReference = (BindingReference)bindingEdge.getTarget();
			Session session = SessionManager.INSTANCE.getSession(bindingReference);
			ECrossReferenceAdapter crossReferencer = session.getSemanticCrossReferencer();
			
			BindingElement left = bindingReference.getLeft();
			BindingElement right = bindingReference.getRight();
			if (hasNoOtherReference(left)) {
				// Delete the useless element
				accessor.eDelete(left, crossReferencer);
			}
			if (hasNoOtherReference(right)) {
				// Delete the useless element
				accessor.eDelete(right, crossReferencer);
			} else {
				
			}
			accessor.eDelete(bindingReference, crossReferencer);
		}
		bindingEdge.setLeft(null);
		bindingEdge.setRight(null);
		EcoreUtil.delete(bindingEdge);
	}
	
	
	private boolean hasNoOtherReference(BindingElement bindingElement) {
		return bindingElement.getReferencedBy().size() <= 1;
	}
}

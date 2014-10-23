package org.obeonetwork.dsl.interaction.design.ui.extension.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.obeonetwork.dsl.interaction.design.Activator;

import fr.obeo.mda.ecore.extender.business.api.permission.IPermissionAuthority;
import fr.obeo.mda.ecore.extender.business.api.permission.LockStatus;
import fr.obeo.mda.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

public class InteractionParentSelectionLabelProvider extends AdapterFactoryLabelProvider {

	private ImageDescriptor LOCKED_BY_ME_IMG_DESC = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/lock-by-me.gif");
	private ImageDescriptor LOCKED_BY_OTHERS_IMG_DESC = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/lock-by-others.gif");

	public InteractionParentSelectionLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Image getImage(Object element) {
		Image image = super.getImage(element);

		if (element instanceof EObject) {
			LockStatus lockStatus = getLockStatus((EObject) element);
            if (LockStatus.LOCKED_BY_ME == lockStatus) {
            	return new DecorationOverlayIcon(image, LOCKED_BY_ME_IMG_DESC, IDecoration.TOP_LEFT).createImage();
            } else if (LockStatus.LOCKED_BY_OTHER == lockStatus) {
            	return new DecorationOverlayIcon(image, LOCKED_BY_OTHERS_IMG_DESC, IDecoration.TOP_LEFT).createImage();
            }
            return image;
		} else {
			return image;
		}
	}


	private LockStatus getLockStatus(EObject element) {
		IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(element);
		if (authority != null) {
			return authority.getLockStatus(element);
		}

		return null;
	}
}
/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.database.m2doc.services;

import org.obeonetwork.m2doc.services.IServiceHolder;

/**
 * Holder for the {@link TableContainerServices}.
 * 
 * @author Romain Guider
 */
public class TableContainerServiceHolder implements IServiceHolder {

    @Override
    public Class<?> getServiceClass() {
        return TableContainerServices.class;
    }

}

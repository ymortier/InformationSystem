/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.obeonetwork.graal.design.graalfeatureextensions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.obeonetwork.graal.design.graalfeatureextensions.GraalfeatureextensionsPackage
 * @generated
 */
public interface GraalfeatureextensionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GraalfeatureextensionsFactory eINSTANCE = org.obeonetwork.graal.design.graalfeatureextensions.impl.GraalfeatureextensionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>UI Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UI Configuration</em>'.
	 * @generated
	 */
	UIConfiguration createUIConfiguration();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	GraalfeatureextensionsPackage getGraalfeatureextensionsPackage();

} //GraalfeatureextensionsFactory

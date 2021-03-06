 /*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hugo Marchadour (Obeo) - initial API and implementation
 *******************************************************************************/

 /**
 * @autor hugo.marchadour@obeo.fr
 * @version 1.0.0
 * @since 0.5.0
 * @generated
 */
/** Start of user code default require function */
/* Define function is an AMD feature. For more information, @see http://requirejs.org. */
define(["require", "app/App", "app/view/ViewUtil", "app/view/CommonEvents", "ember" ], function(require) {
	"use strict";

	
	/* Get all required AMD modules in vars */
	var App = require("app/App"),
		ViewUtil = require("app/view/ViewUtil"),
		CommonEvents = require("app/view/CommonEvents");
	// ember does not need to be stored in a variable.
/** End of user code */

	
	var Pagination = {},
		_Class = Em.View.extend({ templateName : "pagination" });

	
	
	/*
	 * create a Pagination widget
	 * @return the Pagination widget instance
	 */
	Pagination.create = function(listWidget) {
		ViewUtil.loadWidgetTemplate("./asset/template/widget/other/pagination");

		var view = _Class.create({
			paginationProxies : [],
			listWidget : listWidget,
			loadContent : cb_loadProxies,
			paginationLink: paginationLink,
			/** Start of user code additional features */
			/** End of user code */
		});
		return view;
	};
	
	/* events */
	var paginationLink = function(e){
		e.preventDefault();
		var paginationProxy = e.context;
		this.listWidget.paginationChange(paginationProxy.id);
	};

	/*call backs*/
	var cb_loadProxies = function(paginationProxies) {
		
		if(paginationProxies.length>1)
			this.set('paginationProxies', paginationProxies);
		else
			this.set('paginationProxies', []);
	};
	
	/** Start of user code additional functions */
	/** End of user code */

	return Pagination;
});

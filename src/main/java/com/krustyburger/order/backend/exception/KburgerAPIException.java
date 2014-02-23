package com.krustyburger.order.backend.exception;

import java.io.Serializable;

public class KburgerAPIException extends Exception implements Serializable {

	private static final long serialVersionUID = -690510249126589685L;
	
	public KburgerAPIException(String message) {
		super(message);
	}
	
	public KburgerAPIException(String message, Exception exception) {
		super(message, exception);
	}

}

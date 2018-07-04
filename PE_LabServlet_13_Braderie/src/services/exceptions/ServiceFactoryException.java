/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:36:46
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.exceptions;

/**
 * Classe héritant de la classe Exception
 * Cette classe définis les exceptions de la classe ServiceFactory
 * @author 31010-79-11
 *
 */
public class ServiceFactoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceFactoryException() {
		super();
	}

	public ServiceFactoryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceFactoryException(String message) {
		super(message);
	}

	public ServiceFactoryException(Throwable cause) {
		super(cause);
	}

}
/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package modele.services.exceptions;

/**
 * Classe héritant de la classe Exception
 * Cette classe définis les exceptions de la classe ServiceFactory
 * @author Manonegra
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
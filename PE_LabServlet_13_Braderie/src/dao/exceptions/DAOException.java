/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:04:58
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package dao.exceptions;

/**
 * Une exception levée liée à la persistance.
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = -6111973666438757776L;

	private int codeErreur;
	private String messageErreur;

	public DAOException() {
	}

	public DAOException(String message) {
		this(message, 0);
	}

	public DAOException(String message, Throwable cause) {
		super("DBException" + message, cause);
		codeErreur = DAOExceptionMessages.EXCEPTIONCHAINEE;
	}

	public DAOException(Throwable cause) {
		this(null, cause);
	}

	public DAOException(String message, int codeErreur) {
		super(message);
		switch(codeErreur)
		{
		case 1017 :
			this.codeErreur = DAOExceptionMessages.CONNEXIONIMPOSSIBLE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

		case DAOExceptionMessages.PILOTENONTROUVE :
			this.codeErreur = DAOExceptionMessages.PILOTENONTROUVE;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;

		default:
			this.codeErreur = DAOExceptionMessages.AUTREEXCEPTION;
			messageErreur = DAOExceptionMessages.message[this.codeErreur];
			System.out.println(messageErreur + "  :  "+codeErreur);
			break;
		}
	}

	public int getCode() {
		return codeErreur;
	}

	public String getMessageErreur() {
		return messageErreur;
	}
}
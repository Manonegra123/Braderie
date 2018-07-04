/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:05:51
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package dao.exceptions;

public class DAOExceptionMessages {

	/**
	 * Code de l'erreur.
	 * 0 s'il y a une exception chaînée
	 * 1 pour connexion pas ouverte
	 * 2 pour connexion déjà ouverte
	 * 3 pour connexion impossible
	 * 4 pour pilote non trouvé
	 * 5 si pas de transaction en cours
	 * 6 autre exception
	 * ...
	 */

	// Visibilité au niveau package
	static final int EXCEPTIONCHAINEE = 0;
	static final int CONNEXIONPASOUVERTE = 1;
	static final int CONNEXIONDEJAOUVERTE = 2;
	static final int CONNEXIONIMPOSSIBLE = 3;
	static final int PILOTENONTROUVE = 4;
	static final int PASDETRANSACTION = 5;
	static final int AUTREEXCEPTION = 6;
	static final int NO_CREATE = 7;
	static final int NO_UPDATE = 8;
	static final int NO_DELETE = 9;

	static String[]  message = {
			"Exception chainée",
			"Connexion non ouverte",
			"Connexion déjà ouverte",
			"Connexion impossible",
			"Pilote non trouvé",
			"Pas de transaction en cours",
			"Autre exception",
			"Impossible de créer l'entité en base de données",
			"Impossible de modifier l'entité en base de données",
			"Impossible de supprimer l'entité en base de données"
	};


	private DAOExceptionMessages() {
	}
}

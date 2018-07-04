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
package modele.dao.interfaces;

import java.util.List;

import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;

/**
 * Interface permettant de définir les constantes sur les tables de la base de donnée
 * et les méthodes utilisées par les classe DAOBean
 * @author Manonegra
 *
 */
public interface IDao<T> {

	public static final String T_USER = "T_USER";
	public static final String T_ARTICLE = "T_ARTICLE";
	public static final String T_CADDIE = "T_CADDIE";

	public static final int REQUETE_OK = 1;
	public static final int REQUETE_NOK = 0;

	public static final int NO_ID = 0;

	public static final int TIMEOUT = 5;


	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T findByID(int id)throws DAOException;

	/**
	 * Permet de récupérer tous les objets
	 * @param id
	 * @return
	 */
	public abstract List<T> findAll()throws DAOException;

	/**
	 * Permet de récupérer le dernier objet (par son id)
	 * @return
	 */
	public abstract T findLastArticle()throws DAOException;

	/**
	 * Permet de récupérer le premier objet (par son id)
	 * @return
	 */
	public abstract T findFirstArticle()throws DAOException;

	/**
	 * Permet de récupérer l'objet précedent
	 * @return
	 */
	public abstract T findPrevArticle(int id)throws DAOException;

	/**
	 * Permet de récupérer l'objet suivant
	 * @return
	 */
	public abstract T findNextArticle(int id)throws DAOException;

	/**
	 * Permet de récupérer un user par son login et son mdp
	 * @return
	 */
	public abstract int findUser(String login, String pwd)throws DAOException;

	/**
	 * Permet de sauver un caddie dans la base
	 * @return
	 */
	public abstract Boolean sauveCaddie(Caddie caddie)throws DAOException;

}

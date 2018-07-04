/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:01:36
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.interfaces;

import java.util.List;

import dao.exceptions.DAOException;

/**
 * Interface permettant de définir les constantes sur les tables de la base de donnée
 * et les méthodes utilisées par les classe DAOBean
 * @author 31010-79-11
 *
 */
public interface IDao<T> {

	public static final String T_USER = "T_USER";
	public static final String T_ARTICLE = "T_ARTICLE";

	public static final int REQUETE_OK = 1;
	public static final int REQUETE_NOK = 0;

	public static final int NO_ID = 0;

	public static final int TIMEOUT = 5;

	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T findByID(long id)throws DAOException;

	/**
	 * Permet de récupérer tous les objets
	 * @param id
	 * @return
	 */
	public abstract List<T> findAll()throws DAOException;

	/**
	 * Permet de récupérer une liste d'objets via des critères
	 * @return
	 */
	public abstract List<T> findByCriteria(T obj) throws DAOException;

	/**
	 * Permet de récupérer un objet selon 2 criteres
	 * @param
	 * @return
	 */
	public abstract int find(String obj1, String obj2) throws DAOException;

	/**
	 * Permet de récupérer le dernier objet (par son id)
	 * @return
	 */
	public abstract T findLastID()throws DAOException;

	/**
	 * Permet de récupérer le premier objet (par son id)
	 * @return
	 */
	public abstract T findFirstID()throws DAOException;

}

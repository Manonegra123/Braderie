/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:39:12
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.impl;

import java.util.List;

import dao.factory.DAOFactory;
import services.exceptions.ServiceException;

/**
 * Classe abstraite Service contenant les classes utilisées par les BeanServices
 * La couche Service sert de lien entre les controllers et la couche DAO
 * @author 31010-79-11
 *
 */
public abstract class Service<T> {

	// Créer la DAO Factory pour Oracle utilisée par les classes service
	protected DAOFactory myFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);

	protected String name;

	// Méthode abstraite implémentée par les différentes classes service
	public abstract String getName();

	// Méthode abstraite implémentée par les différentes classes service
	public abstract T findById(Long id) throws ServiceException;

	// Méthode abstraite implémentée par les différentes classes service
	public abstract List<T> findByCriteria(T criteres) throws ServiceException;

	// Méthode abstraite implémentée par les différentes classes service
	public abstract List<T> findAll() throws ServiceException;

	@Override
	public String toString() {
		return "Service [getName()=" + getName() + ", getClass()=" + getClass() + "]";
	}
}
/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
/**
 *
 */
package modele.services.impl;

import java.util.List;

import modele.dao.factory.DAOFactory;
import modele.services.exceptions.ServiceException;

/**
 * Classe abstraite Service contenant les classes utilisées par les BeanServices
 * La couche Service sert de lien entre les controllers et la couche DAO
 * @author Manonegra
 *
 */
public abstract class Service<T> {

	// Créer la DAO Factory pour Oracle utilisée par les classes service
	protected DAOFactory myFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);

	protected String name;

	// Méthode abstraite implémentée par les différentes classes service
	public abstract String getName();

	// Méthode abstraite implémentée par les différentes classes service
	public abstract T findById(int id) throws ServiceException;

	// Méthode abstraite implémentée par les différentes classes service
	public abstract List<T> findAll() throws ServiceException;

	@Override
	public String toString() {
		return "Service [getName()=" + getName() + ", getClass()=" + getClass() + "]";
	}
}
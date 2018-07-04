/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:37:40
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.User;
import dao.exceptions.DAOException;
import dao.impl.OracleUserDAO;
import dao.interfaces.IDao;
import services.exceptions.ServiceException;
import services.factory.ServiceFactory;

/**
 * Classe de Service du bean User héritant de la classe Service
 * Elle définis toutes les méthodes de cette classe
 * Ces méthodes font appel a la couche DAO
 * @author 31010-79-11
 *
 */
public class UserService extends Service<User> {

	// Créer un DAO User à partir de la factory
	IDao<User> userDao = myFactory.getUserDAO();

	// Logger
	static final private Log log = LogFactory.getLog(UserService.class);

	public UserService(Class<ServiceFactory> callerClass, String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public int find(String login, String pwd) throws ServiceException {
		int id = 0;
		try {
			System.out.println("envoie de user service a user dao");
			id = userDao.find(login, pwd);
			System.out.println("find user_service ok");
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver le user");
		}
		return id;
	}

	@Override
	public User findById(Long id) throws ServiceException {
		User user = new User();

		try {
			user = userDao.findByID(id);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver le user");
		}
		return user;

	}

	@Override
	public List<User> findByCriteria(User critere) throws ServiceException {

		//Creation d'une collection pour stocker les users
		List<User> lUsers = null;

		try {
			lUsers = userDao.findByCriteria(critere);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver les users");
		}
		return lUsers;
	}

	@Override
	public List<User> findAll() throws ServiceException {
		//Creation d'une collection pour stocker les users
		List<User> lUsers = null;
		OracleUserDAO userDAO = new OracleUserDAO();

		try {
			lUsers = userDAO.findAll();
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver les users");
		}
		return lUsers;
	}


}
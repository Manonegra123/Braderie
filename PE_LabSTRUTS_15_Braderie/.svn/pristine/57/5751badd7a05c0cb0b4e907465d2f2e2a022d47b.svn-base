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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.User;
import modele.dao.exceptions.DAOException;
import modele.dao.impl.OracleUserDAO;
import modele.dao.interfaces.IDao;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;

/**
 * Classe de Service du bean User héritant de la classe Service Elle définis
 * toutes les méthodes de cette classe Ces méthodes font appel a la couche DAO
 *
 * @author Manonegra
 *
 */
public class UserService extends Service<User> {

	// Créer un DAO User à partir de la factory
	IDao<User> userDao = myFactory.getUserDAO();

	// Logger
	private static final Logger log = LogManager.getLogger(UserService.class);


	/**
	 * Constructeur par défaut
	 */
	public UserService() {
		super();
	}

	public UserService(Class<ServiceFactory> callerClass, String name) {
		this.name = name;
	}

	// Methode de creation d'un user
	public User createUser() {
		return new User();
	}

	@Override
	public String getName() {
		return name;
	}

	public int find(String login, String pwd) throws ServiceException {
		int id = 0;
		try {
			id = userDao.findUser(login, pwd);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver le user");
		}
		return id;
	}

	@Override
	public User findById(int id) throws ServiceException {
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
	public List<User> findAll() throws ServiceException {
		// Creation d'une collection pour stocker les users
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

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

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;
import modele.dao.interfaces.IDao;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;

/**
 * Classe de Service du bean Caddie héritant de la classe Service
 * Elle définis toutes les méthodes de cette classe
 * Ces méthodes font appel a la couche DAO
 * @author Manonegra
 *
 */
public class CaddieService extends Service<Caddie>{

	// Logger
	private static final Logger log = LogManager.getLogger(CaddieService.class);

	// Créer un DAO Article à partir de la factory
	IDao<Caddie> caddieDao = myFactory.getCaddieDAO();
	private Caddie caddie = null;
	private HashMap<Integer, Integer> hMapCaddie = null;

	// Constructeurs
	public CaddieService() {
		// TODO Auto-generated constructor stub
	}

	public CaddieService(Class<ServiceFactory> callerClass, String name) {
		this.name = name;
	}

	// Methode de creation d'un caddie
	public Caddie createCaddie() {
		return new Caddie();
	}

	/**
	 * Methode findByID qui demande à la DAO l'existance d'un caddie
	 * pour un id passe en parametre
	 *
	 * @return Caddie
	 */
	@Override
	public Caddie findById(int id) throws ServiceException {
		Caddie caddie = new Caddie();

		try {
			caddie = caddieDao.findByID(id);

		} catch (DAOException e) {
			log.error("Impossible de trouver le caddie"+e);
			throw new ServiceException("Impossible de trouver le caddie");
		}

		return caddie;

	}

	/**
	 * Methode sauveCaddie qui appel la couche DAO pour sauvegarder le caddie
	 * passe en parametre
	 *
	 * @return boolean
	 */
	//---------------------------------------------------------------------------
	public  Boolean sauveCaddie(Caddie caddie)  throws DAOException {

		Boolean result = false;

		log.info("----------------------------------------------------------");
		log.info("sauveCaddie()");
		try {
			result = caddieDao.sauveCaddie(caddie);
			log.info("sauveCaddie = " + result);
		} catch (DAOException e) {
			log.error("merdouille sauveCaddie " + e);
			throw new DAOException("CaddieService : sauveCaddie() <DAOException> ");
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Caddie> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}

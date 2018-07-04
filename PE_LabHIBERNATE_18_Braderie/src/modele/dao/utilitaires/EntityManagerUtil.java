package modele.dao.utilitaires;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 *  keep in mind that JPA EntityManager are not synchronized.
 *  That means that you cannot create an instance of EntityManager and do all the transactions
 *  from that instance.
 *  Although, JPA EntityManagerFactory is a synchronized object and hence you can create
 *  as many EntityManager you want from an instance of EntityManagerFactory.
 *
 *
 *  This allows me to use the JPA trade safe object EntityManagerFactory to provide the same
 *  JPA EntityManager instance call.
 *
 */
public class EntityManagerUtil {

	// Logger
	private static final Logger log = LogManager.getLogger(DAOUtil.class);

	// Nom de l'unité de persistance
	private static final String PERSISTENCE_UNIT_NAME = "demojpa-pu";

	private static ThreadLocal<EntityManager> manager;

	/**
	 * Un EntityManager (équivalent de la Session Hibernate) correspond à l'état d'une
	 * connexion avec la base de données. Il n'est pas souhaitable de partager
	 * un même EntityManager entre deux browsers (session http).
	 */
	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = new ThreadLocal<>();
	}

	private EntityManagerUtil() {
	}

	public static boolean isEntityManagerOpen(){
		return EntityManagerUtil.manager.get() != null && EntityManagerUtil.manager.get().isOpen();
	}

	public static EntityManager getEntityManager() {

		log.info ("EntityManagerUtil : getEntityManager()");

		if (EntityManagerUtil.factory == null)
			EntityManagerUtil.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		EntityManager em = EntityManagerUtil.manager.get();

		if (em == null || !em.isOpen()) {
			em = EntityManagerUtil.factory.createEntityManager();
			EntityManagerUtil.manager.set(em);
		}

		log.info ("Hash code : " + System.identityHashCode(em));

		return em;
	}

	public static void closeEntityManager() {

		log.info ("EntityManagerUtil : closeEntityManager()");

		EntityManager em = EntityManagerUtil.manager.get();
		if (em != null) {
			EntityTransaction tx = em.getTransaction();
			if (tx.isActive()) {
				tx.commit();
			}
			em.close();
			EntityManagerUtil.manager.set(null);
		}
	}

	public static void closeEntityManagerFactory(){

		log.info ("EntityManagerUtil : closeEntityManagerFactory()");

		closeEntityManager();
		EntityManagerUtil.factory.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}
}
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
package controllers.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe Listener écoutant le contexte (le démarrage et la fermerture de l'application)
 * @author Manonegra
 *
 */
public class ServContextListener implements ServletContextListener{

	// Logger
	private static final Logger log = LogManager.getLogger(ServContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Démarrage de l'application"+sce.getSource().toString());

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Fermeture de l'application"+sce.getSource().toString());

	}

}

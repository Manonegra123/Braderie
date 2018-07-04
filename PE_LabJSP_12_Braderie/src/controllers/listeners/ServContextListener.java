/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 14:11:22
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package controllers.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe Listener écoutant le contexte (le démarrage et la fermerture de l'application)
 * @author Manonegra
 *
 */
public class ServContextListener implements ServletContextListener{

	// Logger
	private static final Log log = LogFactory.getLog(ServContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Démarrage de l'application"+sce.getSource().toString());

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Fermeture de l'application"+sce.getSource().toString());

	}

}

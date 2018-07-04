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

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Classe Listener écoutant les sessions
 * Elle réagit a chaque changement de session ou d'attributs de session
 * @author Manonegra
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{

	// Logger
	private static final Logger log = LogManager.getLogger(SessionListener.class);

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		log.info("Nouvel attribut créée "+se.getName());

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		log.info("Attribut détruit "+se.getName());

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		log.info("Attribut modifié "+se.getName());

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.info("Nouvelle session créée ");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("Session détruite ");

	}

}

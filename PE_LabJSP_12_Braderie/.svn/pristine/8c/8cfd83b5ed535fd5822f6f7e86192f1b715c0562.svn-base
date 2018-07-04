/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 14:02:38
 *
 * PE_LabJSP_12_Braderie
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe Listener écoutant les sessions
 * Elle réagit a chaque changement de session ou d'attributs de session
 * @author Manonegra
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{

	// Logger
	private static final Log log = LogFactory.getLog(SessionListener.class);

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

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
package controllers.actions.authentification;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe appelée par la front_controller par la balise action=logout
 * Elle permet à l'utilisateur de se logout grâce a la méthode invalidate()
 * @author Manonegra
 *
 */
@Namespace("/")
@Action(value="logout")
@ResultPath(value="/WEB-INF/")
@Results({@Result(name="success", location="index.jsp")})
public class LogoutAction extends ActionSupport implements RequestAware{

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(LogoutAction.class);

	// Variables pour l'interface SessionAware, RequestAware
	private SessionMap session = null;	// SessionAware
	private Map<String, Object> request = null;	// RequestAware

	public void setSession(SessionMap session) {
		this.session = session;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public String execute() throws ServletException, IOException {
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
			try {
				session.invalidate();
			} catch (IllegalStateException e) {
				log.error("Session deja invalidé", e);
			}
		}
		addActionError(getText("error.logout"));
		return SUCCESS;
	}

}

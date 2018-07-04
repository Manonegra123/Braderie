/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
package controllers.actions.exceptions;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value="error")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="error.jsp")
public class ErrorHandlerAction extends ActionSupport implements RequestAware {

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(ErrorHandlerAction.class);

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
		request.put("errorMessage", "Un problème est survenu, veuillez vous reconnecter");
		return SUCCESS;

	}

}

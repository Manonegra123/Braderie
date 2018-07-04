/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package controllers.commands.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.commands.interfaces.ICommand;

/**
 * Classe appelée par la front_controller par la balise action=logout
 * Elle permet à l'utilisateur de se logout grâce a la méthode invalidate()
 * @author Manonegra
 *
 */
public class LogoutCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("errorMessage", "Vous êtes déconnecté");
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		else
			response.encodeURL("/FC?action=current");

	}

}

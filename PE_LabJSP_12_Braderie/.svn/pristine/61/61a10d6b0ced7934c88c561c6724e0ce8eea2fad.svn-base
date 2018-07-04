/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 15:03:38
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
package controllers.commands.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.commands.interfaces.ICommand;

public class ErrorHandlerCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();


		session.invalidate();
		request.setAttribute("errorMessage", "Un problème est survenu, veuillez vous reconnecter");
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		else
			response.encodeURL("/WEB-INF/error.jsp");

	}

}

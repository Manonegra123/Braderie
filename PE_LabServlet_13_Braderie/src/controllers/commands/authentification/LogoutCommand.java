/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 24 mai 2018
 * A : 12:27:00
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package controllers.commands.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe appelée par la front_controller par la balise action=logout
 * Elle permet à l'utilisateur de se logout grâce a la méthode invalidate()
 * @author 31010-79-11
 *
 */
public class LogoutCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/index.html").forward(request, response);

	}

}

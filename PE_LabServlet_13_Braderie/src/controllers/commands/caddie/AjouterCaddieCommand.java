/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 29 mai 2018
 * A : 08:32:51
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package controllers.commands.caddie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.Caddie;
import controllers.commands.authentification.ICommand;

public class AjouterCaddieCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(AjouterCaddieCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Récupération de l'id de l'article passé en attribut de session
		HttpSession session = request.getSession();
		long idArticle = (long) session.getAttribute("idArticle");

		// Instanciation d'un caddie
		Caddie hcaddie = new Caddie();


		// Redirection vers la page current_article avec l'article suivant
		session.setAttribute("idArticle",idArticle);
		request.getRequestDispatcher("/Front_Controller?action=current").forward(request, response);

	}

}

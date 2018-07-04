/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 28 mai 2018
 * A : 10:56:43
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

import controllers.commands.authentification.ICommand;
import services.exceptions.ServiceException;
import services.factory.ServiceFactory;
import services.impl.ArticleService;

/**
 * Classe appelée par la front_controller par la balise action=precedent
 * @author 31010-79-11
 *
 */
public class PrecedentCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(PrecedentCommand.class);

	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Récupération de l'id de l'article passé en attribut de session
		HttpSession session = request.getSession();
		long idArticle = (long) session.getAttribute("idArticle");
		try {
			// On met en place le carroussel avec le if else qui va controler
			// si l'id est bien dans les bornes de la bases de donnée
			if(idArticle > articleService.findFirstID().getId())
				idArticle--;
			else
				idArticle = articleService.findLastID().getId();

		} catch (ServiceException e) {
			log.error(e);
			log.info(e);
			throw new ServletException(e);
		}

		// Redirection vers la page current_article avec l'article precedent
		session.setAttribute("idArticle",idArticle);
		request.getRequestDispatcher("/Front_Controller?action=current").forward(request, response);

	}

}

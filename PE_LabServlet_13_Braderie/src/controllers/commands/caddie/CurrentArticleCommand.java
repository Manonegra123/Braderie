/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 28 mai 2018
 * A : 11:24:27
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package controllers.commands.caddie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.Article;
import controllers.commands.authentification.ICommand;
import services.exceptions.ServiceException;
import services.factory.ServiceFactory;
import services.impl.ArticleService;

/**
 * Classe appelée par la front_controller par la balise action=current
 * @author 31010-79-11
 *
 */
public class CurrentArticleCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(CurrentArticleCommand.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Déclarations
		Article article = new Article();
		long idArticle;
		HttpSession session = request.getSession();

		// Positionnement de l'id sur l'id récupéré dans l'attribut de session passé dans le login_command
		idArticle = (long) session.getAttribute("idArticle");
		try {
			// Recherche de l'id par la couche service
			article = articleService.findById(idArticle);
		} catch (ServiceException e) {
			log.error(e);
			log.info(e);
			throw new ServletException(e);
		}

		// Génération de la page d'affichage de l'article
		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>\n<html lang=\"fr\">\n<head>\n<meta charset=\"utf-8\">\n<title>Page Article</title>\n</head>\n<body>"
				+ "<h2>ARTICLE n°: "+article.getId()+"<h2>"
				+ "<table>"
				+ "<tr>"
				+ "<td>Identifiant : </td>"
				+ "<td>"+article.getId()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Désignation : </td>"
				+ "<td>"+article.getDescription()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Marque : </td>"
				+ "<td>"+article.getMarque()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Prix : </td>"
				+ "<td>"+article.getPrixunitaire()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Quantitée commandée : </td>"
				+ "<td>"+article.getQtite()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ "<span><a href=\"Front_Controller?action=precedent\">Precedent</a></span>"
				+ "<span><a href=\"Front_Controller?action=suivant\">Suivant</a></span>"
				+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "<a href=\"Front_Controller?action=ajouter_caddie\">"
				+ "<button type=\"submit\">Ajouter au caddie</button></a></br>"
				+ "<a href=\"Front_Controller?action=contenu_caddie\">Contenu du caddie</a></br>"
				+ "<a href=\"Front_Controller?action=logout\">Se déconnecter</a>"
				+ "</body>\n</html>");
		// Vider le flux
		out.flush();

		// Fermer le flux
		out.close();

	}
}

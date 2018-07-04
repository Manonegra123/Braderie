/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 28 mai 2018
 * A : 10:57:50
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package controllers.commands.caddie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.Article;
import beans.Caddie;
import controllers.commands.authentification.ICommand;
import services.exceptions.ServiceException;
import services.factory.ServiceFactory;
import services.impl.ArticleService;

public class ContenuCaddieCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(ContenuCaddieCommand.class);

	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new Caddie();
		Article harticle = new Article();
		long idArticle;
		HttpSession session = request.getSession();
		idArticle = (long) session.getAttribute("idArticle");
		harticle.setId(idArticle);
		List<Article> lArticles;
		try {
			lArticles = articleService.findAll();
		} catch (ServiceException e) {
			log.error(e);
			log.info(e);
			throw new ServletException(e);
		}

		response.setContentType ("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>\n<html lang=\"fr\">\n<head>\n<meta charset=\"utf-8\">\n<title>Page Article</title>\n</head>\n<body>");
		out.println("<h2>Contenu du caddie<h2>"
				+ "<table>"
				+ "<tr>"
				+ "<th>N°</th>"
				+ "<th>Article</th>"
				+ "<th>Quantité</th>"
				+ "</tr>");
		while(lArticles.contains(harticle))
			out.println("<tr>"
					+ "<td>"+0+"</td>"
					+ "<td>"+harticle.getId()+
					" - "+harticle.getDescription()+
					", de marque"+harticle.getMarque()+
					" et de prix "+harticle.getPrixunitaire()+
					"</td>"
					+ "<td>"+0+"</td>");
		out.println("</table>"
				+ "<a href=\"Front_Controller?action=current\">Retour à la sélection</a>");
		out.println("</body>\n</html>");
		// Vider le flux
		out.flush();

		// Fermer le flux
		out.close();
	}

}

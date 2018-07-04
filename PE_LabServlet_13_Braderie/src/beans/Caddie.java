/*******************************************************************************
 * @author Manonegra
 * Date de creation : 28 mai 2018
 * A : 16:05:35
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package beans;

import java.util.ArrayList;
import java.util.List;

public class Caddie {
	private List<Article> lArticles = new ArrayList();

	/**
	 * @param lArticles
	 */
	public Caddie(List<Article> lArticles) {
		super();
		this.lArticles = lArticles;
	}

	/**
	 * @return the lArticles
	 */
	public List<Article> getlArticles() {
		return lArticles;
	}

	/**
	 * @param lArticles the lArticles to set
	 */
	public void setlArticles(List<Article> lArticles) {
		this.lArticles = lArticles;
	}

	public void addArticle() {

	}
}

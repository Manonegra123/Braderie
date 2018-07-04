/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
/**
 * Classe métier caddie
 * @author Manonegra
 *
 */
package modele.beans;

import java.util.HashMap;

public class Caddie {

	// Map d'articles qui va contenir idArticle et quantite Article
	private HashMap<Integer, Integer> articles;
	private int qtite;
	private int id;

	// Constructeurs
	public Caddie() {
		articles = new HashMap<>();
	}

	// Getters et setters
	/**
	 * @return the articles
	 */
	public HashMap<Integer, Integer> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(HashMap<Integer, Integer> articles) {
		this.articles = articles;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	// Methode d'ajout d'articles
	public void addArticle(int idArticle, int qtite) {
		articles.put(idArticle, qtite + 1);
	}

	// Methode de suppression d'articles
	public void removeArticle(int idArticle, int qtite) {
		if(getQtite(idArticle) <= 1)
			articles.remove(idArticle, qtite);
		else
			articles.put(idArticle, qtite - 1);
	}

	// Methode qui retourne la quantite d'un article dans le caddie
	public int getQtite(int idArticle) {
		qtite = 0;
		if(articles.containsKey(idArticle)) {
			qtite = articles.get(idArticle);
		}
		return qtite;
	}

	@Override
	public String toString() {
		return "Caddie "+id+" articles : "+articles;
	}



}

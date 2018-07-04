/*******************************************************************************
 * @author Manonegra
 * Date de creation : 23 mai 2018
 * A : 11:25:43
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package beans;

public class Article {

	//Attributs de la classe
	private long id;
	private String description;
	private String marque;
	private int prixunitaire;

	//Constructeurs
	public Article() {}

	/**
	 * @param id
	 * @param description
	 * @param marque
	 * @param prixunitaire
	 */
	public Article(long id, String description, String marque, int prixunitaire) {
		super();
		this.id = id;
		this.description = description;
		this.marque = marque;
		this.prixunitaire = prixunitaire;
	}

	//Getters et setters

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the prixunitaire
	 */
	public int getPrixunitaire() {
		return prixunitaire;
	}

	/**
	 * @param prixunitaire the prixunitaire to set
	 */
	public void setPrixunitaire(int prixunitaire) {
		this.prixunitaire = prixunitaire;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article : "+id+", description :"+description+", marque :"+marque+", prixunitaire : "+prixunitaire;
	}

	public String getQtite() {
		// TODO Auto-generated method stub
		return null;
	}

}

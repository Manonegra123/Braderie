/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 * Classe métier article
 * @author Manonegra
 */
package modele.beans;

public class Article {

	//Attributs de la classe
	private int id;
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
	public Article(int id, String description, String marque, int prixunitaire) {
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
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
		return "Art "+id+"/"+description+"/"+marque+"/"+prixunitaire+"€ ";
	}

}

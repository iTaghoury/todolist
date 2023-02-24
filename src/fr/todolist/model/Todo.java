package fr.todolist.model;

public class Todo {
	
	private Urgence urgence;
	private String titre, description;
	
	
	/*
	 * Constructeurs
	 */
	
	public Todo(String titre, String description) {
		this(Urgence.MEDIUM, titre, description);
	}


	public Todo(Urgence urgence, String titre, String description) {
		super();
		this.urgence = urgence;
		this.titre = titre;
		this.description = description;
	}
	
	
	/*
	 * GETTERS SETTERS
	 */
	
	public Urgence getUrgence() {
		return urgence;
	}
	public void setUrgence(Urgence urgence) {
		this.urgence = urgence;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	/*
	 * Méthodes
	 */
	
	@Override
	public String toString() {
		return "Todo [urgence=" + urgence + ", titre=" + titre + ", description=" + description + "]";
	}
	
}

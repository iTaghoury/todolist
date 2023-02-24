package fr.todolist.model;

public class Menu {
	private String[] menuItems;
	
	/*
	 * Constructeur
	 */
	
	public Menu(String[] menuItems) {
		this.menuItems = menuItems;
	}

	/*
	 * GETTERS SETTERS
	 */
	
	public String[] getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(String[] menuItems) {
		this.menuItems = menuItems;
	}
	
	/**
	 * Affichage du menu
	 * @return une string décrivant le contenu du menu
	 */
	
	public StringBuilder afficherMenu() {
		StringBuilder sb = new StringBuilder();
		for(String item : this.menuItems) {
			sb.append(item + "\n");
		}
		return sb;
	}
		
}

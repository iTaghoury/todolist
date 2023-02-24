package fr.todolist.model;

import java.util.Scanner;

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
		for(String item : this.getMenuItems()) {
			sb.append(item + "\n");
		}
		return sb;
	}
	
	/**
	 * Choix du menu
	 * @return l'index de l'item choisi
	 */
	public int choixMenu(Scanner sc) {
		int choix = 0;
		do {
			choix = sc.nextInt();
		} while(choix < 1 || choix > this.getMenuItems().length);
		
		return choix;
	}
	
}

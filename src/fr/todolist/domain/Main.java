package fr.todolist.domain;

import java.util.Scanner;

import fr.todolist.model.Menu;

public class Main {

	public static void main(String[] args) {
		
		String[] items = 
			{
			 "1) Ajouter un Todo", 
			 "2) Voir les Todos", 
			 "3) Retirer et afficher un todo par index", 
			 "4) Retirer et afficher le dernier Todo", 
			 "5) Quitter"
			 };
		
		Menu menu = new Menu(items);
		Scanner sc = new Scanner(System.in);
		
		
		// Boucle App
		
		int choix = 0;	
		do {
			System.out.println(menu.afficherMenu());
			choix = menu.choixMenu(sc);
			App.handleMenu(choix, sc);
		} while(choix != 5) ;
	}

}

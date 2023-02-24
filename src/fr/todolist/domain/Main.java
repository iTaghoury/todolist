package fr.todolist.domain;

import fr.todolist.model.Menu;

public class Main {

	public static void main(String[] args) {
		
		// Initialisation
		
		String[] items = 
			{
			 "1) Ajouter un Todo", 
			 "2) Voir les Todos", 
			 "3) Retirer et afficher un todo par index", 
			 "4) Retirer et afficher le dernier Todo", 
			 "5) Quitter"
			 };
		
		Menu menu = new Menu(items);
		
		
		// Boucle App
		
		App.appLoop(menu);
	}

}

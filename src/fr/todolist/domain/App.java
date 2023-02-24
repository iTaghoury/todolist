package fr.todolist.domain;

import java.util.Scanner;

import fr.todolist.model.*;
import fr.todolist.utilitaire.TodoHandler;

public class App {
	
	/**
	 * Gère les interactions avec la console
	 * @param choix : l'option du menu choisie
	 * @param sc : le scanner permettant de récupérer les inputs
	 */
	public static void handleMenu(int choix, Scanner sc) {
		
		TodoHandler th = TodoHandler.getTodoHandler();
		sc.nextLine();
		
		switch(choix) {
			case 1: // Option ajout d'une tache, demande un titre, une description et un niveau d'urgence
				
				System.out.print("Entrez le titre : ");
				String titre = sc.nextLine();
				
				System.out.print("Entrez la description : ");
				String description = sc.nextLine();

				System.out.println("Entrez le niveau d'urgence : (1) Faible, (2) Moyen, (3) Élevé");
				Urgence urg = inputUrgence(sc);
				
				Todo nouvelleTache = new Todo(urg, titre, description);
				
				System.out.println(th.addTodo(nouvelleTache));
				break;
			case 2: // Option affichage de toutes les taches
				
				System.out.println(String.format("Todo List : \n %s", th.viewTodo()));
				break;
			case 3: // Option suppression d'une tache selon son index dans la todolist
				
				System.out.println("Entrez l'index de la tache a supprimer : ");
				int index = sc.nextInt();
				
				System.out.println(th.removeTodo(index));
				break;
			case 4: // Option suppression de la dernière tâche de la liste
				
				System.out.println(th.removeLastTodo());;
				break;
			default: // Option quitter ou invalide
				break;
		}
	}
	
	
	/**
	 * Gère l'input du niveau d'urgence en vérifiant que l'input est correcte
	 * @param sc : le scanner permettant l'input
	 * @return le niveau d'urgence
	 */
	public static Urgence inputUrgence(Scanner sc) {
		
		int choixUrgence = 0;
		do {
			choixUrgence = sc.nextInt();
		} while(choixUrgence < 1 || choixUrgence > 3);
		
		return Urgence.values()[choixUrgence-1];
	}
}

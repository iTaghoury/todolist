package fr.todolist.domain;

import java.util.Scanner;

import fr.todolist.model.*;
import fr.todolist.utilitaire.TodoHandler;

public class App {
	
	public static void handleMenu(int choix, Scanner sc) {
		
		TodoHandler th = TodoHandler.getTodoHandler();
		sc.nextLine();
		
		switch(choix) {
			case 1:
				
				System.out.print("Entrez le titre : ");
				String titre = sc.nextLine();
				
				System.out.print("Entrez la description : ");
				String description = sc.nextLine();

				System.out.println("Entrez le niveau d'urgence : (1) Faible, (2) Moyen, (3) Élevé");
				Urgence urg = inputUrgence(sc);
				
				Todo nouvelleTache = new Todo(urg, titre, description);
				
				System.out.println(th.addTodo(nouvelleTache));
				break;
			case 2:
				
				System.out.println(String.format("Todo List : \n %s", th.viewTodo()));
				break;
			case 3:
				
				System.out.println("Entrez l'index de la tache a supprimer : ");
				int index = sc.nextInt();
				
				System.out.println(th.removeTodo(index));
				break;
			case 4:
				
				System.out.println(th.removeLastTodo());;
				break;
			default:
				break;
		}
	}
	
	public static Urgence inputUrgence(Scanner sc) {
		
		Urgence u = null;
		int choixUrgence = 0;
		do {
			choixUrgence = sc.nextInt();
		} while(choixUrgence < 1 || choixUrgence > 3);
		
		u = Urgence.values()[choixUrgence-1];
		return u;
	}
}

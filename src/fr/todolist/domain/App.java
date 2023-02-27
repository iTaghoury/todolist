package fr.todolist.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import fr.todolist.data.DataBase;
import fr.todolist.model.*;
import fr.todolist.utilitaire.TodoHandler;

public class App {
	
	
	/**
	 * Boucle de l'application
	 * @param menu : le menu à afficher
	 */
	public static void appLoop(Menu menu) {
		
		Scanner sc = new Scanner(System.in);
		
		int choix = 0;	
		do {
			System.out.println(menu.afficherMenu());
			choix = sc.nextInt();
			handleMenu(choix, sc);
		} while(choix != 5);
		System.out.println("Fin TodoList");
	}
	
	
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
				Todo nouvelleTache = inputTodo(sc);
				System.out.println(th.addTodo(nouvelleTache));
				addTodoToDB(nouvelleTache);
				break;
			case 2: // Option affichage de toutes les taches
				viewListFromDB();
				break;
			case 3: // Option suppression d'une tache selon son index dans la todolist
				System.out.println("Entrez l'index de la tache a supprimer : ");
				int index = sc.nextInt();
				removeTodoFromDB(index);
				break;
			case 4: // Option suppression de la dernière tâche de la liste
				removeLastFromDB();
				break;
			default: // Option quitter ou invalide
				break;
		}
	}

	/**
	 * Permet à l'utilisateur d'input la tache
	 * @param sc, le scanner
	 * @return la nouvelle tâche
	 */
	public static Todo inputTodo(Scanner sc) {
		System.out.print("Entrez le titre : ");
		String titre = sc.nextLine();

		System.out.print("Entrez la description : ");
		String description = sc.nextLine();

		System.out.println("Entrez le niveau d'urgence : (1) Faible, (2) Moyen, (3) Élevé");
		Urgence urg = inputUrgence(sc);

		Todo nouvelleTache = new Todo(urg, titre, description);
		return nouvelleTache;
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

	public static void addTodoToDB(Todo todo) {
		try(DataBase db = DataBase.getDataBase()) {
			db.addTodo(todo);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void viewListFromDB() {
		try(DataBase db = DataBase.getDataBase()) {
			System.out.println(String.format("Todo List : \n %s", db.viewTodo()));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void removeTodoFromDB(int index) {
		try(DataBase db = DataBase.getDataBase()) {
			db.removeTodo(index);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void removeLastFromDB() {
		try(DataBase db = DataBase.getDataBase()) {
			db.removeLastTodo();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}

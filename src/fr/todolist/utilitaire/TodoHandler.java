package fr.todolist.utilitaire;

import java.util.LinkedList;

import fr.todolist.model.*;

public class TodoHandler {
	
	private LinkedList<Todo> todoList;
	private static TodoHandler instance;
	
	static {
		instance = null;
	}
	
	/*
	 * Constructeur et getter singleton
	 */
	
	private TodoHandler() {
		instance = this;
		this.todoList = new LinkedList<Todo>();
	}
	
	
	public static TodoHandler getTodoHandler() {
		if(instance == null) {
			return new TodoHandler();
		} else {
			return instance;
		}
	}

	/*
	 * GETTERS / SETTERS
	 */
	
	public LinkedList<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(LinkedList<Todo> todoList) {
		this.todoList = todoList;
	}
	
	
	/*
	 * Méthodes
	 */
	
	
	/**
	 * Ajoute une tache à la todo list
	 * @param todo la tache à ajouter
	 */
	public String addTodo(Todo todo) {
		String message = String.format("Ajout nouvelle tache : %s\n", todo.toString());
		this.todoList.add(todo);
		
		return message;
	}
	
	
	/**
	 * Retire une tache de la todo list à l'index choisi 
	 * @param l'index de la tache à retirer
	 * @return le message de suppression
	 */
	public String removeTodo(int index) {
		
		String message = String.format("Suppression de la tache %s\n", this.todoList.get(index));
		this.todoList.remove(index);
		
		return message;
	}
	
	/**
	 * Retire la dernière tâche de la liste
	 * @return le message de suppression
	 */
	
	public String removeLastTodo() {
		
		String message = String.format("Suppression de la tache %s\n", this.todoList.get(this.todoList.size() - 1));
		this.todoList.pollLast();
		
		return message;
	}
	
	/**
	 * Renvoie la liste des taches
	 * @return une chaine de caractères décrivant chaque tâche
	 */
	
	public StringBuilder viewTodo() {
		StringBuilder sb = new StringBuilder();
		for(Todo item : this.getTodoList()) {
			sb.append(String.format("Todo %d : %s \n", this.todoList.indexOf(item),item.toString()));
		}
		return sb;
	}
}

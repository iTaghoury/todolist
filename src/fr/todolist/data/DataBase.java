package fr.todolist.data;

import fr.todolist.model.Todo;
import fr.todolist.model.Urgence;

import java.sql.*;

public class DataBase implements AutoCloseable {

    //region USER PASSWORD AND URL
    private final String USER = "root";
    private final String PASSWORD = "0628Cara*";
    private static final String URL = "jdbc:mysql://localhost:3306/todoList";
    //endregion

    private String ADD_QUERY = "INSERT INTO tasks (titre, descript, urgence) VALUE (?, ?, ?)";
    private String SELECT_QUERY = "SELECT * FROM tasks";
    private String DELETE_QUERY = "DELETE tasks.* FROM tasks WHERE idTask = ?";
    private String SELECT_DESC_QUERY = "SELECT * FROM tasks ORDER BY idTask DESC";
    private Connection connection;
    private static DataBase instance;
    static {
        instance = null;
    }
    private DataBase() {
        try {
            this.createConnection();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        instance = this;
    }

    public static DataBase getDataBase() {
        if(instance == null) {
            return new DataBase();
        } else {
            try {
                instance.createConnection();
            } catch (SQLException e) {
               System.out.println(e.getMessage());
            }
            return instance;
        }
    }
    public void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    @Override
    public void close() throws SQLException {
        this.connection.close();
    };

    public void addTodo(Todo todo) throws SQLException {
        try(PreparedStatement ps = this.connection.prepareStatement(ADD_QUERY)) {
            ps.setString(1, todo.getTitre());
            ps.setString(2, todo.getDescription());
            ps.setString(3, todo.getUrgence().toString());
            ps.execute();
        }
    }

    public StringBuilder viewTodo() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try(PreparedStatement ps = this.connection.prepareStatement(SELECT_QUERY);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                sb.append(String.format(
                        "Todo %d titre = %s desc = %s urgence = %s\n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        }
        return sb;
    }

    public void removeTodo(int index) throws SQLException {
        try(PreparedStatement ps = this.connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, index);
            ps.execute();
        }
    }
    public void removeLastTodo() throws SQLException {
        try(PreparedStatement ps = this.connection.prepareStatement(SELECT_DESC_QUERY);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                removeTodo(rs.getInt(1));
            }
        }
    }
}

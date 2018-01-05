package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseAPI {

    public Connection connection;

    public DatabaseAPI() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void startConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/phonesstore", "root", "lenovo15");
    }

    public ResultSet read(String sql) throws SQLException {
        startConnection();
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

    public int write(String sql) throws SQLException{
        startConnection();
        Statement statement = connection.createStatement();

        System.out.println(sql);
        int x = statement.executeUpdate(sql);
        connection.close();
        connection = null;
        return x;
    }

    public static String convertToSqlFormat(List<Object> listOfInputs){
        StringBuilder sqlFormat = new StringBuilder("(");
        for (Object object : listOfInputs){
            if (object instanceof Integer){
                sqlFormat.append(object).append(",");
            }else if(object instanceof String && !object.equals("")){
                sqlFormat.append("\"").append(object).append("\",");
            }
        }
        if (sqlFormat.charAt(sqlFormat.length() - 1) == ',')
            sqlFormat.deleteCharAt(sqlFormat.length() - 1);
        sqlFormat.append(")");
        return sqlFormat.toString();
    }

    public static String generateSqlCommand(String listOfParameters, List<Object> listOfInputs){
        StringBuilder sqlFormat = new StringBuilder("(");
        List<String> parameters = Arrays.asList(listOfParameters.split(","));
        int indexOfInput = 0;
        for (Object object : listOfInputs)
            if (object != null)
                if(object instanceof String && !object.equals(""))
                    sqlFormat.append(parameters.get(indexOfInput++)).append(",");
        sqlFormat.deleteCharAt(sqlFormat.length() - 1);
        sqlFormat.append(") Values");
        sqlFormat.append(convertToSqlFormat(listOfInputs));
        return sqlFormat.toString();
    }
}
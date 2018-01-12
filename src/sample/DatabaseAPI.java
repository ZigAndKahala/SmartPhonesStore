package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                "jdbc:mysql://localhost:3306/MobileStore", "root", "root");
    }

    public ResultSet read(String sql) throws SQLException {
        startConnection();
        Statement statement = connection.createStatement();
        System.out.println(sql);
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

    public static String convertToSqlFormat(List<String> listOfInputs){
        StringBuilder sqlFormat = new StringBuilder("(");
        for (String string : listOfInputs){
            if(!string.equals("")){
                if(!isNumber(string))
                    sqlFormat.append("\"").append(string).append("\",");
                else
                    sqlFormat.append(string).append(",");
            }
        }
        if (sqlFormat.charAt(sqlFormat.length() - 1) == ',')
            sqlFormat.deleteCharAt(sqlFormat.length() - 1);
        sqlFormat.append(")");
        return sqlFormat.toString();
    }

    public static String generateSqlCommand(String listOfParameters, List<String> listOfInputs){
        StringBuilder sqlFormat = new StringBuilder("(");
        List<String> parameters = Arrays.asList(listOfParameters.split(","));
        int indexOfInput = 0;
        for (String string : listOfInputs) {
            if (!string.equals(""))
                sqlFormat.append(parameters.get(indexOfInput)).append(",");
            indexOfInput++;
        }
        sqlFormat.deleteCharAt(sqlFormat.length() - 1);
        sqlFormat.append(") Values");
        sqlFormat.append(convertToSqlFormat(listOfInputs));
        return sqlFormat.toString();
    }

    private static Boolean isNumber(String string){
        int numberOfDots = 0;
        for(Character character : string.toCharArray())
            if(!Character.isDigit(character) && character != '.')
                return false;
            else if(character == '.')
                numberOfDots++;
        return numberOfDots == 1 || numberOfDots == 0;
    }
}
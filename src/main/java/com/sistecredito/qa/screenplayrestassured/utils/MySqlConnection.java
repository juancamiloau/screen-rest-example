package com.sistecredito.qa.screenplayrestassured.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySqlConnection {

    private Connection connection;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public MySqlConnection(String url, String user, String password){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getTutorialById(int tutorialId){
        Map<String,Object> tutorialBd = new HashMap();
        try {
            /*Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM \" +\n" +
                    "                    \"tbl_Tutorials WHERE id=" +tutorialId);*/
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " +
                    "tbl_Tutorials WHERE id=?");
            preparedStatement.setInt(1, tutorialId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                tutorialBd.put("id", resultSet.getInt("id"));
                tutorialBd.put("title", resultSet.getString("title"));
                tutorialBd.put("description", resultSet.getString("description"));
                tutorialBd.put("published", resultSet.getBoolean("published"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tutorialBd;
    }
}

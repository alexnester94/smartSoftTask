package ru.nesteria.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectTopFivePopularForms {

    public ArrayList<String> topPopularFormsFive(Connection connection, String topPopularFormsFiveQuery) throws SQLException {
        ArrayList<String> popularFormsList = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement(topPopularFormsFiveQuery);
        ResultSet resset = stmt.executeQuery();

        while (resset.next()) {
            popularFormsList.add(resset.getString(1));
        }
        return popularFormsList;
    }

}

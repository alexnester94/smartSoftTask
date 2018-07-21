package ru.nesteria.db.dbHashMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class SelectHashMapFromDB {

    public static Map<String, ArrayList<String>> hashMap = new HashMap<>();

    public static Map<String, ArrayList<String>> getHashMap(Connection connection, String sqlquery) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sqlquery);
            ResultSet resset = stmt.executeQuery();
            while (resset.next()) {
                addValues(resset.getString(1), resset.getString(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashMap;

    }

    private static void addValues(String key, String value) {
        ArrayList tempList = null;
        if (hashMap.containsKey(key)) {
            tempList = hashMap.get(key);
            if(tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        hashMap.put(key,tempList);
    }

    public abstract void print(Map<String, ArrayList<String>> hashMap, HttpServletResponse resp) throws SQLException, IOException;

}

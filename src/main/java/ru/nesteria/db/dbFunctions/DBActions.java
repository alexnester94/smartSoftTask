package ru.nesteria.db.dbFunctions;

import java.sql.*;

public class DBActions {
    private static String SELECTUSERSANDFORMSLASTHOUR = "SELECT ssoid,formid\n" +
            "  FROM public.smartsofttable\n" +
            "where ssoid != 'Unauthorized'";

    public static void selectAll(Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.smartsofttable");
            ResultSet resset = stmt.executeQuery();

            while (resset.next()) {
                System.out.println(resset.getString(1) + " | " + resset.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertFromFile(Connection connection, String[] country) throws SQLException {
        try {
            String sqlQuery = "INSERT INTO public.smartsofttable" +
                    "(ssoid, ts, grp, types, subtype, url, orgid, formid, code, itpa," +
                    "sudirresponse, ymdh) VALUES " +
                    "('" + country[0] + "','" + country[1] + "', '" + country[2] + "', '" + country[3] + "', '" + country[4] + "', '" +
                    country[5] + "','" + country[6] + "', '" + country[7] + "', '" + country[8] + "', '" + country[9] + "' , '" +
                    country[10] + "', " + country[11] +");";
            Statement st = connection.createStatement();
            st.executeUpdate(sqlQuery);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();

    }




}

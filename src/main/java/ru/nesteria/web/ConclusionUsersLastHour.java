package ru.nesteria.web;

import ru.nesteria.db.dbFunctions.DBConnect;
import ru.nesteria.db.dbHashMap.SelectUsersFromLastHour;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static ru.nesteria.db.dbHashMap.SelectUsersFromLastHour.getCurrentTime;
import static ru.nesteria.db.dbHashMap.SelectUsersFromLastHour.getTimeHourAgo;

public class ConclusionUsersLastHour extends HttpServlet {

    final String SELECTUSERSANDFORMSLASTHOUR = "SELECT ssoid,formid \n" +
            "  FROM public.smartsofttable\n" +
            "where ssoid != 'Unauthorized' and ts > " +
            getTimeHourAgo(getCurrentTime()) +
            " and ts < " +
            getCurrentTime() +
            " and ssoid != '';";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnect connection = new DBConnect();
        SelectUsersFromLastHour hm1 = new SelectUsersFromLastHour();
        try {
            hm1.print(hm1.getHashMap(connection.getCon(),SELECTUSERSANDFORMSLASTHOUR), resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

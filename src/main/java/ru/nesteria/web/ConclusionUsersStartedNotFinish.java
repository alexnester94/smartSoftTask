package ru.nesteria.web;

import ru.nesteria.db.dbFunctions.DBConnect;
import ru.nesteria.db.dbHashMap.SelectUsersStartedNotFinish;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ConclusionUsersStartedNotFinish extends HttpServlet {

    final String USERSSTARTEDNOTFINISHQUERY = "SELECT ssoid,subtype" +
            " FROM public.smartsofttable" +
            " where ssoid != 'Unauthorized' and ssoid != '';";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnect connection = new DBConnect();
        SelectUsersStartedNotFinish hm2 = new SelectUsersStartedNotFinish();
        PrintWriter printWriter = resp.getWriter();

        try {
            hm2.checkLastStep(hm2.getHashMap(connection.getCon(),USERSSTARTEDNOTFINISHQUERY), printWriter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

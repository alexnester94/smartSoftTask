package ru.nesteria.web;

import ru.nesteria.db.SelectTopFivePopularForms;
import ru.nesteria.db.dbFunctions.DBConnect;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ConclusionMostPopularForms extends HttpServlet {

    final String task3 = "Most popular forms:\n";

    final String TOPPOPULARFORMSFIVEQUERY = "SELECT formid\n" +
            "FROM public.smartsofttable\n" +
            "where formid != ''\n" +
            "group by formid\n" +
            "order by COUNT(*)\n" +
            "desc\n" +
            "limit 5";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnect connection = new DBConnect();
        SelectTopFivePopularForms hm3 = new SelectTopFivePopularForms();
        execTopFiveForms(resp, hm3, connection, TOPPOPULARFORMSFIVEQUERY);
    }

    public void execTopFiveForms(HttpServletResponse resp,
                                 SelectTopFivePopularForms hm3,
                                 DBConnect connection,
                                 String TOPPOPULARFORMSFIVEQUERY) {
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(task3);
            for (String x : hm3.topPopularFormsFive(connection.getCon(), TOPPOPULARFORMSFIVEQUERY)) {
                printWriter.write(x + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

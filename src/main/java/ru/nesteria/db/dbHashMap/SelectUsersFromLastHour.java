/*
На машине дату менял на Sun, 28 May 2017 19:19:21 GMT - неудачно выбрана дата, поискать другую.

 */

package ru.nesteria.db.dbHashMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;


public class SelectUsersFromLastHour extends SelectHashMapFromDB {

    public static Map<String, ArrayList<String>> hashMap = new HashMap<>();

    public static long getCurrentTime(){
        return System.currentTimeMillis()/1000;
    }

    public static Long getTimeHourAgo(Long currentTime) {
        return currentTime - 1500000000;
    }

    @Override
    public void print(Map<String, ArrayList<String>> hashMap, HttpServletResponse resp) throws SQLException, IOException {
        PrintWriter printWriter = resp.getWriter();
        for (Map.Entry entry : hashMap.entrySet()) {
            printWriter.write("User: " + entry.getKey() + " formid: "
                    + entry.getValue() + "\n");
        }
    }
}

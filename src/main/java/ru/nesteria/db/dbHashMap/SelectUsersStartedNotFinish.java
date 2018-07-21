package ru.nesteria.db.dbHashMap;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class SelectUsersStartedNotFinish extends SelectHashMapFromDB{

    @Override
    public void print(Map<String, ArrayList<String>> hashMap, HttpServletResponse resp) throws SQLException{
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
    }

    public void checkLastStep(Map<String, ArrayList<String>> hashMap, PrintWriter printWriter) throws SQLException {
        for (Map.Entry entry : hashMap.entrySet()) {
            boolean start = false;
            boolean send = false;
            for (String temp : hashMap.get(entry.getKey())) {
                if(temp.equals("start")) {
                    start = true;
                }
                if(temp.equals("send")) {
                    send = true;
                }
                //System.out.println(temp);
            }

            if (start && send) {
                printWriter.write("Пользователь " + entry.getKey() + " прошел до конца\n\n");
                //System.out.println("Пользователь " + entry.getKey() + " прошел до конца");
            } else if (start && !send) {
                printWriter.write("Пользователь " + entry.getKey() +
                        " начал активность и не дошёл до конца. Последний шаг: " +
                        hashMap.get(entry.getKey()).get(hashMap.get(entry.getKey()).size()-1) + "\n\n");
            } else if (!start) {
                printWriter.write("Пользователь " + entry.getKey() + " не начал активность\n\n");
            }
        }
    }

}

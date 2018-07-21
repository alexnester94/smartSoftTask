package ru.nesteria.worktablepack;

import ru.nesteria.db.dbFunctions.DBActions;
import ru.nesteria.db.dbFunctions.DBConnect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class SmartSoftTasks {
    // остался открытым вопрос о добавлении '' в случае, если ячейка пустая
    public static void importToDB(String csvFile, String line, String cvsSplitBy, DBActions dbActions, DBConnect connection) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int check = 0; // оставлю пока так, потом заменить на что-то адекватнее
            while ((line = br.readLine()) != null) {
                if (check == 0) {
                    check++;
                } else {
                    String[] data = line.split(cvsSplitBy);
                    dbActions.insertFromFile(connection.getCon(), data);
                    check++;
                }
            }
            dbActions.selectAll(connection.getCon());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Импорт из csv в db
        // smartSoftTasks.importToDB(csvFile, line, cvsSplitBy, dbActions, connection);

        // А) Вывести список пользователей и используемых ими форм за последний час
        // hm1.print(hm1.getHashMap(connection.getCon(),SELECTUSERSANDFORMSLASTHOUR));

        // Б) Вывести список пользователей, которые начали активность на форме и не дошли до конца.
        // Например, для услуг grp dszn_* начальное состояние start, конечное состояние send.
        // Вывести на каком шаге остановился.
        // hm2.checkLastStep(hm2.getHashMap(connection.getCon(),USERSSTARTEDNOTFINISHQUERY));

        // В) Составить ТОП – 5 самых используемых форм.
        // try {
        //    for (String x : hm3.topPopularFormsFive(connection.getCon(), TOPPOPULARFORMSFIVEQUERY)) {
        //        System.out.println(x + "\n");
        //    }
        // } catch (SQLException e) {
        //        e.printStackTrace();
        //   }
    }

}



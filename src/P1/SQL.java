package P1;

import java.sql.*;
import java.util.stream.*;

public class SQL {
    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    public ResultSetMetaData rsmd = null;
    public String table_name[] = null;
    public String column_name[] = null;
    public int column_count = 0;

    public boolean connect(String URL, String UN, String PWD) {
        String DB_URL = URL;
        String USER = UN;
        String PASS = PWD;

        try {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            this.stmt = conn.createStatement();

            System.out.println("Connection Success");
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int get_table_name() {
        this.rs = null;
        this.rsmd = null;
        this.table_name = null;
        String sql = "show tables;";
        int count = 0;

        try {
            this.rs = stmt.executeQuery(sql);
            this.rsmd = rs.getMetaData();

            while (rs.next()) {
                count++;
            }
            System.out.println(count);

            rs.beforeFirst();
            table_name = new String[count];

            int i = 0;
            while (rs.next()) {
                table_name[i] = rs.getString(1);
                System.out.println(table_name[i]);
                i++;
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return count;
    }

    public int get_column_name(String table_name) {
        this.rs = null;
        this.rsmd = null;
        String sql = "select * from " + table_name + ";";
        int count = 0;
        System.out.println("[" + table_name + "]");

        try {
            this.rs = stmt.executeQuery(sql);
            this.rsmd = rs.getMetaData();

            count = rsmd.getColumnCount();
            this.column_name = new String[rsmd.getColumnCount()];

            for (int i = 0; i < count; i++) {
                this.column_name[i] = rsmd.getColumnName(i + 1);
                System.out.println(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        this.column_count = count;
        return count;
    }


    public String command(String command) {
        String sql = command;
        String result = null;

        try {
            System.out.println(sql);
            String buffer = sql;
            String str[] = buffer.split(" ");

            for (String element : str)
                System.out.println(element);

            int sql_case = 0;

            if (str[0].equalsIgnoreCase("SELECT"))
                sql_case = 1;

            this.rs = stmt.executeQuery(sql);
            this.rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            int[] column_length = new int[column_count];

            for (int i = 1; i <= column_count; i++) {
                String get = rsmd.getColumnName(i);
                int length = get.length();
                column_length[i - 1] = length;
            }

            while (rs.next()) {
                for (int i = 1; i <= column_count; i++) {
                    String get = rs.getString(i);
                    if (get != null && get.length() > column_length[i - 1])
                        column_length[i - 1] = get.length();
                }
            }

            for (int i = 0; i < column_count; i++)
                System.out.println(column_length[i]);

            rs.beforeFirst();

            int total = IntStream.of(column_length).sum();

            //column name
            result = "+";
            for (int i = 0; i < column_count; i++) {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n";

            for (int i = 1; i <= column_count; i++) {
                result += "| ";
                String get = rsmd.getColumnName(i);
                result += get;

                for (int j = 1; j <= column_length[i - 1] - get.length() + 1; j++)
                    result += " ";
            }
            result += "|\n";

            result += "+";
            for (int i = 0; i < column_count; i++) {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n";

            while (rs.next()) {
                for (int i = 1; i <= column_count; i++) {
                    result += "| ";
                    String get = rs.getString(i);
                    result += get;
                    int get_length = 0;
                    if (get == null)
                        get_length = 4;
                    else
                        get_length = get.length();

                    for (int j = 0; j < column_length[i - 1] - get_length + 1; j++)
                        result += " ";
                }
                result += "|\n";
            }

            result += "+";
            for (int i = 0; i < column_count; i++) {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n\n";

/*
            for (int i = 0; i < column_count; i++)
            {
                String get = rs.getString(i);
                result += "| ";
                for (int j = 0; j < column_length[i] - get.length(); j++)
                    result += " ";
                result += get + " |\n";
            }

            result = "+";

            //column content
            for (int i = 0; i < total + 1; i++)
            {
                result += "-";
            }
            result += "+\n";
*/
        } catch (SQLException se) {
            try {
                stmt.execute(sql);
                result = "Operation success.";
            } catch (SQLException e) {
                try {
                    stmt.executeUpdate(sql);
                    result = "Operation success.";
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    // 处理 JDBC 错误
                    result = "SQL code is not correct, please try again.";
                }
            }
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            result = "SQL code is not correct, please try again.";
        }
        return result;


    }

    public void exit_DB() {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }// 什么都不做

        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
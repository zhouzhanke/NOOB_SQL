package P1;
import java.sql.*;
import java.util.stream.*;

public class SQL
{
    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    public ResultSetMetaData rsmd = null;

    public boolean connect(String URL, String UN, String PWD)
    {
        String DB_URL = URL;
        String USER = UN;
        String PASS = PWD;

        try
        {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            this.stmt = conn.createStatement();

            System.out.println("Connection Success");
        }

        catch(SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
            return false;
        }

        catch(Exception e)
        {
        // 处理 Class.forName 错误
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String command(String command)
    {
        String sql = command;
        String result = null;

        try
        {
            this.rs = stmt.executeQuery(sql);
            this.rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            int[] column_length = new int[column_count];

            for (int i = 1; i <= column_count; i++)
            {
                String get = rsmd.getColumnName(i);
                System.out.println(get);
                int length = get.length();
                column_length[i - 1] = length;
            }

            while (rs.next())
            {
                for (int i = 1; i <= column_count; i++)
                {
                    String get = rs.getString(i);
                    if (get.length() > column_length[i - 1])
                        column_length[i-1] = get.length();
                }
            }

            for (int i = 0; i < column_count; i++)
                System.out.println(column_length[i]);

            rs.beforeFirst();

            int total = IntStream.of(column_length).sum();

            //column name
/*            for (int i = 0; i < total + 1; i++)
            {
                result += "-";
            }
            result += "+\n";*/
            result = "+";
            for (int i = 0; i < column_count; i++)
            {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n";

            for (int i = 1; i <= column_count; i++)
            {
                result += "| ";
                String get = rsmd.getColumnName(i);
                result += get;

                for (int j = 1; j <= column_length[i - 1] - get.length() + 1; j++)
                    result += " ";
            }
            result += "|\n";

            result += "+";
            for (int i = 0; i < column_count; i++)
            {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n";

            while (rs.next())
            {
                for (int i = 1; i <= column_count; i++)
                {
                    result += "| ";
                    String get = rs.getString(i);
                    result += get;

                    for (int j = 0; j < column_length[i - 1] - get.length() + 1; j++)
                        result += " ";
                }
                result += "|\n";
            }

            result += "+";
            for (int i = 0; i < column_count; i++)
            {
                for (int j = 0; j < column_length[i] + 2; j++)
                    result += "-";
                result += "+";
            }
            result += "\n";

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
        }
        catch(SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
            result = "SQL code is not correct, please try again.";
        }

        catch(Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
            result = "SQL code is not correct, please try again.";
        }
        return result;


    }

    public void exit_DB()
    {
        try
        {
            if(stmt!=null) stmt.close();
        }
        catch(SQLException se2)
        {
        }// 什么都不做

        try
        {
            if(conn!=null) conn.close();
        }

        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

}
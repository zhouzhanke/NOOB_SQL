package P1;
import java.sql.*;

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

            while (rs.next())
            {
                for (int i = 1; i <= column_count; i++)
                {
                    System.out.print(rs.getString(i) + "\t\t\t\t");
                    result += rs.getString(i);
                    result += "\n";
                }

                System.out.println();
            }
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
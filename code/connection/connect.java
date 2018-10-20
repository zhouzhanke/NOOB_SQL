import java.sql.*;
import java.util.*;

public class connect
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "";

	public void set_DB_URL(String URL)
	{
		this.DB_URL = URL;
	}

	public String user = "";
	public String pwd = "";

	public void set_login(String user, String pwd)
	{
		this.user = user;
		this.pwd = pwd;
	}

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String result_u, result_p;
		result_u = scan.nextLine();
		result_p = scan.nextLine();

		System.out.println(result_u + result_p);
		scan.close();


		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PWD);
	}
}

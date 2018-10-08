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
		String resutl = scan;
		System.out.println(resutl);
		scan.close();
	}
